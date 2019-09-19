<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/19
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1px">
    <tr>
        <td>编号</td> <td>${requestScope.user.id}</td>
    </tr>
    <tr>
        <td>姓名</td> <td>${requestScope.user.name}</td>
    </tr>
    <tr>
        <td>年龄</td> <td>${requestScope.user.age}</td>
    </tr>
    <tr>
        <td colspan="2"><a href="/view/list">返回主页</a></td>
    </tr>
</table>
</body>
</html>
