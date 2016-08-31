<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/8/25
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>Join Hot News!</title>
    <link rel="shortcut icon" href="favicon.ico">
    <meta charset="GBK">

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/signin.css">

    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.js" charset="GBK"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/register.js"></script>

</head>
<body>
    <div class="container">

        <form id="register" action="register.do" method="POST" class="form-signin">

            <h2 class="form-signin-heading" align="center">注册</h2>

            <div class="form-group">
                <input id="nickname" type="text" name="nickname" class="form-control" placeholder="昵称" maxlength="10" value="${nickname}" autofocus>
            </div>

            <div class="form-group">
                <input id="email" type="email" name="email" class="form-control" placeholder="邮箱" maxlength="30" value="${email}">
            </div>

            <div class="form-group">
                <input id="password" type="password" name="password" class="form-control" placeholder="密码" maxlength="12">
            </div>

            <div class="form-group">
                <input id="repeatPassword" type="password" name="repeatPassword" class="form-control" placeholder="确认密码" maxlength="12">
            </div>

            <div class="form-group">
                <input type="submit" class="btn btn-lg btn-primary btn-block" value="注册">
            </div>

            <div class="form-group" align="center">
                <a href="javascript:history.go(-1)">返回上一页</a>
            </div>

        </form>
    </div>

</body>
</html>
