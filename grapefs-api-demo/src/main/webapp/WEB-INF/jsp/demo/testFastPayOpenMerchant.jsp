<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/demo/createMerchBasicInfo">
		<div class="pageFormContent" layoutH="157">
			<table>
				<tr>
					<td>1、快捷 (无卡) 商户API地址</td>
					<td><input type="text" name="reportMerchantUrl" value="http://localhost:8080/mgmt/createMerchBasicInfo"  size="50"/></td>
				</tr>
				<tr>
					<td>2、agentKey</td>
					<td><input type="text" name="agentKey" size="50" /></td>
				</tr>
				<tr>
					<td>3、agentNo</td>
					<td><input type="text" name="agentNo" value=""  size="50" /></td>
				</tr>
				<tr>
					<td>4、merchantInfo</td>
					<td><textarea name="merchantInfo" cols ="80" rows = "30">
{
	"fullName": "GodLike",
	"shortName": "shoot",
	"servicePhone": "13538291768",
	"businessLicense": "1",
	"businessLicenseType": "NATIONAL_LEGAL",
	"contactInfo": {
		"contactName": "LESHUA",
		"contactPhone": "13531291455",
		"contactPersonType": "LEGAL_PERSON",
		"contactIdCard": "430602197208076577",
		"contactEmail": "yymoth@163.com"
	},
	"addressInfo": {
		"province": "广东省",
		"city": "深圳市",
		"district": "龙岗区",
		"address": "布吉"
	}
}


					</textarea>
					</td>
				</tr>  
		</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">调用商户报备接口</button>
						</div>
</div>
</li>
</ul>
</div>
</form>
</div>
<div>
	<p>1、报备商户API地址</p>
	<p>2、agentKey:我们在银行系统的代理商唯一key，用于参数加密解密</p>
	<p>3、agentNo:我们在银行系统的代理商唯一编号</p>
	<p>4、merchantInfo:商户基本信息</p>
</div>