package com.wgl.cookiedemo.web.servlet.demo1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Cookie快速入门
 * 注意：要在同一浏览器中演示
 * 原因：不同的的浏览器请求是两次不同的会话。所以Cookie会不一致。
 */
@WebServlet("/CookieServletDemo1")
public class CookieServletDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.创建Cookie对象
        Cookie c = new Cookie("msg", "hello");

        // 2.发送Cookie
        response.addCookie(c);
    }
}
