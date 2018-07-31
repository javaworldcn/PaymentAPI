<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <style type="text/css">
        body{
            text-align:center
        }
        .information {
            margin: 0 auto;
        }
        .request_table{
            margin: auto;
        }
        .request_table td{
            text-align: right;
        }
        .message span{
            color: red;
        }
        .input_text{
        	width: 300px;
        }
        .btn_sure{
        	width: 300px;
        	height: 50px
        }
    </style>
</head>
<body>
<div class="Container">
    <div class="information">
        <form action="${pageContext.request.contextPath}/demo/fastH5Pay" method="post">
            <div class="input_cont">
                <div class="search_tit">
                    <h2>H5快捷支付接口</h2>
                </div>
                <table class="request_table">
                	<tr>
                        <td>请求地址：<font color="red">*</font></td>
                        <td><input type="text" name="url" class="input_text" value="http://localhost:8080/fastPay/quickPayH5"/></td>
                    </tr>
                    <tr>
                        <td>商户编号：<font color="red">*</font></td>
                        <td><input type="text" name="merchantNo" class="input_text" value="201707211056316"/></td>
                    </tr>
                    <tr>
                        <td>商户密钥：<font color="red">*</font></td>
                        <td><input type="text" name="merchantkey" class="input_text" value="4997374716ab495686aa76b723445971"/></td>
                    </tr>
                    <tr>
                        <td>渠道编码：<font color="red">*</font></td>
                        <td><input type="text" name="channelCode" class="input_text" value="KJ_HICKORY"/>
                    </tr>
                    <tr>
                        <td>商户订单号：<font color="red">*</font></td>
                        <td><input type="text" name="orderNo" class="input_text" value="${orderNo}"/></td>
                    </tr>
                    <tr>
                        <td>交易金额：<font color="red">*</font></td>
                        <td><input type="text" name="amount" class="input_text" value="2.00"/></td>
                    </tr>
                    <tr>
                        <td>姓名：<font color="red">*</font></td>
                        <td><input type="text" name="bankAccoutName" class="input_text" value="周瑾"/></td>
                    </tr>
                    <tr>
                        <td>证件类型：<font color="red">*</font></td>
                        <td><input type="text" name="idCardType" class="input_text" value="IDCARD"/></td>
                    </tr>
                    <tr>
                        <td>证件号：<font color="red">*</font></td>
                        <td><input type="text" name="idCardNo" class="input_text" value="36220219940330****"/></td>
                    </tr>
                    <tr>
                        <td>银行卡号：<font color="red">*</font></td>
                        <td><input type="text" name="bankAccoutNo" class="input_text" value="625362****296062"/></td>
                    </tr>
                    <tr>
                        <td>下单IP：<font color="red">*</font></td>
                        <td><input type="text" name="ipAddress" class="input_text" value="127.0.0.1"/></td>
                    </tr>
                    <tr>
                        <td>信用卡有效年份：</td>
                        <td><input type="text" name="year" class="input_text" value=""/></td>
                    </tr>
                    <tr>
                        <td>信用卡有效期月份：</td>
                        <td><input type="text" name="month" class="input_text" value=""/></td>
                    </tr>
                    <tr>
                        <td>cvv2：</td>
                        <td><input type="text" name="cvv2" class="input_text" value=""/></td>
                    </tr>
                    <tr>
                        <td>手机号码：<font color="red">*</font></td>
                        <td><input type="text" name="phone" class="input_text" value="18682437474"/></td>
                    </tr>
                    <tr>
                        <td>交易币种：<font color="red">*</font></td>
                        <td><input type="text" name="currency" class="input_text" value="CNY"/></td>
                    </tr>
                    <tr>
                        <td>商品名称：<font color="red">*</font></td>
                        <td><input type="text" name="goodsName" class="input_text" value="iphoneX"/></td>
                    </tr>
                    <tr>
                        <td>商品描述：</td>
                        <td><input type="text" name="goodsDesc" class="input_text" value="iphoneX"/></td>
                    </tr>
                    <tr>
                        <td>回调地址：<font color="red">*</font></td>
                        <td><input type="text" name="callbackUrl" class="input_text" value="http://dev.grapefs.com/fastPay/success"/></td>
                    </tr>
                    <tr>
                        <td>异步通知地址：<font color="red">*</font></td>
                        <td><input type="text" name="notifyUrl" class="input_text" value="http://dev.grapefs.com/fastPay/hlb/h5PayNotify"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" class="btn_sure fw" value="测试H5快捷支付" /></td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
</body>
</html>