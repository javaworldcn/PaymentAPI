<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include	file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<title>按批次更新代理商打款状态</title>
<div class="pageContent">
	<form:form method="post" modelAttribute="model" 
		action="${pageContext.request.contextPath}/business/builderClaerSettRecord"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="157">
		<input type="hidden" name="signType" value="MD5"/>
			<table>
				<tr>
					<td>1、API地址</td>
					<td><input type="text" name="serverUrl" value="http://localhost:8080"  size="50"/></td>
					<td><input type="text" name="interfaceAddr" value="/mgmt/builderClaerSettRecord"  size="30"/></td>
				</tr>
				
			    <tr>
					<td>2、结算开始日期</td>
					<td><input type="text" name="beginDate"   size="50" /></td>
				</tr>
			   <tr>
					<td>3、结算结束日期</td>
					<td><input type="text" name="endDate"   size="50" /></td>
				</tr>
				<tr>
					<td>4、密钥</td>
					<td><input type="text" name="md5Key" size="50" /></td>
				</tr>
		</table>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">生成</button>
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
	<p>2、结算批次号，如有多个，使用英文逗号分隔；</p>
	</div>
