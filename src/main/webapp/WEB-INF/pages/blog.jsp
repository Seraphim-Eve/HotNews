<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/9/5
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="sweetalert/sweetalert.css">
<script src="sweetalert/sweetalert.min.js"></script>

<div class="container center-block">
    <div class="panel panel-default">
        <div class="panel-heading">
            Blog
            <button class="btn btn-default btn-sm">
                <a href="blog_editor.do" target="_blank"><span class="glyphicon glyphicon-edit"></span>新建博客</a>
            </button>
        </div>

        <ul class="list-group">

            <c:forEach items="${blog_list}" var="blog">
                <!-- 博客列表 -->
                <li class="list-group-item title">

                    <a href="blog_view.do?id=${blog.getId()}" target="_blank">
                        ${blog.getTitle()}
                    </a>

                    <!-- 这里的1表示：有1个新的评论，如有n,则有N个评论.-->
                    <span class="badge pull-right" title="评论数"><span class="glyphicon glyphicon-pencil"></span>${blog.getDiscuss_num()}</span>
                    <!-- 赞数量 -->
                    <span class="badge pull-right" title="点赞数"><span class="glyphicon glyphicon-thumbs-up"></span>${blog.getGood_num()}</span>

                    <!-- 只有自己的博客才能编辑和删除 -->
                    <c:if test="${blog.getFlag() eq 'true'}">
                        <div class="btn-group btn-group-xs pull-right">
                            <button id="editor" type="button" class="btn btn-default">编辑</button>
                            <input id="blog_id" type="hidden" value="${blog.getId()}">
                            <button id="delete" type="button" class="btn btn-default">删除</button>
                        </div>
                    </c:if>
                </li>
            </c:forEach>

        </ul>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#delete").click(function () {
            swal({
                title: "确定要删除?",
                text: "你将要删除这篇博客!",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
                    if (isConfirm) {
                        var blog_id = $("#blog_id").val();
                        if ("" != blog_id) {
                            $.ajax({
                                url: "blog_delete.do",
                                type: "POST",
                                data: "blog_id=" + blog_id,
                                success: function (data) {
                                    $("#blog").html(data);
                                    swal("删除成功!", "", "success");
                                }
                            });
                        }
                    } else {
                        swal("取消删除!", "", "error");
                    }
                }
            );
        });
    });
</script>

