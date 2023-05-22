<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <div>
            ${client.name}
            ${client.surname}
        </div>



    </div>
</div>

<%@include file="../modules/module-shop-footer.jsp"%>

<script>
    <%@include file="/sources/script/product-tabs-script.js"%>
</script>
</body>
</html>

