package com.wgl.cookiedemo.web.servlet.demo2;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/CookieServlet3")
public class CookieServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的消息体的数据格式以及编码
        response.setContentType("text/html;charset=utf-8");

        // 1.获取所有Cookie
        Cookie[] cookies = request.getCookies();
        boolean flag = false; //没有cookie为lastTime

        // 2.遍历Cookie数组
        for (Cookie cookie : cookies) {
            // 3.获取cookie的名称
            String name = cookie.getName();

            // 4.判断名称是否是：lastTime
            if ("lastTime".equals(name)) {
                // 有该cookie ，不是第一次访问
                flag = true; //有lastTime的cookie

                // 设置cookie的value值
                // 获取当前世界的字符串，重新设置cookie的值，重新发送cookie
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String str_date = sdf.format(date);
                System.out.println("编码前：" + str_date);
                str_date = URLEncoder.encode(str_date, "utf-8"); //URL编码
                System.out.println("编码后：" + str_date);

                cookie.setValue(str_date);
                //设置cookie的存活时间
                cookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(cookie);

                // 响应数目
                // 获取cookie的value，时间
                String value = cookie.getValue();
                System.out.println("解码前："+value);
                // URL解码
                value = URLDecoder.decode(value, "utf-8");
                System.out.println("解码后："+value);

                response.getWriter().write("<h1>欢迎回来，您上次的访问时间为：" + value + "</h1>");

                break;

            }
        }

        if (cookies == null || cookies.length == 0 || !flag) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str_date = sdf.format(date);
            System.out.println("编码前：" + str_date);
            str_date = URLEncoder.encode(str_date, "utf-8"); //URL编码
            System.out.println("编码后：" + str_date);
            Cookie cookie = new Cookie("lastTime", str_date);
            //设置cookie的存活时间
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);

            response.getWriter().write("<h1>您好，欢迎首次登录</h1>");

        }

    }
}
