package com.grapefs.demo.entity;


/**
 * 条码支付提交Post数据给到API之后，API会返回JSON格式的数据，这个类用来装这些数据
 */
/**
 * @author linjie
 * @date 2016年6月28日
 * @version 1.0
 *
 */
public class MicroPayResData {

	private String respCode = "";
	private String respMsg = "";
	private String orderNo = "";
	private String tradeNo = "";
	private String buyerId = "";
	private String bankType = "";
	private String channelNo = "";
	private String successTime = "";
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

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
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

	public String getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}

	@Override
	public String toString() {
		return "MicroPayResData [respCode=" + respCode + ", respMsg=" + respMsg + ", orderNo=" + orderNo + ", tradeNo="
				+ tradeNo + ", buyerId=" + buyerId + ", bankType=" + bankType + ", channelNo=" + channelNo
				+ ", successTime=" + successTime + ", randomStr=" + randomStr + ", signType=" + signType + ", sign="
				+ sign + "]";
	}
}
