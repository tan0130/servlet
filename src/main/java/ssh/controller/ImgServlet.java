package ssh.controller;

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

/**
 * create by tan on 2018/6/1
 * 生成验证码图片
 **/
@WebServlet(name = "ImgServlet")
public class ImgServlet extends HttpServlet {

    private static final int width = 114;
    private static final int height = 42;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.getParameter("code");

        /**
         * 建立图像缓冲区
         * 建立绘制图像  Graphics
         * 获取颜色
         * 设置图片位置及大小
         * */
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = bi.getGraphics();
        Color c = new Color(255, 157, 2);
        g.setColor(c);
        g.fillRect(0, 0, width, height); // 填充指定的矩形

        /**
         * 设置随机产生数字范围
         * 新建随机数对象，通过对应位置读出对应着字符串
         * 建立验证码字符串对象，设置验证码为4个字符
         * */
        StringBuilder sb = new StringBuilder();
        String str = "";
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            String charOrNum = rand.nextInt(2) % 2 == 0? "char": "num";
            // 判断输出是数字还是字母
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 输出的是大写字母还是小写字母
                int temp = rand.nextInt(2) % 2 == 0? 65: 97;
                str = String.valueOf((char)(rand.nextInt(26) + temp));
            } else {
                str = String.valueOf(rand.nextInt(10));
            }
            g.setColor(new Color(0, 0, 0)); // 设置字体颜色，默认是黑色
            Font font = new Font("楷体", Font.BOLD, 20);
            g.setFont(font);
            g.drawString(str + "", 16 * i + 20, 25);
            sb.append(str);
        }

        /**
         * 保存当前的验证码字符串
         * 绘制对应验证码的图片
         * */
        request.getSession().setAttribute("picCode", sb.toString());
        ImageIO.write(bi, "JPG", response.getOutputStream());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
