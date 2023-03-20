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
            Оформление заказа
        </div>
        <form action="sendFullOrder">
        <div class="shop-ordering-content">
            <div class="shop-ordering-info">
                <div class="shop-ordering-info-bar">
                    <div class="shop-ordering-info-bar-header text-main-20">
                        Покупатель
                    </div>
                    <div class="shop-ordering-info-attributes">
                        <div class="shop-ordering-info-attribute">
                            <p class="shop-ordering-info-attribute-header text-main-15 "> Email </p>
                            <input name="clientEmail" class="shop-ordering-info-attribute-value text-main-15" type="text">
                        </div>
                        <div class="shop-ordering-info-attribute">
                            <p class="shop-ordering-info-attribute-header text-main-15 "> Имя </p>
                            <input name="clientName" class="shop-ordering-info-attribute-value text-main-15" type="text">
                        </div>
                        <div class="shop-ordering-info-attribute">
                            <p class="shop-ordering-info-attribute-header text-main-15 "> Фамилия </p>
                            <input name="clientSurname" class="shop-ordering-info-attribute-value text-main-15" type="text">
                        </div>
                        <div class="shop-ordering-info-attribute">
                            <p class="shop-ordering-info-attribute-header text-main-15 "> Номер телефона </p>
                            <input name="clientPhoneNumber" class="shop-ordering-info-attribute-value text-main-15" type="text">
                        </div>
                    </div>
                </div>
                <div class="shop-ordering-info-bar">
                    <div class="shop-ordering-info-bar-header text-main-20">
                        Способ доставки
                    </div>
                    <div class="shop-ordering-info-attributes">
                        <div class="radio-select-bar">
                            <input class="radio-ordering" type="radio">
                            <div class="radio-select-image">
                                <img src="" alt="">
                            </div>
                            <div class="radio-select-description">
                                <div class="radio-select-header text-main-12 text-bold">
                                    Самовывоз
                                </div>
                                <div class="radio-select-value text-main-12">
                                    Бесплатно
                                </div>
                            </div>
                        </div>
                        <div class="radio-select-bar">
                            <input class="radio-ordering" type="radio">
                            <div class="radio-select-image">
                                <img src="" alt="">
                            </div>
                            <div class="radio-select-description">
                                <div class="radio-select-header text-main-12 text-bold">
                                    Доставка
                                </div>
                                <div class="radio-select-value text-main-12">
                                    200 руб.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="shop-ordering-info-bar">
                    <div class="shop-ordering-info-bar-header text-main-20">
                        Данные о доставке
                    </div>
                    <div class="shop-ordering-info-attributes">
                        <div class="shop-ordering-info-attribute">
                            <p class="shop-ordering-info-attribute-header text-main-15 "> Город </p>
                            <input name="city" class="shop-ordering-info-attribute-value text-main-15" type="text">
                        </div>
                        <div class="shop-ordering-info-attribute">
                            <p class="shop-ordering-info-attribute-header text-main-15 "> Улица </p>
                            <input name="street" class="shop-ordering-info-attribute-value text-main-15" type="text">
                        </div>
                        <div class="shop-ordering-info-attribute-house">
                            <div class="shop-ordering-info-attribute">
                                <p class="shop-ordering-info-attribute-header text-main-15 "> Номер дома </p>
                                <input name="houseNumber" class="shop-ordering-info-attribute-value text-main-15" type="text">
                            </div>
                            <div class="shop-ordering-info-attribute">
                                <p class="shop-ordering-info-attribute-header text-main-15 "> Квартира </p>
                                <input name="flatNumber" class="shop-ordering-info-attribute-value text-main-15" type="text">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="shop-ordering-info-bar">
                    <div class="shop-ordering-info-bar-header text-main-20">
                        Способ оплаты
                    </div>
                    <div class="shop-ordering-info-attributes">
                        <div class="radio-select-bar">
                            <input class="radio-ordering" type="radio">
                            <div class="radio-select-image">
                                <img src="" alt="">
                            </div>
                            <div class="radio-select-description">
                                <div class="radio-select-header text-main-12 text-bold">
                                    Наличными при получении
                                </div>
                                <div class="radio-select-value text-main-12">
                                    Курьеру или в пункте выдачи
                                </div>
                            </div>
                        </div>
                        <div class="radio-select-bar">
                            <input class="radio-ordering" type="radio">
                            <div class="radio-select-image">
                                <img src="" alt="">
                            </div>
                            <div class="radio-select-description">
                                <div class="radio-select-header text-main-12 text-bold">
                                    Картой
                                </div>
                                <div class="radio-select-value text-main-12">
                                    Онлайн оплата
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="shop-ordering-info-bar">
                    <div class="shop-ordering-info-bar-header text-main-20">
                        Комментарий к заказу
                    </div>
                    <div class="shop-ordering-info-attributes">
                        <textarea class="shop-ordering-comment text-main-15"></textarea>
                    </div>
                </div>

                <div class="shop-ordering-info-bar">
                    <br>
                    <div class="shop-ordering-info-bar-header text-main-15">
                        Итоговая стоимость заказа: 2200 руб.
                    </div>
                </div>

                <input type="submit" class="order-confirm-button text-main-20">
                    Оформить заказ
                </input>
            </div>

            <div class="shop-ordering-cart-info">
                <div class="shop-ordering-cart-bar">
                    <p class="shop-ordering-cart-bar-header text-main-15">
                        Мой заказ
                    </p>

                    <input name="orderCartId" type="hidden" value="${orderCartId}">

                    <div class="shop-ordering-cart-items">
                        <c:forEach var="cartItem" items="${orderCart.items}">
                            <div class="shop-ordering-cart-item">
                                <div class="shop-ordering-cart-item-info">
                                    <div class="shop-ordering-cart-item-name text-main-15 text-bold">
                                        ${cartItem.product.name}
                                    </div>
                                    <div class="shop-ordering-cart-item-number text-main-15">
                                        Количество: ${cartItem.number}
                                    </div>
                                </div>
                                <div class="shop-ordering-cart-item-cost text-main-15">
                                    ${cartItem.product.cost * cartItem.number}
                                </div>
                            </div>
                        </c:forEach>

                        <div class="shop-ordering-cart-info-item">
                            <div class="shop-ordering-cart-info-item-text text-main-15">
                                Стоимость заказа
                            </div>
                            <div class="shop-ordering-cart-item-cost text-main-15">
                                2100 руб.
                            </div>
                        </div>
                        <div class="shop-ordering-cart-info-item">
                            <div class="shop-ordering-cart-info-item-text text-main-15">
                                Стоимость доставки
                            </div>
                            <div class="shop-ordering-cart-item-cost text-main-15">
                                100 руб.
                            </div>
                        </div>
                        <div class="shop-ordering-cart-info-item">
                            <div class="shop-ordering-cart-info-item-text text-main-15">
                                Итого
                            </div>
                            <div class="shop-ordering-cart-item-cost text-main-15">
                                2200 руб.
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        </form>
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
