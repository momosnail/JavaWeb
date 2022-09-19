package com.futurus.usermsgdemo.web.servlet;

import com.futurus.usermsgdemo.domain.User;
import com.futurus.usermsgdemo.service.UserService;
import com.futurus.usermsgdemo.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 2.获取参数
        Map<String, String[]> map = request.getParameterMap();

        // 3.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 4.调用Service保存
        UserService userService=new UserServiceImpl();
        userService.addUser(user);

        // 5.跳转到userListServlet
        response.sendRedirect(request.getContextPath()+"/FindUserByPageServlet");
    }
}
