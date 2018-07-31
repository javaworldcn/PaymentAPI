<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form method="post"
		action="${pageContext.request.contextPath}/demo/queryMerchantInfo"
		<div class="pageFormContent" layoutH="157">
			<table>
				<tr>
					<td>1、商户查询API地址</td>
					<td><input type="text" name="queryMerchantUrl" value="http://localhost:8080/mgmt/queryMerchant"  size="50"/></td>
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
		</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">商户查询</button>
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
</div>