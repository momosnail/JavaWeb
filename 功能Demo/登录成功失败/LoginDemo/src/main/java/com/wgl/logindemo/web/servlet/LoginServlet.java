package com.wgl.logindemo.web.servlet;

import com.wgl.logindemo.dao.UserDao;
import com.wgl.logindemo.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 1.获取登录参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        // 2.执行登录请求
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);

        // 3.根据返回对象判断跳转页面
        if (user == null) {
            // 登录失败
            // 跳转到失败页面
            request.getRequestDispatcher("/failServlet").forward(request, response);
        } else {
            // 登录成功
            // 共享返回的user对象
            request.setAttribute("user", user);
            // 跳转到登录成功页面
            request.getRequestDispatcher("/successServlet").forward(request, response);
        }
    }
}
