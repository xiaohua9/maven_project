<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改</title>
    <script type="text/javascript" src="/js/jquery-3.2.1.js" ></script>
    <script type="text/javascript">
        $(function () {
            $("#picFile").change(function () {
                //在文件表单值改变的时候处理
                var formData = new FormData($("#fileForm")[0]);//先将表单数据封装到变量中,再发ajax 请求
                $.ajax({
                    url:"FileUploadServlet",
                    type:"post",
                    data:formData,
                    contentType: false,
                    processData: false,
                    success:function (data) {//上传成功
                        $("#pic").val(data);//把返回的文件名赋值给文件名提交表单
                        $("#picSrc").prop("src","/upload/"+data);//让上传的图片得到回显
                    }
                });
            });
        });
    </script>
    <style type="text/css">/*对位置进行调整*/
        #picSrc{
            position: relative;
            left: 300px;
        }
        #fileForm{
            z-index: 2;
            position: absolute;
            left: 200px;
            top: 200px;
        }
    </style>
</head>
<h1>论坛修改</h1>
<body style="background-color: lightyellow">
<form action="PostInfoServlet" method="post" >
    <input type="hidden" name="method" value="change"/><%--更新识别--%>
    <input type="hidden" name="id" value="${param.id}"/>
    <input type="hidden" name="clickNum" value="${param.clickNum}"/>
    <input type="hidden" name="postTime" value="${param.postTime}"/>

    <table border="1px" width="600px">
        <tr>
            <td>标题</td><td><input type="text" name="title" value="<%=new String(request.getParameter("title").getBytes("iso-8859-1"),"utf-8") %>" /></td>
        </tr>
        <tr>
            <td>帖子类别</td>
            <td>
                <select name="topicId">
                    <c:if test="${1==param.topicId}">
                        <option value="1" selected="selected" >娱乐</option>
                    </c:if>
                    <c:if test="${1!=param.topicId}">
                        <option value="1" >娱乐</option>
                    </c:if>
                    <c:if test="${2==param.topicId}">
                        <option value="2" selected="selected" >军事</option>
                    </c:if>
                    <c:if test="${2!=param.topicId}">
                        <option value="2" >军事</option>
                    </c:if>
                    <c:if test="${3==param.topicId}">
                        <option value="3" selected="selected" >科技</option>
                    </c:if>
                    <c:if test="${1!=param.topicId}">
                        <option value="3" >科技</option>
                    </c:if>
                </select>
            </td>
        </tr>
        <tr>
            <td>帖子内容</td><td><textarea name="content"><%=new String(request.getParameter("content").getBytes("iso-8859-1"),"utf-8") %></textarea></td>
        </tr>
        <tr>
            <td>上传图片</td><td><input type="hidden" name="pic" id="pic" value="${param.pic}" /><img src="/upload/${param.pic}" id="picSrc" width="100px" height="100px"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="修改"/><input type="reset" value="重置"/></td>
        </tr>
    </table>
</form>
<form method="post" enctype="multipart/form-data" id="fileForm">
    <input type="file" name="pic" id="picFile" />
</form>
</body>
</html>
