package ssh.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * create by tan on 2018/6/5
 * 提供文件下载目录
 **/
@WebServlet(name = "FileListServlet")
public class FileListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取文件上传目录
        String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        // 存储要下载的文件名
        Map<String, String> fileNameMap = new HashMap<String, String>();
        // 递归遍历filePath目录下所有的文件和目录，将文件名存储到map集合中
        listFile(new File(uploadFilePath), fileNameMap);
        // 将map集合发送到listfile.jsp进行页面展示
        request.setAttribute("fileNameMap", fileNameMap);
        request.getRequestDispatcher("/WEB-INF/jsp/listfile.jsp").forward(request, response);
    }

    /**
     * 递归遍历指定目录下所有文件
     * @param file 代表一个文件，也代表一个目录
     * @param map 用来存储文件名
     *
     * */
    private void listFile(File file, Map<String, String> map) {
        // 如果file代表的不是一个文件，而是一个目录
        if (!file.isFile()) {
            // 列出文件夹下所有的文件和目录
            File[] files = file.listFiles();
            // 遍历File数组
            for (File f: files) {
                // 递归
                listFile(f, map);
            }
        } else {
            // 处理文件名，上传后的文件是以uuid_文件名的形式重新命名的
            String realFileName = file.getName().substring(file.getName().indexOf("_") + 1);
            map.put(file.getName(), realFileName);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
