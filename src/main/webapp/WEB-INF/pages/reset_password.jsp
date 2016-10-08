<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/9/6
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>密码修改</title>
    <jsp:include page="include.jsp"></jsp:include>
    <script src="js/reset_password.js"></script>
</head>
<body>
    <div class="container center-block">
        <form id="reset_password" action="reset_password.do" method="post" class="form-signin">

            <div class="form-group">
                <input id="s_password" name="s_password" type="password" class="form-control" placeholder="原密码" autofocus>
            </div>

            <div class="form-group">
                <input id="n_password" name="n_password" type="password" class="form-control" placeholder="新密码" required>
            </div>

            <div class="form-group">
                <input id="repeat_n_password" name="repeat_n_password" type="password" class="form-control" placeholder="重复新密码">
            </div>

            <div class="form-group">
                <input type="submit" class="btn btn-primary btn-lg btn-block" value="修改">
            </div>

            <div align="center">
                <strong style="color: red;">${msg}</strong>
            </div>

        </form>
    </div>
</body>
</html>
