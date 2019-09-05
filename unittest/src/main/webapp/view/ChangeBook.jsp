<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改</title>
    <script type="text/javascript" src="/js/jquery-3.2.1.js" ></script>
</head>
<h1 align="center">图书借阅系统</h1>
<body style="background-color: lightyellow">
<form action="BookInfoServlet" method="post"  >
    <input type="hidden" name="method" value="change"/><%--更新识别--%>
    <input type="hidden" name="is_borrow" value="<%=new String(request.getParameter("is_borrow").getBytes("iso-8859-1"),"utf-8") %>"/>
    <input type="hidden" name="book_id" value="${param.book_id}"/>

    <table border="1px" width="600px" align="center">
        <tr>
            <td>图书编号</td><td><input type="text" name="book_code" value="${param.book_code}" /></td>
        </tr>
        <tr>
            <td>图书名称</td><td><input type="text" name="book_name" value="<%=new String(request.getParameter("book_name").getBytes("iso-8859-1"),"utf-8") %>" /></td>
        </tr>
        <tr>
            <td>图书分类</td>
            <td>
                <select name="book_type">
                    <c:forEach items="${sessionScope.bookTypes}" var="bookType">
                        <c:if test="${bookType.id==param.book_type}">
                            <option value="${bookType.id}" selected="selected">${bookType.type_name}</option>
                        </c:if>
                        <c:if test="${bookType.id!=param.book_type}">
                            <option value="${bookType.id}" >${bookType.type_name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>作者</td><td><input type="text" name="book_author" value="<%=new String(request.getParameter("book_author").getBytes("iso-8859-1"),"utf-8") %>" /></td>
        </tr>
        <tr>
            <td>出版社</td><td><input type="text" name="publish_press" value="<%=new String(request.getParameter("publish_press").getBytes("iso-8859-1"),"utf-8") %>" /></td>
        </tr>
        <tr>
            <td>出版时间</td><td><input type="text" name="publish_date" value="<%=request.getParameter("publish_date") %>" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="修改"/><input type="reset" value="重置"/></td>
        </tr>
    </table>
</form>
</body>
</html>
