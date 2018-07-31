<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=1.0">
<meta name="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no,email=no,adress=no">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<style type="text/css">
		*{margin:0px;padding:0px;}
		 body{background-color:#F9F9F9;}
		/* 输入框圆角阴影样式*/
		 .czy_box .input:focus{ box-shadow:inset 0 0 10px #e5e5e5;  }
		 .czy_box .uname{border-radius:5px 5px 0 0;}
		  .czy_box .ss{height:26px;padding:2px 10px;
		 outline:none;color:#999;border:1px solid #eaeaea;}
		 .czy_box .pw{margin-top:-1px;border-radius:0 0 5px 5px;}
		/* 输入框样式*/
		 .czy_box  .c_text {margin:10px auto;width:320px;height:20px;}
		 .czy_box .c_text  .input {height:20px;padding:12px 10px;
		 outline:none;color:#999;border:1px solid #eaeaea;}
		/* 登录按钮样式cursor:pointer;*/ 
		 .czy_box .c_btn{margin-top:15px;}
		 .czy_box .c_btn .btn{ width:320px;height:44px;
								 background:#FF7A4D;color:#fff;font-size:16px;
								 text-align:center:line-height:44px;			
								 border-radius:5px;border:0px;}  
	     .czy_box .c_text .c_btn .btn:hover{background:#111;transition:all 0.3s ease-in-out;cursor:pointer;}
	
	</style>

   <div class="czy_box">
	 
		 <div class="c_text">
			<table>
	<tr>
					<td>API地址</td>
					<td><input type="text" name="apiUrl" Id="apiUrl" class="input uname"
						value="http://192.168.88.90/api/pay/H5Pay" size="30"  /></td>
				</tr>
				<tr>
					<td>交易渠道</td>
					<td>
					<select name="payMethod" id="payMethod" class="ss" >
							<!-- <option value="">请选择</option> -->
							<option value="ALIPAY">ALIPAY</option>
							<option value="WXPAY">WXPAY</option>
					</select></td>
				</tr>
				<tr>
					<td>平台商户号</td>
					<td><input type="text" name="merchantNo"  class="input uname" id="merchantNo" value="201703190006461"  size="30"  /></td>
				</tr>
				<tr>
					<td>商户密钥</td>
					<td><input type="text" name="md5Key" class="input uname" id="md5Key" value="d5eae60ed1b04518b7f9decc5d2680a9"  size="30" /></td>
				</tr>
				<tr>
					<td>交易金额</td>
					<td><input type="text" name="amount" id="amount"  class="input uname" size="30"  value="0.01"
							class="required number" min='0' /></td>
				</tr>

				<tr>
					<td>主题</td>
					<td><input type="text" name="subject" id="subject" class="input uname" value="测试商户"
							size="30"/></td>
				</tr>

				<tr>
					<td>交易订单号</td>
					<td><input type="text" name="orderNo" id="orderNo"  class="input uname" size="30"  /></td>
				</tr>
				<tr>
					<td>通知地址</td>
					<td><input type="text" name="notifyUrl" id="notifyUrl" class="input uname" value="http://banktest.grapefs.com/api/payNotifyAll" size="30"/></td>
				</tr>
				<tr>
					<td>openId</td>
					<td><input type="text" name="openId" id="openId"
							value="o7Gpqw_N4t8PBnh8Ah1BClJjUt5I" size="30" class="input uname" /></td>
				</tr>
				<tr>
					<td>buyerId</td>
					<td><input type="text" name="buyerId" id="buyerId"
							value="2088802705557085" size="30"  class="input uname" /></td>
				</tr>
				<tr>
					<td>信用卡付款：</td>
					<td><select name="payByCredit" id="payByCredit" class=ss >
							<!-- <option value="">请选择</option> -->
							<option value="TRUE">可以</option>
							<option value="FALSE">不能</option>
					</select></td>
				</tr>
    </table>
		 <p class="c_btn">  <input  onclick="doSubmit()" class="btn" type="button" value="支付"/> </p>
        </div>
</div>
<script src="https://a.alipayobjects.com/g/h5-lib/alipayjsapi/0.2.4/alipayjsapi.inc.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
function doSubmit() {
			var apiUrl =$("#apiUrl").val();
			var md5Key =$("#md5Key").val();
			var merchantNo =$("#merchantNo").val();
			var orderNo =$("#orderNo").val();
			var payMethod = $("#payMethod").val();
			var amount =$("#amount").val();
			var subject =$("#subject").val();
			var notifyUrl =$("#notifyUrl").val();
			var openId =$("#openId").val();
			var buyerId =$("#buyerId").val();
			var limitPay =$("#payByCredit").val();
			var url = "${pageContext.request.contextPath}/demo/toPayH5Test";
			$.post(url, {   'apiUrl':apiUrl,
				            'md5Key':md5Key,
				            'amount':amount,
							'buyerId':buyerId,
							'openId':openId,
							'merchantNo':merchantNo,
							'notifyUrl':notifyUrl,
							'limitPay':limitPay,
							'orderNo':orderNo,
							'payMethod':payMethod,
							'subject':subject},function(data){
								var jsonstr=$.parseJSON(data)
								 debugger;
			if(jsonstr.respCode == "000000"){
					if(payMethod == "ALIPAY"){
						//支付宝
						AlipayJSBridge.call("tradePay", {tradeNO :jsonstr.channelNo}, 
								function(result) {
									alert(JSON.stringify(result));
								});
					}else{
						  WeixinJSBridge.invoke(
							          'getBrandWCPayRequest', {
							    	   "appId":jsonstr.payInfo.appId,     //公众号名称，由商户传入     
							           "timeStamp":jsonstr.payInfo.timeStamp,         //时间戳，自1970年以来的秒数     
							           "nonceStr":jsonstr.payInfo.nonceStr, //随机串     
							           "package":jsonstr.payInfo.package,     
							           "signType":jsonstr.payInfo.signType, //微信签名方式：     
							           "paySign":jsonstr.payInfo.paySign //微信签名 
							       },
							       // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。 
							       function(res){
							    	   alert(JSON.stringify(res));
							    	   /* if(res.err_msg =="get_brand_wcpay_request:cancel") {
							    		   window.location.href="${pageContext.request.contextPath}/v1/app/qr/showPayResult/"+jsonData.orderNo+"?status=cancel";
							    	   } else if(res.err_msg =="get_brand_wcpay_request:ok"){
							    	   	   window.location.href="${pageContext.request.contextPath}/v1/app/qr/showPayResult/"+jsonData.orderNo;
							    	   }
							    	   else{
							    		   alert(res.err_code+res.err_desc+res.err_msg);
							    	   } */
							       }
								);
					      }	
					
				}else{
					alert("参数不符合规范,请检查支付方式与订单号");
				}
			});
	}
</script>


