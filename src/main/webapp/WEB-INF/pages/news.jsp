<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/8/24
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>Hot News</title>
    <meta charset="GBK">
    <link rel="shortcut icon" href="favicon.ico">

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/signin.css">

    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

</head>
<body>

    <nav class="navbar navbar-default navbar-fixed-top" style="opacity: .9" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header" style="margin-top: 5px;">
                <img src="favicon.ico" alt="HotNews"><strong style="font-weight: bold;">${nickname}</strong>,我们在看!
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="#">Blog</a></li>
                    <li><a href="https://github.com/RuiShaw">GitHub</a></li>
                    <li><a href="logout.do">登出</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <br/>
    <div class="container center-block">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Blog</h3>
            </div>
            <ul class="list-group">

                <li class="list-group-item title">
                    <a href="#">测试1</a>
                </li>

                <li class="list-group-item title">
                    <a href="#">测试2</a>
                </li>

            </ul>
        </div>
    </div>

</body>
</html>
