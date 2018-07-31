package com.grapefs.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.grapefs.demo.constants.Constants;
import com.grapefs.demo.entity.MicroPayReqData;
import com.grapefs.demo.entity.MicroPayResData;
import com.grapefs.demo.utils.HttpUtil;
import com.grapefs.demo.utils.MD5Util;

/**
 * 
 * 类描述：条码支付
 * 
 * @author lxl
 * @since 1.0, 2016年12月27日
 */
public class MicroPayService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		MicroPayService microPayService = new MicroPayService();
		String payMethod = "WXPAY";
		String orderNo = "20161104019";
		String amount = "0.01";
		String subject = "测试商户";
		String subAppid = "";
		String authCode = "130449875315715166";
		String expireTime = "10";
		String limitPay = "";
		String goodsTag = null;
		try {
			MicroPayReqData microPayReqData = new MicroPayReqData(Constants.MERCHANT_NO, Constants.MERCHANT_SECRET, payMethod, orderNo,
					amount, subject, subAppid, authCode, expireTime, limitPay,goodsTag);
			MicroPayResData microPayResData = microPayService.reqPay(microPayReqData, Constants.MERCHANT_SECRET);
			System.out.println(microPayResData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public MicroPayResData reqPay(MicroPayReqData microPayReqData, String md5Key) throws Exception {
		String url = Constants.API_BASE_URL + "/pay/microPay";
		logger.info("将对象转换为json");
		JSONObject jsonObject = (JSONObject) JSON.toJSON(microPayReqData);
		String request = jsonObject.toString();
		logger.info("发送条码支付POST请求,请求参数" + request);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(url, request);
		logger.info("发送条码支付POST请求,返回参数{}" , response);
		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		logger.info("条码支付返回结果" + resultObj);
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
		MicroPayResData microPayResData = JSONObject.toJavaObject(resultObj, MicroPayResData.class);
		
		return microPayResData;
		
	}
}
