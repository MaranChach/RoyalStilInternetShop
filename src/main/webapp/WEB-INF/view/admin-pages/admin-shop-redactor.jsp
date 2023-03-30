<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tpant
  Date: 2/24/2023
  Time: 12:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>
    <style><%@include file="/sources/style/style.css"%></style>
</head>
<body>
<%@include file="../modules/module-admin-top-bar.jsp" %>
<div class="content">
    <%@include file="../modules/module-admin-side-panel.jsp"%>
    <div class="main-panel">
        <div class="text-header text-header-margin">
            Товары
        </div>
        <div class="data-panel">
            <div style="height: 10px">

            </div>
            <div class="admin-menu-data-panel">
                <div class="admin-table">
                    <div id="table-list-header" class="table-item">

                    </div>
                    <div class="table-item">

                    </div>
                    <div class="table-item">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

