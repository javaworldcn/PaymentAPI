package com.grapefs.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapefs.demo.utils.HttpUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.grapefs.demo.entity.FastH5CreateOrderReqData;
import com.grapefs.demo.utils.DateUtils;
import com.grapefs.demo.utils.FileProcessUtil;
import com.grapefs.demo.utils.MD5Util;


/**
 * @author yjw
 * @version 1.0 支付demo
 */
@Controller
@RequestMapping(value = "/demo")
public class FastPayDemoController {

	Logger log = LoggerFactory.getLogger(FastPayDemoController.class);

	// 填写页面
	@RequestMapping("/tofastH5Pay")
	public String toMicroPay(Model model) {
		model.addAttribute("orderNo","TEST"+DateUtils.currentTime());
		return "/demo/testFastH5Pay";
	}

	@RequestMapping(value = "/fastH5Pay", method = RequestMethod.POST)
	public ModelAndView MicroPayNow(FastH5CreateOrderReqData createOrderReqData,HttpServletRequest request) throws Exception {
		String url  = request.getParameter("url");
		String md5Key = request.getParameter("merchantkey");
		createOrderReqData.setSignType("MD5");
		createOrderReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
		JSONObject jsonObject = (JSONObject) JSON.toJSON(createOrderReqData);
		String sign = MD5Util.getSign(jsonObject, md5Key);
		createOrderReqData.setSign(sign);
		String content = getAutoRequestForm(createOrderReqData,url);
		log.info("content 内容：{}",content);
		ModelAndView mav = new ModelAndView("demo/autoSubmit");
        mav.addObject("autoSubmit", content);	
        return mav;
	}
	
	public static String getAutoRequestForm(Object bean, String requestUrl) throws IllegalAccessException {
		StringBuffer sb = new StringBuffer(
				"<html><body onLoad=\"javascript:document.E_FORM.submit()\"><form name=\"E_FORM\" action=\"");
		sb.append(requestUrl).append("\" method=\"post\">");

		Class clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			String key = f.getName();
			Object value = f.get(bean);
			if (value == null)
				value = "";
			sb.append("<input type=\"hidden\" id=\"").append(key).append("\" name=\"").append(key).append("\" value=\"")
					.append(value).append("\" >");
		}
		sb.append("</form></body></html>");
		return sb.toString();
	}
	
	@RequestMapping("/toMergeFastReconFile")
	public String toMergeFastReconFile(Model model) {
		return "/demo/testMergeFastReconFile";
	}

	@RequestMapping(value = "/mergeFastReconFile", method = RequestMethod.POST)
	public String mergeFastReconFile(HttpServletRequest request,HttpServletResponse response) throws Exception { 
			log.info("====> 合并对账文件上传方法");
			BufferedOutputStream bos = null;
			OutputStream os = null;
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile fastReconFile1 = (CommonsMultipartFile) multipartRequest.getFile("fastReconFile1"); //法人身份证正面
				CommonsMultipartFile fastReconFile2 = (CommonsMultipartFile) multipartRequest.getFile("fastReconFile2"); //法人身份证正面

				if (!fastReconFile1.getOriginalFilename().endsWith(".csv") || !fastReconFile2.getOriginalFilename().endsWith(".csv")) {
					throw new RuntimeException("请上传后缀名为.csv的文件");
				}
				// 将CommonsMultipartFile类型转换成file类型
				DiskFileItem fastFile1 = (DiskFileItem) fastReconFile1.getFileItem();
				File localfastFile1 = fastFile1.getStoreLocation();
				if (!localfastFile1.exists()) {
					log.error("快捷对账文件1不存在");
					throw new RuntimeException("上传快捷对账文件1异常");
				}
				DiskFileItem fastFile2 = (DiskFileItem) fastReconFile2.getFileItem();
				File localfastFile2 = fastFile2.getStoreLocation();
				if (!localfastFile2.exists()) {
					log.error("快捷对账文件2不存在");
					throw new RuntimeException("上传快捷对账文件2异常");
				}
				List<File> fileList = new ArrayList<>();
				fileList.add(localfastFile1);
				fileList.add(localfastFile2);
				String result = FileProcessUtil.MergefastReconFile(fileList);
				os = response.getOutputStream();
				bos = new BufferedOutputStream(os);
				response.reset();// 清空输出流
				response.setHeader("Content-disposition",
						"attachment; filename=" + URLEncoder.encode(fastReconFile1.getOriginalFilename(), "utf-8"));
				response.setContentType("application/txt");// 定义输出类型
				bos.write(result.getBytes());
			} catch (Exception e) {
				log.error("上传对账文件异常：" + e);
				throw new RuntimeException("上传对账文件异常!");
			} finally {
				bos.close();
				os.close();
			}
			return null;
		}

	/**
	 * 快捷开通(无卡)商户
	 * @param model
	 * @return
	 */
	@RequestMapping("/toFastPayOpenMerchant")
	public String toFastPayOpenMerchant(Model model) {
		return "/demo/testFastPayOpenMerchant";
	}

	@RequestMapping(value = "/createMerchBasicInfo", method = RequestMethod.POST)
	@ResponseBody
	public String createMerchBasicInfo(HttpServletRequest request) throws Exception {
		// 测试商户报备，AGENTNO和AGENTkEY都是银行下发给我们的系统参数
		String reportMerchantUrl = request.getParameter("reportMerchantUrl");
		String agentNo = request.getParameter("agentNo");
		String agentKey = request.getParameter("agentKey");
		String merchantInfo = request.getParameter("merchantInfo");
		JSONObject merchantInfoJson = JSONObject.parseObject(merchantInfo);
		// 请求报备到华夏银行的api接口
		JSONObject params = new JSONObject();
		params.put("agentNo", agentNo);
		String randomStr = UUID.randomUUID().toString();
		params.put("randomStr", randomStr);
		params.put("merchantInfo", merchantInfoJson);
		params.put("signType", "MD5");
		String sign = MD5Util.getSign(params, agentKey);
		params.put("sign", sign);

		String requestParam = params.toJSONString();
		log.info("快捷支付开通（无卡）商户 请求参数：{}", requestParam);
		HttpUtil httpUtil = new HttpUtil();
		// 请求的报备商户url
		String response = httpUtil.post(reportMerchantUrl, requestParam);
		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		log.info("快捷支付开通（无卡）商户 返回的结果：{}", response);
		JSONObject resultObj = new JSONObject();
		resultObj.put("快捷支付开通（无卡）商户的结果", response);
		resultObj.put("报备的商户信息", merchantInfo);
		return response;
	}




	/**
	 * 快捷开通产品(非同人)
	 * @param model
	 * @return
	 */
	@RequestMapping("/toFastPayDPOpenMerchFastProduct")
	public String toFastPayDPOpenMerchFastProduct(Model model) {
		return "/demo/testFastPayDPOpenMerchantProduct";
	}

	@RequestMapping("/fastPayDPOpenMerchFastProduct")
	@ResponseBody
	public String fastPayDPOpenMerchFastProduct(HttpServletRequest request) {
		String apiUrl = request.getParameter("apiUrl");
		String agentNo = request.getParameter("agentNo");
		String agentKey = request.getParameter("agentKey");
		String channelCode = request.getParameter("channelCode");
		String merchantNo = request.getParameter("merchantNo");
		String productInfo = request.getParameter("productInfo");
		JSONObject productInfoJson = JSONObject.parseObject(productInfo);
		JSONObject params = new JSONObject();
		params.put("agentNo", agentNo);
		params.put("channelCode", channelCode);
		params.put("merchantNo", merchantNo);
		String randomStr = UUID.randomUUID().toString();
		params.put("randomStr", randomStr);
		params.put("productInfo", productInfoJson);
		params.put("signType", "MD5");
		String sign = MD5Util.getSign(params, agentKey);
		params.put("sign", sign);

		String requestParam = params.toJSONString();
		log.info("开通非同人产品 请求参数：{}", requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);
		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		log.info("开通非同人产品 返回的结果：{}", response);
		JSONObject resultObj = new JSONObject();
		resultObj.put("开通非同人产品的结果", response);

		return response;
	}



	/**
	 * 快捷开通产品(同人)
	 * @param model
	 * @return
	 */
	@RequestMapping("/toFastPaySPOpenMerchFastProduct")
	public String toFastPaySPOpenMerchFastProduct(Model model) {
		return "/demo/testFastPaySPOpenMerchantProduct";
	}

	@RequestMapping("/fastPaySPOpenMerchFastProduct")
	@ResponseBody
	public String fastPaySPOpenMerchFastProduct(HttpServletRequest request) {
		String apiUrl = request.getParameter("apiUrl");
		String agentNo = request.getParameter("agentNo");
		String agentKey = request.getParameter("agentKey");
		String channelCode = request.getParameter("channelCode");
		String merchantNo = request.getParameter("merchantNo");
		String productInfo = request.getParameter("productInfo");
		JSONObject productInfoJson = JSONObject.parseObject(productInfo);
		JSONObject params = new JSONObject();
		params.put("agentNo", agentNo);
		params.put("channelCode", channelCode);
		params.put("merchantNo", merchantNo);
		String randomStr = UUID.randomUUID().toString();
		params.put("randomStr", randomStr);
		params.put("productInfo", productInfoJson);
		params.put("signType", "MD5");
		String sign = MD5Util.getSign(params, agentKey);
		params.put("sign", sign);

		String requestParam = params.toJSONString();
		log.info("开通[同人]产品 请求参数：{}", requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);
		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		log.info("开通[同人]产品 返回的结果：{}", response);
		JSONObject resultObj = new JSONObject();
		resultObj.put("开通[同人]产品的结果", response);

		return response;
	}


}
