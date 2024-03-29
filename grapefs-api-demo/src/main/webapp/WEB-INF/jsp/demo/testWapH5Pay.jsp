<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<title>APP支付</title>
<div class="pageContent">
	<form:form method="post" modelAttribute="model"
		action="${pageContext.request.contextPath}/demo/wapH5PayNow"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="157">
			<input type="hidden" name="signType" value="MD5" />
			<table>
				<tr>
					<td>1、API地址</td>
					<td><input type="text" name="apiUrl"
						value="http://localhost:8080/gpay/wapPay" size="50" /></td>
				</tr>
				<tr>
					<td>2、交易渠道</td>
					<td><input type="text" name="payMethod" size="50" /></td>
				</tr>
				<tr>
					<td>3、平台商户号</td>
					<td><input type="text" name="merchantNo" size="50" /></td>
				</tr>
				<tr>
					<td>4、商户密钥</td>
					<td><input type="text" name="Md5Key" size="50" /></td>
				</tr>
				<tr>
					<td>5、交易金额</td>
					<td><input type="text" name="amount" size="50"
							class="required number" min='0' /></td>
				</tr>

				<tr>
					<td>6、主题</td>
					<td><input type="text" name="subject" value="测试商户"
							size="50" /></td>
				</tr>
				<tr>
					<td>7、描述</td>
					<td><input type="text" name="desc" value="测试" size="50" /></td>
				</tr>

				<tr>
					<td>8、交易订单号</td>
					<td><input type="text" name="orderNo" size="50" /></td>
				</tr>
				<tr>
					<td>9、通知地址</td>
					<td><input type="text" name="notifyUrl" size="50" /></td>
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
					<td>11、订单失效时间(可选，单位分)</td>
				    <td><input type="text" name="expireTime"  size="50"/></td>
				</tr>
				<tr>
					<td>12、商品标记(goodsTag)</td>
					<td><input type="text" name="goodsTag"
							value="" size="50" />(微信渠道可选上送，代金券或立减优惠功能参数，对应微信的goods_tag字段支付宝目前用不到该参数)</td>
				</tr>
				<tr>
					<td>13、WAP网站URL地址(wapUrl)</td>
					<td><input type="text" name="wapUrl"
							value="" size="100" /></td>
				</tr>	
				<tr>
					<td>14、WAP网站名称</td>
					<td><input type="text" name="wapName"
							value="" size="50" /></td>
				</tr>	
				<tr>
					<td>15、用户端真实IP地址</td>
					<td><input type="text" name="ipAddress"
							value="" size="50" /></td>
				</tr>	
				<tr>
					<td>16、交易费率</td>
					<td><input type="text" name="transOnlineRate"  size="50" class="required number" min='0' /></td>
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
		3、平台商户号：在运营系统——》商户管理——》商户信息管理中查询、获取, </br>&nbsp;&nbsp;
		需要确保该商户、所属代理商、渠道商户开通了支付业务，并且费率设置合法;
	</p>
	<p></p>
	<p>4、商户密钥：在运营系统——》商户管理——》商户信息管理——》查看中查询、获取</p>
	<p>6、主题和7、描述：交易的备注信息</p>
	<p>8、交易订单号：可随意输入，但是要保证系统内唯一</p>
	<p>9、通知地址：就是用户在客户端（微信、支付宝等
		）输入密码交易后，支付服务商将交易结果通知到提供的域名，如https://www.shouyinjia.com;</p>
</div>
