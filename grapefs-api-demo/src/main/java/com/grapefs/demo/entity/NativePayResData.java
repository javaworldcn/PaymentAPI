package com.grapefs.demo.entity;

/**
 * 扫码支付提交Post数据给到API之后，API会返回JSON格式的数据，这个类用来装这些数据
 */
public class NativePayResData {

	private String respCode = "";
	private String respMsg = "";
	private String orderNo = "";
	private String codeUrl = "";
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
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
		return "NativePayResData [respCode=" + respCode + ", respMsg=" + respMsg + ", orderNo=" + orderNo + ", codeUrl=" + codeUrl
				+ ", randomStr=" + randomStr + ", signType=" + signType + ", sign=" + sign + "]";
	}

}
