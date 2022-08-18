package com.wgl.usermsglistdemo.web.servlet;

import com.wgl.usermsglistdemo.domain.User;
import com.wgl.usermsglistdemo.service.UserListService;
import com.wgl.usermsglistdemo.service.impl.UserListServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.查询数据数据
        UserListService userListService = new UserListServiceImpl();
        List<User> users = userListService.findAll();

        // 2.共享数据
        request.setAttribute("users", users);

        // 3.转发
        request.getRequestDispatcher("/list.jsp").forward(request, response);

    }
}
