<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="pageContent">
	<form name="updateStatusUrlForm" action="${pageContext.request.contextPath}/business/channelMerchApproveStatusUpdate" 
		method="post" enctype="multipart/form-data" >
		<table class="styled">
			<tr>
				<td>批量更新渠道商户状态接口地址：</td>
				<td><input type="text" name="updateStatusUrl" value="http://localhost:8080/mgmt/channelMerchApproveStatusUpdate"/></td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">主动查询民生渠道商户审核结果</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

