package com.learn.web;

import com.learn.entity.BookInfo;
import com.learn.entity.BookType;
import com.learn.service.BookInfoServiceI;
import com.learn.service.BookTypeServiceI;
import com.learn.service.impl.BookInfoServiceImpl;
import com.learn.service.impl.BookTypeServiceImpl;
import com.learn.utils.EmptyUtils;
import com.learn.utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookInfoServlet" ,urlPatterns = "/view/BookInfoServlet")
public class BookInfoServlet extends HttpServlet {
    BookInfoServiceI bookInfoService=new BookInfoServiceImpl();//获取书服务层对象
    BookTypeServiceI bookTypeService=new BookTypeServiceImpl();//获取类型服务对象
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将前端的请求数据解码
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");//获取识别码
        if ("add".equals(method)){
            this.doAdd(request,response);
        }else if ("delete".equals(method)){
            this.doDelete(request,response);
        } else if ("change".equals(method)){
            this.doChange(request,response);
        }else if("find".equals(method)){
            this.doFind(request,response);
        }else {
            this.doFindAll(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    //增加服务中心
    protected void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到表单提交的值，并构造对象
        String book_code = req.getParameter("book_code");
        String book_name = req.getParameter("book_name");
        int book_type = Integer.parseInt(req.getParameter("book_type"));
        String book_author = req.getParameter("book_author");
        String publish_press = req.getParameter("publish_press");
        String publish_date = req.getParameter("publish_date");
        String is_borrow = req.getParameter("is_borrow");
        BookInfo bookInfo=new BookInfo(book_code,book_name,book_type,book_author,publish_press,publish_date,is_borrow);//新增对象
        int flag = this.bookInfoService.insert(bookInfo);//将数据加入数据库，flag接收影响的行数
        if (flag>0){
            //插入成功
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            resp.getWriter().print("<script>alert('新增成功');location.href='/view/BookInfoServlet'</script>");
        }else {
            resp.setContentType("text/html;charset=UTF-8");//设置返回内容的编码格式
            //给出提示
            resp.getWriter().print("<script>alert('新增失败');location.href='/view/BookInfoServlet'</script>");
        }
    }
    //删除服务中心
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");//获取超链接提交的需要删除
        BookInfo bookInfo = new BookInfo(Integer.parseInt(id));//构造临时
        int delete = this.bookInfoService.delete(bookInfo);//删除
        resp.getWriter().print(delete);//将影响的行数回应给ajax请求
    }
    //更改服务中心
    protected void doChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到表单提交的值，并构造对象
        int book_id = Integer.parseInt(req.getParameter("book_id"));
        String book_code = req.getParameter("book_code");
        String book_name = req.getParameter("book_name");
        int book_type = Integer.parseInt(req.getParameter("book_type"));
        String book_author = req.getParameter("book_author");
        String publish_press = req.getParameter("publish_press");
        String publish_date = req.getParameter("publish_date");
        String is_borrow = req.getParameter("is_borrow");
        BookInfo bookInfo=new BookInfo(book_id,book_code,book_name,book_type,book_author,publish_press,publish_date,is_borrow);//对象
        int update = this.bookInfoService.update(bookInfo);//修改
        if (update>0){
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            resp.getWriter().print("<script>alert('修改成功');location.href='/view/BookInfoServlet'</script>");
        }else {
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            //提示错误信息
            resp.getWriter().print("<script>alert('修改失败');location.href='/view/BookInfoServlet'</script>");
        }
    }
    //查找全部
    protected void doFindAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageBean pageBean = new PageBean();//构造一个页面显示信息类对象
        String currentBookType = req.getParameter("currentBookType");//获取当前条件
        String currentBookName = req.getParameter("currentBookName");
        String currentIsBorrow = req.getParameter("currentIsBorrow");
        if (EmptyUtils.isEmpty(currentIsBorrow)){//默认显示点击数1-100
            currentIsBorrow="未借阅";
        }
        pageBean.setRows(this.bookInfoService.selectCount(currentBookType,currentBookName,currentIsBorrow));//获取总行数，并赋值,必须要放在这个位置，至少不能放在当前页数设置后面
        String page = req.getParameter("page");//获取前端提交过来的页数
        if (!EmptyUtils.isEmpty(page)){//在不为空的情况下进行当前页数设置
            int i = Integer.parseInt(page);
            pageBean.setCurrentPage(i);
        }
        List<BookInfo> bookInfos = this.bookInfoService.selectAll(pageBean.getCurrentPage(),pageBean.getPageSize(),currentBookType,currentBookName,currentIsBorrow);//获取分页数据
        pageBean.setBookInfos(bookInfos);//将查到的数据赋值进页面对象
        List<BookType> bookTypes = this.bookTypeService.selectAll();//获取所有类型
        req.getSession().setAttribute("bookTypes",bookTypes);//包装类型数据,用于条件选择
        req.setAttribute("pageBean",pageBean);//包装页面数据和查询结果
        req.setAttribute("currentBookType",currentBookType);//将前端提交的条件返回用于回显
        req.setAttribute("currentBookName",currentBookName);
        req.setAttribute("currentIsBorrow",currentIsBorrow);
        req.getRequestDispatcher("/view/BookInfo.jsp").forward(req,resp);
    }
    //查找一个
    protected void doFind(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        BookInfo bookInfo = bookInfoService.select(id);
        System.out.println(bookInfo);
        req.setAttribute("bookInfo",bookInfo);
        req.getRequestDispatcher("/view/OneBook.jsp").forward(req,resp);
    }
}
