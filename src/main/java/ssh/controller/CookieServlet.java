package ssh.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * create by tan on 2018/5/31
 * Cookie:客户端技术，程序吧每个用户的数据以cookie的形式写给用户各自的浏览器
 * 1.使用Cookie记录用户上一次访问的时间
 *
 **/
@WebServlet(name = "CookieServlet")
public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置服务端以UTF-8编码进行输出
        response.setCharacterEncoding("UTF-8");
        //设置浏览器以UTF-8编码进行接收,解决中文乱码问题
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        // 获取浏览器访问服务器传递过来的cookie数组
        Cookie[] cookies = request.getCookies();
        // 如果用户是第一次访问，那么得到的cookies将是null
        if (null != cookies) {
            out.write("上次的访问时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
              //  out.write(URLDecoder.decode(cookies[i].getValue(), "UTF-8")); 显示中文，要进行解码操作
                if ("lastAccessTime".equals(cookie.getName())) {
                    Long lsatAccessTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lsatAccessTime);
                    out.write(date.toLocaleString());
                }
            }

        } else {
            out.write("第一次访问本站");
        }

        // 用户访问过后重新设置访问时间，存储cookie中，然后发送到客户端浏览器
        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + ""); // 新建一个cookie

       // Cookie cookie1 = new Cookie("userName", URLEncoder.encode("中国", "utf-8")); 中文显示
        // 设置cookie的有效期为1天，如果未设置有效期，关闭浏览器之后就失效了，设置有效期，在关闭浏览器之后仍然有效
         cookie.setMaxAge(24 * 60 * 60);
        // cookie.setMaxtAge(0); // 删除cookie的操作
        // 将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端
        response.addCookie(cookie);
       // response.addCookie(cookie1);
    }
}
