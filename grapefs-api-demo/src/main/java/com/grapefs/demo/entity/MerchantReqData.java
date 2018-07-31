package com.grapefs.demo.entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.grapefs.demo.utils.ApiUtil;
import com.grapefs.demo.utils.MD5Util;

/**
 * 请求H5扫码支付API需要提交的数据
 */
public class MerchantReqData {

	// 每个字段具体的意思请查看API文档
	private String agentNo = "";
	private String fullName = "";
	private String shortName = "";
	private String legalPerson = "";
	private String bizCategory = "";
	private String cardNo = "";
	private String mobileNo = "";
	private String province = "";
	private String city = "";
	private String address = "";
	private String bankName = "";
	private String bankAccountName = "";
	private String bankAccountNo = "";
	private String bankAccountType = "";
	private String bankChannelNo = "";
	private String bankCardNo = "";
	private String aliPayTradeRate = "";
	private String wxPayTradeRate = "";
	private String d0Rate = "";
	private String remitFee = "";
	private String randomStr = "";
	private String signType = "";
	private String sign = "";

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getBizCategory() {
		return bizCategory;
	}

	public void setBizCategory(String bizCategory) {
		this.bizCategory = bizCategory;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getBankChannelNo() {
		return bankChannelNo;
	}

	public void setBankChannelNo(String bankChannelNo) {
		this.bankChannelNo = bankChannelNo;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getAliPayTradeRate() {
		return aliPayTradeRate;
	}

	public void setAliPayTradeRate(String aliPayTradeRate) {
		this.aliPayTradeRate = aliPayTradeRate;
	}

	public String getWxPayTradeRate() {
		return wxPayTradeRate;
	}

	public void setWxPayTradeRate(String wxPayTradeRate) {
		this.wxPayTradeRate = wxPayTradeRate;
	}

	public String getD0Rate() {
		return d0Rate;
	}

	public void setD0Rate(String d0Rate) {
		this.d0Rate = d0Rate;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRemitFee() {
		return remitFee;
	}

	public void setRemitFee(String remitFee) {
		this.remitFee = remitFee;
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
		return "MerchantReqData [agentNo=" + agentNo + ", fullName=" + fullName + ", shortName=" + shortName
				+ ", legalPerson=" + legalPerson + ", bizCategory=" + bizCategory + ", cardNo=" + cardNo + ", mobileNo="
				+ mobileNo + ", province=" + province + ", city=" + city + ", address=" + address + ", bankName="
				+ bankName + ", bankAccountName=" + bankAccountName + ", bankAccountNo=" + bankAccountNo
				+ ", bankAccountType=" + bankAccountType + ", bankChannelNo=" + bankChannelNo + ", bankCardNo="
				+ bankCardNo + ", aliPayTradeRate=" + aliPayTradeRate + ", wxPayTradeRate=" + wxPayTradeRate
				+ ", d0Rate=" + d0Rate + ", remitFee=" + remitFee + ", randomStr=" + randomStr + ", signType="
				+ signType + ", sign=" + sign + "]";
	}

}
