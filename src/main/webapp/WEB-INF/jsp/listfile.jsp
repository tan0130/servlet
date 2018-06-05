<%--
  Created by IntelliJ IDEA.
  User: tan.jinming
  Date: 2018/6/5
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>下载文件显示页面</title>
</head>
<body>
    <table border="1">
        <!-- 遍历Map集合 -->
        <c:forEach var="file" items="${fileNameMap}">
         <tr>
           <c:url value="/servlet/DownloadServlet" var="downurl">
                <c:param name="filename" value="${file.key}"/>
            </c:url>
           <td> ${file.value}<a href="${downurl}">下载</a></td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>
