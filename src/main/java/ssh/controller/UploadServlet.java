package ssh.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * create by tan on 2018/6/4
 * 实现文件上传的servlet
 **/
@WebServlet(name = "UploadServlet")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置文件上传保存的目录
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(savePath);
        if (!(file.exists()) && file.isDirectory()) {
            file.mkdir();
        }

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
            // 设置工厂的缓冲区大小，当上传文件超过缓冲区大小，就会创建一个临时文件存放到指定的临时目录
            factory.setSizeThreshold(1024 * 100); // 设置缓冲区的大小为100KB，默认大小是10KB
            // 设置上传时生成的临时文件保存目录
            factory.setRepository(tempFile);
            // 2.创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解决上传文件中文乱码
            upload.setHeaderEncoding("utf-8");
            // 设置上传单个文件的大小最大值，设置1024 * 1024字节，也就是1MB
            upload.setFileSizeMax(1024 * 1024 * 50);
            // 设置同时上传各个文件大小总和不能超过10MB
            upload.setSizeMax(1024 * 1024 * 500);
            // 3.判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                return;
            }

            // 4.使用ServletFilterUpload解析器解析上传数据,解析结果返回的是一个List<FileItem>集合，每一个FileItem代表一个表单输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item: list) {
                // 如果fileItem封装的是普通的输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    // 解决普通输入项的数据的中文乱码问题
                    String value = item.getString("utf-8");
                } else { // 如果fileItem封装的是上传的文件
                    // 获取上传文件的名称
                    String fileName = item.getName();
                    if (null == fileName || "".equals(fileName.trim())) {
                        continue;
                    }
                    // 处理获取到的上传文件名的路径部分，只保留文件名部分
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    // 得到文件的扩展名
                    String fileExtrName = fileName.substring(fileName.lastIndexOf(".") + 1);
                    // 如果需要限制上传文件的格式，可以根据扩展名做过滤
                    if (!("txt".equals(fileExtrName))) {
                        message = "只能上传文本文档";
                        //request.setAttribute("message", "只能上传文本文档");
                        //request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
                    } else {
                        // 监听文件上传进度
                        upload.setProgressListener(new ProgressListener() {
                            @Override
                            public void update(long l, long l1, int i) {
                                System.out.println("文件大小为：" + l1 + ",当前已处理：" + l);
                            }
                        });
                        // 获取item中的上传文件的输入流
                        InputStream in = item.getInputStream();
                        // 创建一个文件输出流
                        FileOutputStream out = new FileOutputStream(savePath + "\\" + fileName);
                        // 创建一个缓冲区
                        byte[] buffer = new byte[1024];
                        // 判断输入流是否读完的标志
                        int len = 0;
                        // 循环将输入流读到缓冲区
                        while ((len = in.read(buffer)) > 0) {
                            // 使用输入流将缓冲区的数据写入指定的保存目录savePath
                            out.write(buffer, 0, len);
                        }
                        // 关闭输入流
                        in.close();
                        // 关闭输出流
                        out.close();
                        // 删除处理文件上传时生成的临时文件
                        item.delete();
                        message = "文件上传成功";
                    }
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            message = "单个文件大小不能超过50MB";
         } catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            message = "上传的文件总数大小超限";
        } catch (Exception e) {
            e.printStackTrace();
            message = "文件上传失败";
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/upload/upload.jsp").forward(request, response);
        }
    }

    /**
     * 生成文件文件名，文件名以：uuid+"_"+文件名称
     * @param fileName 文件名称
     * @return uuid + "_" + 文件名称
     * */
    private String createFileName(String fileName) {
        // 为防止文件覆盖的现象发生，为上传的文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + fileName;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
