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
<c:if test="${param.flag==1}">
    <script>alert("新增成功")</script>
</c:if>
<c:if test="${param.flag==2}">
    <script>alert("新增失败")</script>
</c:if>
<c:if test="${param.flag==3}">
    <script>alert("修改成功")</script>
</c:if>
<c:if test="${param.flag==4}">
    <script>alert("修改失败")</script>
</c:if>
<%--//空的话 ，去访问查全部组件--%>
<c:if test="${ empty requestScope.pageBean}">
    <script>location.href="/view/list"</script>
</c:if>
<form action="list" method="post" id="queryForm"><%--使用一个表单实现分页选择的数据提交--%>
    <input type="hidden" name="page" id="page" value="1"/>
    图书分类:
    <select name="currentBookType">
        <option value="">全部</option>
            <c:if test="${1==requestScope.currentBookType}">
                <option value="1" selected="selected" >小说</option>
            </c:if>
            <c:if test="${1!=requestScope.currentBookType}">
                <option value="1" >小说</option>
            </c:if>
            <c:if test="${2==requestScope.currentBookType}">
                <option value="2" selected="selected" >文学</option>
            </c:if>
            <c:if test="${2!=requestScope.currentBookType}">
                <option value="2" >文学</option>
            </c:if>
            <c:if test="${3==requestScope.currentBookType}">
                <option value="3" selected="selected" >传记</option>
            </c:if>
            <c:if test="${3!=requestScope.currentBookType}">
                <option value="3" >传记</option>
            </c:if>
            <c:if test="${4==requestScope.currentBookType}">
                <option value="4" selected="selected" >艺术</option>
            </c:if>
            <c:if test="${4!=requestScope.currentBookType}">
                <option value="4" >艺术</option>
            </c:if>
            <c:if test="${5==requestScope.currentBookType}">
                <option value="5" selected="selected" >少儿</option>
            </c:if>
            <c:if test="${5!=requestScope.currentBookType}">
                <option value="5" >少儿</option>
            </c:if>
            <c:if test="${6==requestScope.currentBookType}">
                <option value="6" selected="selected" >经济</option>
            </c:if>
            <c:if test="${6!=requestScope.currentBookType}">
                <option value="6" >经济</option>
            </c:if>
            <c:if test="${7==requestScope.currentBookType}">
                <option value="7" selected="selected" >管理</option>
            </c:if>
            <c:if test="${7!=requestScope.currentBookType}">
                <option value="7" >管理</option>
            </c:if>
            <c:if test="${8==requestScope.currentBookType}">
                <option value="8" selected="selected" >科技</option>
            </c:if>
            <c:if test="${8!=requestScope.currentBookType}">
                <option value="8" >科技</option>
            </c:if>
    </select>
    图书名称：<input type="text" name="currentBookName" value="${requestScope.currentBookName}"/>
    是否借阅：
    <select name="currentIsBorrow">
        <option value="">全部</option>
        <c:if test="${'未借阅'==requestScope.currentIsBorrow}">
            <option value="未借阅" selected="selected">未借阅</option>
        </c:if>
        <c:if test="${'未借阅'!=requestScope.currentIsBorrow}">
            <option value="未借阅" >未借阅</option>
        </c:if>
        <c:if test="${'已借阅'==requestScope.currentIsBorrow}">
            <option value="已借阅" selected="selected" >已借阅</option>
        </c:if>
        <c:if test="${'已借阅'!=requestScope.currentIsBorrow}">
            <option value="已借阅"  >已借阅</option>
        </c:if>
    </select>
    <input type="submit" value="查询"/>
</form>
<a href="/view/add.jsp">增加一个</a>
<table border="1" width="1000">
    <tr>
        <td>图书编号</td><td>图书分类</td><td>图书名称</td><td>作者</td><td>出版社</td><td>操作</td><td>详情</td><td>删除</td><td>修改</td>
    </tr>
    <c:forEach items="${requestScope.pageBean.list}" var="bookInfo" >
        <tr class="trColor">
            <td>${bookInfo.book_code}</td>
            <td>${bookInfo.book_type.type_name}</td>
            <td>${bookInfo.book_name}</td>
            <td>${bookInfo.book_author}</td>
            <td>${bookInfo.publish_press}</td>
            <td>${bookInfo.is_borrow}</td>
            <td><a href="selectOne?id=${bookInfo.book_id}" >详情</a></td>
            <td><a  href="javascript:void(0)" name="${bookInfo.book_id}" class="delete" >删除</a></td>
            <td><a href="/view/update.jsp?book_id=${bookInfo.book_id}&book_code=${bookInfo.book_code}&book_name=${bookInfo.book_name}&id=${bookInfo.book_type.id}&type_name=${bookInfo.book_type.type_name}&book_author=${bookInfo.book_author}&publish_press=${bookInfo.publish_press}&publish_date=${bookInfo.publish_date}&is_borrow=${bookInfo.is_borrow}" >修改</a></td>
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
