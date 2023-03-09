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
    <title>Title</title>

    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>

    <style>
        <%@include file="/sources/style/style.css"%>
    </style>
</head>
<body style="">
<div class="shop-main-container">
    <div class="shop-top-bar">
        <a class="text-main" style="float: right;" href="#">Личный кабинет</a>
        <a class="text-main" style="float: right; margin-left: 10px" href="main">Администрирование</a>
    </div>

    <div class="shop-main-bar">
        <div class="shop-logo-bar">
            <image style="width: 80%; height: 80%; margin: 20px"
                   src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/YouTube_Logo_2017.svg/2560px-YouTube_Logo_2017.svg.png">

            </image>
        </div>

        <div class="shop-search-bar">
            <input placeholder="Поиск" type="search" class="shop-search text-item">
        </div>

        <div class="shop-phone-number-bar">
            <p class="text-header">
                8 (492) 232-27-52
            </p>
        </div>

        <div class="shop-buttons-bar">
            <button class="shop-nav-button">

            </button>
            <button class="shop-nav-button">

            </button>
            <button class="shop-nav-button">

            </button>
        </div>
    </div>

    <div class="shop-navbar">
        <div class="shop-catalog-bar center">
            <button onclick="window.location.href='catalog'" class="catalog-button">
                Каталог
            </button>
        </div>
        <div class="shop-info-bar center">
            <div class="shop-info-buttons-bar">

            </div>
        </div>
    </div>

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

<div class="shop-footer">
    <div class="shop-footer-column">
        <div class="shop-footer-column-header text-main">
            Информация
        </div>
        <a class="text-main">О магазине</a>
        <a class="text-main">О магазине</a>
        <a class="text-main">О магазине</a>
        <a class="text-main">О магазине</a>
    </div>
    <div class="shop-footer-column">
        <div class="shop-footer-column-header text-main">
            Информация
        </div>
        <a class="text-main">О магазине</a>
        <a class="text-main">О магазине</a>
        <a class="text-main">О магазине</a>
        <a class="text-main">О магазине</a>
    </div>
    <div class="shop-footer-column">
        <div class="shop-footer-column-header text-main">
            Информация
        </div>
        <a class="text-main">О магазине</a>
        <a class="text-main">О магазине</a>
        <a class="text-main">О магазине</a>
        <a class="text-main">О магазине</a>
        <a class="text-main">О магазине</a>
    </div>
</div>

<script>
    <%@include file="/sources/script/tabs-script.js"%>
</script>
</body>
</html>

