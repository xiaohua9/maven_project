
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="login" method="post">
    用户名:<input type="text" name="userName"/><br/>
    用户密码:<input type="text" name="password"/><br/>
    <input type="submit" value="提交"/>
</form>
<br/>
<img src="/images/a.jpg"><%--图片名不要用中文--%>
<form action="upload" method="post" enctype="multipart/form-data">
    文件上传：<input type="file" name="pic"/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
