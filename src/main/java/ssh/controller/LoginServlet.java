package ssh.controller;

import org.springframework.beans.BeanUtils;
import ssh.entity.User;
import ssh.exception.UserException;
import ssh.formbean.LoginFormBean;
import ssh.service.UserService;
import ssh.service.UserServiceImpl;
import ssh.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * create by tan on 2018/5/11
 * 对登录界面的输入做校验
 **/
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 设置字符编码
        String id1 = request.getParameter("id"); // 获取登录id
        String password = request.getParameter("password"); // 获取登录密码
        String code = request.getParameter("code"); // 获取输入的验证码
        // String serverCode = request.getParameter("picCode");
        String checkImg_server=(String) request.getSession().getAttribute("picCode"); // 获取保存在session的picCode

        LoginFormBean formBean = WebUtil.request2Bean(request, LoginFormBean.class);

        // 对输入数据是否为空做校验
        if (!formBean.loginValidate()) {
            request.setAttribute("formBean", formBean);
            request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        } else if(!checkImg_server.equalsIgnoreCase(code)) {
            request.setAttribute("formBean", formBean);
            formBean.getErrors().put("error", "验证码不正确！");
            request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        }

        try {
            int id = Integer.parseInt(id1);
            UserService userService = new UserServiceImpl();
            User user = userService.login(id, password);
            // 登录成功，将user对象保存到session中
            request.getSession().setAttribute("user", user);
            // 表单数据填充javabean
            BeanUtils.copyProperties(user, formBean);
//			request.getRequestDispatcher("/pages/menu.jsp").forward(request, response);
            request.getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(request, response);
        } catch (UserException e) {
            if(e.getMessage().equals("用户不存在")) {
                formBean.getErrors().put("error", "用户不存在！");
            } else if(e.getMessage().equals("密码不正确")) {
                formBean.getErrors().put("error", "密码不正确！");
            }
            request.setAttribute("formBean", formBean);
            request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
