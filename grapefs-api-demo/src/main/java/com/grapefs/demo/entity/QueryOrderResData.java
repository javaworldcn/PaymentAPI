package com.grapefs.demo.entity;

/**
 * 交易查询提交Post数据给到API之后，API会返回JSON格式的数据，这个类用来装这些数据
 */
public class QueryOrderResData {

	private String respCode = "";
	private String respMsg = "";
	private String orderNo = "";
	private String tradeNo = "";
	private String channelNo = "";
	private String tradeType = "";
	private String merchantNo = "";
	private String amount = "";
	private String refundAmount = "";
	private String payMethod = "";
	private String tradeStatus = "";
	private String buyerId = "";
	private String bankType = "";
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

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
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
	
	public String getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
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

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}



	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	@Override
	public String toString() {
		return "QueryOrderResData [respCode=" + respCode + ", respMsg=" + respMsg + ", orderNo=" + orderNo
				+ ", tradeNo=" + tradeNo + ", channelNo=" + channelNo + ", tradeType=" + tradeType + ", merchantNo="
				+ merchantNo + ", amount=" + amount + ", refundAmount=" + refundAmount + ", payMethod=" + payMethod
				+ ", tradeStatus=" + tradeStatus + ", buyerId=" + buyerId + ", bankType=" + bankType + ", successTime="
				+ successTime + ", randomStr=" + randomStr + ", signType=" + signType + ", sign=" + sign + "]";
	}
}
