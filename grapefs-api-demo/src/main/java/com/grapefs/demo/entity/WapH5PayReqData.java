package com.grapefs.demo.entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.grapefs.demo.utils.ApiUtil;
import com.grapefs.demo.utils.MD5Util;

/**
 * 请求WapH5支付API需要提交的数据
 */
public class WapH5PayReqData {

	// 每个字段具体的意思请查看API文档
	private String merchantNo = "";
	private String payMethod = "";
	private String orderNo = "";
	private String amount = "";
	private String subject = "";
	private String wapUrl = "";
	private String wapName = "";
	private String ipAddress = "";
	//private String subAppid = "";
	private String limitPay = "";
	private String notifyUrl = "";
	//private String openId = "";
	private String expireTime = "";
	private String randomStr = "";
	private String signType = "";
	private String sign = "";
	private String goodsTag = "";
	private String transOnlineRate = "";
	

	public WapH5PayReqData(String merchantNo,String md5Key, String payMethod, String orderNo, String amount, String subject, String wapUrl,
			String wapName, String ipAddress, String limitPay, String notifyUrl, String expireTime,
			 String goodsTag,String transOnlineRate) {
		super();
		this.merchantNo = merchantNo;
		this.payMethod = payMethod;
		this.orderNo = orderNo;
		this.amount = amount;
		this.subject = subject;
		this.wapUrl = wapUrl;
		this.wapName = wapName;
		this.ipAddress = ipAddress;
		this.limitPay = limitPay;
		this.notifyUrl = notifyUrl;
		this.expireTime = expireTime;
		this.randomStr = ApiUtil.genRandomStr();
		this.signType = "MD5";
		this.sign = MD5Util.getSign(toMap(), md5Key);
		this.goodsTag = goodsTag;
		this.transOnlineRate = transOnlineRate;
	}

	public String getTransOnlineRate() {
		return transOnlineRate;
	}

	public void setTransOnlineRate(String transOnlineRate) {
		this.transOnlineRate = transOnlineRate;
	}

	public String getMerchantNo() {
		return merchantNo;
	}


	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


	public String getPayMethod() {
		return payMethod;
	}


	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getWapUrl() {
		return wapUrl;
	}


	public void setWapUrl(String wapUrl) {
		this.wapUrl = wapUrl;
	}


	public String getWapName() {
		return wapName;
	}


	public void setWapName(String wapName) {
		this.wapName = wapName;
	}


	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getLimitPay() {
		return limitPay;
	}


	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}


	public String getNotifyUrl() {
		return notifyUrl;
	}


	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}


	public String getExpireTime() {
		return expireTime;
	}


	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}


	public String getRandomStr() {
		return randomStr;
	}


	public void setRandomStr(String randomStr) {
		this.randomStr = randomStr;
	}


	public String getSignType() {
		return signType;
	}


	public void setSignType(String signType) {
		this.signType = signType;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}


	public String getGoodsTag() {
		return goodsTag;
	}


	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}


	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object obj;
			try {
				obj = field.get(this);
				if (obj != null) {
					map.put(field.getName(), (String) obj);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

}
