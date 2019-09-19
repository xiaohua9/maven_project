package com.learn.controller;

import com.learn.entity.User;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Scope("prototype")
public class UserController {
    @Autowired
    private UserService userService;//注入子容器中的userService

    @RequestMapping("/view/list")
    public String listUser(ModelMap map){
        map.put("list",userService.findAll());
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
