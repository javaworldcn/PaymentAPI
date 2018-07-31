package com.grapefs.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.grapefs.demo.constants.Constants;
import com.grapefs.demo.entity.NativePayReqData;
import com.grapefs.demo.entity.NativePayResData;
import com.grapefs.demo.utils.HttpUtil;
import com.grapefs.demo.utils.MD5Util;

/**
 * 
 * 类描述：原生扫码支付
 * 
 * @author lxl
 * @since 1.0, 2016年12月27日
 */
public class NativePayService {
	private static Logger logger = LoggerFactory.getLogger(NativePayService.class);

	public static void main(String[] args) {
		NativePayService nativePayService = new NativePayService();
		String md5Key = "5bf5db1b87424fcc8676127632b06b2c";
		String payMethod = "WXPAY";
		String merchantNo = "201611150000966";
		String orderNo = "201612072020";
		String amount = "0.01";
		String subject = "测试商户";
		String subAppid = "";
		String limitPay="no_credit";
		String notifyUrl = "https://www.grapefs.com";
		String expireTime = "10";
		String goodsStag = null;
		try {
			NativePayReqData nativePayReqData = new NativePayReqData(Constants.MERCHANT_NO, Constants.MERCHANT_SECRET, payMethod, orderNo,
					amount, subject, subAppid, notifyUrl, limitPay, expireTime,goodsStag);
			NativePayResData nativePayResData = nativePayService.reqPay(nativePayReqData, Constants.MERCHANT_SECRET);
			System.out.println(nativePayResData);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}

	public NativePayResData reqPay(NativePayReqData nativePayReqData, String md5Key) throws Exception {
//		String url = "http://192.168.88.90/api/pay/nativePay";
		String url = Constants.API_BASE_URL + "/pay/nativePay";
		logger.info("将对象转换为json");
		JSONObject jsonObject = (JSONObject) JSON.toJSON(nativePayReqData);
		String request = jsonObject.toString();
		logger.info("发送原生扫码支付POST请求,请求参数" + request);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(url, request);
		
		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		logger.info("原生扫码支付返回结果" + resultObj);
		if ("000000".equals(resultObj.get("respCode"))) {
			String sign = resultObj.getString("sign");
			if (sign == null || "".equals(sign)) {
				throw new RuntimeException("签名为空");
			}
			logger.info("验证返回参数的签名");
			boolean result = MD5Util.verifySign(resultObj, md5Key, sign);
			if (result != true) {
				throw new RuntimeException("返回参数的签名验证失败");
			} 
		}
		NativePayResData nativePayResData = JSONObject.toJavaObject(resultObj, NativePayResData.class);
		
		return nativePayResData;
		
	}
}
