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

        <div class="shop-content">
            <div class="shop-categories-bar">
                <div class="shop-categories-buttons-bar">
                    <div class="shop-categories-button shop-radius-catalog">

                    </div>
                    <div class="shop-categories-button shop-radius-catalog">

                    </div>
                    <div class="shop-categories-button shop-radius-catalog">

                    </div>
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
                <div class="text-header shop-catalog-header">
                    Каталог
                </div>

                <div class="shop-catalog-categories-bar">
                    <div class="shop-catalog-category">
                        <p class="text-main shop-catalog-category-block-header">Категория 1</p>
                    </div>
                    <div class="shop-catalog-category">
                        <p class="text-main shop-catalog-category-block-header">Категория 2</p>
                    </div>
                    <div class="shop-catalog-category">
                        <p class="text-main shop-catalog-category-block-header">Категория 3</p>
                    </div>
                    <div class="shop-catalog-category">
                        <p class="text-main shop-catalog-category-block-header">Категория 4</p>
                    </div>
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


</body>
</html>
