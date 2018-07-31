package com.grapefs.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.grapefs.demo.constants.Constants;
import com.grapefs.demo.entity.RefundReqData;
import com.grapefs.demo.entity.RefundResData;
import com.grapefs.demo.utils.HttpUtil;
import com.grapefs.demo.utils.MD5Util;

/**
 * 
 * 类描述：申请退款
 * 
 * @author lxl
 * @since 1.0, 2016年12月27日
 */
public class RefundService {
	private static Logger logger = LoggerFactory.getLogger(RefundService.class);

	public static void main(String[] args) {
		RefundService refundService = new RefundService();
		String refundOrderNo = "201609051530";
		String origOrderNo = "201609051523";
		String amount = "0.01";
		try {
			RefundReqData refundReqData = new RefundReqData(Constants.MERCHANT_NO, Constants.MERCHANT_SECRET, refundOrderNo, origOrderNo, amount);
			RefundResData refundResData = refundService.reqPay(refundReqData, Constants.MERCHANT_SECRET);
			System.out.println(refundResData);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}

	public RefundResData reqPay(RefundReqData refundReqData, String md5Key) throws Exception {
		String url = Constants.API_BASE_URL + "/toRefund";
		logger.info("将对象转换为json");
		JSONObject jsonObject = (JSONObject) JSON.toJSON(refundReqData);
		String request = jsonObject.toString();
		logger.info("发送退款POST请求,请求参数" + request);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(url, request);
		
		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		logger.info("退款返回结果" + resultObj);
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
		RefundResData refundResData = JSONObject.toJavaObject(resultObj, RefundResData.class);
		
		return refundResData;
		
	}
}
