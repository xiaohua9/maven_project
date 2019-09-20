<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详细</title>
</head>
<body style="background-color: lightyellow">
<h1 align="center">图书借阅系统</h1>
<table border="1px" width="600px" align="center">
    <tr>
        <td>图书编号</td><td>${requestScope.bookInfo.book_code}</td>
    </tr>
    <tr>
        <td>图书名称</td><td>${requestScope.bookInfo.book_name}</td>
    </tr>
    <tr>
        <td>图书分类</td><td>${requestScope.bookInfo.book_type.type_name}</td>
    </tr>
    <tr>
        <td>作者</td><td>${requestScope.bookInfo.book_author}</td>
    </tr>
    <tr>
        <td>出版社</td><td>${requestScope.bookInfo.publish_press}</td>
    </tr>
    <tr>
        <td>出版时间</td><td>${requestScope.bookInfo.publish_date}</td>
    </tr>
    <tr>
        <td colspan="2" align="center"><a href="/view/list">返回首页</a></td>
    </tr>
</table>
</body>
</html>
