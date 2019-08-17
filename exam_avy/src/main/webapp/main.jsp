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
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>机试题</title>
<meta name="description" content="实例演示" />

<link rel="stylesheet" type="text/css" href="css/styles.css" />
<style type="text/css">
.demo{width:760px; margin:60px auto 10px auto}

</style>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script src="js/quiz.js"></script>
<script>
//var init={'questions':[{'question':'jQuery是什么？','answers':['JavaScript库','CSS库','JAVA框架','以上都不是'],'correctAnswer':1},{'question':'找出不同类的一项?','answers':['写字台','沙发','电视','桌布'],'correctAnswer':3},{'question':'国土面积最大的国家是：','answers':['美国','中国','俄罗斯','加拿大','法国'],'correctAnswer':3},{'question':'月亮距离地球多远？','answers':['18万公里','38万公里','100万公里','180万公里'],'correctAnswer':2}]};

$(function(){
	$.ajax({
		type:"post",
		url:"questions/list",
		success:function(msg){
		$('#quiz-container').jquizzy({
	        questions: msg.questions
	    });
		}
	})
	
	
	
	
});
</script>
</head>

<body>


	<div class="demo">
		<div id='quiz-container'></div>
	</div>
</body>
</html>
