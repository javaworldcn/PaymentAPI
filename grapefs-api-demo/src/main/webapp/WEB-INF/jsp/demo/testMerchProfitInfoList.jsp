<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form:form method="post" modelAttribute="model"
		action="${pageContext.request.contextPath}/demo/QueryMerchProfitInfo"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="157">
			<table>
				<tr>
					<td>1、请求地址</td>
					<td><input type="text" name="apiUrl"
						value="http://localhost:8080/api/queryMerchProfitInfo"
						size="50" /></td>
				</tr>
				<tr>
					<td>2、代理商编号</td>
					<td><input type="text" name="agentNo" size="50" /></td>
				</tr>
				<tr>
					<td>3、代理商密钥</td>
					<td><input type="text" name="Md5Key" size="50" /></td>
				</tr>
				<tr>
					<td>4、结算日期</td>
					<td><input type="text" name="settDate" /></td>

				</tr>
				<tr>
					<td>5、结算类型</td>
					<td>
						<select name="settMode">
							<option value="1">T1</option>
							<option value="2">D0</option>
						</select>
					</td>

				</tr>
			</table>

		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">查询</button>
						</div>
					</div></li>
				<!-- <li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li> -->
			</ul>
		</div>

	</form:form>
</div>
<div>
	<p>1、API地址：默认80端口，如非80端口则需输入对应的端口号；</p>
	<p>2、代理商编号：在运营系统——》代理商管理——》代理商信息管理中查询、获取；</p>
	<p>3、代理商密钥：在运营系统——》代理商管理——》代理商信息管理——》查看中查询、获取</p>
	<p>4、结算日期：格式为2016-11-22</p>
</div>

