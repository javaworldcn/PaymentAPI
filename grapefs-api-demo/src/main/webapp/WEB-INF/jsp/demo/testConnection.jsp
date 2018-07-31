<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="<c:url value='/assets/dwz/js/jquery-2.1.4.min.js'/>" type="text/javascript"></script>
<div class="pageContent">
	<table>
		<tr>
			<td>请输入url:</td>
			<td><input type="text" name="pingUrl" id="pingUrl" value="" size="50" /></td>
			<td><button onclick="testUnicomPing(1)">ping</button></td>
			<td><button onclick="testUnicomPing(2)">wget</button></td>
			<td><button onclick="testUnicomPing(3)">telnet</button></td>
			<td><button onclick="testUnicomPing(4)">HttpClient</button></td>
		</tr>
	</table>
</div>
<div id="resultDiv" style="border: 1px solid #8e8989;width: 670px;min-height: 200px;;height:auto;background-color: #e2e2e2;">
</div>
<script type="text/javascript">
	function testUnicomPing(cmdType){
		var pingUrl = $("#pingUrl").val();
		if(pingUrl==""){
			alert("请输入url");
			return;
		}
		$("#resultDiv").html("<pre>尝试连接中...</pre>");
		$.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/demo/testConnection",
            data: {"pingUrl":pingUrl,"cmdType":cmdType},
            dataType: "text",
            success: function (data) {
            	$("#resultDiv").html("<pre>"+data+"</pre>");
            },
            error: function (msg) {
            	$("#resultDiv").html("<pre>"+msg+"</pre>");
            }
        });
	}
</script>
