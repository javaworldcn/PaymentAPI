<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form name="importReconFileForm" id="importReconFileForm" action=""  
		method="post" enctype="multipart/form-data" >
		<table class="styled">
			<tr>
				<td>1、API地址</td>
				<td><input type="text" name="serverUrl" id="serverUrl" value="http://localhost:8084/boss/implFileRecon"  size="50"/></td>
			</tr>
			<tr>
				<td>2、对账日期</td>
				<td><input type="text" name="reconDate"   size="50" /></td>
			</tr>
			<tr>
				<td>3、渠道编码</td>
				<td><input type="text" name="channelCode" value="HX_BANK"  size="50" /></td>
			</tr>
			<tr>
				<td>4、对账文件</td>
				<td><input type="file" name="reconFile"/></td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="submitForm()">导入对账文件对账</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
	function submitForm(){
		document.getElementById("importReconFileForm").action = document.getElementById("serverUrl").value;
		document.getElementById("importReconFileForm").submit();
	}
</script>

