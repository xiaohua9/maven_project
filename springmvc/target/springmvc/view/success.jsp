<%@ page import="com.learn.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/17
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录成功!</h1>
${requestScope.user.userName}<br/>
${requestScope.user.password}<br/>
<hr/>
${sessionScope.user.userName}${sessionScope.user.password}
</body>
</html>
