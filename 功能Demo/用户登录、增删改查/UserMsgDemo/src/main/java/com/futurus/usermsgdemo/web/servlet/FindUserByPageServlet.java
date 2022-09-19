package com.futurus.usermsgdemo.web.servlet;

import com.futurus.usermsgdemo.domain.PageBean;
import com.futurus.usermsgdemo.domain.User;
import com.futurus.usermsgdemo.service.UserService;
import com.futurus.usermsgdemo.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 1.获取参数
        String currentPage = request.getParameter("currentPage"); //当前页码
        String rows = request.getParameter("rows");//每页显示数

        if (currentPage == null || "".equals(currentPage) || Integer.parseInt(currentPage) <= 0) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "2";
        }

        // 获取条件查询参数
        Map<String, String[]> condition = request.getParameterMap();


        // 2.调用service查询
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage, rows, condition);
        System.out.println(pb);

        // 3.将PageBean存入reques
        request.setAttribute("pb", pb);
        request.setAttribute("condition",condition); //将查询条件存入request

        // 4.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
