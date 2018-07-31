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
public class H5PayResData {

	private String respCode = "";
	private String respMsg = "";
	private String orderNo = "";
	private JSONObject payInfo = null;
	private String channelNo = "";
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

	public JSONObject getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(JSONObject payInfo) {
		this.payInfo = payInfo;
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

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	@Override
	public String toString() {
		return "H5PayResData [respCode=" + respCode + ", respMsg=" + respMsg + ", orderNo=" + orderNo + ", payInfo="
				+ payInfo + ", channelNo=" + channelNo + ",randomStr=" + randomStr + ", signType=" + signType + ", sign=" + sign + "]";
	}

}
