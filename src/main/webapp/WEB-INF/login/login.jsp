<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
    request.setAttribute("path", request.getContextPath());
%>
<html>
<head>
    <title>员工信息管理系统</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/icon.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/login.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/base.css">

    <script type="text/javascript" src="../../resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="../../resources/js/jquery.easyui.min.js"></script>
</head>
<body>
<div class="login-hd">
    <div class="left-bg"></div>
    <div class="right-bg"></div>
    <div class="hd-inner">
        <span class="logo"></span>
        <span class="split"></span>
        <span class="sys-name">员工信息管理系统</span>
    </div>
</div>
<div class="login-bd">
    <div class="bd-inner">
        <div class="inner-wrap">
            <div class="lg-zone">
                <div class="lg-box">
                    <div class="lg-label"><h4>用户登录</h4></div>
                    <div class="alert alert-error">
                        <i class="iconfont">&#xe62e;</i>
                        <span><font color="red">${formBean.errors.error }</font></span>
                    </div>
                    <form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="post">
                        <div class="input-item">
                            <i class="iconfont">&#xe60d;</i>
                            <input class="easyui-validatebox" type="text" name="id" placeholder="请输入id" value="${formBean.id }" >
                        </div>
                        <div class="input-item">
                            <i class="iconfont">&#xe634;</i>
                            <input class="easyui-validatebox" type="password" name="password" placeholder="请输入密码" value="${formBean.password }">
                        </div>

                        <!-- <div class="input-item1">
                            <input type="radio" name="type" value="管理员" id="admin" style="margin-left:35px" checked="checked">
                            <label for="admin">管理员</label>
                            <input type="radio" name="type" value="员工" id="employee" style="margin-left:35px">
                            <label for="employee">员工</label>
                        </div> -->

                        <div class="lg-check clearfix">
                            <div class="input-item">
                                <i class="iconfont">&#xe633;</i>
                                <input type="text" name="code" placeholder="验证码">
                            </div>
                            <span class="check-code"><img src="${pageContext.request.contextPath }/servlet/ImgServlet"  id="checkImg" onclick="refresh()"></span>

                        </div>
                        <!-- <div class="tips clearfix">
                            <label><input type="checkbox" checked="checked">记住用户名</label>
                            <!--  <a href="javascript:;" class="register">立即注册</a>
                            <a href="javascript:;" class="forget-pwd">忘记密码？</a>-->
                        <div class="enter" style="margin-top:-15px">
                            <!--  <a href="javascript:;" class="user" onClick="javascript:window.location=''">登录</a>-->
                            <input class= "login" type="submit" value="登录" />
                        </div>
                    </form>
                </div>
            </div>
            <div class="lg-poster"></div>
        </div>
    </div>
    <div class="login-ft">
        <div class="ft-inner">
            <div class="about-us">
                <a href="javascript:;">关于我们</a>
                <a href="javascript:;">法律声明</a>
                <a href="javascript:;">服务条款</a>
                <a href="javascript:;">联系方式</a>
            </div>
            <div class="address">地址：深圳市 &nbsp;&nbsp;Copyright&nbsp;©&nbsp;2017&nbsp;-&nbsp;2020&nbsp;版权所有</div>
            <div class="other-info">建议使用IE8及以上版本浏览器、Firefox或Chrome&nbsp;</div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    function refresh() {
        document.getElementById("checkImg").src = "${pageContext.request.contextPath }/servlet/ImgServlet?" + Math.random();
    }
</script>
</html>
