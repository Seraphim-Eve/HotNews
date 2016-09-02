<%--
  title: 有效code页面
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/9/2
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>密码重置</title>
</head>
<body>
    <!--
        TODO 当提交更改密码成功后,后台需要把reset_code设置为null.
     -->
    <input id="email" name="email" type="hidden" value="${email}">
    <input id="reset_code" name="reset_code" type="hidden" value="${reset_code}">
</body>
</html>
