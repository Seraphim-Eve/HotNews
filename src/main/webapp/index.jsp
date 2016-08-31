<%@ page contentType="text/html; charset=GBK" %>
<html lang="zh">
<head>
    <title>Hot News</title>
    <meta charset="GBK">
    <link rel="shortcut icon" href="favicon.ico">

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/signin.css">

    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/login.js"></script>

</head>
<body>

    <div class="container">
        <form id="login" action="login.do" method="post" class="form-signin">

            <h3 class="form-signin-heading" align="center">Welcome to Hot News!</h3>

            <div class="form-group">
                <input type="email" name="email" class="form-control" placeholder="邮箱" maxlength="30" value="${email}" autofocus/>
            </div>

            <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="密码" maxlength="12"/>
            </div>

            <div class="form-group">
                <input type="submit" class="btn btn-lg btn-primary btn-block" value="登陆">
            </div>

            <div align="center">
                <strong style="color: red;">${msg}</strong>
            </div>

            <div class="form-group">
                <a href="registerJump.do" class="pull-left">创建账户</a>
                <a href="forgotJump.do" class="pull-right">忘记密码</a>
            </div>

        </form>
    </div>

</body>
</html>