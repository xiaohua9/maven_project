package com.learn.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/*专门用于文件的上传处理的服务器组件*/
@WebServlet(name = "FileUploadServlet",urlPatterns = "/view/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置编码
        String upload = request.getServletContext().getRealPath("upload");//当前项目下的upload 绝对路径
        ServletFileUpload fileUpload=new ServletFileUpload(new DiskFileItemFactory());//文件上传对象
        List<FileItem> fileItems=null;
        try {
            fileItems = fileUpload.parseRequest(request);//解析多媒体请求
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        for (FileItem fileItem:fileItems){
            if (!fileItem.isFormField()){
                String newFileName = getFileName(fileItem.getName());//获取新名字
                File file=new File(upload+"/"+newFileName);
                try {
                    fileItem.write(file);//将数据写入文件
                    response.getWriter().print(newFileName);//把文件名返回给ajax请求
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    //构建新文件名
    public String getFileName(String fileName){
        String substring = fileName.substring(fileName.lastIndexOf("."));//得到扩展名
        String newFileName = UUID.randomUUID().toString() + substring;
        return newFileName;
    }
}
