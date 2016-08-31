<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/8/27
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>注册成功</title>
    <meta charset="GBK">
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" href="css/signin.css">
    <script type="text/javascript">
        setInterval(refer, 1000);
        var time = 9;
        function refer() {
            if (time == 0) {
                window.location.href = "index.jsp";
            } else {
                document.getElementById("show").innerHTML = time + "秒后跳转到<span style='font-weight: bold;'>登陆界面</span>!";
            }
            time--;
        }
    </script>
</head>
<body>
    <div align="center" style="margin-top: 10%;">
        <h3><span style='color:red; font-weight: bold;'>注册成功</span></h3>
        <p id="show">10秒后跳转到<span style='font-weight: bold;'>登陆界面</span>!</p>
        <a href="index.jsp">立即跳转</a>
    </div>
</body>
</html>
