package com.wgl.downloaddemo.web.utils;

//import sun.misc.BASE64Encoder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * 多浏览器兼容中文工具类
 */
public class DownLoadUtils {

    public static String getFileName(String agent, String filename) throws UnsupportedEncodingException {
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            Base64.Encoder encoder = Base64.getEncoder();
            filename = "=?utf-8?B?" + encoder.encodeToString(filename.getBytes("utf-8")) + "?=";
            /*
            // 旧代码
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
            */
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}
