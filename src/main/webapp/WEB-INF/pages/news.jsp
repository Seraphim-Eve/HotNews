<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/8/24
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hot News</title>
    <jsp:include page="include.jsp"></jsp:include>
</head>
<body>

    <nav class="navbar navbar-default navbar-fixed-top" style="opacity: .9" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header" style="margin-top: 5px;">
                <img src="favicon.ico" alt="HotNews"><strong style="font-weight: bold;">${nickname}</strong>,我们在看!
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Blog</a></li>
                    <li class="active"><a href="#">News</a></li>
                    <li><a href="https://github.com/RuiShaw">GitHub</a></li>
                    <li><a href="logout.do">登出</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <br/>
    <br/>

    <div class="container center-block">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Blog</h3>
            </div>

            <ul class="list-group">
                <f:forEach items="${hotNews}" var="news">
                    <li class="list-group-item title">
                        ${news}
                    </li>
                </f:forEach>
            </ul>
        </div>

    </div>

</body>
</html>
