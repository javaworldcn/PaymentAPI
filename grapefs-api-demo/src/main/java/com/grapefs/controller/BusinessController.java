package com.grapefs.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.grapefs.demo.utils.FileProcessUtil;
import com.grapefs.demo.utils.HttpUtil;
import com.grapefs.demo.utils.MD5Util;

/**
 * @author yjw
 * @version 1.0 支付demo
 */
@Controller
@RequestMapping(value = "/business")
public class BusinessController {

	Logger log = LoggerFactory.getLogger(BusinessController.class);

	// 计费
	@RequestMapping("/tocharge")
	public String toMicroPay(Model model) {
		return "/demo/chargeBysettDate";
	}

	@RequestMapping(value = "/chargeNow", method = RequestMethod.POST)
	@ResponseBody
	public String MicroPayNow(HttpServletRequest request) throws Exception {
		String serverUrl = request.getParameter("serverUrl");
		String interfaceAddr = request.getParameter("interfaceAddr");
		String apiUrl = serverUrl + interfaceAddr;

		String settleDate = request.getParameter("settleDate");
		String md5Key = request.getParameter("md5Key");
		String signType = "MD5";

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("settleDate", settleDate);
		jsonObject.put("signType", signType);
		jsonObject.put("randomStr", UUID.randomUUID().toString().replace("-", ""));

		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();

		log.info("发送计费POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("计费返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String respSign = resultObj.getString("sign");
			if (respSign == null || "".equals(respSign)) {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, respSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}
		return resultObj.toString();
	}

	// 代理商结算
	@RequestMapping("/toagentSett")
	public String toagentSett(Model model) {
		return "/demo/agentSett";
	}

	@RequestMapping(value = "/agentSettNow", method = RequestMethod.POST)
	@ResponseBody
	public String agentSettNow(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");

		String serverUrl = request.getParameter("serverUrl");
		String interfaceAddr = request.getParameter("interfaceAddr");
		String apiUrl = serverUrl + interfaceAddr;

		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");

		String signType = "MD5";

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("beginDate", beginDate);
		jsonObject.put("endDate", endDate);
		jsonObject.put("signType", signType);
		jsonObject.put("randomStr", UUID.randomUUID().toString().replace("-", ""));

		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();

		log.info("发送代理商结算POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("代理商结算返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String respSign = resultObj.getString("sign");
			if (respSign == null || "".equals(respSign)) {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, respSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}
		return resultObj.toString();
	}

	// 按批次号导出代理商打款文件
	@RequestMapping("/toagentSettleExportExcel")
	public String toagentSettleExportExcel(Model model) {
		return "/demo/agentSettleExportExcel";
	}

	@RequestMapping(value = "/agentSettleExportExcelNow", method = RequestMethod.POST)
	public String agentSettleExportExcelNow(HttpServletRequest request, HttpServletResponse respon) throws Exception {
		String md5Key = request.getParameter("md5Key");

		String serverUrl = request.getParameter("serverUrl");
		String interfaceAddr = request.getParameter("interfaceAddr");
		String apiUrl = serverUrl + interfaceAddr;

		String settBatchNo = request.getParameter("settBatchNo");

		String signType = "MD5";
		String randomStr = UUID.randomUUID().toString().replace("-", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("settBatchNo", settBatchNo);
		jsonObject.put("signType", signType);
		jsonObject.put("randomStr", randomStr);

		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();

		log.info("发送下载代理商打款文件POST请求,请求参数" + requestParam);
		try {
			String url = apiUrl + "?settBatchNo=" + settBatchNo + "&randomStr=" + randomStr + "&signType=" + signType
					+ "&sign=" + sign;
			return "redirect:" + url;
		} catch (Exception e) {
			throw new RuntimeException("调用“下载代理商打款文件”接口异常");
		}

	}

	// 按结算流水号更新代理商结算记录状态
	@RequestMapping("/toupdateAgentSettStatus")
	public String toupdateAgentSettStatus(Model model) {
		return "/demo/updateAgentSettStatus";
	}

	@RequestMapping(value = "/updateAgentSettStatusNow", method = RequestMethod.POST)
	@ResponseBody
	public String updateAgentSettStatusNow(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");

		String serverUrl = request.getParameter("serverUrl");
		String interfaceAddr = request.getParameter("interfaceAddr");
		String apiUrl = serverUrl + interfaceAddr;

		String settTrxNos = request.getParameter("settTrxNos");

		String signType = "MD5";

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("settTrxNos", settTrxNos);
		jsonObject.put("signType", signType);
		jsonObject.put("randomStr", UUID.randomUUID().toString().replace("-", ""));

		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();

		log.info("发送更新代理商结算状态POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("更新代理商结算状态返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String respSign = resultObj.getString("sign");
			if (respSign == null || "".equals(respSign)) {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, respSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}
		return resultObj.toString();
	}

	// 按结算【批次】号更新代理商结算记录状态
	@RequestMapping("/toupdateAgentSettBatchStatus")
	public String toupdateAgentSettBatchStatus(Model model) {
		return "/demo/updateAgentSettBatchStatus";
	}

	@RequestMapping(value = "/updateAgentSettBatchStatusNow", method = RequestMethod.POST)
	@ResponseBody
	public String updateAgentSettBatchStatusNow(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");

		String serverUrl = request.getParameter("serverUrl");
		String interfaceAddr = request.getParameter("interfaceAddr");
		String apiUrl = serverUrl + interfaceAddr;

		String settBatchNo = request.getParameter("settBatchNo");

		String signType = "MD5";

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("settBatchNo", settBatchNo);
		jsonObject.put("signType", signType);
		jsonObject.put("randomStr", UUID.randomUUID().toString().replace("-", ""));

		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();

		log.info("发送更新代理商结算状态POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("更新代理商结算状态返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String respSign = resultObj.getString("sign");
			if (respSign == null || "".equals(respSign)) {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, respSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}
		return resultObj.toString();
	}

	// 下载民生对账文件，导入，对账
	@RequestMapping("/toImportChannelRecord")
	public String toimportMsTransRecord(Model model) {
		return "/demo/importChannelRecord";
	}

	@RequestMapping(value = "/importChannelRecordNow", method = RequestMethod.POST)
	@ResponseBody
	public String importMsTransRecordNow(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");

		String serverUrl = request.getParameter("serverUrl");
		String interfaceAddr = request.getParameter("interfaceAddr");
		String apiUrl = serverUrl + interfaceAddr;

		String reconDate = request.getParameter("reconDate");
		String channelCode = request.getParameter("channelCode");
		String signType = "MD5";

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("reconDate", reconDate);
		jsonObject.put("channelCode", channelCode);
		jsonObject.put("signType", signType);
		jsonObject.put("randomStr", UUID.randomUUID().toString().replace("-", ""));

		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();

		log.info("发送下载民生对账文件，导入，对账POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("下载民生对账文件，导入，对账状态返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String respSign = resultObj.getString("sign");
			if (respSign == null || "".equals(respSign)) {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, respSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}
		return resultObj.toString();
	}

	// 对账文件调账
	@RequestMapping("/toProcessReconError")
	public String toProcessReconError(Model model) {
		return "/demo/processReconError";
	}

	@RequestMapping(value = "/processReconErrorNow", method = RequestMethod.POST)
	@ResponseBody
	public String toProcessReconErrorNow(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");

		String serverUrl = request.getParameter("serverUrl");
		String interfaceAddr = request.getParameter("interfaceAddr");
		String apiUrl = serverUrl + interfaceAddr;

		String reconErrorId = request.getParameter("reconErrorId");
		String processType = request.getParameter("processType");
		String signType = "MD5";

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("reconErrorId", reconErrorId);
		jsonObject.put("processType", processType);
		jsonObject.put("signType", signType);
		jsonObject.put("randomStr", UUID.randomUUID().toString().replace("-", ""));

		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();

		log.info("发送下载民生对账文件，导入，对账POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("下载民生对账文件，导入，对账状态返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String respSign = resultObj.getString("sign");
			if (respSign == null || "".equals(respSign)) {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, respSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}
		return resultObj.toString();
	}

	// 导入民生对账文件分流
	@RequestMapping("/toImportReconFile")
	public String toImportMsReconFile() {
		return "/demo/testImportReconFile";
	}

	@RequestMapping("/distinguishReconFile")
	public String distinguishReconFile(
			@RequestParam(value = "reconFileupload", required = false) CommonsMultipartFile file, String prefix,
			String reconBankFile, HttpServletResponse response) throws IOException {
		log.info("====> 分流对账文件上传方法");
		BufferedOutputStream bos = null;
		OutputStream os = null;
		try {
			if (file == null) {
				throw new RuntimeException("对账文件不存在");
			}
			if (!file.getOriginalFilename().endsWith(".txt")) {
				throw new RuntimeException("请上传后缀名为.txt的文件");
			}
			// 将CommonsMultipartFile类型转换成file类型
			DiskFileItem fi = (DiskFileItem) file.getFileItem();
			File localFile = fi.getStoreLocation();
			if (!localFile.exists()) {
				log.error("对账文件不存在");
				throw new RuntimeException("上传对账文件异常");
			}
			log.info("对账文件" + localFile);

			os = response.getOutputStream();

			String result = null;
			if (reconBankFile.equals("1")) {
				result = FileProcessUtil.msFileDistinguish(localFile, prefix);
			} else if (reconBankFile.equals("2")) {
				result = FileProcessUtil.hxFileDistinguish(localFile, prefix);
			}

			bos = new BufferedOutputStream(os);
			response.reset();// 清空输出流
			response.setHeader("Content-disposition",
					"attachment; filename=" + URLEncoder.encode(file.getOriginalFilename(), "utf-8"));
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

	@RequestMapping("/distinguishReconFileByMerchant")
	public String distinguishReconFileByMerchant(
			@RequestParam(value = "reconFileupload", required = false) CommonsMultipartFile file, String merchantNo,
			HttpServletResponse response) throws IOException {
		log.info("====> 分流对账文件上传方法");
		BufferedOutputStream bos = null;
		OutputStream os = null;
		try {
			if (file == null) {
				throw new RuntimeException("对账文件不存在");
			}
			if (!file.getOriginalFilename().endsWith(".txt")) {
				throw new RuntimeException("请上传后缀名为.txt的文件");
			}
			// 将CommonsMultipartFile类型转换成file类型
			DiskFileItem fi = (DiskFileItem) file.getFileItem();
			File localFile = fi.getStoreLocation();
			if (!localFile.exists()) {
				log.error("对账文件不存在");
				throw new RuntimeException("上传对账文件异常");
			}
			log.info("对账文件" + localFile);

			os = response.getOutputStream();

			String result = FileProcessUtil.MerchantFileDistinguish(localFile, merchantNo);

			bos = new BufferedOutputStream(os);
			response.reset();// 清空输出流
			response.setHeader("Content-disposition",
					"attachment; filename=" + URLEncoder.encode(file.getOriginalFilename(), "utf-8"));
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

	// 批量更新渠道商户状态
	@RequestMapping("/toChannelMerchApproveStatusUpdate")
	public String toChannelMerchApproveStatusUpdate(Model model) {
		return "/demo/channelMerchApproveStatusUpdate";
	}

	// 手动执行批量更新渠道商户状态
	@RequestMapping("/channelMerchApproveStatusUpdate")
	@ResponseBody
	public String channelMerchApproveStatusUpdate(HttpServletRequest request) {
		String updateStatusUrl = request.getParameter("updateStatusUrl");
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(updateStatusUrl, "text/html");
		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("执行返回结果：{}", resultObj);
		return resultObj.toString();
	}

	// 生成T1应结算记录
	@RequestMapping("/toBuilderT1SettRecord")
	public String toBuilderT1SettRecord(Model model) {
		return "/demo/builderT1SettRecord";
	}

	@RequestMapping(value = "/builderT1SettRecord", method = RequestMethod.POST)
	@ResponseBody
	public String builderT1SettRecord(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");
		String serverUrl = request.getParameter("serverUrl");
		String interfaceAddr = request.getParameter("interfaceAddr");
		String apiUrl = serverUrl + interfaceAddr;
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String channelCode = request.getParameter("channelCode");
		String signType = "MD5";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("beginDate", beginDate);
		jsonObject.put("endDate", endDate);
		jsonObject.put("channelCode", channelCode);
		jsonObject.put("signType", signType);
		jsonObject.put("randomStr", UUID.randomUUID().toString().replace("-", ""));

		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();

		log.info("发送生成T1应结算记录POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("生成T1应结算记录返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String respSign = resultObj.getString("sign");
			if (respSign == null || "".equals(respSign)) {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, respSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}
		return resultObj.toString();
	}

	// 生成清算应结算记录
	@RequestMapping("/toBuilderClaerSettRecord")
	public String toBuilderClaerSettRecord(Model model) {
		return "/demo/builderClaerSettRecord";
	}

	@RequestMapping(value = "/builderClaerSettRecord", method = RequestMethod.POST)
	@ResponseBody
	public String builderClaerSettRecord(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");
		String serverUrl = request.getParameter("serverUrl");
		String interfaceAddr = request.getParameter("interfaceAddr");
		String apiUrl = serverUrl + interfaceAddr;
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String signType = "MD5";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("beginDate", beginDate);
		jsonObject.put("endDate", endDate);
		jsonObject.put("signType", signType);
		jsonObject.put("randomStr", UUID.randomUUID().toString().replace("-", ""));

		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();

		log.info("发送生成清算应结算记录POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("生成清算应结算记录返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String respSign = resultObj.getString("sign");
			if (respSign == null || "".equals(respSign)) {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, respSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}
		return resultObj.toString();
	}

	/** 跳转导入中信渠道商户数据页面 */
	@RequestMapping("/importZxChannelMerchan")
	public String importZxChannelMerchan(Model model) {
		return "/demo/importZxChannelMerchan";
	}

	@RequestMapping(value = "/importZxChannelMerchanDemo", method = RequestMethod.POST)
	@ResponseBody
	public String importZxChannelMerchanDemo(HttpServletRequest request) throws Exception {
		String serverUrl = request.getParameter("serverUrl");
		String interfaceAddr = request.getParameter("interfaceAddr");
		String apiUrl = serverUrl + interfaceAddr;
		JSONObject jsonObject = new JSONObject();
		JSONArray dataArray = new JSONArray();
		@SuppressWarnings("static-access")
		List<List<Object>> list = this.getBankListByExcel(request);
		for (int i = 0; i < list.size(); i++) {
			List<Object> zxObjList = list.get(i);
			for(int j = i+1; j < list.size(); j++) {
				List<Object> objListTwe = list.get(j);
				if(zxObjList.get(1).equals(objListTwe.get(1))) {
					log.error("导入中信渠道商户：序号："+zxObjList.get(0)+"与序号："+objListTwe.get(0)+",excel中渠道商户号不能相同");
					throw new RuntimeException("序号："+zxObjList.get(0)+"与序号："+objListTwe.get(0)+",excel中渠道商户号不能相同");
				}
			}
			if (zxObjList.size() < 5) {
				log.error("导入中信渠道商户：序号："+zxObjList.get(0)+",前5个单元格必须要有数据");
				throw new RuntimeException("序号："+zxObjList.get(0)+",前5个单元格必须要有数据");
			}
			JSONObject data = new JSONObject();
			data.put("rowIndex", zxObjList.get(0).toString());
			data.put("channelMerchName", zxObjList.get(2).toString());
			data.put("channelMerchNo", zxObjList.get(1).toString());
			data.put("platMerchNo", zxObjList.get(4).toString());
			data.put("secret", zxObjList.get(3).toString());
			if (zxObjList.size() >= 6) {
				data.put("wxAppId", zxObjList.get(5).toString());
			}
			dataArray.add(data);
		}

		jsonObject.put("reconDate", dataArray);

		String requestParam = jsonObject.toString();

		log.info("发送导入中信渠道商户数据POST请求," + "请求地址：" + apiUrl + "请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			log.error("发送导入中信渠道商户数据POST请求,返回的结果" + response);
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		return resultObj.toString();
	}

	/**
	 * 解析excel数据
	 * 
	 * @param work
	 * @return
	 * @throws Exception
	 */
	public static List<List<Object>> getBankListByExcel(HttpServletRequest request) throws Exception {
		List<List<Object>> list = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					String fileName = file.getOriginalFilename();
					if (fileName.trim() != "") {
						String ext = fileName.substring(fileName.lastIndexOf("."));
						ext = ext.toLowerCase();
						if (!".xls".equals(ext) && !".xlsx".equals(ext)) {
							throw new RuntimeException("excel格式不正确");
						}
						Workbook work = WorkbookFactory.create(file.getInputStream());
						if (null == work) {
							throw new RuntimeException("工作薄为空");
						}
						Sheet sheet = null;
						Row row = null;
						Cell cell = null;
						list = new ArrayList<List<Object>>();
						// 遍历Excel中所有的sheet
						for (int i = 0; i < work.getNumberOfSheets(); i++) {
							sheet = work.getSheetAt(i);
							if (sheet == null) {
								continue;
							}
							// 遍历当前sheet中的所有行
							for (int j = 1; j <= sheet.getLastRowNum(); j++) {
								row = sheet.getRow(j);
								// 遍历所有的列
								List<Object> li = new ArrayList<Object>();
								for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
									cell = row.getCell(y);
									li.add(getCellValue(cell));
								}
								list.add(li);
							}
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * 描述：对表格中数值进行格式化
	 * 
	 * @param cell
	 * @return
	 */
	public static Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
				value = sdf.format(cell.getDateCellValue());
			} else {
				value = df.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			value = "";
			break;
		}
		return value;
	}

	// 获取特定结算状态商户列表
	@RequestMapping("/toModifyMerchatSettStatus")
	public String toModifyMerchatSettStatus(Model model) {
		return "/demo/testModifyMerchatSettStatus";
	}

	@RequestMapping(value = "/modifyMerchatSettStatus", method = RequestMethod.POST)
	@ResponseBody
	public String modifyMerchatSettStatus(HttpServletRequest request) throws Exception {
		String modifyMerchatSettStatusUrl = request.getParameter("modifyMerchatSettStatusUrl");
		String agentNo = request.getParameter("agentNo");
		String agentKey = request.getParameter("agentKey");
		JSONObject params = new JSONObject();
		params.put("agentNo", agentNo);
		params.put("merchantNoList", request.getParameter("merchantNoList"));
		params.put("settStatus", request.getParameter("settStatus"));
		params.put("remark", request.getParameter("remark"));
		params.put("randomStr", UUID.randomUUID().toString());
		params.put("signType", "MD5");
		params.put("sign", MD5Util.getSign(params, agentKey));
		String requestParam = params.toJSONString();
		log.info("修改商户结算状态API请求参数：{}", requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(modifyMerchatSettStatusUrl, requestParam);
		log.info("修改商户结算状态API返回的结果：{}", response);
		JSONObject resultObj = new JSONObject();
		resultObj.put("修改商户结算状态API返回的结果", response);
		return response;
	}
	
	
	//手动导入对账文件对账
	@RequestMapping("/toImplFileRecon")
	public String toImplFileRecon(Model model) {
		return "/demo/testImplFileRecon";
	}
}
