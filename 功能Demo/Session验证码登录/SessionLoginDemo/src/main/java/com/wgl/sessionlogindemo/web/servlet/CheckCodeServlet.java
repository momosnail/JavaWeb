package com.wgl.sessionlogindemo.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        Random random = new Random();

        // 1.初始化图片对象
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 2.初始化画笔
        Graphics graphics = img.getGraphics();

        // 3.给图片填充背景
        graphics.setColor(Color.PINK); //给画笔设置颜色
        graphics.fillRect(0, 0, width, height); //给背景设置颜色

        // 4.给图片添加边框
        graphics.setColor(Color.BLUE); //给画笔设置颜色
        graphics.drawRect(0, 0, width - 1, height - 1); //给图像画边框(因为边框自己宽度默认为1，所以需要减去1)

        // 5.添加验证码数字或字母
        graphics.setColor(Color.RED); //给画笔设置颜色
        String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcefghijklmnopqrstuvwxyz0123456789"; //验证码随机的内容
        StringBuilder sb=new StringBuilder();
        for (int i = 1; i <= 4; i++) { //生成由4个字符组成的验证码
            int index = random.nextInt(text.length()); //在text的长度中随机一个数字作为角标
            char c = text.charAt(index); //随机生层的字符
            graphics.drawString(Character.toString(c), width / 5 * i, height / 2); //用画笔把字符画到指定位置

            sb.append(c);
        }
        String checkCode_session = sb.toString();
        request.getSession().setAttribute("checkCode_session",checkCode_session);

        // 6.画验证码干扰线
        graphics.setColor(Color.GREEN); //给画笔设置颜色
        for (int i = 0; i < 6; i++) { //随机画6条干扰线
            int start_x = random.nextInt(width); //随机生成干扰线起点的x坐标
            int start_y = random.nextInt(height); //随机生成干扰线起点的y坐标
            int end_x = random.nextInt(width); //随机生成干扰线终点的x坐标
            int end_y = random.nextInt(height); //随机生成干扰线终点的y坐标
            graphics.drawLine(start_x, start_y, end_x, end_y);
        }

        // 7.把图片转换为输出流 formatName格式：gif、GIF、png、PNG、JPEG、jpeg、JPG、jpg、bmp、BMP、wbmp、WBMP、tif、TIF、tiff、TIFF
        ImageIO.write(img, "png", response.getOutputStream());

    }
}
