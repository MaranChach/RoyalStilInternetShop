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
<%@include file="../modules/module-admin-top-bar.jsp" %>
<div class="content">
    <%@include file="../modules/module-admin-side-panel.jsp"%>
    <div class="main-panel">
        <div class="text-header text-header-margin">
            Заказы
        </div>
        <div class="data-panel">
            <div style="height: 10px">

            </div>
            <div class="admin-menu-data-panel">
                <div class="admin-orders-tabs-panel">
                    <div class="admin-orders-tab tab-all-orders">

                    </div>
                    <div class="admin-orders-tab tab-conf-orders">

                    </div>
                    <div class="admin-orders-tab tab-not-conf-orders">

                    </div>
                </div>
                <div class="input-group" style=" margin: 20px">
                    <button class="input-panel-button filter-button">Добавить фильтр</button>
                    <input class="search-input border-gray">
                    <div class="results-num-panel border-gray text">
                        Найдено результатов: ${orders.size()}
                    </div>
                </div>
                <div class="admin-table">
                    <div id="table-list-header" class="table-item">
                        <div class="table-marker table-cell">
                            <input class="marker" type="checkbox">
                        </div>
                        <div class="order-number table-cell">
                            Номер
                        </div>
                        <div class="order-status table-cell">
                            Статус
                        </div>
                        <div class="order-client table-cell">
                            Покупатель
                        </div>
                        <div class="order-town table-cell">
                            Город получателя
                        </div>
                        <div class="order-sum table-cell">
                            Сумма
                        </div>
                        <div class="order-payed table-cell">
                            Оплата
                        </div>
                        <div class="order-payment-method table-cell">
                            Метод оплаты
                        </div>
                        <div class="order-shipment-method table-cell">
                            Метод доставки
                        </div>
                        <div class="order-date-time table-cell">
                            Комментарий
                        </div>
                        <div class="order-edit-button table-cell">
                            Дата и время
                        </div>
                        <div class="order-delete-button table-cell">

                        </div>
                    </div>
                    <div class="order-list-tab-all">
                        <c:forEach var="order" items="${orders}">
                            <c:url var="orderButton" value="order">
                                <c:param name="orderId" value="${order.id}"/>
                            </c:url>

                            <div style="display: none">
                                    ${orderSum = 0}

                                <c:forEach var="orderItem" items="${order.orderCart.items}">
                                    ${orderSum = orderSum + orderItem.product.cost}
                                </c:forEach>
                            </div>


                            <div class="table-item" onclick="window.location.href = '${orderButton}'">
                                <div class="table-marker table-cell">
                                    <input class="marker" type="checkbox">
                                </div>
                                <div class="order-number table-cell">
                                        ${order.id}
                                </div>
                                <div class="order-status table-cell">
                                        ${order.isConfirmedStr()}
                                </div>
                                <div class="order-client table-cell">
                                        ${order.client.name} ${order.client.surname}
                                </div>
                                <div class="order-town table-cell">
                                        ${order.address.city.name}
                                </div>
                                <div class="order-sum table-cell">
                                        ${orderSum} руб.
                                </div>
                                <div class="order-payed table-cell">

                                </div>
                                <div class="order-payment-method table-cell">
                                    ${order.paymentMethodStr}
                                </div>
                                <div class="order-shipment-method table-cell">
                                    ${order.shipmentMethodStr}
                                </div>

                                <div class="order-comment table-cell">

                                </div>
                                <div class="order-date-time table-cell">
                                        ${order.orderDate}
                                </div>
                                <div class="order-edit-button table-cell">
                                    <button></button>
                                </div>
                                <div class="order-delete-button table-cell">
                                    <button></button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="order-list-tab-conf">
                        <c:forEach var="order" items="${ordersConf}">
                            <c:url var="orderButton" value="order">
                                <c:param name="orderId" value="${order.id}"/>
                            </c:url>

                            <div style="display: none">
                                    ${orderSum = 0}

                                <c:forEach var="orderItem" items="${order.orderCart.items}">
                                    ${orderSum = orderSum + orderItem.product.cost}
                                </c:forEach>
                            </div>


                            <div class="table-item" onclick="window.location.href = '${orderButton}'">
                                <div class="table-marker table-cell">
                                    <input class="marker" type="checkbox">
                                </div>
                                <div class="order-number table-cell">
                                        ${order.id}
                                </div>
                                <div class="order-status table-cell">
                                        ${order.isConfirmedStr()}
                                </div>
                                <div class="order-client table-cell">
                                        ${order.client.name} ${order.client.surname}
                                </div>
                                <div class="order-town table-cell">
                                        ${order.address.city.name}
                                </div>
                                <div class="order-sum table-cell">
                                        ${orderSum}
                                </div>
                                <div class="order-payed table-cell">

                                </div>
                                <div class="order-payment-method table-cell">
                                    null
                                </div>
                                <div class="order-shipment-method table-cell">
                                    null
                                </div>

                                <div class="order-comment table-cell">

                                </div>
                                <div class="order-date-time table-cell">
                                        ${order.orderDate}
                                </div>
                                <div class="order-edit-button table-cell">
                                    <button></button>
                                </div>
                                <div class="order-delete-button table-cell">
                                    <button></button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="order-list-tab-not-conf">
                        <c:forEach var="order" items="${ordersNotConf}">
                            <c:url var="orderButton" value="order">
                                <c:param name="orderId" value="${order.id}"/>
                            </c:url>

                            <div style="display: none">
                                    ${orderSum = 0}

                                <c:forEach var="orderItem" items="${order.orderCart.items}">
                                    ${orderSum = orderSum + orderItem.product.cost}
                                </c:forEach>
                            </div>


                            <div class="table-item" onclick="window.location.href = '${orderButton}'">
                                <div class="table-marker table-cell">
                                    <input class="marker" type="checkbox">
                                </div>
                                <div class="order-number table-cell">
                                        ${order.id}
                                </div>
                                <div class="order-status table-cell">
                                        ${order.isConfirmedStr()}
                                </div>
                                <div class="order-client table-cell">
                                        ${order.client.name} ${order.client.surname}
                                </div>
                                <div class="order-town table-cell">
                                        ${order.address.city.name}
                                </div>
                                <div class="order-sum table-cell">
                                        ${orderSum}
                                </div>
                                <div class="order-payed table-cell">

                                </div>
                                <div class="order-payment-method table-cell">
                                    null
                                </div>
                                <div class="order-shipment-method table-cell">
                                    null
                                </div>

                                <div class="order-comment table-cell">

                                </div>
                                <div class="order-date-time table-cell">
                                        ${order.orderDate}
                                </div>
                                <div class="order-edit-button table-cell">
                                    <button></button>
                                </div>
                                <div class="order-delete-button table-cell">
                                    <button></button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script>
    <%@include file="/sources/script/orders-tabs-script.js"%>
</script>

</body>
</html>
