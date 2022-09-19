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

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.设置编码
        request.setCharacterEncoding("utf-8");
        // 2.获取map
        Map<String, String[]> map = request.getParameterMap();
        // 3.封装对象
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("gender:"+user.getGender());
        System.out.println("address:"+user.getAddress());
        System.out.println("user:"+user.toString());
        // 4.调用Service修改
        UserService service=new UserServiceImpl();
        service.updateUser(user);
        // 5.跳转到查询所有Servlet
        response.sendRedirect(request.getContextPath()+"/FindUserByPageServlet");
    }
}
