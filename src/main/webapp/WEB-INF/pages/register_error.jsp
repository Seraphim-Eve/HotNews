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
    <title>注册失败</title>
    <meta charset="GBK">
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" href="css/signin.css">
    <script type="text/javascript">
        var time = 9;
        setInterval(refer, 1000);
        function refer() {
            if (time == 0) {
                window.location.href = "registerJump.do";
            } else {
                document.getElementById("show").innerHTML = time + "秒后跳转到<span style='color:red'>注册界面</span>!"
            }
            time--;
        }
    </script>
</head>
<body>
    <div align="center" style="margin-top: 15%;">
        <p id="show">10秒后跳转到<span style='color:red'>注册界面</span>!</p>
        <a href="registerJump.do">立即跳转</a>
    </div>
</body>
</html>
