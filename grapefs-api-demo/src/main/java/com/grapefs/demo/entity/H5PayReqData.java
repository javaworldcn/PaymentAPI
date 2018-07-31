package com.grapefs.demo.entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.grapefs.demo.utils.ApiUtil;
import com.grapefs.demo.utils.MD5Util;

/**
 * 请求H5扫码支付API需要提交的数据
 */
public class H5PayReqData {

	// 每个字段具体的意思请查看API文档
	private String merchantNo = "";
	private String payMethod = "";
	private String orderNo = "";
	private String amount = "";
	private String subject = "";
	private String subAppid = "";
	private String limitPay = "";
	private String notifyUrl = "";
	private String openId = "";
	private String expireTime = "";
	private String randomStr = "";
	private String signType = "";
	private String sign = "";
	private String goodsTag = "";
	
	public H5PayReqData(String merchantNo, String md5Key, String payMethod, String orderNo, String amount, String subject, String subAppid,
			String limitPay, String notifyUrl, String openId, String expireTime,String goodsTag) throws Exception {
		super();
		this.merchantNo = merchantNo;
		this.payMethod = payMethod;
		this.orderNo = orderNo;
		this.amount = amount;
		this.subject = subject;
		this.subAppid = subAppid;
		this.limitPay = limitPay;
		this.notifyUrl = notifyUrl;
		this.openId = openId;
		this.expireTime = expireTime;
		this.randomStr = ApiUtil.genRandomStr();
		this.goodsTag = goodsTag;
		this.signType = "MD5";
		this.sign = MD5Util.getSign(toMap(), md5Key);
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

	public String getSubAppid() {
		return subAppid;
	}

	public void setSubAppid(String subAppid) {
		this.subAppid = subAppid;
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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


	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
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

	@Override
	public String toString() {
		return "H5PayReqData [merchantNo=" + merchantNo + ", payMethod=" + payMethod + ", orderNo=" + orderNo + ", amount=" + amount
				+ ", subject=" + subject + ", subAppid=" + subAppid + ", limitPay=" + limitPay + ", notifyUrl=" + notifyUrl + ", openId="
				+ openId + ", expireTime=" + expireTime + ", randomStr=" + randomStr + ", signType=" + signType + ", sign=" + sign + "]";
	}

}
