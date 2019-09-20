<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加</title>
    <script type="text/javascript" src="/js/jquery-3.2.1.js" ></script>
</head>
<body style="background-color: lightyellow">
<h1 align="center">图书借阅系统</h1>
<form action="add" method="post" >
    <input type="hidden" name="is_borrow" value="未借阅"/>
    <table border="1px" width="600px" align="center">
        <tr>
            <td>图书编号</td><td><input type="text" name="book_code" /></td>
        </tr>
        <tr>
            <td>图书名称</td><td><input type="text" name="book_name" /></td>
        </tr>
        <tr>
            <td>图书分类</td>
            <td>
                <select name="book_type">
                        <option value="1" >小说</option>
                        <option value="2" >文学</option>
                        <option value="3" >传记</option>
                        <option value="4" >艺术</option>
                        <option value="5" >少儿</option>
                        <option value="6" >经济</option>
                        <option value="7" >管理</option>
                        <option value="8" >科技</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>作者</td><td><input type="text" name="book_author"/></td>
        </tr>
        <tr>
            <td>出版社</td><td><input type="text" name="publish_press"/></td>
        </tr>
        <tr>
            <td>时间</td><td><input type="text" name="publish_date"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"/><input type="reset" value="重置"/></td>
        </tr>
    </table>
</form>
</body>
</html>