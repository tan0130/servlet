<%--
  Created by IntelliJ IDEA.
  User: tan.jinming
  Date: 2018/5/11
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Hello World!!!</h2>
    <a href = "${pageContext.request.contextPath }/servlet/CookieServlet">跳转测试Cookie保存会话</a>
    <a href = "${pageContext.request.contextPath }/servlet/SessionServlet">跳转测试Session会话</a>
    <a href = "${pageContext.request.contextPath }/servlet/ApplicationServlet">跳转测试Application测试</a>
    <a href = "${pageContext.request.contextPath }/servlet/ToServlet">登录界面</a>
    <a href = "${pageContext.request.contextPath }/servlet/ToUploadServlet">上传文件</a>
    <a href = "${pageContext.request.contextPath }/servlet/FileListServlet">文件下载</a>
</body>
</html>
