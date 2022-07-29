package com.wgl.downloaddemo.web.servlet;

import com.wgl.downloaddemo.web.utils.DownLoadUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 1.获取参数
        String filename = request.getParameter("filename");

        // 2.把文件加载到内存
        ServletContext servletContext = this.getServletContext(); //获取到Servlet对象
        String realPath = servletContext.getRealPath("/img/" + filename);//获取文件服务器路径
        FileInputStream fis = new FileInputStream(realPath); //加载文件到内存

        // 3.设置响应头信息

        // 3.1设置响应头类型：content-type
        String mimeType = servletContext.getMimeType(filename);//获取文件Mime类型
        response.setHeader("content-type", mimeType);

        // 3.2设置响应头响应类型：content-disposition

        // 3.2.1为了兼容多浏览器 重新编码 filename
        String agent = request.getHeader("user-agent");
        filename = DownLoadUtils.getFileName(agent, filename); //工具类：针对不同的浏览器重新编码

        // 3.2.2
        response.setHeader("content-disposition", "attachment;filename=" + filename);

        // 4.把输入流写入到输出流中
        ServletOutputStream sos = response.getOutputStream(); //获取输出流对象

        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(buff)) != -1) {
            sos.write(buff, 0, len);
        }

        fis.close();

    }
}
