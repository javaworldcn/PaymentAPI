<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<title>商户进件</title>
<div class="pageContent">

	<form:form method="post" modelAttribute="model"
		action="${pageContext.request.contextPath}/demo/reportMerchant"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="157">
			<input type="hidden" name="signType" value="MD5" />

			<table>
				<tr>
					<td>1、报备商户API地址</td>
					<td><input type="text" name="apiUrl"
						value="http://dev.grapefs.com/gpay/reportMerchant" size="50" /></td>
				</tr>
				<tr>
					<td>2、agentKey</td>
					<td><input type="text" name="md5Key" size="50" value="" /></td>
				</tr>
				<tr>
					<td>3、agentNo</td>
					<td><input type="text" name="agentNo" value="" size="50" /></td>
				</tr>
				<tr>
					<td>4、商户全称</td>
					<td><input type="text" name="fullName" value="" size="50" /></td>
				</tr>
				<tr>
					<td>5、商户简称</td>
					<td><input type="text" name="shortName" value="" size="50" /></td>
				</tr>
				<tr>
					<td>6、行业类型</td>
					<td><input type="text" name="bizCategory" value="204"
						size="50" /></td>
				</tr>
				<tr>
					<td>7、法人姓名</td>
					<td><input type="text" name="legalPerson" value="陈知庶"
						size="50" /></td>
				</tr>
				<tr>
					<td>8、省份证号</td>
					<td><input type="text" name="cardNo"
						value="431023198907171615" size="50" /></td>
				</tr>
				<tr>
					<td>9、手机号</td>
					<td><input type="text" name="mobileNo" value="13510381642"
						size="50" /></td>
				</tr>
				<tr>
					<td>10、省</td>
					<td><input type="text" name="province" value="湖南省" size="50" /></td>
				</tr>
				<tr>
					<td>11、市</td>
					<td><input type="text" name="city" value="郴州市" size="50" /></td>
				</tr>
				<tr>
					<td>12、详细地址</td>
					<td><input type="text" name="address" value="天都峰" size="50" /></td>
				</tr>
				<tr>
					<td>13、银行名称c</td>
					<td><input type="text" name="bankName" value="中国建设银行"
						size="50" /></td>
				</tr>
				<tr>
					<td>14、账户姓名</td>
					<td><input type="text" name="bankAccountName" value="周瑾"
						size="50" /></td>
				</tr>
				<tr>
					<td>15、银行卡号</td>
					<td><input type="text" name="bankAccountNo"
						value="6226191500462710" size="50" /></td>
				</tr>
				<tr>
					<td>16、账户类型</td>
					<td><input type="text" name="bankAccountType" value="1"
						size="50" /></td>
				</tr>
				<tr>
					<td>17、支行行号</td>
					<td><input type="text" name="bankChannelNo"
						value="105290068180" size="50" /></td>
				</tr>
				<tr>
					<td>18、省份证</td>
					<td><input type="text" name="bankCardNo"
						value="362202199403308113" size="50" /></td>
				</tr>
				<tr>
					<td>19、支付宝交易费率</td>
					<td><input type="text" name="aliPayTradeRate" size="50"
						class="required number" min='0' value="0.005" /></td>
				</tr>
				<tr>
					<td>20、微信交易费率</td>
					<td><input type="text" name="wxPayTradeRate" size="50"
						class="required number" min='0' value="0.004" /></td>
				</tr>
				<tr>
					<td>21、D0费率</td>
					<td><input type="text" name="d0Rate" size="50"
						class="required number" min='0' value="0.004" /></td>
				</tr>
			    <tr>
					<td>21、打款手续费</td>
					<td><input type="text" name="remitFee" size="50"
						class="required number" min='0' value="0.004" /></td>
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
	<p>1、报备商户API地址</p>
	<p>2、agentKey:我们在银行系统的代理商唯一key，用于参数加密解密</p>
	<p>3、agentNo:我们在银行系统的代理商唯一编号</p>
	<p>4、merchantInfo:商户基本信息</p>
</div>