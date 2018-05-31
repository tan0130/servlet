package ssh.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * create by tan on 2018/5/31
 * application测试
 **/
@WebServlet(name = "ApplicationServlet")
public class ApplicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application = this.getServletContext(); // 获取servletContext对象
        Integer number = (Integer)application.getAttribute("Count");
        if(null == number) {
            number = new Integer(1); // 实例化number对象
            application.setAttribute("Count", number);
        } else {
            number=new Integer(number.intValue()+1);
            application.setAttribute("Count", number);
        }
        Integer numberCount = (Integer)application.getAttribute("Count");
        application.setAttribute("number", numberCount);
        response.sendRedirect("/applicationtest.jsp");
    }
}
