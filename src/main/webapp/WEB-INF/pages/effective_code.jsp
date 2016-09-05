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
    <title>密码修改</title>
    <jsp:include page="include.jsp"></jsp:include>
    <script src="js/effective_code.js"></script>
</head>
<body>
    <div class="container">
        <form id="effective_code" action="effective_code.do" method="post" class="form-signin">
            <h3 class="form-signin-heading" align="center">密码修改</h3>
            <input id="email" name="email" type="hidden" value="${email}">
            <input id="reset_code" name="reset_code" type="hidden" value="${reset_code}">

            <div class="form-group">
                <input id="password" type="password" name="password" class="form-control" placeholder="密码" maxlength="12" autofocus>
            </div>

            <div class="form-group">
                <input id="repeatPassword" type="password" name="repeatPassword" class="form-control" placeholder="确认密码" maxlength="12">
            </div>

            <div class="form-group">
                <input type="submit" class="btn btn-lg btn-primary btn-block" value="修改">
                <div align="center" style="margin-top: 1%;">
                    <a href="index.do">返回主页</a>
                </div>
            </div>
        </form>
    </div>

</body>
</html>
