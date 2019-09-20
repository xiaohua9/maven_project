package com.learn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.entity.BookInfo;
import com.learn.entity.BookType;
import com.learn.service.BookInfoService;
import com.learn.utils.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class BookInfoController {
    @Autowired
    private BookInfoService bookInfoService;
    @RequestMapping("/view/list")
    public String listUser(Integer page,String currentBookType,String currentBookName,String currentIsBorrow,ModelMap map) throws Exception{
        if (currentBookType==null){
            currentBookType="";
        }
        if (currentBookName==null){
            currentBookName="";
        }
        if (currentIsBorrow==null){
            currentIsBorrow="";
        }
        if (EmptyUtils.isEmpty(page)){
            page=1;
        }
        PageHelper.startPage(page,3,true);//分页设置
        List<BookInfo> bookInfos = bookInfoService.selectAll(currentBookType, new String(currentBookName.getBytes("iso-8859-1"), "utf-8"),  new String(currentIsBorrow.getBytes("iso-8859-1"), "utf-8"));
        PageInfo<BookInfo> pageBean=new PageInfo<BookInfo>(bookInfos);
        map.put("pageBean",pageBean);
        map.put("currentBookType",currentBookType);
        map.put("currentBookName",new String(currentBookName.getBytes("iso-8859-1"), "utf-8"));
        map.put("currentIsBorrow", new String(currentIsBorrow.getBytes("iso-8859-1"), "utf-8"));
        return "list";
    }
    @RequestMapping("/view/delete")
    public void delete(int id, HttpServletResponse response){
        int delete = bookInfoService.delete(id);
        try {
            response.getWriter().print(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/view/add")
    public String addUser(String book_code,String book_name,String book_type,String book_author,String publish_press,String publish_date,String is_borrow) throws Exception{
        BookInfo bookInfo=new BookInfo();
        bookInfo.setBook_code(book_code);
        bookInfo.setBook_name(new String(book_name.getBytes("iso-8859-1"), "utf-8"));
        int i = Integer.parseInt(book_type);
        bookInfo.setBook_type(new BookType(i));
        bookInfo.setBook_author(new String(book_author.getBytes("iso-8859-1"), "utf-8"));
        bookInfo.setPublish_press(new String(publish_press.getBytes("iso-8859-1"), "utf-8"));
        bookInfo.setPublish_date(publish_date);
        bookInfo.setIs_borrow(new String(is_borrow.getBytes("iso-8859-1"), "utf-8"));
        int insert = bookInfoService.insert(bookInfo);
        if (insert>0){
            return "redirect:/view/list.jsp?flag=1";
        }else {
            return "redirect:/view/list.jsp?flag=2";
        }
    }
    @RequestMapping("/view/update")
    public String updateUser(int book_id,String book_code,String book_name,String book_type,String book_author,String publish_press,String publish_date,String is_borrow) throws Exception{
        BookInfo bookInfo=new BookInfo();
        bookInfo.setBook_id(book_id);
        bookInfo.setBook_code(book_code);
        bookInfo.setBook_name(new String(book_name.getBytes("iso-8859-1"), "utf-8"));
        int i = Integer.parseInt(book_type);
        bookInfo.setBook_type(new BookType(i));
        bookInfo.setBook_author(new String(book_author.getBytes("iso-8859-1"), "utf-8"));
        bookInfo.setPublish_press(new String(publish_press.getBytes("iso-8859-1"), "utf-8"));
        bookInfo.setPublish_date(publish_date);
        bookInfo.setIs_borrow(new String(is_borrow.getBytes("iso-8859-1"), "utf-8"));
        int update = bookInfoService.update(bookInfo);
        if (update>0){
            return "redirect:/view/list.jsp?flag=3";
        }else {
            return "redirect:/view/list.jsp?flag=4";
        }
    }
    @RequestMapping("/view/selectOne")
    public String selectOne(int id,ModelMap modelMap) throws Exception{
        BookInfo select = bookInfoService.select(id);
        modelMap.put("bookInfo",select);
        return "one";
    }
}
