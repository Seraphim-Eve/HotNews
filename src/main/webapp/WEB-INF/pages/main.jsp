<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/8/24
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hot News</title>
    <jsp:include page="include.jsp"></jsp:include>
    <link rel="stylesheet" href="css/jquery-ui.min.css">
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/reset_password.js"></script>
    <script type="text/javascript">
        $(document).tooltip({
            //support html
            content: function () {
                return $(this).prop('title');
            }
        });
    </script>
    <style>
        .show-grid {
            margin-bottom: 15px;
        }
        .show-grid [class^="col-"] {
            padding-top: 10px;
            padding-bottom: 10px;
            background-color: #eee;
            background-color: rgba(86,61,124,.15);
            border: 1px solid #ddd;
            border: 1px solid rgba(86,61,124,.2);
        }
    </style>
</head>
<body>

    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">

            <div class="navbar-header">
                <img class="navbar-brand" src="favicon.ico" title="欢迎<b style='color: red;'>${nickname}</b>来到HotNews!">
            </div>


            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-left">
                    <li class="active">
                        <a id="blog_link" href="#blog" data-toggle="tab">Blog</a>
                    </li>

                    <li>
                        <a id="news_link" href="#news" data-toggle="tab">News</a>
                    </li>

                    <li>
                        <a href="https://github.com/RuiShaw"  target="_blank">GitHub</a>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" id="dropdown" class="dropdown-toggle" data-toggle="dropdown">
                            Profile
                            <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdown">
                            <li>
                                <a href="#reset" data-toggle="tab">密码修改</a>
                            </li>

                            <li class="divider"></li>

                            <li>
                                <a href="#author" data-toggle="tab">了解作者</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="logout.do">登出</a>
                    </li>
                </ul>
            </div>

        </div>
    </nav>

    <div class="tab-content">
        <!-- blog -->
        <div class="tab-pane fade in" id="blog">
            <jsp:include page="/blog.do"></jsp:include>
        </div>

        <!-- news -->
        <div class="tab-pane fade" id="news">
            <jsp:include page="/news.do"></jsp:include>
        </div>

        <!-- reset -->
        <div class="tab-pane fade" id="reset">
            <div class="container center-block">
                <form id="reset_password" action="reset_password.do" method="post" class="form-signin">

                    <div class="form-group">
                        <input id="s_password" name="s_password" type="password" class="form-control" placeholder="原密码" autofocus>
                    </div>

                    <div class="form-group">
                        <input id="n_password" name="n_password" type="password" class="form-control" placeholder="新密码" required>
                    </div>

                    <div class="form-group">
                        <input id="repeat_n_password" name="repeat_n_password" type="password" class="form-control" placeholder="重复新密码" required>
                    </div>

                    <div class="form-group">
                        <input type="submit" class="btn btn-primary btn-lg btn-block" value="修改">
                    </div>

                    <div align="center">
                        <strong style="color: red;">${msg}</strong>
                    </div>

                </form>
            </div>
        </div>

        <!-- about author -->
        <div class="tab-pane fade" id="author">
            <div class="well center-block" style="max-width: 400px;">
                <p align="center">
                    <strong style="color: red;">发送成功</strong>,请检查你的邮箱,并重置密码.
                </p>
                <a href="index.do" class="btn btn-default btn-lg btn-block">返回登陆</a>
            </div>
        </div>
    </div>

</body>
</html>
