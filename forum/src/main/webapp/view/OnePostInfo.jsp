<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详细</title>
</head>
<body style="background-color: lightyellow">
<form action="PostInfoServlet" method="post" >
    <input type="hidden" name="method" value="change"/><%--更新识别--%>
    <input type="hidden" name="id" value="${requestScope.postInfo.id}"/>
    <input type="hidden" name="title" value="${requestScope.postInfo.title}"/>
    <input type="hidden" name="postTime" value="${requestScope.postInfo.postTime}"/>
    <input type="hidden" name="clickNum" value="${requestScope.postInfo.clickNum+1}"/><%--点击数加1--%>
    <input type="hidden" name="content" value="${requestScope.postInfo.content}"/>
    <input type="hidden" name="topicId" value="${requestScope.postInfo.topicId}"/>
    <input type="hidden" name="pic" value="${requestScope.postInfo.pic}"/>
    <h1>显示信息</h1>
    <table border="1px" width="600px">
        <tr>
            <td>标题</td><td>${requestScope.postInfo.title}</td>
        </tr>
        <tr>
            <td>帖子类别</td>
            <td>
                    <c:forEach items="${sessionScope.topics}" var="topic">
                        <c:if test="${topic.topicId==requestScope.postInfo.topicId}">
                            ${topic.topicName}
                        </c:if>
                    </c:forEach>
            </td>
        </tr>
        <tr>
            <td>帖子内容</td><td>${requestScope.postInfo.content}</td>
        </tr>
        <tr>
            <td>上传图片</td><td><img src="/upload/${requestScope.postInfo.pic}" width="100px" height="100px"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="返回列表" /></td>
        </tr>
    </table>
</form>
</body>
</html>
