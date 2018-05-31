package ssh.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * create by tan on 2018/5/31
 * session
 * 1.测试session实现
 *
 * 销毁session:session对象默认30分钟没有使用服务器会自动销毁
 * 人工销毁：
 * 1.在web.xml中
 * 设置Session的有效时间:以分钟为单位
 * <session-config>
 *     <session-timeout>15</session-timeout>
 * </session-config>
 * 2.程序中
 * session.invalidate();
 **/
@WebServlet(name = "SessionServlet")
public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 使用request对象的getSession()获取session，如果不存在就创建一个
        HttpSession session = request.getSession();
        // 将数据存储到session中
        session.setAttribute("str", "中国");
        // 获取session的id
        String sessionId = session.getId();
        // 判断session是不是新建的
        if (session.isNew()) {
            response.getWriter().print("session创建成功，sessionId是：" + sessionId);
        } else {
            response.getWriter().print("服务器已存在该session, session的id是：" + sessionId);
        }
    }
}
