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

    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">

    <style>
        <%@include file="/sources/style/style.css"%>
    </style>
</head>
<body style="">
    <div class="shop-main-container">
        <div class="shop-top-bar">
            <a class="text-main" style="float: right;" href="personal-page">Личный кабинет</a>
            <a class="text-main" style="float: right; margin-left: 10px" href="admin/">Администрирование</a>
        </div>

        <div class="shop-main-bar">
            <div onclick="window.location.href = 'http://localhost:8080/main'"  class="shop-logo-bar">
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
                    <img class="shop-nav-button-image" src="<c:url value="/sources/images/heart-svgrepo-com.svg"/>" alt="">
                </button>
                <button class="shop-nav-button">

                </button>
                <button onclick="window.location.href = 'cart'" class="shop-nav-button">
                    <img class="shop-nav-button-image" src="<c:url value="/sources/images/cart-svgrepo-com.svg"/>" alt="">
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

        <div class="shop-content">
            <div class="shop-categories-bar">
                <div class="shop-categories-buttons-bar">
                    <c:forEach var="category" items="${categories}">
                        <c:url var="categoryButton" value="category">
                            <c:param name="categoryId" value="${category.id}"/>
                        </c:url>
                        <div onclick="window.location.href = '${categoryButton}'" class="shop-categories-button shop-radius-catalog">
                            <p class="text-main shop-categories-button-text">${category.name}</p>
                        </div>
                    </c:forEach>
                </div>

                <div class="shop-news-bar">
                    <div class="shop-news-header text-header">
                        Новости
                    </div>

                    <div class="shop-news-item">
                        <div class="shop-news-text text-main">
                            Слава россии!
                        </div>
                        <div class="shop-news-date text">
                            1 февраля 2023
                        </div>
                    </div>

                    <div class="shop-news-item">
                        <div class="shop-news-text text-main">
                            Слава россии!
                        </div>
                        <div class="shop-news-date text">
                            1 февраля 2023
                        </div>
                    </div>

                    <a href="" class="text shop-news-date">Показать ещё</a>
                </div>

            </div>
            <div class="shop-playground">

                <div class="shop-images-slider">
                    <div class="shop-slider-line">
                        <img src="<c:url value="/sources/images/JHCIbuoyYS8.jpg"/> " alt="">
                        <img src="<c:url value="/sources/images/Dee4fzy5waQ.jpg"/> " alt="">
                        <img src="<c:url value="/sources/images/K7HEmhzttAk.jpg"/> " alt="">
                        <img src="<c:url value="/sources/images/pjZjugxlwEE.jpg"/> " alt="">
                    </div>
                </div>
                <div class="shop-slider-buttons-bar">
                    <button class="shop-slider-button shop-button-left">
                        <img width="40px" height="40px" src="<c:url value="/sources/images/arrow-left-circle-svgrepo-com.svg"/>">
                    </button>

                    <div style="flex: 1">

                    </div>

                    <button class="shop-slider-button shop-button-right">
                        <img width="40px" height="40px" src="<c:url value="/sources/images/arrow-right-circle-svgrepo-com.svg"/>">
                    </button>
                </div>


                <div class="shop-benefits-bar">
                    <div class="shop-benefits-bar-item">
                        <div class="shop-benefits-bar-item-image">
                            <image class="shop-benefits-image" src="https://cdn-icons-png.flaticon.com/512/4437/4437655.png"></image>
                        </div>
                        <div class="shop-benefits-bar-item-text">
                            Акции и скидки для
                            постоянных клиентов
                        </div>
                    </div>
                    <div class="shop-benefits-bar-item">
                        <div class="shop-benefits-bar-item-image">
                            <image class="shop-benefits-image" src="https://cdn-icons-png.flaticon.com/512/4437/4437655.png"></image>
                        </div>
                        <div class="shop-benefits-bar-item-text">
                            Качественные услуги
                            и сервис
                        </div>
                    </div>
                    <div class="shop-benefits-bar-item">
                        <div class="shop-benefits-bar-item-image">
                            <image class="shop-benefits-image" src="https://cdn-icons-png.flaticon.com/512/4437/4437655.png"></image>
                        </div>
                        <div class="shop-benefits-bar-item-text">
                            Широкий ассортимент
                            товаров
                        </div>
                    </div>
                    <div class="shop-benefits-bar-item">
                        <div class="shop-benefits-bar-item-image">
                            <image class="shop-benefits-image" src="https://cdn-icons-png.flaticon.com/512/4437/4437655.png"></image>
                        </div>
                        <div class="shop-benefits-bar-item-text">
                            Широкий ассортимент
                            товаров
                        </div>
                    </div>
                </div>

                <div class="shop-new-products-bar">
                    <div class="shop-new-products-header">
                        Новинки
                    </div>

                    <div class="shop-products-row">
                        <div class="shop-product">
                            <div class="shop-product-image-bar">

                            </div>
                            <div class="shop-product-info-bar">
                                <div class="shop-product-title text-product">
                                    Товарный товар товара товару
                                </div>
                                <div class="shop-product-cost text-product">
                                    1234.00 руб.
                                </div>
                                <div class="shop-product-buy-button-bar">
                                    <button class="shop-product-buy-button text-product-buttons">
                                        В корзину
                                    </button>
                                    <button class="shop-product-buy-button text-product-buttons">
                                        Купить
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="shop-product">

                        </div>

                        <div class="shop-product">

                        </div>
                    </div>
                </div>

                <div class="shop-description-bar text-main">
                    <p>Наш интернет-магазин сделан для того, чтобы Вы смогли удобно, без лишних забот найти и заказать то, что Вас интересует. Мы заботимся о том, чтобы ассортимент в нашем интернет-магазине был всегда актуальным, цены доступными, сервис лучшим.</p>
                    <br>
                    <p>Предлагаем Вам убедится в этом и сделать заказ в нашем интернет-магазине. Вы сможете быстро оплатить и получить заказ. Подробнее о вариантах оплаты и доставки Вы сможете узнать на соответствующих страницах.</p>
                    <br>
                    <p>Для всех постоянных клиентов мы делаем скидки и заботимся о том, чтобы интернет-магазин Вам нравился и Вы приходили в него снова и снова.</p>
                    <br>
                    <p>Наш интернет-магазин сделан для того, чтобы Вы смогли удобно, без лишних забот найти и заказать то, что Вас интересует. Мы заботимся о том, чтобы ассортимент в нашем интернет-магазине был всегда актуальным, цены доступными, сервис лучшим.</p>
                </div>
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

    <script src="<c:url value="/sources/script/slider-script.js" />">
    </script>
</body>
</html>
