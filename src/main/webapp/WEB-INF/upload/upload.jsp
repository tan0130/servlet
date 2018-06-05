<%--
  Created by IntelliJ IDEA.
  User: tan.jinming
  Date: 2018/6/4
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试文件上传</title>
    <script type="text/javascript" src="../../resources/js/jquery.min.js"></script>
</head>
<body>
    <form action="${pageContext.request.contextPath }/servlet/UploadServlet" enctype="multipart/form-data"  method="post">
        上传用户：<input type="text" id="file" name="name"/><br/>
        上传文件：<input type="file" name="file"><br/>
        <input type="submit" value="上传"/>文件上传结果：${message}
    </form>

<script type="text/javascript">

    // 触发文件选择的click事件
    $("#file").trigger("click");

</script>
</body>
</html>
