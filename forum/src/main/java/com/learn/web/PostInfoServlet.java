package com.learn.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.entity.PostInfo;
import com.learn.entity.Topic;
import com.learn.mapper.PostInfoMapperI;
import com.learn.utils.EmptyUtils;
import com.learn.utils.GetSession;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PostInfoServlet",urlPatterns = "/view/PostInfoServlet")
public class PostInfoServlet extends HttpServlet {
    SqlSession session = GetSession.getSession();//得到数据库操作的持久化对象
    PostInfoMapperI mapper = session.getMapper(PostInfoMapperI.class);//动态实现类
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
        String title = req.getParameter("title");
        String postTime = req.getParameter("postTime");
        int clickNum = Integer.parseInt(req.getParameter("clickNum"));
        String content = req.getParameter("content");
        int topicId = Integer.parseInt(req.getParameter("topicId"));
        Topic topic=new Topic();
        if (topicId==1){
            topic.setTopicId(1);
            topic.setTopicName("娱乐");
        }else if (topicId==2){
            topic.setTopicId(2);
            topic.setTopicName("军事");
        }else if (topicId==3){
            topic.setTopicId(3);
            topic.setTopicName("科技");
        }
        String pic = req.getParameter("pic");
        PostInfo postInfo=new PostInfo(title,postTime,clickNum,content,topic,pic);//新增对象
        int flag = this.mapper.insert(postInfo);//将数据加入数据库，flag接收影响的行数
        session.commit();
        if (flag>0){
            //插入成功
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            resp.getWriter().print("<script>alert('新增成功');location.href='/view/PostInfoServlet'</script>");
        }else {
            resp.setContentType("text/html;charset=UTF-8");//设置返回内容的编码格式
            //给出提示
            resp.getWriter().print("<script>alert('新增失败');location.href='/view/PostInfoServlet'</script>");
        }
    }
    //删除服务中心
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");//获取超链接提交的需要删除的学号
        PostInfo postInfo = new PostInfo(Integer.parseInt(id));//构造临时贴
        int delete = this.mapper.delete(postInfo);//删除
        session.commit();
        resp.getWriter().print(delete);//将影响的行数回应给ajax请求
    }
    //更改服务中心
    protected void doChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到表单提交的值，并构造对象
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String postTime = req.getParameter("postTime");
        int clickNum = Integer.parseInt(req.getParameter("clickNum"));
        String content = req.getParameter("content");
        int topicId = Integer.parseInt(req.getParameter("topicId"));
        String pic = req.getParameter("pic");
        Topic topic=new Topic();
        if (topicId==1){
            topic.setTopicId(1);
            topic.setTopicName("娱乐");
        }else if (topicId==2){
            topic.setTopicId(2);
            topic.setTopicName("军事");
        }else if (topicId==3){
            topic.setTopicId(3);
            topic.setTopicName("科技");
        }
        PostInfo postInfo=new PostInfo(id,title,postTime,clickNum,content,topic,pic);//构造对象
        int update = this.mapper.update(postInfo);//修改
        session.commit();
        if (update>0){
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            resp.getWriter().print("<script>alert('修改成功');location.href='/view/PostInfoServlet'</script>");
        }else {
            resp.setContentType("text/html;charset=UTF-8");//设置回应数据的编码
            //提示错误信息
            resp.getWriter().print("<script>alert('修改失败');location.href='/view/PostInfoServlet'</script>");
        }
    }
    //查找全部
    protected void doFindAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentShortTitle = req.getParameter("currentShortTitle");//获取当前条件
        String currentTopicId = req.getParameter("currentTopicId");
        String currentBegain = req.getParameter("currentBegain");
        String currentEnd = req.getParameter("currentEnd");
        if (EmptyUtils.isEmpty(currentBegain)){//默认显示点击数1-100
            currentBegain=""+0;
        }
        if (EmptyUtils.isEmpty(currentEnd)){
            currentEnd=""+100;
        }
        String page = req.getParameter("page");//获取前端提交过来的页数
        int i=1;
        if (!EmptyUtils.isEmpty(page)){
            i=Integer.parseInt(page);
        }
        PageHelper.startPage(i,3,true);//分页设置
        List<PostInfo> postInfos = this.mapper.selectAll(currentShortTitle,currentTopicId,currentBegain,currentEnd);//获取分页数据
        PageInfo<PostInfo> pageBean=new PageInfo<>(postInfos);
        req.setAttribute("pageBean",pageBean);//包装页面数据和查询结果
        req.setAttribute("currentShortTitle",currentShortTitle);//将前端提交的条件返回用于回显
        req.setAttribute("currentTopicId",currentTopicId);
        req.setAttribute("currentBegain",currentBegain);
        req.setAttribute("currentEnd",currentEnd);
        req.getRequestDispatcher("/view/PostInfo.jsp").forward(req,resp);
    }
    //查找一个
    protected void doFind(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PostInfo postInfo = this.mapper.select(id);
        System.out.println(postInfo);
        req.setAttribute("postInfo",postInfo);
        req.getRequestDispatcher("/view/OnePostInfo.jsp").forward(req,resp);
    }
}



