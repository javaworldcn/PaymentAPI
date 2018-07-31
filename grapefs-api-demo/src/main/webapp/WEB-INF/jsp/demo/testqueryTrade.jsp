<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<title>交易查询</title>
<div class="pageContent">
	<form:form method="post" modelAttribute="model"
		action="${pageContext.request.contextPath}/demo/queryTrade"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="157">
			<input type="hidden" name="signType" value="MD5" />
			<table>
				<tr>
					<td>1、API地址</td>
					<td><input type="text" name="apiUrl"
						value="http://dev.grapefs.com/gpay/orderQuery" size="50" /></td>
				</tr>
				<tr>
					<td>2、平台商户号</td>
					<td><input type="text" name="merchantNo" size="50" /></td>
				</tr>
				<tr>
					<td>3、商户密钥</td>
					<td><input type="text" name="md5Key" size="50" /></td>
				</tr>
				<tr>
					<td>4、交易订单号</td>
					<td><input type="text" name="orderNo" size="50" /></td>
				</tr>
			</table>

		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">点击这里发起查询</button>
						</div>
					</div></li>
			</ul>
		</div>

	</form:form>
</div>
<div>
	<p>1、API地址：默认80端口，如非80端口则需输入对应的端口号；</p>
	<p>2、平台商户号：在运营系统——》商户管理——》商户信息管理中查询、获取；</p>
	<p>3、商户密钥：在运营系统——》商户管理——》商户信息管理——》查看中查询、获取</p>
	<p>4、交易订单号：要查询的订单号</p>
</div>
