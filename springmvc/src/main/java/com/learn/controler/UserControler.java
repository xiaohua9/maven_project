package com.learn.controler;
import com.learn.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
@Controller//注册到框架中
@SessionAttributes(names = "user")//将user加入request时，同时加入session
@Scope("prototype")//将默认的单例改成多例
public class UserControler {
    @RequestMapping("/view/login")//请求映射
    public String login(User user, ModelMap modelMap) throws Exception{
        /*先解决乱码问题*/
        String userName=new String(user.getUserName().getBytes("iso-8859-1"),"utf-8");
        String password=new String(user.getPassword().getBytes("iso-8859-1"),"utf-8");
        modelMap.put("user",new User(userName,password));
        /*return "redirect:/view/success.jsp";*///重定向
        return "success";//转发
    }
}
