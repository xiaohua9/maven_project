<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/19
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="update" method="post">
    <input type="hidden" name="id" value="${param.id}"/>
    <table border="1px">
        <tr>
            <td>编号</td><td>${param.id}</td>
        </tr>
        <tr>
            <td>姓名</td><td><input type="test" name="name" value="<%=new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8") %>"/></td>
        </tr>
        <tr>
            <td>年龄</td><td><input type="text" name="age" value="${param.age}"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="修改"/></td>
        </tr>
    </table>
</form>

</body>
</html>
