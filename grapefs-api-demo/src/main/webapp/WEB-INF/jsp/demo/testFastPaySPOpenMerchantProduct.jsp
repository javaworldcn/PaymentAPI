<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/demo/fastPaySPOpenMerchFastProduct">
		<div>（同人） 开通产品</div>
		<div class="pageFormContent" layoutH="157">
			<table>
				<tr>
					<td>1、商户开通产品api地址</td>
					<td><input type="text" name="openMerchantProductUrl" value="http://localhost:8080/mgmt/openMerchFastProduct"  size="50"/></td>
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
					<td>5、merchantProduct</td>
					<td><textarea name="merchantProduct" cols ="80" rows = "16">
{
	"wxProduct":{
	    "onlineRate":"0.003",
	    "d0SettleRate":"0.005",
	    "tradeCode":"50554"
	},
	"alipayProduct":{
        "onlineRate":"0.003",
        "d0SettleRate":"0.005",
        "bizCategory":"2015050700000000"
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