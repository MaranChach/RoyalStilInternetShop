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
            Заказы
        </div>
        <div class="data-panel">
            <div class="admin-orders-panel">
                <div class="admin-orders-tabs-panel">
                    <div class="admin-orders-tab">

                    </div>
                    <div class="admin-orders-tab">

                    </div>
                    <div class="admin-orders-tab">

                    </div>
                </div>
                <div class="input-group" style=" margin: 20px">
                    <button class="input-panel-button filter-button">Добавить фильтр</button>
                    <input class="search-input border-gray">
                    <div class="results-num-panel border-gray text">
                        Найдено результатов:
                    </div>
                </div>
                <div class="admin-orders-list">
                    <div id="order-list-header" class="order-item">
                        <div class="order-marker order-cell">
                            <input class="marker" type="checkbox">
                        </div>
                        <div class="order-number order-cell">
                            Номер
                        </div>
                        <div class="order-status order-cell">
                            Статус
                        </div>
                        <div class="order-client order-cell">
                            Покупатель
                        </div>
                        <div class="order-town order-cell">
                            Город получателя
                        </div>
                        <div class="order-sum order-cell">
                            Сумма
                        </div>
                        <div class="order-payed order-cell">
                            Оплата
                        </div>
                        <div class="order-payment-method order-cell">
                            Метод оплаты
                        </div>
                        <div class="order-shipment-method order-cell">
                            Метод доставки
                        </div>
                        <div class="order-comment order-cell">
                            Менеджер
                        </div>
                        <div class="order-date-time order-cell">
                            Комментарий
                        </div>
                        <div class="order-edit-button order-cell">
                            Дата и время
                        </div>
                        <div class="order-delete-button order-cell">

                        </div>
                    </div>
                    <div class="order-item">
                        <div class="order-marker order-cell">
                            <input class="marker" type="checkbox">
                        </div>
                        <div class="order-number order-cell">
                            fffdsfdf
                        </div>
                        <div class="order-status order-cell">
                           fdafdsfasdf
                        </div>
                        <div class="order-client order-cell">
                            fdasfdsaf
                        </div>
                        <div class="order-town order-cell">
                            fdasfdsaf
                        </div>
                        <div class="order-sum order-cell">
                            fdasfdsaf
                        </div>
                        <div class="order-payed order-cell">
                            fdasfdsaf
                        </div>
                        <div class="order-payment-method order-cell">
                            fdasfdsaf
                        </div>
                        <div class="order-shipment-method order-cell">
                            fdasfdsaf
                        </div>
                        <div class="order-comment order-cell">
                            fdasfdsaf
                        </div>
                        <div class="order-date-time order-cell">
                            fdasfdsaf
                        </div>
                        <div class="order-edit-button order-cell">
                            fdasfdsaf
                        </div>
                        <div class="order-delete-button order-cell">
                            fdasfdsaf
                        </div>
                    </div>
                    <c:forEach var="order" items="${orders}">
                        <c:url var="orderButton" value="order">
                            <c:param name="orderId" value="${order.id}"/>
                        </c:url>
                        <div class="order-item" onclick="window.location.href = '${orderButton}'">
                            <div class="order-marker order-cell">
                                <input class="marker" type="checkbox">
                            </div>
                            <div class="order-number order-cell">
                                ${order.id}
                            </div>
                            <div class="order-status order-cell">
                                null
                            </div>
                            <div class="order-client order-cell">
                                ${order.client.name} ${order.client.surname}
                            </div>
                            <div class="order-town order-cell">
                                null
                            </div>
                            <div class="order-sum order-cell">
<%--                                ${order.orderSum()}--%>
                            </div>
                            <div class="order-payed order-cell">

                            </div>
                            <div class="order-payment-method order-cell">
                                null
                            </div>
                            <div class="order-shipment-method order-cell">
                                null
                            </div>
                            <div class="order-comment order-cell">

                            </div>
                            <div class="order-date-time order-cell">
                                ${order.date}
                            </div>
                            <div class="order-edit-button order-cell">
                                <button></button>
                            </div>
                            <div class="order-delete-button order-cell">
                                <button></button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
