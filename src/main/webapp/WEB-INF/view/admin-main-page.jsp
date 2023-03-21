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
    <div class="top-bar">
        <div class="logo-bar">
            <p class="text-logo">ROYAL STEEL</p>
        </div>
    </div>
    <div class="content">
        <div class="side-bar">
            <p class="side-bar-block-header text-header"> Основное </p>
            <div class="side-bar-block">
                <p class="side-bar-block-header" ></p>
                <div class="side-bar-item" onclick="window.location.href = 'main'">
                    <p class="side-bar-item-text text-item" >Рабочий стол</p>
                </div>
                <div class="side-bar-item text-item" onclick="window.location.href = 'orders'">
                    <p class="side-bar-item-text text-item" >Заказы</p>
                </div>
                <div class="side-bar-item" onclick="window.location.href = 'products'">
                    <p class="side-bar-item-text text-item" >Товары</p>
                </div>

                <div class="side-bar-item">

                </div>
            </div>

        </div>
        <div class="main-panel">
            <div class="orders-panel panel">

            </div>

            <div class="orders-sources-panel panel">

            </div>
        </div>
    </div>
</body>
</html>
