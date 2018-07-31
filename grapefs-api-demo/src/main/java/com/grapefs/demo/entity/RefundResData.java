package com.grapefs.demo.entity;

/**
 * 退款提交Post数据给到API之后，API会返回JSON格式的数据，这个类用来装这些数据
 */
public class RefundResData {

	private String respCode = "";
	private String respMsg = "";
	private String refundTime = "";
	private String tradeNo = "";
	private String orderNo = "";
	private String channelNo = "";
	private String settleDate;
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
	


	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
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
	
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	@Override
	public String toString() {
		return "RefundResData [respCode=" + respCode + ", respMsg=" + respMsg + ", refundTime=" + refundTime
				+ ", tradeNo=" + tradeNo + ", orderNo=" + orderNo + ", channelNo=" + channelNo + ", settleDate="
				+ settleDate + ", randomStr=" + randomStr + ", signType=" + signType + ", sign=" + sign + "]";
	}

}
