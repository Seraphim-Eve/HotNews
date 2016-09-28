<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/9/5
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
<link rel="stylesheet" href="nprogress/nprogress.css">
<script src="nprogress/nprogress.js"></script>
--%>

<div class="container center-block">

    <div class="panel panel-default">
        <div class="panel-heading">
            Blog
            <button class="btn btn-default btn-sm">
                <a href="blog_editor.do" target="_blank"><span class="glyphicon glyphicon-edit"></span>新建博客</a>
            </button>
        </div>

        <ul class="list-group">

            <!-- TODO 博客内容组织 -->
            <li class="list-group-item title">
                <a href="blog_view.do?id=123" target="_blank">
                    Collapsible Group 2
                </a>
                <!-- TODO 这里的1表示：有1个新的评论，如有n,则有N个评论.-->
                <span class="badge pull-right" title="总评论"><span class="glyphicon glyphicon-pencil"></span>1</span>
                <!-- TODO 赞数量 -->
                <span class="badge pull-right" title="赞"><span class="glyphicon glyphicon-thumbs-up"></span>1</span>
            </li>

        </ul>
    </div>

</div>

<%--<script type="text/javascript">

    //TODO 一套通用请求后台
    $(document).ready(function () {

        $("#target").click(function() {

            NProgress.configure({
                parent: '#blogOne'
            });

            NProgress.start();
            $.ajax({
                type: "POST",
                url: "blogGet.do",
                data: "博客id",
                success: function (content) {
                    console.log(content);
                    NProgress.done();
                    $("#flag").val("true");
                }
            });
        });

    });

</script>--%>

