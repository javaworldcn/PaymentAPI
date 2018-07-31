package com.grapefs.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.grapefs.demo.entity.PayNotifyResData;
import com.grapefs.demo.utils.MD5Util;

/**
 * 
 * 类描述：支付结果异步通知
 * 
 * @author lxl
 * @since 1.0, 2016年12月27日
 */
public class PayNotifyService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public PayNotifyResData responseNotify(String response, String md5Key) throws Exception {
	
		JSONObject resultObj = JSONObject.parseObject(response);
		logger.info("异步交易通知返回结果" + resultObj);
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
		PayNotifyResData payNotifyResData = JSONObject.toJavaObject(resultObj, PayNotifyResData.class);
		
		return payNotifyResData;
		
	}
}
