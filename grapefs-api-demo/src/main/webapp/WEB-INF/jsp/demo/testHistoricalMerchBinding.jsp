<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form method="post"
		  action="${pageContext.request.contextPath}/demo/historicalMerchBinding">
		<div class="pageFormContent" layoutH="157">
			<table>
				<tr>
					<td>1、存量商户报备API地址</td>
					<td><input type="text" name="apiUrl" value="http://localhost:8080/mgmt/historicalMerchBinding"  size="50"/></td>
				</tr>
				<tr>
					<td>2、agentKey</td>
					<td><input type="text" name="agentKey" size="50" /></td>
				</tr>
				<tr>
					<td>3、agentNo</td>
					<td><input type="text" name="agentNo" value=""  size="50" /></td>
				</tr>
				<tr>
					<td>4、merchBindInfo</td>
					<td><textarea name="merchBindInfo" cols ="90" rows = "30">
[
	{"merchantNo":"201704060007128", "bizCategory":"204", "channelId":"1000111"},
	{"merchantNo":"201704060007128", "bizCategory":"204", "channelId":"1000111"},
	{"merchantNo":"201704060007128", "bizCategory":"204", "channelId":"1000111"}
 ]
					</textarea>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
					<div class="buttonContent">
						<button type="submit">调用存量商户报备接口</button>
					</div>
				</div>
				</li>
			</ul>
		</div>
	</form>
</div>
<div>
	<p>1、报备商户API地址</p>
	<p>2、agentKey:我们在银行系统的代理商唯一key，用于参数加密解密</p>
	<p>3、agentNo:我们在银行系统的代理商唯一编号</p>
	<p>4、merchBindInfo:商户绑定信息</p>
</div>