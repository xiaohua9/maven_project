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
                    $.post("PostInfoServlet",{"method":"delete","id":propName},function (data) {
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
                top: 125px;
                left: 300px;
            }
        </style>
    </head>
<body style="background-color: lightyellow">
<%--//空的话 ，去访问查全部组件--%>
<c:if test="${ empty requestScope.pageBean}">
    <script>location.href="/view/PostInfoServlet"</script>
</c:if>
<h1>论坛发帖列表</h1>
<form action="PostInfoServlet" method="post" id="queryForm"><%--使用一个表单实现分页选择的数据提交--%>
    <input type="hidden" name="page" id="page" value="1"/>
    标题：<input type="text" name="currentShortTitle" value="${requestScope.currentShortTitle}"/>
    板块:
    <select name="currentTopicId">
        <option value="">全部</option>
        <c:forEach items="${sessionScope.topics}" var="topic">
            <c:if test="${topic.topicId==requestScope.currentTopicId}">
                <option value="${topic.topicId}" selected="selected">${topic.topicName}</option>
            </c:if>
            <c:if test="${topic.topicId!=requestScope.currentTopicId}">
                <option value="${topic.topicId}" >${topic.topicName}</option>
            </c:if>
        </c:forEach>
    </select>
    点击数：<input type="text" name="currentBegain" value="${requestScope.currentBegain}" />到<input type="text" name="currentEnd" value="${requestScope.currentEnd}" />
    <input type="submit" value="查询"/>
</form>
<a href="/view/AddPostInfo.jsp" id="addStudent">发帖</a> <br/><br/>
<table border="2px" width="800px">
    <tr>
        <td>编号</td><td>标题</td><td>发帖时间</td><td>点击数</td><td>板块名称</td><td>删除</td><td>修改</td><td>详情</td>
    </tr>
    <c:forEach items="${requestScope.pageBean.postInfo}" var="postInfo" >
            <tr class="trColor">
                <td>${postInfo.id}</td>
                <td>${postInfo.title}</td>
                <td>${postInfo.postTime}</td>
                <td>${postInfo.clickNum}</td>
                <td>
                    <c:forEach items="${sessionScope.topics}" var="topic">
                        <c:if test="${topic.topicId==postInfo.topicId}">
                            ${topic.topicName}
                        </c:if>
                    </c:forEach>
                </td>
                <td><a  href="javascript:void(0)" name="${postInfo.id}" class="deleteOne" >删除</a></td>
                <td><a href="/view/ChangePostInfo.jsp?id=${postInfo.id}&title=${postInfo.title}&postTime=${postInfo.postTime}&clickNum=${postInfo.clickNum}&content=${postInfo.content}&topicId=${postInfo.topicId}&pic=${postInfo.pic}" >修改</a></td>
                <td><a href="PostInfoServlet?method=find&id=${postInfo.id}">详细</a></td>
            </tr>
    </c:forEach>
    <tr><td colspan="8" align="center">
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
