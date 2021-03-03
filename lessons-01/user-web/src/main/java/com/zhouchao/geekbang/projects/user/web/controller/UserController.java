package com.zhouchao.geekbang.projects.user.web.controller;

import com.zhouchao.geekbang.projects.user.domain.User;
import com.zhouchao.geekbang.projects.user.service.UserService;
import com.zhouchao.geekbang.projects.user.service.impl.UserServiceImpl;
import com.zhouchao.web.mvc.controller.MyPageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @Author zhouchao
 * @Date 2021/3/3 21:29
 * @Description
 **/
@Path("/user")
public class UserController implements MyPageController {

    @GET
    @Path("/to/register")
    public String toRegister(HttpServletRequest request, HttpServletResponse response){
        System.out.println("进入注册页面。。。");
        return "register.jsp";
    }

    @POST
    @Path("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));

        UserService userService = new UserServiceImpl();
        if (userService.register(user)){
            return "success.jsp";
        }
        return "fail.jsp";
    }
}
