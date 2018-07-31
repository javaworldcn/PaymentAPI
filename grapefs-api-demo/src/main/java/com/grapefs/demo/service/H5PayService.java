package com.grapefs.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.grapefs.demo.constants.Constants;
import com.grapefs.demo.entity.H5PayReqData;
import com.grapefs.demo.entity.H5PayResData;
import com.grapefs.demo.utils.HttpUtil;
import com.grapefs.demo.utils.MD5Util;

/**
 * 
 * 类描述：公众号支付
 * 
 * @author lxl
 * @since 1.0, 2016年12月27日
 */
public class H5PayService {
	private static Logger logger = LoggerFactory.getLogger(H5PayService.class);

	public static void main(String[] args) {
		H5PayService h5PayService = new H5PayService();
		String payMethod = "WXPAY";
		String orderNo = "201609021140";
		String amount = "0.01";
		String subject = "测试商户";
		String subAppid = "";
		String limitPay="no_credit";
		String notifyUrl = "https://www.grapefs.com";
		String openId = "oolktwJp9lLA_Nm1AARcY34UPraI";
		String buyerId = "oolktwJp9lLA_Nm1AARcY34UPraI";
		String userId = null;
		if ("WXPAY".equals(payMethod)) {
			userId = openId;
		}
		if ("ALIPAY".equals(payMethod)) {
			userId = buyerId;
		}
		String expireTime = "10";
		String goodsTag = null;
		try {
			H5PayReqData h5PayReqData = new H5PayReqData(Constants.MERCHANT_NO, Constants.MERCHANT_SECRET, payMethod, orderNo, amount,
					subject, subAppid, limitPay, notifyUrl, userId, expireTime,goodsTag);
			H5PayResData h5PayResData = h5PayService.reqPay(h5PayReqData, Constants.MERCHANT_SECRET);
			System.out.println(h5PayResData);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}

	public H5PayResData reqPay(H5PayReqData h5PayReqData, String md5Key) throws Exception {
		String url = Constants.API_BASE_URL + "/pay/H5Pay";
		logger.info("将对象转换为json");
		JSONObject jsonObject = (JSONObject) JSON.toJSON(h5PayReqData);
		String request = jsonObject.toString();
		logger.info("发送H5扫码支付POST请求,请求参数" + request);
		HttpUtil httpUtil = new HttpUtil();
		String response = httpUtil.post(url, request);
		
		if (response == null || "".equals(response)) {
			throw new RuntimeException("返回参数为空");
		}
		JSONObject resultObj = JSONObject.parseObject(response);
		logger.info("H5生扫码支付返回结果" + resultObj);
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
		H5PayResData h5PayResData = JSONObject.toJavaObject(resultObj, H5PayResData.class);
		
		return h5PayResData;
		
	}
}
