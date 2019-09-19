package com.learn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.entity.User;
import com.learn.service.UserService;
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
public class UserController {
    @Autowired
    private UserService userService;//注入子容器中的userService

    @RequestMapping("/view/list")
    public String listUser(Integer page,ModelMap map){
        if (EmptyUtils.isEmpty(page)){
            page=1;
        }
        PageHelper.startPage(page,3,true);//分页设置
        List<User> users = userService.findAll();
        PageInfo<User> pageBean=new PageInfo<User>(users);
        map.put("pageBean",pageBean);
        return "list";
    }
    @RequestMapping("/view/delete")
    public void delete(int id, HttpServletResponse response){
        int delete = userService.delete(id);
        try {
            response.getWriter().print(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/view/add")
    public String addUser(User user) throws Exception{
        String s = new String(user.getName().getBytes("iso-8859-1"), "utf-8");
        user.setName(s);
        int insert = userService.insert(user);
        return "redirect:/view/list";
    }
    @RequestMapping("/view/update")
    public String updateUser(User user) throws Exception{
        String s = new String(user.getName().getBytes("iso-8859-1"), "utf-8");
        user.setName(s);
        System.out.println(user);
        int update = userService.update(user);
        return "redirect:/view/list";
    }
    @RequestMapping("/view/selectOne")
    public String selectOne(int id,ModelMap modelMap) throws Exception{
        User user = userService.findById(id);
        modelMap.put("user",user);
        return "one";
    }
}
