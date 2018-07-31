<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form:form method="post" modelAttribute="model"
		action="${pageContext.request.contextPath}/demo/QueryYLSinglePay"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="157">
			<table>
				<tr>
					<td>查询银联单笔代付结果测试</td>
				</tr>
				<tr>
					<td>1、单笔代付订单号</td>
					<td><input type="text" name="OrderNo" size="50" /></td>
				</tr>
				<tr>
					<td>2、代付日期</td>
					<td><input type="text" name="OrderDate" size="50" /></td>
				</tr>
				<tr>
					<td>3、代付商户号</td>
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
	<p>1、代付日期格式为：20161229</p>
</div>
