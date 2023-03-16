<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <a class="text-main" style="float: right;" href="admin/">Личный кабинет</a>
        <a class="text-main" style="float: right; margin-left: 10px" href="main">Администрирование</a>
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
            Корзина
        </div>

        <div class="shop-cart-bar">
            <c:choose>

                <c:when test="${empty orderCart}">
                    <p>
                        Корзина пуста
                    </p>
                </c:when>

                <c:otherwise>
                    <div class="shop-cart-bar-header">
                        <div class="shop-cart-item-cell cart-item-name text-gray">
                            Товар
                        </div>
                        <div class="shop-cart-item-cell cart-item-cost text-gray">
                            Цена
                        </div>
                        <div class="shop-cart-item-cell cart-item-number text-gray">
                            Количество
                        </div>
                        <div class="shop-cart-item-cell cart-item-final-cost text-gray">
                            Стоимость
                        </div>
                        <div class="shop-cart-item-cell cart-item-delete text-gray">
                            Удалить
                        </div>
                    </div>


                        <c:forEach var="item" items="${orderCart.items}">
                            <div class="shop-cart-bar-item">
                                <div class="shop-cart-item-cell cart-item-name text-gray">
                                    ${item.product.name}
                                </div>
                                <div class="shop-cart-item-cell cart-item-cost text-gray">
                                    ${item.product.cost}
                                </div>
                                <div class="shop-cart-item-cell cart-item-number text-gray">
                                    ${item.number}
                                </div>
                                <div class="shop-cart-item-cell cart-item-final-cost text-gray">
                                    ${item.number * item.product.cost}
                                </div>
                                <div class="shop-cart-item-cell cart-item-delete text-gray">

                                </div>
                            </div>
                        </c:forEach>

                        <div class="shop-cart-bar-footer">
                            <c:url var="orderSendButton" value="sendOrder">
                                <c:param name="orderCartId" value="${orderCart.id}"/>
                            </c:url>

<%--                            <form:select path="orderCart">--%>
<%--                                <form:option value="${orderCart.}" label="${orderCart.id}"/>--%>
<%--                            </form:select>--%>

<%--                            <form:hidden path="orderCartId" value="${orderCart.id}"/>--%>


                            <button onclick="window.location.href = '${orderSendButton}'">

                            </button>
                        </div>

                </c:otherwise>

            </c:choose>



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

