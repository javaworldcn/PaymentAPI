package com.grapefs.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.grapefs.demo.constants.Constants;
import com.grapefs.demo.entity.ReverseReqData;
import com.grapefs.demo.entity.ReverseResData;
import com.grapefs.demo.utils.HttpUtil;
import com.grapefs.demo.utils.MD5Util;

/**
 * 
 * 类描述：交易撤销
 * 
 * @author lxl
 * @since 1.0, 2016年12月27日
 */
public class ReverseService {
	private static Logger logger = LoggerFactory.getLogger(ReverseService.class);

	public static void main(String[] args) {
		ReverseService reverseService = new ReverseService();
		String orderNo = "201609051532";
		String origOrderNo = "201609021140";
		try {
			ReverseReqData reverseReqData = new ReverseReqData(Constants.MERCHANT_NO, Constants.MERCHANT_SECRET, orderNo, origOrderNo);
			ReverseResData reverseResData = reverseService.reqPay(reverseReqData, Constants.MERCHANT_SECRET);
			System.out.println(reverseResData);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}

	public ReverseResData reqPay(ReverseReqData reverseReqData, String md5Key) throws Exception {
		String url = Constants.API_BASE_URL + "/toReverse";
		logger.info("将对象转换为json");
		JSONObject jsonObject = (JSONObject) JSON.toJSON(reverseReqData);
		String request = jsonObject.toString();
		logger.info("发送退款POST请求,请求参数" + request);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(url, request);
		
		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		logger.info("撤销返回结果" + resultObj);
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
		ReverseResData reverseResData = JSONObject.toJavaObject(resultObj, ReverseResData.class);
		
		return reverseResData;
		
	}
}
