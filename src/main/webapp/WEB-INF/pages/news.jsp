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
                <img src="favicon.ico" alt="HotNews">${nickname},我们在看!
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="#">Blog</a></li>
                    <li><a href="https://github.com/RuiShaw">GitHub</a></li>
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
                    <a href="http://yinwang.org/blog-cn/2016/07/10/tesla-autopilot-fatal-crash">Tesla autopilot
                        引起致命车祸</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/07/05/google-maps">Google Maps的设计问题</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/06/28/yangsheng">养生节目带来的危害</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/06/27/tiger-for-rent">老虎出租</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/06/24/robot-tax">欧盟草拟法案，对机器人征税</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/06/22/plan-change">两个计划的变动</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/06/20/it-and-society">IT业给世界带来的危机</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/06/19/leaving-united-states">关于离开美国的决定</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/06/14/information-inequality">美国社会的信息不平等现象</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/06/08/java-value-type">Java 有值类型吗？</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/06/06/swift">Swift 语言的设计错误</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/05/25/my-tweet">我的 tweet 系统</a>
                </li>

                <li class="list-group-item title">
                    <a href="http://yinwang.org/blog-cn/2016/05/22/positive-thinking">正面思维的误区</a>
                </li>
            </ul>
        </div>
    </div>

</body>
</html>
