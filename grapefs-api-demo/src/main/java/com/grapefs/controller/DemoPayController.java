package com.grapefs.controller;

import java.io.BufferedOutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.grapefs.demo.entity.H5PayReqData;
import com.grapefs.demo.entity.H5PayResData;
import com.grapefs.demo.entity.MerchantReqData;
import com.grapefs.demo.entity.MerchantResData;
import com.grapefs.demo.entity.MerchantUpdateReqData;
import com.grapefs.demo.entity.MerchantUpdateResData;
import com.grapefs.demo.entity.MicroPayReqData;
import com.grapefs.demo.entity.MicroPayResData;
import com.grapefs.demo.entity.NativePayReqData;
import com.grapefs.demo.entity.NativePayResData;
import com.grapefs.demo.entity.QueryOrderReqData;
import com.grapefs.demo.entity.QueryOrderResData;
import com.grapefs.demo.entity.RefundReqData;
import com.grapefs.demo.entity.RefundResData;
import com.grapefs.demo.entity.ReverseReqData;
import com.grapefs.demo.entity.ReverseResData;
import com.grapefs.demo.entity.UnifiedOrderReqData;
import com.grapefs.demo.entity.WapH5PayReqData;
import com.grapefs.demo.entity.WapH5PayResData;
import com.grapefs.demo.utils.HttpUtil;
import com.grapefs.demo.utils.MD5Util;


/**
 * @author yjw
 * @version 1.0 支付demo
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoPayController {

	Logger log = LoggerFactory.getLogger(DemoPayController.class);

	// demo主页
	@RequestMapping("/test")
	public String testHomePage(Model model) {

		return "/demo/testHomePage";
	}

	// 填写页面
	@RequestMapping("/toMicroPay")
	public String toMicroPay(Model model) {
		return "/demo/testMicroPay";
	}

	@RequestMapping(value = "/MicroPayNow", method = RequestMethod.POST)
	@ResponseBody
	public String MicroPayNow(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");
		String payMethod = request.getParameter("payMethod");
		String merchantNo = request.getParameter("merchantNo");
		String orderNo = request.getParameter("orderNo");
		String amount = request.getParameter("amount");
		String subject = request.getParameter("subject");
		String subAppid = request.getParameter("subAppid");
		String authCode = request.getParameter("authCode");
		String expireTime = request.getParameter("expireTime");
		String limitPay = request.getParameter("payByCredit");
		String goodsTag = request.getParameter("goodsTag");
		if ("TRUE".equals(limitPay)) {
			limitPay = null;
		} else {
			limitPay = "no_credit";
		}
		MicroPayReqData microPayReqData = new MicroPayReqData(merchantNo, md5Key, payMethod, orderNo, amount, subject, subAppid, authCode,
				expireTime, limitPay,goodsTag);
		microPayReqData.setSignType("MD5");
		microPayReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
		JSONObject jsonObject = (JSONObject) JSON.toJSON(microPayReqData);
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);
		String requestParam = jsonObject.toString();

		String apiUrl = request.getParameter("apiUrl");
		log.info("发送条码支付POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("条码支付返回结果" + resultObj);
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
		MicroPayResData microPayResData = JSONObject.toJavaObject(resultObj, MicroPayResData.class);

		return microPayResData.toString();
	}

	// 填写页面
	@RequestMapping("/toNativePay")
	public String toNativePay(Model model) {
		return "/demo/testNativePay";
	}

	@RequestMapping(value = "/NativePayNow", method = RequestMethod.POST)
	@ResponseBody
	public String NativePayNow(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");
		String payMethod = request.getParameter("payMethod");
		String merchantNo = request.getParameter("merchantNo");
		String orderNo = request.getParameter("orderNo");
		String amount = request.getParameter("amount");
		String subject = request.getParameter("subject");
		String subAppid = request.getParameter("subAppid");
		String notifyUrl = request.getParameter("notifyUrl");
		String expireTime = request.getParameter("expireTime");
		String limitPay = request.getParameter("payByCredit");
		String goodsTag = request.getParameter("goodsTag");
		if ("TRUE".equals(limitPay)) {
			limitPay = null;
		} else {
			limitPay = "no_credit";
		}
		NativePayReqData nativePayReqData = new NativePayReqData(merchantNo, md5Key, payMethod, orderNo, amount, subject, subAppid,
				notifyUrl, limitPay, expireTime,goodsTag);
		nativePayReqData.setSignType("MD5");
		nativePayReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
		JSONObject jsonObject = (JSONObject) JSON.toJSON(nativePayReqData);
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);
		String requestParam = jsonObject.toString();

		String apiUrl = request.getParameter("apiUrl");
		log.info("发送NativePay的POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("原生扫码支付返回结果" + resultObj);
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
		NativePayResData nativePayResData = JSONObject.toJavaObject(resultObj, NativePayResData.class);

		return nativePayResData.toString();
	}
	
	
	// 填写页面
		@RequestMapping("/toAppPay")
		public String toAppPay(Model model) {
			return "/demo/testAppPay";
		}

		@RequestMapping(value = "/appPayNow", method = RequestMethod.POST)
		@ResponseBody
		public String appPayNow(HttpServletRequest request) throws Exception {
			String md5Key = request.getParameter("md5Key");
			String payMethod = request.getParameter("payMethod");
			String merchantNo = request.getParameter("merchantNo");
			String orderNo = request.getParameter("orderNo");
			String amount = request.getParameter("amount");
			String subject = request.getParameter("subject");
			String subAppid = request.getParameter("subAppid");
			String notifyUrl = request.getParameter("notifyUrl");
			String expireTime = request.getParameter("expireTime");
			String limitPay = request.getParameter("payByCredit");
			String goodsTag = request.getParameter("goodsTag");
			if ("TRUE".equals(limitPay)) {
				limitPay = null;
			} else {
				limitPay = "no_credit";
			}
			NativePayReqData nativePayReqData = new NativePayReqData(merchantNo, md5Key, payMethod, orderNo, amount, subject, subAppid,
					notifyUrl, limitPay, expireTime,goodsTag);
			nativePayReqData.setSignType("MD5");
			nativePayReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
			JSONObject jsonObject = (JSONObject) JSON.toJSON(nativePayReqData);
			String sign = MD5Util.getSign(jsonObject, md5Key);
			jsonObject.put("sign", sign);
			String requestParam = jsonObject.toString();

			String apiUrl = request.getParameter("apiUrl");
			log.info("发送app的POST请求,请求参数" + requestParam);
			HttpUtil httpUtil = new HttpUtil();
			String response = httpUtil.post(apiUrl, requestParam);

			if (response == null || "".equals(response)) {
				throw new RuntimeException("返回参数为空");
			}
			JSONObject resultObj = JSONObject.parseObject(response);
			log.info("app支付返回结果" + resultObj);
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
	
	

	// 填写页面
	@RequestMapping("/toH5Pay")
	public String toH5Pay(Model model) {

		return "/demo/testH5Pay";
	}

	@RequestMapping(value = "/H5PayNow", method = RequestMethod.POST)
	@ResponseBody
	public String H5PayNow(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");
		String payMethod = request.getParameter("payMethod");
		String merchantNo = request.getParameter("merchantNo");
		String orderNo = request.getParameter("orderNo");
		String amount = request.getParameter("amount");
		String subject = request.getParameter("subject");
		String subAppid = request.getParameter("subAppid");
		String notifyUrl = request.getParameter("notifyUrl");
		String openId = request.getParameter("openId");
		String goodsTag = request.getParameter("goodsTag");
		String expireTime = request.getParameter("expireTime");
		String limitPay = request.getParameter("payByCredit");
	
		if ("TRUE".equals(limitPay)) {
			limitPay = null;
		} else {
			limitPay = "no_credit";
		}
		H5PayReqData h5PayReqData = new H5PayReqData(merchantNo, md5Key, payMethod, orderNo, amount, subject, subAppid, limitPay, notifyUrl,
				openId, expireTime,goodsTag);
		h5PayReqData.setSignType("MD5");
		h5PayReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
		JSONObject jsonObject = (JSONObject) JSON.toJSON(h5PayReqData);
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);
		String requestParam = jsonObject.toString();

		String apiUrl = request.getParameter("apiUrl");
		log.info("发送H5Pay的POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("H5支付返回结果" + resultObj);
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
		H5PayResData h5PayResData = JSONObject.toJavaObject(resultObj, H5PayResData.class);

		return h5PayResData.toString();
	}

	// 退款填写页面
	@RequestMapping("/torefund")
	public String toRefund(Model model) {
		return "/demo/testRefund";
	}

	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	@ResponseBody
	public String refund(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");
		String apiUrl = request.getParameter("apiUrl");
		String merchantNo = request.getParameter("merchantNo");
		String origOrderNo = request.getParameter("origOrderNo");
		String refundOrderNo = request.getParameter("refundOrderNo");
		String amount = request.getParameter("amount");

		RefundReqData refundReqData = new RefundReqData(merchantNo, md5Key, refundOrderNo, origOrderNo, amount);
		refundReqData.setSignType("MD5");
		refundReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
		JSONObject jsonObject = (JSONObject) JSON.toJSON(refundReqData);
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);
		String requestParam = jsonObject.toString();

		log.info("发起退款POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("退款返回结果" + resultObj);
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
		RefundResData RefundResData = JSONObject.toJavaObject(resultObj, RefundResData.class);

		return RefundResData.toString();
	}
	// 退款填写页面
		@RequestMapping("/toReverse")
		public String toReverse(Model model) {
			return "/demo/testReverse";
		}

		@RequestMapping(value = "/reverse", method = RequestMethod.POST)
		@ResponseBody
		public String reverse(HttpServletRequest request) throws Exception {
			String md5Key = request.getParameter("md5Key");
			String apiUrl = request.getParameter("apiUrl");
			String merchantNo = request.getParameter("merchantNo");
			String origOrderNo = request.getParameter("origOrderNo");
			String orderNo = request.getParameter("orderNo");


			ReverseReqData reverseReqData = new ReverseReqData(merchantNo, md5Key, orderNo, origOrderNo);
			reverseReqData.setSignType("MD5");
			reverseReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
			JSONObject jsonObject = (JSONObject) JSON.toJSON(reverseReqData);
			String sign = MD5Util.getSign(jsonObject, md5Key);
			jsonObject.put("sign", sign);
			String requestParam = jsonObject.toString();

			log.info("发送撤销POST请求,请求参数" + requestParam);
			HttpUtil httpUtil = new HttpUtil();
			String response = httpUtil.post(apiUrl, requestParam);

			if (response == null || "".equals(response)) {
				throw new RuntimeException("返回参数为空");
			}
			JSONObject resultObj = JSONObject.parseObject(response);
			log.info("撤销返回结果" + resultObj);
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
			ReverseResData reverseResData = JSONObject.toJavaObject(resultObj, ReverseResData.class);

			return reverseResData.toString();
		}
	// 填写页面
	@RequestMapping("/toQueryTrade")
	public String toQueryTrade() {

		return "/demo/testqueryTrade";
	}

	@RequestMapping(value = "/queryTrade", method = RequestMethod.POST)
	@ResponseBody
	public String queryTradeNow(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");
		String apiUrl = request.getParameter("apiUrl");
		String merchantNo = request.getParameter("merchantNo");
		String orderNo = request.getParameter("orderNo");
		QueryOrderReqData queryOrderReqData = new QueryOrderReqData(merchantNo, md5Key, orderNo);
		queryOrderReqData.setSignType("MD5");
		queryOrderReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
		JSONObject jsonObject = (JSONObject) JSON.toJSON(queryOrderReqData);
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);
		String requestParam = jsonObject.toString();

		log.info("发送查询交易的POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = null;

		response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("交易查询返回结果" + resultObj);
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
		QueryOrderResData queryOrderResData = JSONObject.toJavaObject(resultObj, QueryOrderResData.class);

		return queryOrderResData.toString();
	}

	// 报备商户填写页面
	@RequestMapping("/toreportMerchant")
	public String toMerchantReport() {

		return "/demo/testMerchantReport";
	}

	@RequestMapping(value = "/reportMerchant", method = RequestMethod.POST)
	@ResponseBody
	public String testMerchantReport(HttpServletRequest request) throws Exception {

		String md5Key = request.getParameter("md5Key");
		String apiUrl = request.getParameter("apiUrl");
		String agentNo = request.getParameter("agentNo");
		String fullName = request.getParameter("fullName");
		String shortName = request.getParameter("shortName");
		String bizCategory = request.getParameter("bizCategory");
		String legalPerson = request.getParameter("legalPerson");
		String cardNo = request.getParameter("cardNo");
		String mobileNo = request.getParameter("mobileNo");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String address = request.getParameter("address");

		String bankName = request.getParameter("bankName");
		String bankAccountName = request.getParameter("bankAccountName");
		String bankAccountNo = request.getParameter("bankAccountNo");
		String bankAccountType = request.getParameter("bankAccountType");
		String bankChannelNo = request.getParameter("bankChannelNo");
		String bankCardNo = request.getParameter("bankCardNo");
		String aliPayTradeRate = request.getParameter("aliPayTradeRate");
		String d0Rate = request.getParameter("d0Rate");
		String remitFee = request.getParameter("remitFee");
		String wxPayTradeRate = request.getParameter("wxPayTradeRate");

		MerchantReqData merchantReqData = new MerchantReqData();
		merchantReqData.setAgentNo(agentNo);
		merchantReqData.setFullName(fullName);
		merchantReqData.setShortName(shortName);
		merchantReqData.setLegalPerson(legalPerson);
		merchantReqData.setBizCategory(bizCategory);
		merchantReqData.setCardNo(cardNo);
		merchantReqData.setMobileNo(mobileNo);
		merchantReqData.setProvince(province);
		merchantReqData.setCity(city);
		merchantReqData.setAddress(address);
		merchantReqData.setBankName(bankName);
		merchantReqData.setBankAccountName(bankAccountName);
		merchantReqData.setBankAccountNo(bankAccountNo);
		merchantReqData.setBankAccountType(bankAccountType);
		merchantReqData.setBankChannelNo(bankChannelNo);
		merchantReqData.setBankCardNo(bankCardNo);
		merchantReqData.setAliPayTradeRate(aliPayTradeRate);
		merchantReqData.setD0Rate(d0Rate);
		merchantReqData.setWxPayTradeRate(wxPayTradeRate);
		merchantReqData.setRemitFee(remitFee);
		merchantReqData.setSignType("MD5");
		merchantReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
		JSONObject jsonObject = (JSONObject) JSON.toJSON(merchantReqData);
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);
		String requestParam = jsonObject.toString();

		log.info("发送报备商户的POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = null;

		response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("报备商户返回结果" + resultObj);
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
		MerchantResData merchantResData = JSONObject.toJavaObject(resultObj, MerchantResData.class);

		return merchantResData.toString();

	}
	// 报备商户填写页面
		@RequestMapping("/toMerchantUpdateReport")
		public String toMerchantUpdateReport() {

			return "/demo/testMerchantUpdateReport";
		}

		@RequestMapping(value = "/reportUpdateMerchant", method = RequestMethod.POST)
		@ResponseBody
		public String reportUpdateMerchant(HttpServletRequest request) throws Exception {

			String md5Key = request.getParameter("md5Key");
			String apiUrl = request.getParameter("apiUrl");
			String agentNo = request.getParameter("agentNo");
			String merchantNo = request.getParameter("merchantNo");
			String shortName = request.getParameter("shortName");
			String bizCategory = request.getParameter("bizCategory");
			String legalPerson = request.getParameter("legalPerson");
			String cardNo = request.getParameter("cardNo");
			String mobileNo = request.getParameter("mobileNo");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String address = request.getParameter("address");

			String bankName = request.getParameter("bankName");
			String bankAccountName = request.getParameter("bankAccountName");
			String bankAccountNo = request.getParameter("bankAccountNo");
			String bankAccountType = request.getParameter("bankAccountType");
			String bankChannelNo = request.getParameter("bankChannelNo");
			String bankCardNo = request.getParameter("bankCardNo");
			String aliPayTradeRate = request.getParameter("aliPayTradeRate");
			String d0Rate = request.getParameter("d0Rate");
			String remitFee = request.getParameter("remitFee");
			String wxPayTradeRate = request.getParameter("wxPayTradeRate");

			MerchantUpdateReqData merchantUpdateReqData = new MerchantUpdateReqData();
			merchantUpdateReqData.setAgentNo(agentNo);
			merchantUpdateReqData.setMerchantNo(merchantNo);
			merchantUpdateReqData.setShortName(shortName);
			merchantUpdateReqData.setLegalPerson(legalPerson);
			merchantUpdateReqData.setBizCategory(bizCategory);
			merchantUpdateReqData.setCardNo(cardNo);
			merchantUpdateReqData.setMobileNo(mobileNo);
			merchantUpdateReqData.setProvince(province);
			merchantUpdateReqData.setCity(city);
			merchantUpdateReqData.setAddress(address);
			merchantUpdateReqData.setBankName(bankName);
			merchantUpdateReqData.setBankAccountName(bankAccountName);
			merchantUpdateReqData.setBankAccountNo(bankAccountNo);
			merchantUpdateReqData.setBankAccountType(bankAccountType);
			merchantUpdateReqData.setBankChannelNo(bankChannelNo);
			merchantUpdateReqData.setBankCardNo(bankCardNo);
			merchantUpdateReqData.setAliPayTradeRate(aliPayTradeRate);
			merchantUpdateReqData.setD0Rate(d0Rate);
			merchantUpdateReqData.setWxPayTradeRate(wxPayTradeRate);
			merchantUpdateReqData.setRemitFee(remitFee);
			merchantUpdateReqData.setSignType("MD5");
			merchantUpdateReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
			JSONObject jsonObject = (JSONObject) JSON.toJSON(merchantUpdateReqData);
			String sign = MD5Util.getSign(jsonObject, md5Key);
			jsonObject.put("sign", sign);
			String requestParam = jsonObject.toString();

			log.info("发送修改商户的POST请求,请求参数" + requestParam);
			HttpUtil httpUtil = new HttpUtil();
			String response = null;

			response = httpUtil.post(apiUrl, requestParam);

			if (response == null || "".equals(response)) {
				throw new RuntimeException("返回参数为空");
			}
			JSONObject resultObj = JSONObject.parseObject(response);
			log.info("修改商户返回结果" + resultObj);
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
			MerchantUpdateResData merchantResData = JSONObject.toJavaObject(resultObj, MerchantUpdateResData.class);

			return merchantResData.toString();

		}
		
	// 商户入驻填写页面
	@RequestMapping("/toOpenMerchant")
	public String toOpenMerchant() {

		return "/demo/testOpenMerchant";
	}

	@RequestMapping(value = "/openMerchant", method = RequestMethod.POST)
	@ResponseBody
	public String openMerchant(HttpServletRequest request) throws Exception {
		// 测试商户报备，AGENTNO和AGENTkEY都是银行下发给我们的系统参数
		String reportMerchantUrl = request.getParameter("reportMerchantUrl");
		String AGENTNO = request.getParameter("agentNo");
		String AGENTkEY = request.getParameter("agentKey");
		String channelCode = request.getParameter("channelCode");
		String merchantInfo = request.getParameter("merchantInfo");
		JSONObject merchantInfoJson = JSONObject.parseObject(merchantInfo);
		// 请求报备到华夏银行的api接口
		JSONObject params = new JSONObject();
		params.put("agentNo", AGENTNO);
		params.put("channelCode", channelCode);
		String randomStr = UUID.randomUUID().toString();
		params.put("randomStr", randomStr);
		params.put("merchantInfo", merchantInfoJson);
		params.put("signType", "MD5");
		String sign = MD5Util.getSign(params, AGENTkEY);
		params.put("sign", sign);
	
		String requestParam = params.toJSONString();
		log.info("银行api商家报备请求参数：{}", requestParam);
		HttpUtil httpUtil = new HttpUtil();
		// 请求的报备商户url
		String response = httpUtil.post(reportMerchantUrl, requestParam);
		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		log.info("银行api商家报备返回的结果：{}", response);
		JSONObject resultObj = new JSONObject();
		resultObj.put("调用报备接口返回的结果", response);
		resultObj.put("报备的商户信息", merchantInfo);
		return response;
	}

	// 商户修改填写页面
	@RequestMapping("/toModifyMerchant")
	public String toModifyMerchant() {

		return "/demo/testMerchantModify";
	}

	@RequestMapping(value = "/modifyMerchant", method = RequestMethod.POST)
	@ResponseBody
	public String modifyMerchant(HttpServletRequest request) throws Exception {
		// 测试商户报备，AGENTNO和AGENTkEY都是银行下发给我们的系统参数
		String merchantModifyUrl = request.getParameter("merchantModifyUrl");
		String agentNo = request.getParameter("agentNo");
		String agentKey = request.getParameter("agentKey");
		String merchantNo = request.getParameter("merchantNo");
		String merchantInfo = request.getParameter("merchantInfo");
		JSONObject merchantInfoJson = JSONObject.parseObject(merchantInfo);
		// 请求报备到华夏银行的api接口
		JSONObject params = new JSONObject();
		params.put("agentNo", agentNo);
		params.put("merchantNo", merchantNo);
		params.put("randomStr", UUID.randomUUID().toString());
		params.put("signType", "MD5");
		params.put("merchantInfo", merchantInfoJson);
		params.put("sign", MD5Util.getSign(params, agentKey));
		// merchantInfo不参与签名
		String requestParam = params.toJSONString();
		log.info("银行api商户修改请求参数：{}", requestParam);
		HttpUtil httpUtil = new HttpUtil();
		// 请求的报备商户url
		String response = httpUtil.post(merchantModifyUrl, requestParam);
		log.info("银行api商户修改返回的结果：{}", response);
		JSONObject resultObj = new JSONObject();
		resultObj.put("调用商户修改接口返回的结果", response);
		resultObj.put("修改的商户信息", merchantInfo);
		return response;
	}

	// 商户开通产品
	@RequestMapping("/toOpenMerchantProduct")
	public String toOpenMerchantProduct() {

		return "/demo/testOpenMerchantProduct";
	}

	@RequestMapping(value = "/openMerchantProduct", method = RequestMethod.POST)
	@ResponseBody
	public String openMerchantProduct(HttpServletRequest request) throws Exception {
		// 测试商户报备，AGENTNO和AGENTkEY都是银行下发给我们的系统参数
		String openMerchantProductUrl = request.getParameter("openMerchantProductUrl");
		String agentNo = request.getParameter("agentNo");
		String agentKey = request.getParameter("agentKey");
		String merchantNo = request.getParameter("merchantNo");
		String merchantProduct = request.getParameter("merchantProduct");
		JSONObject merchantProductJson = JSONObject.parseObject(merchantProduct);

		// 请求报备到华夏银行的api接口
		JSONObject params = new JSONObject();
		params.put("agentNo", agentNo);
		params.put("merchantNo", merchantNo);
		params.put("merchantProduct", merchantProductJson);
		params.put("randomStr", UUID.randomUUID().toString());
		params.put("signType", "MD5");
		params.put("sign", MD5Util.getSign(params, agentKey));
		String requestParam = params.toJSONString();
		log.info("银行api商户开通产品请求参数：{}", requestParam);
		HttpUtil httpUtil = new HttpUtil();
		// 请求的报备商户url
		String response = httpUtil.post(openMerchantProductUrl, requestParam);
		log.info("银行api商户开通产品返回的结果：{}", response);
		JSONObject resultObj = new JSONObject();
		resultObj.put("商户开通产品信息返回的结果", response);
		resultObj.put("商户开通产品信息", merchantProduct);
		return response;
	}

	// 商户修改产品
	@RequestMapping("/toModifyMerchantProduct")
	public String toMerchantProductModify() {

		return "/demo/testModifyMerchantProduct";
	}

	@RequestMapping(value = "/modifyMerchantProduct", method = RequestMethod.POST)
	@ResponseBody
	public String modifyMerchantProduct(HttpServletRequest request) throws Exception {
		// 测试商户报备，AGENTNO和AGENTkEY都是银行下发给我们的系统参数
		String openMerchantProductUrl = request.getParameter("modifyMerchantProductUrl");
		String agentNo = request.getParameter("agentNo");
		String agentKey = request.getParameter("agentKey");
		String merchantNo = request.getParameter("merchantNo");
		String merchantProduct = request.getParameter("merchantProduct");
		JSONObject merchantProductJson = JSONObject.parseObject(merchantProduct);

		// 请求报备到华夏银行的api接口
		JSONObject params = new JSONObject();
		params.put("agentNo", agentNo);
		params.put("merchantNo", merchantNo);
		// merchantProduct不参与签名
		params.put("merchantProduct", merchantProductJson);
		params.put("randomStr", UUID.randomUUID().toString());
		params.put("signType", "MD5");
		params.put("sign", MD5Util.getSign(params, agentKey));
		String requestParam = params.toJSONString();
		log.info("银行api商户修改产品请求参数：{}", requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(openMerchantProductUrl, requestParam);
		log.info("银行api商户修改产品返回的结果：{}", response);
		JSONObject resultObj = new JSONObject();
		resultObj.put("商户修改产品信息返回的结果", response);
		resultObj.put("商户修改产品信息", merchantProduct);
		return response;
	}
		
	@RequestMapping("/toD0SettList")
	public String toD0SettList(Model model) {
		return "/demo/testtoD0SettList";
	}

	@RequestMapping(value = "/D0SettList", method = RequestMethod.POST)
	@ResponseBody
	public String D0SettLt(HttpServletRequest request) throws Exception {


		String md5Key = request.getParameter("md5Key");
		String apiUrl = request.getParameter("apiUrl");
		String merchantNo = request.getParameter("merchantNo");
		String notify_url = request.getParameter("notify_url");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("merchantNo", merchantNo);
		jsonObject.put("signType", "MD5");
		jsonObject.put("notify_url", notify_url);
		jsonObject.put("randomStr", UUID.randomUUID());
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();
		log.info("发送D0提现请求,请求参数" + request);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || response == "") {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("D0提现返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String resultSign = resultObj.getString("sign");
			if (resultSign == null || resultSign == "") {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, resultSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}

		return resultObj.toJSONString();
	}

	@RequestMapping("/toD0settByMerch")
	public String toD0settByMerch(Model model) {
		return "/demo/testtoD0settByMerch";
	}

	@RequestMapping(value = "/D0SettHxListByMerch", method = RequestMethod.POST)
	@ResponseBody
	public String D0SettHxListByMerch(HttpServletRequest request) throws Exception {
		String md5Key = request.getParameter("md5Key");
		String apiUrl = request.getParameter("apiUrl");
		String merchantNo = request.getParameter("merchantNo");
		String notify_url = request.getParameter("notify_url");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("merchantNo", merchantNo);
		jsonObject.put("signType", "MD5");
		jsonObject.put("notify_url", notify_url);
		jsonObject.put("randomStr", UUID.randomUUID());
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();
		log.info("发送华夏D0提现请求,请求参数" + request);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || response == "") {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("华夏D0提现返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String resultSign = resultObj.getString("sign");
			if (resultSign == null || resultSign == "") {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, resultSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}

		return resultObj.toJSONString();
	}


	// D0提现查询页面
	@RequestMapping("/toD0SettQuery")
	public String toD0SettQuery() {
		return "/demo/testD0SettQuery";
	}

	@RequestMapping(value = "/D0SettQuery", method = RequestMethod.POST)
	@ResponseBody
	public String D0SettQuery(HttpServletRequest request) throws Exception {


		String md5Key = request.getParameter("md5Key");
		String apiUrl = request.getParameter("apiUrl");
		String merchantNo = request.getParameter("merchantNo");
		String orderNo = request.getParameter("orderNo");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("merchantNo", merchantNo);
		jsonObject.put("signType", "MD5");
		jsonObject.put("orderNo", orderNo);
		jsonObject.put("randomStr", UUID.randomUUID());
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();
		log.info("发送查询提现订单记录请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || response == "") {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("查询提现订单记录返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String resultSign = resultObj.getString("sign");
			if (resultSign == null || resultSign == "") {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, resultSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}

		return resultObj.toJSONString();
	}
	// 代理商对账文件下载
	@RequestMapping("/toDownloadReconFileList")
	public String toDownloadReconFileList() {

		return "/demo/testDownloadReconFileList";
	}
	@RequestMapping(value = "/DownloadReconFile", method = RequestMethod.POST)
	@ResponseBody
	public void DownloadReconFile(HttpServletRequest request,HttpServletResponse respon) throws Exception {
		String md5Key = request.getParameter("md5Key");
		String apiUrl = request.getParameter("apiUrl");
		String agentNo = request.getParameter("agentNo");
		String reconDate = request.getParameter("reconDate");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("agentNo", agentNo);
		jsonObject.put("signType", "MD5");
		jsonObject.put("reconDate", reconDate);
		jsonObject.put("randomStr", UUID.randomUUID());
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();
		log.info("发送下载对账文件POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String responseResult = null;
		try {
			responseResult = httpUtil.post(apiUrl, requestParam);
		} catch (Exception e) {
			throw new RuntimeException("调用对账接口异常");
		}
		if (responseResult == null || responseResult == "") {
			throw new RuntimeException("返回参数为空");
		}

		BufferedOutputStream bos = new BufferedOutputStream(respon.getOutputStream());
		respon.reset();// 清空输出流
		respon.setHeader("Content-disposition",
				"attachment; filename=" + URLEncoder.encode("结算文件" + reconDate + ".txt", "utf-8"));
		respon.setContentType("application/txt");// 定义输出类型
		bos.write(responseResult.getBytes());
		bos.flush();
		respon.flushBuffer();
	}
	
	
		// 商户对账文件下载
		@RequestMapping("/toDownloadMerchantReconFile")
		public String toDownloadMerchantReconFile() {
			return "/demo/testDownloadReconFileByMerchant";
		}
		@RequestMapping(value = "/downloadMerchantReconFile", method = RequestMethod.POST)
		@ResponseBody
		public void downloadMerchantReconFile(HttpServletRequest request,HttpServletResponse respon) throws Exception {
			String md5Key = request.getParameter("md5Key");
			String apiUrl = request.getParameter("apiUrl");
			String merchantNo = request.getParameter("merchantNo");
			String reconDate = request.getParameter("reconDate");
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("merchantNo", merchantNo);
			jsonObject.put("signType", "MD5");
			jsonObject.put("reconDate", reconDate);
			jsonObject.put("randomStr", UUID.randomUUID());
			String sign = MD5Util.getSign(jsonObject, md5Key);
			jsonObject.put("sign", sign);

			String requestParam = jsonObject.toString();
			log.info("发送下载对账文件POST请求,请求参数" + requestParam);
			HttpUtil httpUtil = new HttpUtil();
			String responseResult = null;
			try {
				responseResult = httpUtil.post(apiUrl, requestParam);
			} catch (Exception e) {
				throw new RuntimeException("调用对账接口异常");
			}
			if (responseResult == null || responseResult == "") {
				throw new RuntimeException("返回参数为空");
			}

			BufferedOutputStream bos = new BufferedOutputStream(respon.getOutputStream());
			respon.reset();// 清空输出流
			respon.setHeader("Content-disposition",
					"attachment; filename=" + URLEncoder.encode("商户对账文件" + reconDate + ".txt", "utf-8"));
			respon.setContentType("application/txt");// 定义输出类型
			bos.write(responseResult.getBytes());
			bos.flush();
			respon.flushBuffer();
		}
		
	
	@RequestMapping("/toALIPayH5Test")
	public String toALIPayH5Test() {

		return "/demo/testAliPay";
	}
	
	// 填写页面
	@RequestMapping("/toPayH5Test")
	public @ResponseBody String toPayH5Test(HttpServletRequest request,Model model) throws Exception {
		String md5Key = request.getParameter("md5Key");
		String payMethod = request.getParameter("payMethod");
		String merchantNo = request.getParameter("merchantNo");
		String orderNo = request.getParameter("orderNo");
		String amount = request.getParameter("amount");
		String subject = request.getParameter("subject");
		String notifyUrl = request.getParameter("notifyUrl");
		String userId = null;
		String openId = request.getParameter("openId");
		String buyerId = request.getParameter("buyerId");
		if ("WXPAY".equals(payMethod)) {
			userId = openId;
		}
		if ("ALIPAY".equals(payMethod)) {
			userId = buyerId;
		}
		String expireTime = request.getParameter("expireTime");
		String limitPay = request.getParameter("limitPay");
		if (limitPay.equals("TRUE")) {
			limitPay = null;
		} else
			limitPay = "no_credit";
	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("merchantNo", merchantNo);
		jsonObject.put("payMethod", payMethod);
		jsonObject.put("orderNo", orderNo);
		jsonObject.put("amount", amount);
		jsonObject.put("subject", subject);
		jsonObject.put("notifyUrl", notifyUrl);
		jsonObject.put("openId", userId);
		jsonObject.put("buyerId", userId);
		jsonObject.put("expireTime", expireTime);
		jsonObject.put("limitPay", limitPay);
		jsonObject.put("signType", "MD5");
		jsonObject.put("randomStr", UUID.randomUUID());
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);
		String requestParam = jsonObject.toString();

		String apiUrl =request.getParameter("apiUrl");
		log.info("发送H5Pay的POST请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("H5支付返回结果" + resultObj);
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
		H5PayResData h5PayResData = JSONObject.toJavaObject(resultObj, H5PayResData.class);
		return resultObj.toString();
	}
	
	
	@RequestMapping("/toD0SettHxList")
	public String toD0SettHxList(Model model) {
		return "/demo/testtoD0SettHxList";
	}

	@RequestMapping(value = "/D0SettHxList", method = RequestMethod.POST)
	@ResponseBody
	public String D0SettHxList(HttpServletRequest request) throws Exception {


		String md5Key = request.getParameter("md5Key");
		String apiUrl = request.getParameter("apiUrl");
		String merchantNo = request.getParameter("merchantNo");
		String notify_url = request.getParameter("notify_url");
		String orderNoList = request.getParameter("orderNoList");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("merchantNo", merchantNo);
		jsonObject.put("signType", "MD5");
		jsonObject.put("notify_url", notify_url);
		jsonObject.put("orderNoList", orderNoList);
		jsonObject.put("randomStr", UUID.randomUUID());
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();
		log.info("发送华夏D0提现请求,请求参数" + request);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || response == "") {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("华夏D0提现返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String resultSign = resultObj.getString("sign");
			if (resultSign == null || resultSign == "") {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, resultSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}

		return resultObj.toJSONString();
	}	
	// D0提现查询页面
	@RequestMapping("/toD0SettHxQuery")
	public String toD0SettHxQuery() {
		return "/demo/testD0SettHxQuery";
	}

	@RequestMapping(value = "/D0SettHxQuery", method = RequestMethod.POST)
	@ResponseBody
	public String D0SettHxQuery(HttpServletRequest request) throws Exception {


		String md5Key = request.getParameter("md5Key");
		String apiUrl = request.getParameter("apiUrl");
		String merchantNo = request.getParameter("merchantNo");
		String withdrawBatchNo = request.getParameter("withdrawBatchNo");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("merchantNo", merchantNo);
		jsonObject.put("signType", "MD5");
		jsonObject.put("withdrawBatchNo", withdrawBatchNo);
		jsonObject.put("randomStr", UUID.randomUUID());
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);

		String requestParam = jsonObject.toString();
		log.info("发送查询提现订单记录请求,请求参数" + requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestParam);

		if (response == null || response == "") {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("查询提现订单记录返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String resultSign = resultObj.getString("sign");
			if (resultSign == null || resultSign == "") {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, resultSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}

		return resultObj.toJSONString();
	}
	
	 // 微信支付配置新增
	@RequestMapping("/toPayconfigAdd")
	public String toPayconfigAdd() {
		return "/demo/testPayConfigAdd";
	}

	@RequestMapping(value = "/payConfigAdd", method = RequestMethod.POST)
	@ResponseBody
	public String testPayConfigAdd(HttpServletRequest request) throws Exception {
		String payConfigAddUrl = request.getParameter("payConfigAddUrl");
		String merchantNo = request.getParameter("merchantNo");
		String merchantKey = request.getParameter("merchantKey");
		String jsapiPath = request.getParameter("jsapiPath");
		String subAppid = request.getParameter("subAppid");
		String subscribeAppid = request.getParameter("subscribeAppid");
		String subscribeRelatedAppid = request.getParameter("subscribeRelatedAppid");
		String aliLogonid = request.getParameter("aliLogonid");
		JSONObject params = new JSONObject();
		params.put("merchantNo", merchantNo);
		params.put("jsapiPath", jsapiPath);
		params.put("subAppid",subAppid);
		params.put("subscribeAppid",subscribeAppid);
		params.put("subscribeRelatedAppid",subscribeRelatedAppid);
		params.put("aliLogonid",aliLogonid);
		params.put("randomStr", UUID.randomUUID().toString());
		params.put("signType", "MD5");
		params.put("sign", MD5Util.getSign(params, merchantKey));
		String requestParam = params.toJSONString();
		log.info("服务商子商户开发配置新增API请求参数：{}", requestParam);

		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(payConfigAddUrl, requestParam);
		log.info("服务商子商户开发配置新增API返回的结果：{}", response);
		JSONObject resultObj = new JSONObject();
		resultObj.put("服务商子商户开发配置新增API返回的结果", response);
		return response;
	}
	
	 // 微信支付配置查询
	@RequestMapping("/toPayConfigQuery")
	public String toPayConfigQuery() {
		return "/demo/testPayConfigQuery";
	}

	@RequestMapping(value = "/payConfigQuery", method = RequestMethod.POST)
	@ResponseBody
	public String testPayconfigQuery(HttpServletRequest request) throws Exception {
		String payConfigQueryUrl = request.getParameter("payConfigQueryUrl");
		String merchantNo = request.getParameter("merchantNo");
		String merchantKey = request.getParameter("merchantKey");
		JSONObject params = new JSONObject();
		params.put("merchantNo", merchantNo);
		params.put("randomStr", UUID.randomUUID().toString());
		params.put("signType", "MD5");
		params.put("sign", MD5Util.getSign(params, merchantKey));
		String requestParam = params.toJSONString();
		log.info("服务商子商户开发配置查询API请求参数：{}", requestParam);

		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(payConfigQueryUrl, requestParam);
		log.info("服务商子商户开发配置查询API返回的结果：{}", response);
		JSONObject resultObj = new JSONObject();
		resultObj.put("服务商子商户开发配置查询API返回的结果", response);
		return response;
	}
	// 商户查询填写页面
	@RequestMapping("/toQueryMerchantInfo")
	public String toQueryMerchantInfo(Model model) {
		return "/demo/testQueryMerchantInfo";
	}

	
	@RequestMapping(value = "/queryMerchantInfo", method = RequestMethod.POST)
	@ResponseBody
	public String testQueryMerchantInfo(HttpServletRequest request) throws Exception {
		// 测试商户报备，AGENTNO和AGENTkEY都是银行下发给我们的系统参数
		String queryMerchantUrl = request.getParameter("queryMerchantUrl");
		String agentNo = request.getParameter("agentNo");
		String merchantNo = request.getParameter("merchantNo");
		String agentKey = request.getParameter("agentKey");
		// 请求报备到华夏银行的api接口
		JSONObject params = new JSONObject();
		params.put("agentNo", agentNo);
		params.put("merchantNo", merchantNo);
		params.put("randomStr", UUID.randomUUID().toString());
		params.put("signType", "MD5");
		params.put("sign", MD5Util.getSign(params, agentKey));
		String requestParam = params.toJSONString();
		log.info("商户查询请求参数：{}", requestParam);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(queryMerchantUrl, requestParam);
		log.info("商户查询返回的结果：{}", response);
		JSONObject resultObj = new JSONObject();
		resultObj.put("调用商户查询接口返回的结果", response);
		return response;
	}



	@RequestMapping("/toHistoricalMerchBinding")
	public String toHistoricalMerchBinding(Model model) {
		return "/demo/testHistoricalMerchBinding";
	}

	@RequestMapping(value = "/historicalMerchBinding", method = RequestMethod.POST)
	@ResponseBody
	public String historicalMerchBinding(HttpServletRequest request) throws Exception {
		//参数 : {"agentNo","merchBindInfo", "randomStr", "signType", "sign" }
		String apiUrl = request.getParameter("apiUrl");
		String agentNo = request.getParameter("agentNo");
		String md5Key = request.getParameter("agentKey");
		String merchBindInfo = request.getParameter("merchBindInfo");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("agentNo", agentNo);
		jsonObject.put("randomStr", UUID.randomUUID());
		jsonObject.put("signType", "MD5");
		String sign = MD5Util.getSign(jsonObject, md5Key);
		jsonObject.put("sign", sign);
		//merchBindInfo 不参与签名
		jsonObject.put("merchBindInfo", merchBindInfo);
		String requestStr = jsonObject.toString();
		log.info("发送查询订单记录请求,请求参数" + requestStr);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(apiUrl, requestStr);

		if (response == null || response == "") {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		log.info("查询订单记录返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String resultSign = resultObj.getString("sign");
			if (resultSign == null || resultSign == "") {
				throw new RuntimeException("签名为空");
			}
			log.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, resultSign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			}
		}
		return resultObj.toJSONString();
	}

	// 填写页面
		@RequestMapping("/toUnifiedOrder")
		public String toUnifiedOrder(Model model) {

			return "/demo/testUnifiedOrder";
		}

		@RequestMapping(value = "/UnifiedOrderNow", method = RequestMethod.POST)
		@ResponseBody
		public String UnifiedOrderNow(HttpServletRequest request) throws Exception {
			String md5Key = request.getParameter("md5Key");
			String payMethod = request.getParameter("payMethod");
			String merchantNo = request.getParameter("merchantNo");
			String orderNo = request.getParameter("orderNo");
			String amount = request.getParameter("amount");
			String subject = request.getParameter("subject");
			String subAppid = request.getParameter("subAppid");
			String notifyUrl = request.getParameter("notifyUrl");
			String buyerId = request.getParameter("buyerId");
			String goodsTag = request.getParameter("goodsTag");
			String expireTime = request.getParameter("expireTime");
			String limitPay = request.getParameter("payByCredit");
			if ("TRUE".equals(limitPay)) {
				limitPay = null;
			} else {
				limitPay = "no_credit";
			}
			UnifiedOrderReqData h5PayReqData = new UnifiedOrderReqData(merchantNo, md5Key, payMethod, orderNo, amount, subject, subAppid, limitPay, notifyUrl,
					buyerId, expireTime,goodsTag);
			h5PayReqData.setSignType("MD5");
			h5PayReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
			JSONObject jsonObject = (JSONObject) JSON.toJSON(h5PayReqData);
			String sign = MD5Util.getSign(jsonObject, md5Key);
			jsonObject.put("sign", sign);
			String requestParam = jsonObject.toString();

			String apiUrl = request.getParameter("apiUrl");
			log.info("发送公众号/服务窗下单的POST请求,请求参数" + requestParam);
			HttpUtil httpUtil = new HttpUtil();
			String response = httpUtil.post(apiUrl, requestParam);

			if (response == null || "".equals(response)) {
				throw new RuntimeException("返回参数为空");
			}
			JSONObject resultObj = JSONObject.parseObject(response);
			log.info("公众号/服务窗下单返回结果" + resultObj);
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
			H5PayResData h5PayResData = JSONObject.toJavaObject(resultObj, H5PayResData.class);

			return h5PayResData.toString();
		}

		
		
		// 填写页面
		@RequestMapping("/toWapH5Pay")
		public String toWapH5Pay(Model model) {

			return "/demo/testWapH5Pay";
		}

		@RequestMapping(value = "/wapH5PayNow", method = RequestMethod.POST)
		@ResponseBody
		public String wapH5PayNow(HttpServletRequest request) throws Exception {
			String md5Key = request.getParameter("Md5Key");
			String payMethod = request.getParameter("payMethod");
			String merchantNo = request.getParameter("merchantNo");
			String orderNo = request.getParameter("orderNo");
			String amount = request.getParameter("amount");
			String subject = request.getParameter("subject");
			String notifyUrl = request.getParameter("notifyUrl");
			String goodsTag = request.getParameter("goodsTag");
			String expireTime = request.getParameter("expireTime");
			String payByCredit = request.getParameter("payByCredit");
			String wapUrl = request.getParameter("wapUrl");
			String wapName = request.getParameter("wapName");
			String limitPay = request.getParameter("limitPay");
			String ipAddress = request.getParameter("ipAddress");
			String transOnlineRate = request.getParameter("transOnlineRate");
			if ("TRUE".equals(payByCredit)) {
				payByCredit = null;
			} else {
				payByCredit = "no_credit";
			}
			WapH5PayReqData waph5PayReqData = new WapH5PayReqData(merchantNo, md5Key, payMethod, orderNo, amount, subject, wapUrl, wapName, ipAddress, limitPay, notifyUrl, expireTime, goodsTag,transOnlineRate);
			waph5PayReqData.setSignType("MD5");
			waph5PayReqData.setRandomStr(UUID.randomUUID().toString().replace("-", ""));
			JSONObject jsonObject = (JSONObject) JSON.toJSON(waph5PayReqData);
			String sign = MD5Util.getSign(jsonObject, md5Key);
			jsonObject.put("sign", sign);
			String requestParam = jsonObject.toString();

			String apiUrl = request.getParameter("apiUrl");
			log.info("发送微信H5Pay的POST请求,请求参数" + requestParam);
			HttpUtil httpUtil = new HttpUtil();
			String response = httpUtil.post(apiUrl, requestParam);

			if (response == null || "".equals(response)) {
				throw new RuntimeException("返回参数为空");
			}
			JSONObject resultObj = JSONObject.parseObject(response);
			log.info("微信H5支付返回结果" + resultObj);
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
			WapH5PayResData wapH5PayResData = JSONObject.toJavaObject(resultObj, WapH5PayResData.class);
			return wapH5PayResData.toString();
		}

}
