<%--
  Created by IntelliJ IDEA.
  User: emac
  Date: 2019/7/18
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

   <%-- <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
    <script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>--%>
    <title>Insert title here</title>


    <script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
    /*   function fun(){
           var uname=$("#uname").val();
           alert(uname);
           $.ajax({
               type:"post",
               url:"login/limit",
               data:{"uname":uname},
               success:function(msg){
                   alert(msg);
                   //$("#bin").val(msg)
               }
           })
       }*/

    </script>
</head>
<body>

    <form id="form" action="login/limit" method="get">
        <input id ="uname" name="uname" type="text"> </br>
        <input id="upass" name="upass" type="text">
       <div style="color: red">
           ${limitLogin}
       </div>

        <input type="submit" >
    </form>


</body>
</html>
