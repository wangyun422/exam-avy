<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
<base href="<%=basePath%>"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function transfer(){
	var content=$("#content").val();
	$.ajax({
		type:"post",
		url:"youdao/translating",
		data:{"content":content},
		success:function(msg){
			
		/* 	var transfer =${transfer}*/
			$("#transfer").val(msg.transfer); 
		}
	})
	}
</script>


</head>
<body>
	<!-- <form action="youdao/translating" method="post">
		<input type="text" name="content"/>
		<input type="submit"/>
	</form> -->
	
	<form method="post"  id="formName"> 
		<label for="content">输入需要翻译的内容:</label> 
		<input type="text" name="content" id="content">
		<a href="#" data-role="button" onclick="transfer()">翻译</a> 
		<label for="content">翻译内容:</label> 
		 <input type="text" name="transfer" id="transfer" >
	</form> 
	
</body>
</html>