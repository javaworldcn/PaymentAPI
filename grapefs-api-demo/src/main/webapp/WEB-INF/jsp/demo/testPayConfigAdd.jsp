<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form method="post"
		action="${pageContext.request.contextPath}/demo/payConfigAdd"
		<div class="pageFormContent" layoutH="157">
			<table>
				<tr>
					<td>1、服务商子商户开发配置新增API</td>
					<td><input type="text" name="payConfigAddUrl" value="http://localhost:8080/mgmt/payConfigAdd"  size="50"/></td>
				</tr>
				<tr>
					<td>2、商户编号</td>
					<td><input type="text" name="merchantNo" size="50" /></td>
				</tr>
				<tr>
					<td>3、商户密钥</td>
					<td><input type="text" name="merchantKey" size="50" /></td>
				</tr> 
				<tr>
					<td>4、授权目录</td>
					<td><input type="text" name="jsapiPath" size="50" /></td>
				</tr>
				<tr>
					<td>5、绑定公众号</td>
					<td><input type="text" name="subAppid" size="50" /></td>
				</tr>
				<tr>
					<td>6、订阅公众号</td>
					<td><input type="text" name="subscribeAppid" size="50" /></td>
				</tr>
				<tr>
					<td>6.1、订阅关联appId</td>
					<td><input type="text" name="subscribeRelatedAppid" size="50" /></td>
				</tr>
				<tr>
					<td>7、支付宝生活号</td>
					<td><input type="text" name="aliLogonid" size="50" /></td>
				</tr>
		</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">调用服务商子商户开发新增配置</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
<div>
	<p>1、服务商子商户开发配置新增API</p>
	<p>2、merchantNo:商户编号</p>
	<p>3、merchantKey:商户密钥</p>
	<p>4、configType:配置类型</p>
	<p>5、configValue:配置值</p>
</div>