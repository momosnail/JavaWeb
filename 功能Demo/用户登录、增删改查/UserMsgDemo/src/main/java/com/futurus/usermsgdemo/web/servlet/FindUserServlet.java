package com.futurus.usermsgdemo.web.servlet;

import com.futurus.usermsgdemo.domain.User;
import com.futurus.usermsgdemo.service.UserService;
import com.futurus.usermsgdemo.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/FindUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取id
        String id = request.getParameter("id");
        // 2.调用Service查询
        UserService service = new UserServiceImpl();
        User user = service.findUserById(id);
        // 3.将user存入request
        request.setAttribute("user", user);
        // 4.转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }
}
