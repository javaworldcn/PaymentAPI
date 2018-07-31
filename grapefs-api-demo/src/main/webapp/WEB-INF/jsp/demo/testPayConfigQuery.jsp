<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form method="post"
		action="${pageContext.request.contextPath}/demo/payConfigQuery"
		<div class="pageFormContent" layoutH="157">
			<table>
				<tr>
					<td>1、服务商子商户开发配置新增API</td>
					<td><input type="text" name="payConfigQueryUrl" value="http://localhost:8080/mgmt/payConfigQuery"  size="50"/></td>
				</tr>
				<tr>
					<td>2、商户编号</td>
					<td><input type="text" name="merchantNo" size="50" /></td>
				</tr>
				<tr>
					<td>3、商户密钥</td>
					<td><input type="text" name="merchantKey" size="50" /></td>
				</tr> 
		</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">调用服务商子商户开发查询配置</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
<div>
	<p>1、服务商子商户开发配置查询API</p>
	<p>2、merchantNo:商户编号</p>
</div>