package com.futurus.usermsgdemo.web.servlet;

import com.futurus.usermsgdemo.service.UserService;
import com.futurus.usermsgdemo.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/DelSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取所有id
        String[] ids = request.getParameterValues("uid");

        if (ids != null && ids.length > 0) {
            // 2.调用service删除
            UserService service = new UserServiceImpl();
            service.delSelectedUser(ids);

            // 3.跳转到查询所有的Servlet
            response.sendRedirect(request.getContextPath() + "/FindUserByPageServlet");
        } else {
            System.out.println("no select !!!");
        }


    }
}
