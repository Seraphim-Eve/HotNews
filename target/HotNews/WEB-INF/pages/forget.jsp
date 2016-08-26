<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/8/26
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>Forgot your password?</title>
    <meta charset="GBK">
    <link rel="shortcut icon" href="favicon.ico">

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/signin.css">

    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
    <div class="container">
        <form id="forgot" action="forgot.do" method="post" class="form-signin">

            <h3 class="form-signin-heading" align="center">重置密码</h3>

            <div class="form-group">
                <input type="email" name="email" class="form-control" placeholder="邮箱" maxlength="30" required autofocus/>
            </div>

            <div class="form-group">
                <input type="submit" class="btn btn-lg btn-primary btn-block" value="发送重置密码邮件">
            </div>

            <div class="form-group" align="center">
                <a href="javascript:history.go(-1)">返回上一页</a>
            </div>

        </form>
    </div>
</body>
</html>
