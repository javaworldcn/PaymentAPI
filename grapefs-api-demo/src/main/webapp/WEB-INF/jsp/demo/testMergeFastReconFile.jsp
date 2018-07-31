<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form name="mergeFastReconFileForm" action="${pageContext.request.contextPath}/demo/mergeFastReconFile" 
		method="post" enctype="multipart/form-data" >
		<table class="styled">
			<tr>
				<td>快捷对账文件1：</td>
				<td><input type="file" name="fastReconFile1"/></td>
			</tr>
			<tr>
				<td>快捷对账文件2：</td>
				<td><input type="file" name="fastReconFile2"/></td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">合并快捷对账单</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

