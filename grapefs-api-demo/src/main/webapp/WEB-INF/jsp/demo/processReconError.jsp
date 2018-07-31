<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include	file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<title>对账差错处理</title>
<div class="pageContent">
	<form:form method="post" modelAttribute="model" 
		action="${pageContext.request.contextPath}/business/processReconErrorNow"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="157">
		<input type="hidden" name="signType" value="MD5"/>
			<table>
				<tr>
					<td>1、API地址</td>
					<td><input type="text" name="serverUrl" value="http://localhost:8080"  size="50"/></td>
					<td><input type="text" name="interfaceAddr" value="/mgmt/processReconError"  size="30"/></td>
				</tr>
				<tr>
					<td>2、差错ID</td>
					<td><input type="text" name="reconErrorId"  size="50" /></td>
				</tr>
				<tr>
					<td>3、处理方式</td>
					<td>
						<select name="processType">
							<option value="1">无须处理</option>
							<option value="2">调帐处理</option>
						</select>
					</td>
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
							<button type="submit">点击这里处理对账差错</button>
						</div>
					</div>
				</li>
								<!-- <li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li> -->
			</ul>
		</div>
		<br><br><br>
		<div>
				<table>	<tr>
					<td>1、对账差错查询地址</td>
					<td><input type="text" name="reconLookAddress" id="reconLookAddress" value="http://localhost:8080/mgmt/findReconErrorRecord"  size="50"/></td>
				</tr>
				<tr></tr>	<tr></tr><tr></tr><tr></tr>
				<tr><td>
				
							<button type="button" onclick="lookReconError()">点击这里查看对账差错</button>
					
							</td>
		</tr>
				</table>	
		</div>
	</form:form>
</div>
<br>
<div>
	<p>1、API地址：默认80端口，如非80端口则需输入对应的端口号；</p>
	<p>2、对账日期格式示例：20170309；</p>
</div>
<script type="text/javascript">
function lookReconError(){
	var url = document.getElementById("reconLookAddress").value; 
	window.open(url);  
}
</script>