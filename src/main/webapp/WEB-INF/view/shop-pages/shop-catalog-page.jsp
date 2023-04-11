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
        <%@include file="../modules/module-shop-top-bar.jsp"%>

        <div class="shop-content">
            <div class="shop-categories-bar">
                <div class="shop-categories-buttons-bar">
                    <c:forEach var="category" items="${categories}">
                        <c:url var="categoryButton" value="category">
                            <c:param name="categoryId" value="${category.id}"/>
                        </c:url>
                        <div onclick="window.location.href = '${categoryButton}'" class="shop-categories-button shop-radius-catalog">
                            <div class="">

                            </div>
                            <p class="text-main-15 shop-categories-button-text">${category.name}</p>

                        </div>
                    </c:forEach>
                </div>

                <div class="shop-news-bar">
                    <div class="shop-news-header text-header">
                        Новости
                    </div>

                    <div class="shop-news-item">
                        <div class="shop-news-text text-main-15">
                            Слава россии!
                        </div>
                        <div class="shop-news-date text">
                            1 февраля 2023
                        </div>
                    </div>

                    <div class="shop-news-item">
                        <div class="shop-news-text text-main-15">
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

                    <c:forEach var="category" items="${categories}">
                        <c:url var="categoryButton" value="category">
                            <c:param name="categoryId" value="${category.id}"/>
                        </c:url>
                        <div class="shop-catalog-category" onclick="window.location.href = '${categoryButton}'">
                            <div class="shop-category-item-header">
                                <p class="text-main-15 shop-catalog-category-block-header">${category.name}</p>
                            </div>
                            <div class="shop-category-item-image-bar">
                                <img class="shop-category-item-image" src="${category.imageUrl}" alt="">
                            </div>
                        </div>
                    </c:forEach>
                </div>
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


</body>
</html>
