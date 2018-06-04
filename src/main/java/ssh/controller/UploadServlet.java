package ssh.controller;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * create by tan on 2018/6/4
 * 实现文件上传的servlet
 **/
@WebServlet(name = "UploadServlet")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置文件上传保存的目录
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");

        // 上传时生成的临时问价保存目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");

        File tempFile = new File(tempPath);
        if (!tempFile.exists()) { // 存放临时目录的文件夹不存在，新建目录
            tempFile.mkdir();
        }

        // 消息提示
        String message = "";
        try {
            // 1.创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 2.创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解决上传文件中文乱码
            upload.setHeaderEncoding("utf-8");
            // 3.判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                return;
            }
            // 4.使用ServletFilterUpload解析器解析上传数据
        } catch(Exception e) {
            message = "文件上传失败";
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
