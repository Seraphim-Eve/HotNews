<%--
  Created by IntelliJ IDEA.
  User: Xr
  Date: 2016/8/24
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="container center-block">

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">News</h3>
        </div>

        <ul class="list-group">
            <c:forEach items="${hotNews}" var="news">
                <li class="list-group-item title">
                    ${news}
                </li>
            </c:forEach>
        </ul>
    </div>

</div>

