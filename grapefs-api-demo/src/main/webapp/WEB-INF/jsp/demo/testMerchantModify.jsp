<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form method="post"
		action="${pageContext.request.contextPath}/demo/modifyMerchant"
		<div class="pageFormContent" layoutH="157">
			<table>
				<tr>
					<td>1、商户修改API地址</td>
					<td><input type="text" name="merchantModifyUrl" value="http://localhost:8080/mgmt/modifyMerchant"  size="50"/></td>
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
					<td>4、merchantNo</td>
					<td><input type="text" name="merchantNo" value=""  size="50" /></td>
				</tr>
				<tr>
					<td>5、merchantInfo</td>
					<td><textarea name="merchantInfo" cols ="80" rows = "30">
{
    "fullName":"韬乐园大中华国际金融中心店",
    "shortName":"韬乐园",
    "servicePhone":"95188",
    "businessLicense":"320483000067847",
    "businessLicenseType":"NATIONAL_LEGAL",
    "contactInfo":{
        "contactName":"周瑾",
        "contactPhone":"13267141053",
        "contactPersonType":"LEGAL_PERSON",
        "contactIdCard":"362202199403308113",
        "contactEmail":"183400826@qq.com"
    },
    "bankCardInfo":{
        "bankAccountName":"周瑾",
        "bankAccountNo":"6212264000062521428",
        "bankAccountType":"1",
        "bankAccountLineNo":"102100000089",
        "bankAccountAddress":"中国工商银行莲花北支行",
        "idCard":"362202199403308113"
    },
    "addressInfo":{
	"province":"广东省",
	"city":"深圳市",
	"district":"罗湖区",
	"address":"大剧院嘉宾花园A座27H"
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
	<p>3、merchantNo:银行商户入驻成功返回的唯一商户编号</p>
	<p>5、merchantInfo:需要修改的商户信息</p>
</div>