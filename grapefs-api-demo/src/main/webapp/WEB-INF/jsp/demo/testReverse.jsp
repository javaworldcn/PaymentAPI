<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include	file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<title>撤销测试</title>
<div class="pageContent">
	<form:form method="post" modelAttribute="model"
		action="${pageContext.request.contextPath}/demo/reverse"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="157">
		<input type="hidden" name="signType" value="MD5"/>
			<table>
				<tr>
					<td>请求地址</td>
					<td><input type="text" name="apiUrl"  value="http://dev.grapefs.com/gpay/reverse"  size="50"/></td>
				</tr>
				<tr>
					<td>平台商户号</td>
					<td><input type="text" name="merchantNo" size="50" /></td>
				</tr>
				<tr>
					<td>退款订单号</td>
					<td><input type="text" name="orderNo" size="50"/></td>
				</tr>
				<tr>
					<td>原交易订单号</td>
					<td><input type="text"  name="origOrderNo" size="50" /></td>
				</tr>
				<tr>
					<td>商户密钥</td>
					<td><input type="text" name="md5Key" " size="50" /></td>
				</tr>
				
				
			</table>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form:form>
</div>
