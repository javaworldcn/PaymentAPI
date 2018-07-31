<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form name="importReconFileForm" id="importReconFileForm" action="${pageContext.request.contextPath}/business/importMsReconFile" 
		method="post" enctype="multipart/form-data" >
		<table class="styled">
			<tr>
				<td>订单前缀：</td>
				<td><input type="text" name="prefix"/></td>
			</tr>
			<tr>
				<td>选择分流的对账文件：</td>
				<td>
					<select name="reconBankFile">
						<option value="1">民生对账文件</option>
						<option value="2">华夏对账文件</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>选择分流的商户编号(可选)：</td>
				<td>
					<input type="text" name="merchantNo"/>
				</td>
			</tr>
			<tr>
				<td>模版文件：</td>
				<td><input type="file" name="reconFileupload"/>(仅支持txt格式)</td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="submitForm('1')">按订单前缀分流对账文件</button>
							<button type="button" onclick="submitForm('2')">按商户编号分流对账文件</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
<div>
	<p>1、文件模板：导入.txt对账文件分类；</p>
	<p>2、订单前缀：需要分流的订单前缀；</p>
</div>
<script type="text/javascript">
	function submitForm(type){
		if(type=="1"){
			document.getElementById("importReconFileForm").action = "${pageContext.request.contextPath}/business/distinguishReconFile";
		}else if(type=="2"){
			document.getElementById("importReconFileForm").action = "${pageContext.request.contextPath}/business/distinguishReconFileByMerchant";
		}
		document.getElementById("importReconFileForm").submit();
	}
</script>

