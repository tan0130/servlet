package ssh.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * create by tan on 2018/6/5
 * 实现文件下载
 **/
@WebServlet(name = "DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要下载的文件名
        String fileName = request.getParameter("filename");

        // 上传文件保存的目录
        String fileSavePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        // 通过文件名找出文件所在目录
        String path = findFileSavePathByName(fileName, fileSavePath);

        // 得到要下载的文件
        File file = new File(path + "\\" + fileName);
        // 如果文件不存在
        if (!file.exists()) {
            request.setAttribute("message", "您要下载的资源已被删除");
            request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
        }
        // 处理文件名
        String realName = fileName.substring(fileName.indexOf("_") + 1);
        // 设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realName, "UTF-8"));
        // 读取要下载的文件，保存到文件输入流
        FileInputStream fis = new FileInputStream(path + "\\" + fileName);
        // 创建输出流
        OutputStream os = response.getOutputStream();
        // 创建缓冲区
        byte[] bytes = new byte[1024];
        int len = 0;
        // 循环将输入流中的内容读取到缓冲区
        while ((len = fis.read(bytes)) > 0) {
            os.write(bytes, 0, len);
        }
        // 关闭文件输入流
        fis.close();
        // 关闭文件输出流
        os.close();
    }

    /**
     * 通过文件名找出文件所在的路径
     * @param fileName 要下载的文件名
     * @param fileSavePath 上传文件的根目录
     * @return 返回要下载文件的存储目录
     * */
    private String findFileSavePathByName(String fileName, String fileSavePath) {
        int hashcode = fileName.hashCode();
        //int dir1 = hashcode & 0xf; // 使用hascode打散存储，但是文件目录获取之后出错
        //int dir2 = (hashcode & 0xf0) >> 4;
        String dir = fileSavePath;
        File file = new File(dir);
        if (!file.exists()) {
            // 创建目录
            file.mkdir();
        }
        return dir;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
