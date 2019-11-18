package com.zl.controller;

import com.zl.pojo.User;
import com.zl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author fy
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService us ;
    @RequestMapping("/login")
    public ModelAndView login(HttpSession session, User loginUser, String isNo, HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        loginUser=us.login(loginUser);
        if (loginUser!=null){
            if ("yes".equals(isNo)){
                //创建cookie
                Cookie cName = null;
                try {
                    cName = new Cookie("cName", URLEncoder.encode(loginUser.getName(),"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Cookie cPwd = new Cookie("cPwd",loginUser.getPwd());

                //设置保存的最大期限
                cName.setMaxAge(10*24*60*60);
                cPwd.setMaxAge(10*24*60*60);

                //设置根路径
                cName.setPath("/springbootnews");
                cPwd.setPath("/springbootnews");
                //通过response发送给客户端
                response.addCookie(cName);
                response.addCookie(cPwd);
            }
            mv.setViewName("redirect:/news/show");
        }else {
            mv.addObject("error","用户名或者密码错误");
            mv.setViewName("redirect:/login.html");
        }
        session.setAttribute("user",loginUser);

        return mv;
    }

    @RequestMapping("/out")
    public ModelAndView out(HttpSession session){
        ModelAndView mv = new ModelAndView();
        session.invalidate();
        mv.setViewName("redirect:/login.html");
        return mv;
    }

    @RequestMapping("/loginJson")
    @ResponseBody
    public void loginJson(String name, HttpServletResponse response){
        User u = us.loginAjax(name);
        String mess = null;
        System.out.println(u);
        if (u == null) {
            mess = "{\"loginFlag\":true}";
        } else {
            mess = "{\"loginFlag\":false}";
        }
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(mess);
        out.flush();
        out.close();
    }

    @RequestMapping("/registerJson")
    @ResponseBody
    public void registerJson(String name, HttpServletResponse response){
        User u = us.registerAjax(name);
        String mess = null;
        System.out.println(u);
        if (u == null) {
            mess = "{\"registerFlag\":true}";
        } else {
            mess = "{\"registerFlag\":false}";
        }
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(mess);
        out.flush();
        out.close();
    }
}
