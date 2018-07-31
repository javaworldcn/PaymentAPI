package com.grapefs.demo.entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.grapefs.demo.utils.ApiUtil;
import com.grapefs.demo.utils.MD5Util;

/**
 * 请求扫码支付API需要提交的数据
 */
public class NativePayReqData{

    //每个字段具体的意思请查看API文档
	private String merchantNo = "";
	private String payMethod = "";
	private String orderNo = "";
	private String amount = "";
	private String subject = "";
	private String subAppid = "";
	private String notifyUrl = "";
	private String limitPay = "";
	private String expireTime = "";
	private String goodsTag = "";
	private String randomStr = "";
	private String signType = "";
	private String sign = "";
	
	public NativePayReqData(String merchantNo, String md5Key, String payMethod, String orderNo, String amount, String subject,
			String subAppid, String notifyUrl, String limitPay, String expireTime,String goodsTag) throws Exception {
		super();
		this.merchantNo = merchantNo;
		this.payMethod = payMethod;
		this.orderNo = orderNo;
		this.amount = amount;
		this.subject = subject;
		this.subAppid = subAppid;
		this.notifyUrl = notifyUrl;
		this.limitPay = limitPay;
		this.expireTime = expireTime;
		this.goodsTag = goodsTag;
		this.randomStr = ApiUtil.genRandomStr();
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

	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	public String getLimitPay() {
		return limitPay;
	}
	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
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

	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
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
		return "NativePayReqData [merchantNo=" + merchantNo + ", payMethod=" + payMethod + ", orderNo=" + orderNo
				+ ", amount=" + amount + ", subject=" + subject + ", subAppid=" + subAppid + ", notifyUrl=" + notifyUrl
				+ ", limitPay=" + limitPay + ", expireTime=" + expireTime + ", goodsTag=" + goodsTag + ", randomStr="
				+ randomStr + ", signType=" + signType + ", sign=" + sign + "]";
	}
}
