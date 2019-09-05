<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <head>
        <title>论坛中心</title>
        <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
        <script type="text/javascript">
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
            $(function () {/*将插入的数据以奇数行显示浅绿色*/
                $(".trColor:odd").css({"background-color":"lightgreen"});
            })
            $(function () {//ajax请求实现删除一行记录的局部刷新
                $(".deleteOne").click(function () {
                    var obj=$(this);
                    var propName = obj.prop("name");
                    $.post("BookInfoServlet",{"method":"delete","id":propName},function (data) {
                        var number = parseInt(data);
                        if (number>0){
                            obj.parents("tr").remove();
                            alert("删除成功");
                        }else {
                            alert("删除失败");
                        }
                    });
                });
            });
        </script>
        <style>
            #addStudent{
                z-index: 2;
                position: absolute;
                top: 115px;
                left: 1200px;
                font-size: 30px;
            }
        </style>
    </head>
<body style="background-color: lightyellow">
<%--//空的话 ，去访问查全部组件--%>
<c:if test="${ empty requestScope.pageBean}">
    <script>location.href="/view/BookInfoServlet"</script>
</c:if>
<h1 align="center">图书借阅系统</h1>
<form align="center" action="BookInfoServlet" method="post" id="queryForm" ><%--使用一个表单实现分页选择的数据提交--%>
    <input type="hidden" name="page" id="page" value="1"/>
    图书分类:
    <select name="currentBookType">
        <option value="">全部</option>
        <c:forEach items="${sessionScope.bookTypes}" var="bookType">
            <c:if test="${bookType.id==requestScope.currentBookType}">
                <option value="${bookType.id}" selected="selected">${bookType.type_name}</option>
            </c:if>
            <c:if test="${bookType.id!=requestScope.currentBookType}">
                <option value="${bookType.id}" >${bookType.type_name}</option>
            </c:if>
        </c:forEach>
    </select>
    图书名称：<input type="text" name="currentBookName" value="${requestScope.currentBookName}"/>
    是否借阅：
    <select name="currentIsBorrow">
        <option value="未借阅">请选择</option>
            <c:if test="${未借阅==requestScope.currentIsBorrow}">
                <option value="未借阅" selected="selected">未借阅</option>
            </c:if>
            <c:if test="${未借阅!=requestScope.currentIsBorrow}">
                <option value="未借阅" >未借阅</option>
            </c:if>
            <c:if test="${已借阅==requestScope.currentIsBorrow}">
                <option value="已借阅" selected="selected" >已借阅</option>
            </c:if>
            <c:if test="${已借阅!=requestScope.currentIsBorrow}">
                <option value="已借阅"  >已借阅</option>
            </c:if>
    </select>
    <input type="submit" value="查询"/>
</form>
<a href="/view/AddBook.jsp" id="addStudent" >添加</a> <br/><br/>
<table border="2px" width="800px" align="center">
    <tr>
        <td>图书编号</td><td>图书分类</td><td>图书名称</td><td>作者</td><td>出版社</td><td>操作</td><td>详情</td><td>删除</td><td>修改</td>
    </tr>
    <c:forEach items="${requestScope.pageBean.bookInfos}" var="bookInfo" >
        <tr class="trColor">
            <td>${bookInfo.book_code}</td>
            <td>
                <c:forEach items="${sessionScope.bookTypes}" var="bookType">
                    <c:if test="${bookType.id==bookInfo.book_type}">
                        ${bookType.type_name}
                    </c:if>
                </c:forEach>
            </td>
            <td>${bookInfo.book_name}</td>
            <td>${bookInfo.book_author}</td>
            <td>${bookInfo.publish_press}</td>
            <td>${bookInfo.is_borrow}</td>
            <td><a href="BookInfoServlet?method=find&id=${bookInfo.book_id}">详细</a></td>
            <td><a  href="javascript:void(0)" name="${bookInfo.book_id}" class="deleteOne" >删除</a></td>
            <td><a href="/view/ChangeBook.jsp?book_id=${bookInfo.book_id}&book_code=${bookInfo.book_code}&book_name=${bookInfo.book_name}&book_type=${bookInfo.book_type}&book_author=${bookInfo.book_author}&publish_press=${bookInfo.publish_press}&publish_date=${bookInfo.publish_date}&is_borrow=${bookInfo.is_borrow}" >修改</a></td>
        </tr>
    </c:forEach>
    <tr><td colspan="9" align="center">
        <a  href="javascript:void(0)"  name="1" class="pageChoose" >首页</a>
        <a  href="javascript:void(0)"  name="${requestScope.pageBean.currentPage-1}" class="pageChoose" >上一页</a>
        <a  href="javascript:void(0)"  name="${requestScope.pageBean.currentPage+1}" class="pageChoose" >下一页</a>
        <a  href="javascript:void(0)"  name="${requestScope.pageBean.totalPages}" class="pageChoose" >尾页</a>
        ${requestScope.pageBean.currentPage}/${requestScope.pageBean.totalPages}页
        <input type="text" id="goto" value="${requestScope.pageBean.currentPage}" style="width: 70px"/><input type="button" value="跳转" id="buttonGo" />
    </td></tr>
</table>
</body>
</html>
