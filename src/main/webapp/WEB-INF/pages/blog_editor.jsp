<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/9/28
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>博客撰写</title>
    <link rel="shortcut icon" href="favicon.ico">
    <!-- 编辑器样式 -->
    <link href="umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">

    <link href="bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">

    <script type="text/javascript" src="js/jquery.min.js"></script>

    <!--编辑器js -->
    <script type="text/javascript" charset="gbk" src="umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="gbk" src="umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="umeditor/lang/zh-cn/zh-cn.js"></script>

    <!-- 其他js -->
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/blog_editor.js"></script>
</head>
<body>
    <br/><br/><br/>
    <div class="container">
        <div id="content" style="padding: 2% 8% 5% 8%; border: 1px solid LightGrey; border-radius: 4px;">
            <form id="blog_editor" action="blog_create.do" method="post">
                <div class="form-group">
                    <label for="title">博客标题:</label>
                    <input id="title" name="title" type="text" class="form-control" placeholder="标题" maxlength="10" autofocus required>
                </div>

                <div class="form-group">
                    <label for="sel">博客权限:</label>
                    <select class="form-control" id="sel" name="authority">
                        <option value="private">私有</option>
                        <option value="public">公开</option>
                    </select>
                </div>

                <br/>
                <!-- editor(编辑器) -->
                <script id="container" name="content" type="text/plain" style="width:956px;height:240px;"><p>输入博客内容.</p></script>

                <br/>

                <input type="submit" class="btn btn-primary btn-block bt-lg" value="发表博客">

            </form>


            <div align="center">
                <p>Notes: 每次发表博客后,请重新刷新主页面,才能看到最新发表的博客!</p>
                <br/>
                <p style="color: red;">${msg}</p>
            </div>

        </div>
    </div>

    <!-- init -->
    <script type="text/javascript">
        var um = UM.getEditor('container');
    </script>
</body>
</html>
