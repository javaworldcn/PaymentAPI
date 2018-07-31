package com.grapefs.demo.entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.grapefs.demo.utils.ApiUtil;
import com.grapefs.demo.utils.MD5Util;

/**
 * 撤销API需要提交的数据
 */
public class ReverseReqData {

	// 每个字段具体的意思请查看API文档
	private String merchantNo = "";
	private String orderNo = "";
	private String origOrderNo = "";
	private String randomStr = "";
	private String signType = "";
	private String sign = "";

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrigOrderNo() {
		return origOrderNo;
	}

	public void setOrigOrderNo(String origOrderNo) {
		this.origOrderNo = origOrderNo;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getRandomStr() {
		return randomStr;
	}

	public void setRandomStr(String randomStr) {
		this.randomStr = randomStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public ReverseReqData(String merchantNo, String md5Key, String orderNo, String origOrderNo) throws Exception {
		this.merchantNo = merchantNo;
		this.orderNo = orderNo;
		this.origOrderNo = origOrderNo;
		this.randomStr = ApiUtil.genRandomStr();
		this.signType = "MD5";
		this.sign = MD5Util.getSign(toMap(), md5Key);
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
		return "ReverseReqData [merchantNo=" + merchantNo + ", orderNo=" + orderNo + ", origOrderNo=" + origOrderNo + ", randomStr="
				+ randomStr + ", signType=" + signType + ", sign=" + sign + "]";
	}

}
