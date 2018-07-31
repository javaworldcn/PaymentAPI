<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--声明当前页面的编码集：charset=gbk,gb2312(中文编码)，utf-8国际编码-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!--当前页面的三要素-->
<title>暂停服务</title>
<meta name="Keywords" content="暂停服务">
<meta name="description" content="">
<!--响应式mate标签-->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/wrong.css" />
</head>
<body>
 	<div id="storeInfo">
		<div class="store-info">
			<span class="store-info__avatar"></span>
		</div>
	</div>
	
	<div id="storeInfo1">
		<div class="store-info1">
		    <%
		    	
		    		out.println("<div class=\"store-info__avatar1\">错误信息</div>");
		    		out.println("<div class=\"store-info__name1\" style=\"word-break: break-word;width:98%;white-space:normal;\">" + exception.getMessage() + "</div>");
		    
		    %>
		</div>
	</div>
 
  <p class="c_btn1">
  	<a class="btn1">由<c:choose><c:when test="${empty oemName}">收银家</c:when><c:otherwise>${oemName}</c:otherwise></c:choose>提供技术服务</a>
  </p>
</body>
</html>