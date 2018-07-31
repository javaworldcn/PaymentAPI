<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<title>银联代笔代付测试</title>
<div class="pageContent">
	<form:form method="post" modelAttribute="model"
		action="${pageContext.request.contextPath}/demo/YLSinglePay"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="157">
			<table>
				<tr>
					<td>银联单笔代付测试</td>
				</tr>
				<tr>
					<td>1、收款账户</td>
					<td><input type="text" name="accountName" size="50" /></td>
				</tr>
				<tr>
					<td>2、收款账号</td>
					<td><input type="text" name="accountNo" size="50" /></td>
				</tr>

				<tr>
					<td>3、代付金额(分)</td>
					<td><input type="text" name="amount" class="number required"
						size="50" /></td>
				</tr>
				<tr>
					<td>4、打款摘要</td>
					<td><input type="text" name="remark" size="50" /></td>
				</tr>
				<tr>
					<td>5、代付商户号</td>
					<td><input type="text" name="merchantCode" size="50" /></td>

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
	<p>1、此渠道仅支持对私账户，且账号长度最多为19位；</p>
</div>
