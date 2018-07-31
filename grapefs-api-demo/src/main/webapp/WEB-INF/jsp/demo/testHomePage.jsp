<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<title>测试Demo地址汇总</title>
<style type="text/css">
body {
	font-family: "Microsoft YaHei", sans-serif;
	color: #000;
}

a {
	color: blue;
}

a:hover {
	color: red;
}

hr {
	border: 1px solid #724598;
	margin-left: 100px;
	margin-right: 100px;
}

#logo {
	margin-left: 100px;
}

#content {
	font-size: 12px;
	width: 100%;
	display: inline-block;
}

#content a {
	line-height: 24px;
	font-size: 14px;
}

#mng {
	margin-left: 100px;
	width: 220px;
	display: inline-block;
	float: left;
	padding: 5px;
	margin-right: 5px;
}

#dev {
	width: 220px;
	display: inline-block;
	float: left;
	padding: 5px;
	margin-right: 5px;
}

#test {
	width: 250px;
	display: inline-block;
	float: left;
	padding: 5px;
	margin-right: 5px;
}

.head1 {
	font-size: 22px;
	margin-left: 10px;
	font-weight: bold;
	line-height: 30px;
}

.head2 {
	font-size: 14px;
	font-weight: bold;
	line-height: 20px;
	display: block;
}

#foot {
	font-size: 12px;
	margin-left: 100px;
}
</style>


<div id="content">
	<div>
		<div align="center">
			<span class="head1">测试Demo地址汇总</span>
		</div>
	</div>
	<hr />
	<div id="mng">
		<span class="head2">交易</span>
		 <a href="<c:url value='/demo/toMicroPay'/>" target="_blank" style="color: blue;"> MicroPay</a> <br/> 
		 <a href="<c:url value='/demo/toNativePay'/>" target="_blank" style="color: blue;"> NativePay</a> <br/>
		 <a href="<c:url value='/demo/toH5Pay'/>" target="_blank"  style="color: blue;"> H5Pay</a> <br/>
		  <a href="<c:url value='/demo/toWapH5Pay'/>" target="_blank"  style="color: blue;"> WapH5Pay</a> <br/>
		  		 <a href="<c:url value='/demo/toUnifiedOrder'/>" target="_blank"  style="color: blue;"> 公众号/服务窗统一下单</a> <br/>
		  <a href="<c:url value='/demo/toAppPay'/>" target="_blank"  style="color: blue;"> AppPay</a> <br/>
		 <a href="<c:url value='/demo/torefund'/>" target="_blank"  style="color: blue;"> 退款</a> <br/>
		  <a href="<c:url value='/demo/toReverse'/>" target="_blank"  style="color: blue;"> 撤销</a> <br/>
		 <a href="<c:url value='/demo/toQueryTrade'/>" target="_blank"  style="color: blue;"> 交易查询</a> <br/>
	</div>
	
	<div id="dev">
		<span class="head2">对账</span>
		 <a href="<c:url value='/demo/toDownloadReconFileList'/>" target="_blank" style="color: blue;"> 代理商对账文件下载</a> <br/> 
		 <a href="<c:url value='/demo/toDownloadMerchantReconFile'/>" target="_blank" style="color: blue;"> 商户对账文件下载</a> <br/> 
	</div>
	
	 <div id="dev" style="display: none">
		<span class="head2">D0提现</span> 
		 <a href="<c:url value='/demo/toD0SettList'/>" target="_blank" style="color: blue;"> 民生D0提现</a> <br/> 
		 <a href="<c:url value='/demo/toD0SettQuery'/>" target="_blank" style="color: blue;"> 民生提现查询</a> <br/> 
		 <a href="<c:url value='/demo/toD0SettHxList'/>" target="_blank" style="color: blue;"> 华夏D0提现</a> <br/>
		 <a href="<c:url value='/demo/toD0settByMerch'/>" target="_blank" style="color: blue;"> 华夏D0提现（按商户）</a> <br/>
		<a href="<c:url value='/demo/toD0SettHxQuery'/>" target="_blank" style="color: blue;"> 华夏提现查询</a> <br/>
		
	</div>
	
<%--

	<div id="dev">
		<span class="head2">代付</span> 
		 <a href="<c:url value='/demo/toYLSinglePay'/>" target="_blank" style="color: blue;"> 银联单笔代付</a> <br/> 
		 <a href="<c:url value='/demo/toQueryYLSinglePay'/>" target="_blank" style="color: blue;"> 银联单笔代代付-查询</a> <br/> 
	</div> --%>

	<div id="dev">
		<span class="head2">商户</span> 
<%-- 		 <a href="<c:url value='/demo/toreportMerchant'/>" target="_blank" style="color: blue;"> 报备商户(旧)</a> <br/> 
		 <a href="<c:url value='/demo/toMerchantUpdateReport'/>" target="_blank" style="color: blue;"> 报修改商户(旧)</a> <br/>  --%>
		 <a href="<c:url value='/demo/toOpenMerchant'/>" target="_blank" style="color: blue;">商户入驻</a> <br/> 
		 <a href="<c:url value='/demo/toModifyMerchant'/>" target="_blank" style="color: blue;">商户修改</a> <br/> 
		  <a href="<c:url value='/demo/toQueryMerchantInfo'/>" target="_blank" style="color: blue;">商户查询</a> <br/> 
		 <a href="<c:url value='/demo/toOpenMerchantProduct'/>" target="_blank" style="color: blue;">开通商户产品</a> <br/> 
		 <a href="<c:url value='/demo/toModifyMerchantProduct'/>" target="_blank" style="color: blue;">修改开通的商户</a> <br/> 
		 <a href="<c:url value='/demo/toPayconfigAdd'/>" target="_blank" style="color: blue;">子商户配置新增</a> <br/> 
		 <a href="<c:url value='/demo/toPayConfigQuery'/>" target="_blank" style="color: blue;">子商户配置查询</a> <br/>
		<a href="<c:url value='/demo/toHistoricalMerchBinding'/>" target="_blank" style="color: blue;">存量商户批量配置接口</a> <br/>
		
	</div>
	
	<div id="dev">
		<span class="head2">运营</span>
		 <a href="<c:url value='/business/toImportReconFile'/>" target="_blank" style="color: blue;">导入对账文件分流</a> <br/> 
		 <a href="<c:url value='/business/toImportChannelRecord'/>" target="_blank" style="color: blue;">接口导入对账文件、对账</a> <br/> 
		 <a href="<c:url value='/business/toImplFileRecon'/>" target="_blank" style="color: blue;">手动导入对账文件、对账</a> <br/> 
 		 <a href="<c:url value='/business/toProcessReconError'/>" target="_blank" style="color: blue;">查询、处理对账差错</a> <br/> 
		 <a href="<c:url value='/business/tocharge'/>" target="_blank" style="color: blue;"> 计费</a> <br/> 
		 <a href="<c:url value='/business/toagentSett'/>" target="_blank" style="color: blue;"> 代理商结算</a> <br/> 
		 <a href="<c:url value='/business/toagentSettleExportExcel'/>" target="_blank" style="color: blue;">导出代理商打款文件</a> <br/> 
		 <a href="<c:url value='/business/toupdateAgentSettBatchStatus'/>" target="_blank" style="color: blue;">按【批次号】更新代理商打款结算状态</a> <br/> 
		 <a href="<c:url value='/business/toupdateAgentSettStatus'/>" target="_blank" style="color: blue;">按【流水号】更新代理商打款结算状态</a> <br/> 
		 <a href="<c:url value='/business/toChannelMerchApproveStatusUpdate'/>" target="_blank" style="color: blue;">手动执行批量更新渠道商户审核结果</a> <br/> 
		 
		 <a href="<c:url value='/business/toBuilderT1SettRecord'/>" target="_blank" style="color: blue;">生成商户T1应结算记录</a> <br/> 
		 <a href="<c:url value='/business/toBuilderClaerSettRecord'/>" target="_blank" style="color: blue;">生成平台应清算记录</a> <br/> 
		 <a href="<c:url value='/business/importZxChannelMerchan'/>" target="_blank" style="color: blue;">导入中信渠道商户数据</a> <br/> 
		  <a href="<c:url value='/business/toModifyMerchatSettStatus'/>" target="_blank" style="color: blue;">更新商户结算状态</a> <br/> 
		  <span>支付宝H5测试地址：https://test.grapefs.com/api-demo/demo/toALIPayH5Test 请在手机支付宝打开</span> <br/>
	</div>
	
	<div>
		<span class="head2">快捷交易</span>
		 <a href="<c:url value='/demo/tofastH5Pay'/>" target="_blank" style="color: blue;"> H5快捷支付 </a> <br/> 
		 <a href="<c:url value='/demo/toMergeFastReconFile'/>" target="_blank" style="color: blue;"> 合并快捷对账单 </a> <br/>
		<a href="<c:url value='/demo/toFastPayOpenMerchant'/>" target="_blank" style="color: blue;"> 商户(无卡)开通 </a> <br/>
		<a href="<c:url value='/demo/toFastPayDPOpenMerchFastProduct'/>" target="_blank" style="color: blue;"> (非同人)开通产品 </a> <br/>
		<%--<a href="<c:url value='/demo/toFastPaySPOpenMerchFastProduct'/>" target="_blank" style="color: blue;"> (同人)开通产品 </a> <br/>--%>
	</div>

</div>
<br />
<hr>
<div id="foot">
	<p>说明</p>
	<p>1、本页测试功能均是从下游角度进行使用的，如下游下载本平台的对账文件；</p>
</div>
<br />
