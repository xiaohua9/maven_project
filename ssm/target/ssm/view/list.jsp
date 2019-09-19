<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
    <script>
        $(function () {
            $(".delete").click(function () {
                var obj = $(this);
                var propName = obj.prop("name");
                $.post("delete", {"id": propName}, function (data) {
                    var number = parseInt(data);
                    if (number > 0) {
                        obj.parents("tr").remove();
                        alert("删除成功");
                    } else {
                        alert("删除失败");
                    }
                })
            })
        })
    </script>
</head>
<body>
<h2>信息列表</h2>
<a href="/view/add.jsp">增加一个</a>
<table border="1" width="600">
    <tr>
        <th>编号</th><th>姓名</th><th>年龄</th><th>操作</th>
    </tr>
    <c:forEach items="${list}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td><a href="/view/update.jsp?id=${user.id}&name=${user.name}&age=${user.age}">修改</a>
                <a href="javascript:void(0)" name="${user.id}" class="delete">删除</a>
                <a href="selectOne?id=${user.id}" >详情</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
