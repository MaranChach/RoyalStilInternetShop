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
            <a class="text-main-15" style="float: right;" href="personal-page">Личный кабинет</a>
            <a class="text-main-15" style="float: right; margin-left: 10px" href="admin/">Администрирование</a>
        </div>

        <div class="shop-main-bar">
            <div onclick="window.location.href = 'http://localhost:8080/main'" class="shop-logo-bar">
                <img style="height: 90%;" src="/main/sources/images/logo2.png" alt="">
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
                <button onclick="window.location.href='catalog'" class="catalog-button text-product-buttons">
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
                ${product.name}
            </div>
            <div class="shop-product-rating-bar">
                <div class="shop-product-article text-main-15">
                    Артикул: ${product.article}
                </div>
                <div class="shop-product-reviews-number text-main-15">
                    Отзывов: 0
                </div>
            </div>
            <div class="shop-product-content">
                <div class="shop-product-content-image-bar">
                    <img height="300px" class="shop-product-content-image" src="${product.imageUrl}" alt="">
                </div>
                <div class="shop-product-content-cost-bar">
                    <div class="shop-product-content-number">
                        <div class="text-main-15">
                            Количество:
                        </div>
                        <input class="shop-product-number-input text-main-15" type="number">
                    </div>

                    <div class="shop-product-details-payment-bar">
                        <div class="shop-product-content-cost text-product-header">
                            ${product.cost} руб.
                        </div>

                        <c:choose>

                            <c:when test="${product.number <= '0'}">
                                <div style="color: red" class="shop-product-content-availability text-availability">
                                    Нет в наличии
                                </div>

                                <button onclick="window.location.href = '${addToCartButton}'" style="background-color: gainsboro" disabled="disabled" class="shop-product-content-buy-button text-product-buttons">
                                    Добавить
                                </button>
                            </c:when>

                            <c:otherwise>
                                <div class="shop-product-content-availability text-availability">
                                    Есть в наличии
                                </div>

                                <c:url var="addToCartButton" value="saveToCart">
                                    <c:param name="productId" value="${product.id}"/>
                                </c:url>

                                <button onclick="window.location.href='${addToCartButton}'" class="shop-product-content-buy-button text-product-buttons">
                                    Добавить
                                </button>

                                <div class="shop-product-content-oneclick-buy-button text-product-buy">Купить в один клик</div>
                            </c:otherwise>

                        </c:choose>

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

            <div class="shop-product-tab-bar shop-product-description text-main-15">
                ${product.description}
            </div>

            <div class="shop-product-tab-bar shop-product-details text-main-15">
                <p>Детали Детали Детали</p>
                <p>Детали Детали Детали</p>
                <p>Детали Детали Детали</p>
            </div>

            <div class="shop-product-tab-bar shop-product-reviews text-main-15">
                <p>Отзывы Отзывы Отзывы </p>
                <p>Отзывы Отзывы Отзывы </p>
                <p>Отзывы Отзывы Отзывы </p>
            </div>
        </div>
    </div>

    <div class="shop-footer">
        <div class="shop-footer-column">
            <div class="shop-footer-column-header text-main-15">
                Информация
            </div>
            <a class="text-main-15">О магазине</a>
            <a class="text-main-15">О магазине</a>
            <a class="text-main-15">О магазине</a>
            <a class="text-main-15">О магазине</a>
        </div>
        <div class="shop-footer-column">
            <div class="shop-footer-column-header text-main-15">
                Информация
            </div>
            <a class="text-main-15">О магазине</a>
            <a class="text-main-15">О магазине</a>
            <a class="text-main-15">О магазине</a>
            <a class="text-main-15">О магазине</a>
        </div>
        <div class="shop-footer-column">
            <div class="shop-footer-column-header text-main-15">
                Информация
            </div>
            <a class="text-main-15">О магазине</a>
            <a class="text-main-15">О магазине</a>
            <a class="text-main-15">О магазине</a>
            <a class="text-main-15">О магазине</a>
            <a class="text-main-15">О магазине</a>
        </div>
    </div>

    <script>
        <%@include file="/sources/script/product-tabs-script.js"%>
    </script>
</body>
</html>
