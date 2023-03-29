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

<div class="page-shadow">

</div>

<div class="shop-main-container">
    <%@include file="../modules/module-shop-top-bar.jsp"%>

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
                            <div class="shop-cart-item-cell cart-item-name text-black">
                                    ${item.product.name}
                            </div>
                            <div class="shop-cart-item-cell cart-item-cost text-black">
                                    ${item.product.cost}
                            </div>
                            <div class="shop-cart-item-cell cart-item-number text-black">
                                    ${item.number}
                            </div>
                            <div class="shop-cart-item-cell cart-item-final-cost text-black">
                                    ${item.number * item.product.cost}
                            </div>
                            <div class="shop-cart-item-cell cart-item-delete text-black">
                                <button class="cart-delete-button">
                                    <img class="cart-delete-icon"
                                         src="<c:url value="/sources/images/garbage-trash-svgrepo-com.svg"/>" alt="">
                                </button>
                            </div>
                        </div>
                    </c:forEach>


                    <div class="shop-cart-bar-footer">
                        <c:url var="orderSendButton" value="sendOrder">
                            <c:param name="orderCartId" value="${orderCart.id}"/>
                        </c:url>
                        <c:url var="orderingButton" value="ordering">
                            <c:param name="orderCartId" value="${orderCart.id}"/>
                        </c:url>

                        <button class="shop-product-buy-button shop-order-send-button"
                                onclick="window.location.href = '${orderingButton}'">
                            Оформить
                        </button>
                        <button class="shop-product-buy-button shop-order-send-button shop-order-one-click-buy"
                                onclick="">
                            Купить в один клик
                        </button>
                        <form:form modelAttribute="client" action="saveClient">
                            <dialog class="shop-order-one-click-buy-dialog">
                                <div class="order-dialog-header">
                                    <div class="order-dialog-header-text text-black">
                                        Оформить заказ
                                    </div>
                                    <div class="order-dialog-close-button">

                                    </div>
                                </div>
                                <div class="order-dialog-body">
                                    <div class="dialog-body-text text-main-15">
                                        Пожалуйста, укажите свой телефон, чтобы мы могли с вами связаться
                                    </div>
                                    <div class="dialog-body-attribute-header text-main-15">
                                        Ваше имя
                                    </div>
                                    <form:input path="name" class="dialog-body-attribute" type="text"/>

                                    <div class="dialog-body-attribute-header text-main-15">
                                        Номер телефона
                                    </div>
                                    <form:input path="phoneNumber" class="dialog-body-attribute" type="text"/>

                                    <div class="dialog-body-attribute-header text-main-15">
                                        Комментарий
                                    </div>
                                    <input class="dialog-body-attribute" type="text"/>

                                    <div style="flex-direction: column; flex: 1;">

                                    </div>

                                    <button class="shop-product-buy-button">
                                        Заказать
                                    </button>
                                </div>
                            </dialog>
                        </form:form>
                    </div>

                </c:otherwise>

            </c:choose>


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
    <%@include file="/sources/script/open-order-dialog-script.js"%>
</script>
</body>
</html>

