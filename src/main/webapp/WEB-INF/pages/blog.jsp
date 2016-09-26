<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/9/5
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Blog</title>
    <jsp:include page="include.jsp"></jsp:include>

    <link rel="stylesheet" href="nprogress/nprogress.css">
    <script src="nprogress/nprogress.js"></script>
    <style>
        #id {
            background-color: white;
            border: 1px solid #e1e1e8;
            border-radius: 4px;
        }
        #id:before {
            margin-left: 20px;
            font-size: 14px;
            font-weight: 700;
            color: #959595;
            text-transform: uppercase;
            letter-spacing: 1px;
            content: "Blog:";
        }
    </style>
</head>
<body>

    <div class="container center-block">

        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <%--<h3 class="panel-title">--%>
                    Blog
                    <button class="btn btn-default btn-sm">
                        <a href="#"><span class="glyphicon glyphicon-pencil"></span>新建博客</a>
                    </button>
                </div>

                <ul class="list-group">
                    <li id="blogOne" class="list-group-item title">
                        <a id="target" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                            <!-- TODO 这里的1表示：有1个新的回复，如有n,则有N个回复.-->
                            Collapsible Group Item #1<a href="#"><span class="badge" title="最新评论">1</span></a>
                        </a>
                        <!-- TODO 标志位(每个标志位用来确定用户是否点击了) -->
                        <input id="flag" type="hidden" name="flag" value="false">
                    </li>

                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                        </div>
                    </div>

                    <li class="list-group-item title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            Collapsible Group Item #2
                        </a>
                    </li>

                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                        </div>
                    </div>
                </ul>

                <%--<ul class="list-group">
                    &lt;%&ndash;<c:forEach items="${hotNews}" var="news">
                        <li class="list-group-item title">
                                ${news}
                        </li>
                    </c:forEach>&ndash;%&gt;
                    <li class="list-group-item title">
                        <a href="#">博客1</a>

                </ul>--%>
            </div>
        </div>

        <%--<div id="id">
            <br/>
            <br/>
            <div class="panel-group container" id="accordion" style="width: 90%;">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                                Collapsible Group 1</a>
                        </h4>
                    </div>
                    <div id="collapse1" class="panel-collapse collapse in">
                        <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
                            minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                            commodo consequat.</div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                                Collapsible Group 2</a>
                        </h4>
                    </div>
                    <div id="collapse2" class="panel-collapse collapse">
                        <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
                            minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                            commodo consequat.</div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
                                Collapsible Group 3</a>
                        </h4>
                    </div>
                    <div id="collapse3" class="panel-collapse collapse">
                        <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
                            minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                            commodo consequat.</div>
                    </div>
                </div>
            </div>
        </div>--%>
    </div>

    <script type="text/javascript">

        //TODO 一套通用请求后台
        $(document).ready(function () {

            $("#target").click(function() {
                NProgress.configure({
                    parent: '#blogOne'
                });

                if ($("#flag").val() == "false") {
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
                } else {
                    console.log($("#flag").val());
                    $("#" + $(this).attr("href").replace("#", "")).removeClass("in");
                }

            });

        });

    </script>
</body>
</html>
