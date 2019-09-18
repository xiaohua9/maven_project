<%@ page import="com.learn.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
    <script>
        $(function () {//点击事件发生后，由服务器返回一个json对象的数据
            $("#btn").click(function () {
                $.post("getJson",{"userName":$("#userName").val()},function (data) {
                    alert(data.userName+","+data.password)/*输出json对象数据*/
                    console.log(data)
                });
            });
        });
    </script>
</head>
<body>
<h1>登录成功!</h1>
${requestScope.user.userName}<br/>
${requestScope.user.password}<br/>
<hr/>
${sessionScope.user.userName}${sessionScope.user.password}

<input type="text" name="userName" id="userName"/>
<input type="button" value="提交" id="btn">
</body>
</html>
