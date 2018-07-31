<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include	file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<html>
  <head>
    <title>导入中信渠道商户数据</title>
  </head>
  <body>
			<form id="importForm" enctype="multipart/form-data" action="${pageContext.request.contextPath}/business/importZxChannelMerchanDemo" method="post">
				<div style="padding: 10px;">
					<p>
						<label>
							接口访问ip：
						</label>
						<input type="test" name="serverUrl" id="serverUrl"/>
						<label>
							接口访问地址：
						</label>
						<input type="test" name="interfaceAddr" value="/boss/addZxChannelMerchanListDemo" id="interfaceAddr"/><br/>
						<label>
							待导入文件：
						</label>
						
						<input type="file" name="importFile" id="importFile"/>
						
					</p>
					<input type="submit" value="提交">
				</div>
			</form>
  </body>
</html>
