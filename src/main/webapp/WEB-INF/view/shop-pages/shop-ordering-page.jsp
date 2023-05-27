<%@ page import="java.rmi.server.UID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оформление заказа</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>

    <style>
        <%@include file="/sources/style/style.css"%>
    </style>

</head>
<body style="">
<div class="shop-main-container">
    <%@include file="../modules/module-shop-top-bar.jsp" %>

    <div style="flex-direction: column" class="shop-content">
        <div class="shop-product-header text-product-header">
            Оформление заказа
        </div>
        <form action="sendFullOrder" method="post">
            <input type="hidden" name="uid" value="${uid}">
            <input type="hidden" name="clientId" value="${client.id}">
            <div class="shop-ordering-content">
                <div class="shop-ordering-info">
                    <div class="shop-ordering-info-bar">
                        <div class="shop-ordering-info-bar-header text-main-20">
                            Покупатель
                        </div>
                        <div class="shop-ordering-info-attributes">
                            <div class="shop-info-attribute">
                                <p class="shop-ordering-info-attribute-header text-main-15 "> Email </p>
                                <input name="clientEmail" class="shop-ordering-info-attribute-value text-main-15"
                                       type="email" value="${client.email}">
                            </div>
                            <div class="shop-info-attribute">
                                <p class="shop-ordering-info-attribute-header text-main-15 "> Имя </p>
                                <input name="clientName" class="shop-ordering-info-attribute-value text-main-15"
                                       type="text" value="${client.name}">
                            </div>
                            <div class="shop-info-attribute">
                                <p class="shop-ordering-info-attribute-header text-main-15 "> Фамилия </p>
                                <input name="clientSurname" class="shop-ordering-info-attribute-value text-main-15"
                                       type="text" value="${client.surname}">
                            </div>
                            <div class="shop-info-attribute">
                                <p class="shop-ordering-info-attribute-header text-main-15 "> Номер телефона </p>
                                <input name="clientPhoneNumber" class="shop-ordering-info-attribute-value text-main-15"
                                       type="text" pattern="[0-9]{1}[0-9]{3}[0-9]{3}[0-9]{4}" value="${client.phoneNumber}">
                            </div>
                        </div>
                    </div>
                    <div class="shop-ordering-info-bar">
                        <div class="shop-ordering-info-bar-header text-main-20">
                            Способ доставки
                        </div>
                        <div class="shop-ordering-info-attributes">
                            <div class="radio-select-bar">
                                <input checked id="pickup" name="shipmentMethod" value="pickup" class="radio-ordering"
                                       type="radio">
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
                                <input id="ship" name="shipmentMethod" value="ship" class="radio-ordering" type="radio">
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
                    <div class="shop-ordering-info-bar hide" id="ordering-info">
                        <div class="shop-ordering-info-bar-header text-main-20">
                            Данные о доставке
                        </div>
                        <div class="shop-ordering-info-attributes">
                            <div class="shop-info-attribute">
                                <p class="shop-ordering-info-attribute-header text-main-15 "> Город </p>
                                <input name="city" class="shop-ordering-info-attribute-value text-main-15" type="text">
                            </div>
                            <div class="shop-info-attribute">
                                <p class="shop-ordering-info-attribute-header text-main-15 "> Улица </p>
                                <input name="street" class="shop-ordering-info-attribute-value text-main-15"
                                       type="email">
                            </div>
                            <div class="shop-ordering-info-attribute-house">
                                <div class="shop-info-attribute">
                                    <p class="shop-ordering-info-attribute-header text-main-15 "> Номер дома </p>
                                    <input name="houseNumber" class="shop-ordering-info-attribute-value text-main-15"
                                           type="text">
                                </div>
                                <div class="shop-info-attribute">
                                    <p class="shop-ordering-info-attribute-header text-main-15 "> Квартира </p>
                                    <input name="flatNumber" class="shop-ordering-info-attribute-value text-main-15"
                                           type="text">
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
                                <input checked id="cash" name="paymentMethod" value="cash" class="radio-ordering"
                                       type="radio">
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
                                <input id="card" name="paymentMethod" value="card" class="radio-ordering" type="radio">
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
                            Итоговая стоимость заказа: ${order.orderSum()} руб.
                        </div>
                    </div>

                    <input type="submit" value="Оформить заказ" class="order-confirm-button text-main-20">
                </div>

                <div class="shop-ordering-cart-info">
                    <div class="shop-ordering-cart-bar">
                        <p class="shop-ordering-cart-bar-header text-main-15">
                            Мой заказ
                        </p>

                        <input name="orderCartId" type="hidden" value="${orderCartId}">

                        <div class="shop-ordering-cart-items">
                            <c:forEach var="cartItem" items="${orderCart.items}">
                                <div style="display: none">
                                        ${orderSum = orderSum + (cartItem.product.cost * cartItem.number)}
                                </div>

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
                                            ${cartItem.product.cost * cartItem.number} руб.
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="shop-ordering-cart-info-item">
                                <div class="shop-ordering-cart-info-item-text text-main-15">
                                    Стоимость заказа
                                </div>
                                <div class="shop-ordering-cart-item-cost text-main-15">
                                    ${orderSum} руб.
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
                                    ${orderSum + 100} руб.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </form>
    </div>
</div>
<%@include file="../modules/module-shop-footer.jsp" %>
<script>
    <%@include file="/sources/script/show-address-info.js"%>
</script>
</body>
</html>
