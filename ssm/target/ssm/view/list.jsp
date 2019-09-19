<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
    <script>
        $(function () {
            $(".pageChoose").click(function () { //首页，尾页，上一页，下一页点击事件处理
                var choose = $(this).prop("name");
                $("#page").val(choose);
                $("#queryForm").submit();
            });
            $("#buttonGo").click(function () {//跳转页面处理
                var gotoPage = $("#goto").val();
                $("#page").val(gotoPage);
                $("#queryForm").submit();
            });
        });
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
<form action="list" method="post" id="queryForm"><%--使用一个表单实现分页选择的数据提交--%>
    <input type="hidden" name="page" id="page" value="1"/>
</form>
<a href="/view/add.jsp">增加一个</a>
<table border="1" width="600">
    <tr>
        <th>编号</th><th>姓名</th><th>年龄</th><th>操作</th>
    </tr>
    <c:forEach items="${requestScope.pageBean.list}" var="user">
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
    <tr><td colspan="2" align="center">
        <a  href="javascript:void(0)"  name="1" class="pageChoose" >首页</a>
        <a  href="javascript:void(0)"  name="${requestScope.pageBean.prePage}" class="pageChoose" >上一页</a>
        <a  href="javascript:void(0)"  name="${requestScope.pageBean.nextPage}" class="pageChoose" >下一页</a>
        <a  href="javascript:void(0)"  name="${requestScope.pageBean.lastPage}" class="pageChoose" >尾页</a>
        ${requestScope.pageBean.pageNum}/${requestScope.pageBean.pages}页
        <input type="text" id="goto" value="${requestScope.pageBean.pageNum}" style="width: 70px"/><input type="button" value="跳转" id="buttonGo" />
    </td></tr>
</table>
</body>
</html>
