<%@ page import="org.jsoup.Connection" %>
<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/9/28
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>博客查看</title>
    <meta charset="UTF-8" http-equiv="Content-Type">
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="nprogress/nprogress.css">
    <script src="js/jquery.min.js"></script>
    <script src="nprogress/nprogress.js"></script>
    <script src="js/blog_view.js" charset="UTF-8"></script>

    <link rel="stylesheet" href="sweetalert/sweetalert.css">
    <script src="sweetalert/sweetalert.min.js"></script>

    <style>
        H2 {
            font-family: "Palatino Linotype", "Book Antiqua", Palatino, Helvetica, STKaiti, SimSun, serif;
            font-weight: bold;
            margin-bottom: 60px;
            margin-bottom: 40px;
            padding: 5px;
            border-bottom: 2px LightGrey solid;
            width: 98%;
            line-height: 150%;
            color: #666666;
        }

        H3 {
            font-family: "Palatino Linotype", "Book Antiqua", Palatino, Helvetica, STKaiti, SimSun, serif;
            font-weight: bold;
            margin-top: 40px;
            margin-bottom: 30px;
            border-bottom: 1px LightGrey solid;
            width: 98%;
            line-height: 150%;
            color: #666666;
        }

    </style>
</head>
<body>
    <br/>

    <div class="container">
        <div class="row">
            <div class="col-md-12" role="main">
                <div class="col-md-9">
                    <!-- 内容区 -->
                    <div id="content" style="padding: 2% 8% 5% 8%; border: 1px solid LightGrey; border-radius: 4px;">
                        <h2>${title}</h2>
                        <input id="blog_id" name="blog_id" type="hidden" value="${id}">
                        <!-- 博客内容 -->
                        ${content}
                    </div>
                </div>

                <div class="col-md-3">
                    <!-- 评论区 -->
                    <div  id="comment" style="padding: 2% 8% 5% 8%; border: 1px solid LightGrey; border-radius: 4px; overflow-y: auto; width: 100%;">
                        <h3>评论</h3>
                        <!-- TODO 读取评论列表 -->

                        <div id="plArea">
                            <c:forEach items="${discuss_list}" var="discuss">
                                <label for="${discuss.d_user}">${discuss.d_user}: </label>
                                <div id="${discuss.d_user}" style="border-bottom: 1px LightGrey solid;">
                                    ${discuss.content}
                                </div>
                                <div class="pull-right" style="font-size: 10px;">
                                    ${discuss.time}
                                </div>
                                <br/>
                            </c:forEach>
                        </div>


                        <br/>
                        <textarea  id="comments" class="form-control" placeholder="输入评论" maxlength="100" required></textarea>
                        <br/>
                        <div align="center">
                            <button id="commentSub" type="button" class="btn btn-defualt" style="background-color: white; border-color: #ccc;">评论</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <br/>

        <!-- 赞区域 -->
        <div class="row">
            <div id="good_area" class="col-md-12" align="center">
                <!-- 赞验证(不能重复赞) -->
                ${good}
            </div>
            <div id="good_warn" class="col-md-12" align="center"></div>
        </div>
    </div>

    <script type="text/javascript">
        //评论区与内容区高度同步
        $("#comment").css("height", $("#content").css("height"));

        NProgress.start();
        $(document).ready(function () {
            NProgress.done();
        });

        $("button[id='commentSub']").click(function () {
            //TODO 提交评论
            if ($("#comments").val() != "" && $("#blog_id").val() != "") {

                NProgress.configure({parent: "#comment"});

                $("button[id='commentSub']").attr("disabled", "disabled");

                NProgress.start();

                var comment = $("#comments").val();
                var id = $("#blog_id").val();
                $.ajax({
                    url: "blog_discuss.do",
                    type: "POST",
                    data: "comment=" + comment + "&id=" + id,
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    success: function (rData) {
                        //至评论为空.
                        $("#comments").val("");
                        //console.log(rData);
                        var obj = JSON.parse(rData);
                        if (obj.flag == "true") {
                            swal("评论成功!");
                        } else {
                            swal("评论失败!");
                        }

                        var html = "";

                        for (var i = 0; i < obj.discuss_list.length; i++) {
                            var ob = obj.discuss_list[i];
                            html += "<label for='" + ob.d_user + "'>" + obj.discuss_list[i].d_user + ": </label>"
                            html += "<div id='" + ob.d_user + "' style='border-bottom: 1px LightGrey solid;'>" + ob.content + "</div>";
                            html += "<div class='pull-right' style='font-size: 10px;'>" + ob.time + "</div><br/>";
                        }
                        $("#plArea").val("");
                        $("#plArea").html(html);
                        NProgress.done();
                    }
                });

                //按钮计时
                setTimeout(function () {
                    $("button[id='commentSub']").removeAttr("disabled");
                }, 10000);

            } else {
                swal("你输入的评论为空!");
            }

        });
    </script>

</body>
</html>
