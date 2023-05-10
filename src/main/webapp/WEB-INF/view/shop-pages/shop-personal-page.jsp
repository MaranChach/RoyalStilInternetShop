<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tpant
  Date: 3/3/2023
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>

    <style>
        <%@include file="/sources/style/style.css"%>
    </style>
</head>
<body style="">
<div class="shop-main-container">
    <%@include file="../modules/module-shop-top-bar.jsp"%>

    <div style="flex-direction: column" class="shop-content">
        <div class="shop-product-header text-product-header">
            Личный кабинет
        </div>

        <ul class="personal-buttons-bar" type="none">
            <li class="personal-button-bar">
                <a class="personal-button-text" href="">Информация</a>
            </li>
            <li class="personal-button-bar">
                <a class="personal-button-text" href="">Информация</a>
            </li>
            <li class="personal-button-bar">
                <a class="personal-button-text" href="">Информация</a>
            </li>
            <li class="personal-button-bar">
                <a class="personal-button-text" href="">Информация</a>
            </li>
            <li class="personal-button-bar">
                <a class="personal-button-text" href="">Информация</a>
            </li>
        </ul>
    </div>
</div>

<%@include file="../modules/module-shop-footer.jsp"%>

<script>
    <%@include file="/sources/script/product-tabs-script.js"%>
</script>
</body>
</html>

