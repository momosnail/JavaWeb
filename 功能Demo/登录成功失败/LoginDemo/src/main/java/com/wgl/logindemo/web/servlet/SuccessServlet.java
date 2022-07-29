package com.wgl.logindemo.web.servlet;

import com.wgl.logindemo.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.设置编码
        response.setContentType("text/html;charset=utf-8");

        // 2.获取User对象
        User user = (User) request.getAttribute("user");
        String username = user.getUsername(); //获取用户名

        // 3.打印一句话
        response.getWriter().write("登录成功，欢迎"+username+"回来！");
    }
}
