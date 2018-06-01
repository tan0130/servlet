<%--
  Created by IntelliJ IDEA.
  User: tan.jinming
  Date: 2018/6/1
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    request.setAttribute("path", request.getContextPath());
%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'admin.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" type="text/css" href="../../resources/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/icon.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/login.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/base.css">

    <script type="text/javascript" src="../../resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="../../resources/js/jquery.easyui.min.js"></script>
</head>

<body>
<div class="admin-hd">
    <span class="hd-image"></span>
    <span class="span" style="margin-left:20px"><font color="red">欢迎 ${user.name } 登录！</font>
	   			<a href="">|&nbsp;邮箱&nbsp;</a>
	   			<a href="">|&nbsp;我的账号&nbsp;</a>
	   			<a href="${pageContext.request.contextPath }/file/password.html">|&nbsp;修改密码&nbsp;</a>
	   			<a href="">|&nbsp;注销&nbsp;</a>
	   			<a href="">|&nbsp;其它&nbsp;</a>
	   			<label style="margin-left:20px">关键词:</label><input type="text" name="search" style="margin-left:20px;width:150px;height:23px"/>
	   			<a href="#" class="easyui-linkbutton" iconCls="icon-search" style="height:23px">检索</a>
	   		</span>
    <div class="easyui-tabs" style="width:100%">
        <div title="首页" style="padding:10px 5px 5px 10px;">
            <iframe class="page-iframe" src="${pageContext.request.contextPath }/file/workbench.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
        </div>
    </div>
</div>
</body>
</html>
