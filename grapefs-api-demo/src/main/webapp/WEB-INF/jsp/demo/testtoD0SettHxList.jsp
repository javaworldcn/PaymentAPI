<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form:form method="post" modelAttribute="model"
		action="${pageContext.request.contextPath}/demo/D0SettHxList"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="157">
			<input type="hidden" name="signType" value="MD5" />
			<table>
				<tr>
					<td>1、API地址</td>
					<td><input type="text" name="apiUrl"
						value="http://dev.grapefs.com/gpay/withdraw" size="50" /></td>
				</tr>
				<tr>
					<td>2、平台商户号</td>
					<td><input type="text" name="merchantNo" size="50" /></td>
				</tr>
				<tr>
					<td>3、商户密钥</td>
					<td><input type="text" name="md5Key" "
						size="50" /></td>
				</tr>
	
				<tr>
					<td>4、异步通知地址</td>
					<td><input type="text" name="notify_url" size="50" /></td>
				</tr>
				<tr>
					<td>5、提现订单号</td>
					<td><input type="text" name="orderNoList" size="50" /></td>
				</tr>

			</table>

		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">点击这里发起交易</button>
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
	<p>
		2、平台商户号：在运营系统——》商户管理——》商户信息管理中查询、获取,</br>&nbsp;&nbsp;
		需要确保该商户、所属代理商、渠道商户开通了D0业务，并且费率设置合法;
	</p>
	<p></p>
	<p>3、商户密钥：在运营系统——》商户管理——》商户信息管理——》查看中查询、获取</p>
	<p>
		4、交易订单号，必须是已经交易成功且未提现的订单；</br>&nbsp;&nbsp; 并使用英语逗号隔开订单号
	</p>
	<p>5、注意配置好代付证书等，因为D0提现成功是直接打款给商户的。</p>
</div>
