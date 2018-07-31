<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<title>修改商户结算状态</title>
<div class="pageContent">
	<form method="post"
		action="${pageContext.request.contextPath}/business/modifyMerchatSettStatus"
		<div class="pageFormContent" layoutH="157">
			<table>
				<tr>
					<td>1、修改商户结算状态API</td>
					<td><input type="text" name="modifyMerchatSettStatusUrl" value="http://localhost:8080/mgmt/modifyMerchatSettStatus"  size="50"/></td>
				</tr>
				<tr>
					<td>2、代理编号</td>
					<td><input type="text" name="agentNo" size="50" /></td>
				</tr>
				<tr>
					<td>3、代理密钥</td>
					<td><input type="text" name="agentKey" size="50" /></td>
				</tr> 
				
				<tr>
					<td>4、商户列表</td>
					<td><input type="text" name="merchantNoList" size="50" /></td>
				</tr>
				<tr>
					<td>5、结算状态</td>
					<td>
						<select name="settStatus"> 
							<option value ="100">恢复结算</option>
							<option value ="101">展缓结算</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>6、备注</td>
					<td><input type="text" name="remark" size="50" /></td>
				</tr>
		</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">修改商户结算状态</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
<div>
	<p>1、修改商户结算状态API</p>
	<p>2、agentNo:代理编号</p>
	<p>3、agentKey:代理密钥</p>
	<p>4、merchantNoList:商户列表以，分割</p>
	<p>5、settStatus:结算状态100-恢复结算，101-暂缓结算</p>
	<p>5、remark:备注</p>
</div>