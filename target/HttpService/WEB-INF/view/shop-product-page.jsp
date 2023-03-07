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
                <image style="width: 80%; height: 80%; margin: 20px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/YouTube_Logo_2017.svg/2560px-YouTube_Logo_2017.svg.png">

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
                Товар товар
            </div>
            <div class="shop-product-rating-bar">
                <div class="shop-product-rating">

                </div>
                <div class="shop-product-article text-main">
                    Артикул: 1234
                </div>
                <div class="shop-product-reviews-number">
                    Отзывов: 0
                </div>
            </div>
            <div class="shop-product-content">
                <div class="shop-product-content-image-bar">
                    <img class="shop-product-content-image" src="<c:url value="/sources/images/g-jJlyEe1UQ.jpg"/>" alt="">
                </div>
                <div class="shop-product-content-cost-bar">
                    <div class="shop-product-content-number">
                        <div class="text-main">
                            Количество:
                        </div>
                        <input class="shop-product-number-input text-main" type="number">
                    </div>

                    <div class="shop-product-details-payment-bar">
                        <div class="shop-product-content-cost text-product-header">
                            1000 руб.
                        </div>
                        <div class="shop-product-content-availability text-availability">
                            Есть в наличии
                        </div>
                        <button class="shop-product-content-buy-button text-product-buttons">
                            Добавить
                        </button>
                        <div onclick="window.location.href='#'" class="shop-product-content-oneclick-buy-button text-product-buy">Купить в один клик</div>
                    </div>
                </div>
                <div class="shop-product-content-delivery-info-bar">
                    <div class="shop-product-content-delivery-info">

                    </div>
                </div>
            </div>

            <div class="shop-product-tabs-bar">
                <div class="shop-product-tab tab-desc text-header">
                    Описание
                </div>
                <div class="shop-product-tab tab-details text-header">
                    Характеристики
                </div>
                <div class="shop-product-tab tab-reviews text-header">
                    Отзывы
                </div>
            </div>

            <div class="shop-product-tab-bar shop-product-description text-main">
                <p>
                    Товар ультра товар товар
                </p>
                <p>
                    Товар ультра товар товар
                </p>
                <p>
                    Товар ультра товар товар
                </p>
            </div>

            <div class="shop-product-tab-bar shop-product-details text-main">
                <p>Детали Детали Детали</p>
                <p>Детали Детали Детали</p>
                <p>Детали Детали Детали</p>
            </div>

            <div class="shop-product-tab-bar shop-product-reviews text-main">
                <p>Отзывы Отзывы Отзывы </p>
                <p>Отзывы Отзывы Отзывы </p>
                <p>Отзывы Отзывы Отзывы </p>
            </div>
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
