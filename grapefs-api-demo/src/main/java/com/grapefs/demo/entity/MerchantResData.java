package com.grapefs.demo.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * H5扫码支付提交Post数据给到API之后，API会返回JSON格式的数据，这个类用来装这些数据
 */
/**
 * @author linjie
 * @date 2016年6月28日
 * @version 1.0
 *
 */
public class MerchantResData {

	private String respCode = "";
	private String respMsg = "";
	private String merchantNo = "";
	private String merchantKey = "";
	private String randomStr = "";
	private String signType = "";
	private String sign = "";

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}


	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
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

	@Override
	public String toString() {
		return "MerchantResData [respCode=" + respCode + ", respMsg=" + respMsg + ", merchantNo=" + merchantNo
				+ ", merchantKey=" + merchantKey + ", randomStr=" + randomStr + ", signType=" + signType + ", sign="
				+ sign + "]";
	}



}
