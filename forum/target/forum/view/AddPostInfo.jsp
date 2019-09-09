<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发帖</title>
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
            top: 240px;
        }
    </style>
</head>
<body style="background-color: lightyellow">
<h1>论坛发帖</h1>
<form action="PostInfoServlet" method="post" >
    <input type="hidden" name="method" value="add"/><%--添加识别--%>
    <input type="hidden" name="clickNum" value="0"/>
    <table border="1px" width="600px">
        <tr>
            <td>标题</td><td><input type="text" name="title" /></td>
        </tr>
        <tr>
            <td>帖子类别</td>
            <td>
                <select name="topicId">
                    <option value="1">娱乐</option>
                    <option value="2">军事</option>
                    <option value="3">科技</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>帖子内容</td><td><textarea name="content"></textarea></td>
        </tr>
        <tr>
            <td>发布时间</td><td><input type="text" name="postTime"/></td>
        </tr>
        <tr>
            <td>上传图片</td><td ><input type="hidden" name="pic" id="pic" /><img src="" id="picSrc" width="100px" height="100px" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="发帖"/><input type="reset" value="重置"/></td>
        </tr>
    </table>
</form>
<form method="post" enctype="multipart/form-data" id="fileForm">
    <input type="file" name="pic" id="picFile" />
</form>
</body>
</html>