<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include	file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<title>microPay</title>
<div class="pageContent">
	<form:form method="post" modelAttribute="model"
		action="${pageContext.request.contextPath}/demo/MicroPayNow"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="157">
		<input type="hidden" name="signType" value="MD5"/>
			<table>
				<tr>
					<td>1、API地址</td>
					<td><input type="text" name="apiUrl" value="http://dev.grapefs.com/gpay/microPay"  size="50"/></td>
				</tr>
				
				<tr>
					<td>2、交易渠道</td>
					<td><input type="text" name="payMethod"   size="50" /></td>
				</tr>
				<tr>
					<td>3、平台商户号</td>
					<td><input type="text" name="merchantNo" size="50" /></td>
				</tr>
				<tr>
					<td>4、商户密钥</td>
					<td><input type="text" name="md5Key"  size="50" /></td>
				</tr> 
				<tr>
					<td>5、交易金额</td>
					<td><input type="text" name="amount"  size="50" class="required number" min='0' /></td>
				</tr>
				<tr>
					<td>6、主题</td>
					<td><input type="text" name="subject"  size="50"/></td>
				</tr>

				<tr>
					<td>7、交易订单号</td>
					<td><input type="text" name="orderNo"  size="50"/></td>
				</tr>
				<tr>
					<td>8、授权码</td>
					<td><input type="text" name="authCode"  size="50"/></td>
				</tr>
			    <tr>
					<td>9、订单失效时间</td>
					<td><input type="text" name="expireTime" id="expireTime"
							value="6" size="30"  class="input uname" /></td>
				</tr>
				<tr>
					<td>10、信用卡付款：</td>
					<td><select name="payByCredit">
							<!-- <option value="">请选择</option> -->
							<option value="TRUE">可以</option>
							<option value="FALSE">不能</option>
					</select></td>
				</tr>
				<tr>
					<td>11、微信公众号ID(appid)</td>
					<td><input type="text" name="subAppid" id="expireTime"
							value="" size="30"  class="input uname" /></td>
				</tr>
				<tr>
					<td>12、商品标记(goodsTag)</td>
					<td><input type="text" name="goodsTag"
							value="" size="50" />(微信渠道可选上送，代金券或立减优惠功能参数，对应微信的goods_tag字段支付宝目前用不到该参数)</td>
				</tr>				
			<!-- <tr>
				<td>10、同时发起D0业务：</td>
					<td><select name="isD0Sett">
						<option value="">请选择</option>
						<option value="FALSE">否</option>
						<option value="TRUE">是</option>
					</select></td>
			</tr> -->
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
	<p>2、目前只有微信支付渠道</p>
	<p>3、平台商户号：在运营系统——》商户管理——》商户信息管理中查询、获取,</br>&nbsp;&nbsp;	需要确保该商户、所属代理商、渠道商户开通了微信支付，并且费率设置合法;</p>
	<p></p>
	<p>4、商户密钥：在运营系统——》商户管理——》商户信息管理——》查看中查询、获取</p>
	<p>6、主题和7、描述：交易的备注信息</p>
	<p>8、交易订单号：可随意输入，但是要保证系统内唯一</p>
	<p>9、授权码:</br>&nbsp;&nbsp;	
		微信：打开app，点击右上角的“+”（加号），选择“收付款”，再选择“向商家付款"，可看见授权码</p>
	<!-- <p>10、同时发起D0业务，选择“是”则同时完成交易并进行D0提现，否则仅作交易；D0提现结果可在交易管理-》分润查询中查询</p> -->
</div>
