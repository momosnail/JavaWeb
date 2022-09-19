package com.futurus.usermsgdemo.web.servlet;

import com.futurus.usermsgdemo.domain.SystemUser;
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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.设置编码
        request.setCharacterEncoding("utf-8");

        // 2.获取数据
        String verifycode = request.getParameter("verifycode");
        Map<String, String[]> map = request.getParameterMap();

        // 3.校验验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER"); //确保验证码一次性

        if (!verifycode.equalsIgnoreCase(checkcode_server)) { //忽略大小写比较验证码
            // 验证码不正确
            // 提示信息
            request.setAttribute("login_error", "验证码不正确！！！");
            // 跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // 4.封装user对象
        SystemUser user = new SystemUser();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 5.调用Service查询
        UserService userService = new UserServiceImpl();
        SystemUser loginUser = userService.login(user);

        // 6.判断是否登录成功
        if (loginUser == null) {
            // 登录失败
            // 提示信息
            request.setAttribute("login_error", "用户名或密码不正确！！！");
            // 跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            // 登录成功
            // 1.将用户存入session
            session.setAttribute("user", loginUser);

            // 2.跳转页面
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
