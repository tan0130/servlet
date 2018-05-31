<%--
  Created by IntelliJ IDEA.
  User: tan.jinming
  Date: 2018/5/31
  Time: 13:18

  使用application内置对象来演示一个网站计数器
  1. 没有加入application对象，没刷新一次网页，计数器自动加1，网站访问人员应该以是否是一次新的对话来判定是否是一个新的
  访问用户
  2.加入application对象可以保存访问人数

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>网站计数器</title>
</head>
<body>
欢迎访问本站，你是第${number }个访问用户
</body>
</html>
