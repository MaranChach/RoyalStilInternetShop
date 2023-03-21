<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tpant
  Date: 2/24/2023
  Time: 12:19 PM
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
<body>
<div class="top-bar">
    <div class="logo-bar">
        <p class="text-logo">ROYAL STEEL</p>
    </div>
</div>
<div class="content">
    <div class="side-bar">
        <p class="side-bar-block-header text-header"> Основное </p>
        <div class="side-bar-block">
            <p class="side-bar-block-header" ></p>
            <div class="side-bar-item" onclick="window.location.href = 'http://localhost:8080/main/admin/'">
                <p class="side-bar-item-text text-item" >Рабочий стол</p>
            </div>
            <div class="side-bar-item text-item">
                <p class="side-bar-item-text text-item" >Заказы</p>
            </div>
            <div class="side-bar-item" onclick="window.location.href='products'">
                <p class="side-bar-item-text text-item" >Товары</p>
            </div>
            <div class="side-bar-item">

            </div>
            <div class="side-bar-item">

            </div>
        </div>

    </div>
    <div class="main-panel">
        <div class="text-header text-header-margin">
            Заказ №${order.id}
            <div>
                <input type="submit" value="Сохранить" class="input-panel-button filter-button" style="float: right"/>
                <button class="delete-button">Удалить</button>
            </div>
        </div>
        <div class="order-data-panel">
            <div class="order-left-panel">
                <div class="order-date">
                    <input class="order-date-picker text-main-12" type="date" value="${order.date}">
                </div>
                <div class="order-list-header text-black">
                    Содержание заказа
                </div>
                <div class="order-list-bar">
                    <div class="order-list">
                        <div class="order-table-header">
                            <div class="order-list-id order-cell">
                                №
                            </div>
                            <div class="order-list-image-space order-cell">

                            </div>
                            <div class="order-list-name order-cell">
                                Название
                            </div>
                            <div class="order-list-cost order-cell">
                                Цена
                            </div>
                            <div class="order-list-number order-cell">
                                Кол-во
                            </div>
                            <div class="order-list-sum order-cell">
                                Стоимость
                            </div>
                        </div>

                        <c:forEach var="orderItem" items="${order.orderCart.items}">
                            <div style="display: none">
                                ${orderSum = orderSum + (orderItem.product.cost * orderItem.number)}
                            </div>
                            <div class="order-table-item">
                                <div class="order-list-id order-cell">
                                    ${iterator = iterator+1}
                                </div>
                                <div class="order-list-image-space order-cell">

                                </div>
                                <div class="order-list-name order-cell">
                                    ${orderItem.product.name}
                                </div>
                                <div class="order-list-cost order-cell">
                                    ${orderItem.product.cost}
                                </div>
                                <div class="order-list-number order-cell">
                                    ${orderItem.number}
                                </div>
                                <div class="order-list-sum order-cell">
                                    ${orderItem.number * orderItem.product.cost}
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="order-data-footer">
                    <div class="order-footer-attribute">
                        <div class="order-footer-attribute-name text-main-12">
                            Стоимость заказа:
                        </div>
                        <div class="order-footer-attribute-value text-main-12 text-bold">
                            ${orderSum} руб.
                        </div>
                    </div>

                    <div class="order-footer-attribute">
                        <div class="order-footer-attribute-name text-main-12">
                            Скидка:
                        </div>
                        <div class="order-footer-attribute-value text-main-12 text-bold">
                            10 руб.
                        </div>
                    </div>

                    <div class="order-footer-attribute">
                        <div class="order-footer-attribute-name text-main-12">
                            Доставка:
                        </div>
                        <div class="order-footer-attribute-value text-main-12 text-bold">
                            100 руб.
                        </div>
                    </div>

                    <div class="order-footer-attribute">
                        <div class="order-footer-attribute-name text-black">
                            Итого:
                        </div>
                        <div class="order-footer-attribute-value text-black text-bold   ">
                            ${orderSum + orderDelivery - orderDiscount} руб.
                        </div>
                    </div>
                </div>
            </div>
            <div class="order-right-panel">
                <div class="order-client-contact">
                    <div class="order-list-header text-black">
                        Контакты
                    </div>
                    <div class="order-client-contact-name order-client-contact-item text-main-15">
                        ${order.client.surname} ${order.client.name}
                    </div>
                    <div class="order-client-contact-email order-client-contact-item text-main-12">
                        ${order.client.email}
                    </div>
                    <div class="order-client-contact-phone order-client-contact-item text-main-12">
                        ${order.client.phoneNumber}
                    </div>
                </div>
                <div class="order-client-address">
                    <div class="order-list-header text-black">
                        Адрес
                    </div>
                    <div class="order-client-contact-name order-client-contact-item text-main-12">
                        ${order.address.summary}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
