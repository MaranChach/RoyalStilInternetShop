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
    <title>Заказ №${order.id}</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
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
            Заказ №${order.id}
            <div>
                <c:url var="deleteButton" value="deleteOrder">
                    <c:param name="orderId" value="${order.id}"/>
                </c:url>
                <input ${isDisabled} type="submit" value="Сохранить" class="input-panel-button filter-button" style="float: right"/>
                <button class="delete-button">Удалить</button>
            </div>
        </div>
        <div class="order-data-panel">
            <div class="order-left-panel">
                <div class="order-date">
                    <c:url var="confirmButton" value="confirmOrder">
                        <c:param name="orderId" value="${order.id}"/>
                    </c:url>
                    <input class="order-date-picker text-main-12" type="date" value="${order.orderDate}">
                    <button ${isDisabled} class="input-panel-button filter-button" onclick="window.location.href = '${confirmButton}'">Завершить заказ</button>
                </div>
                <div class="order-list-header text-black">
                    Содержание заказа
                </div>
                <div class="order-list-bar">
                    <div class="order-list">
                        <div class="order-table-header">
                            <div class="order-list-id table-cell">
                                №
                            </div>
                            <div class="order-list-image-space table-cell">

                            </div>
                            <div class="order-list-name table-cell">
                                Название
                            </div>
                            <div class="order-list-cost table-cell">
                                Цена
                            </div>
                            <div class="order-list-number table-cell">
                                Кол-во
                            </div>
                            <div class="order-list-sum table-cell">
                                Стоимость
                            </div>
                        </div>

                        <c:forEach var="orderItem" items="${order.orderCart.items}">
                            <%--<div style="display: none">
                                ${orderSum = orderSum + (orderItem.product.cost * orderItem.number)}
                            </div>--%>
                            <div class="order-table-item">
                                <div class="order-list-id table-cell">
                                    ${iterator = iterator+1}
                                </div>
                                <div class="order-list-image-space table-cell">

                                </div>
                                <div class="order-list-name table-cell">
                                    ${orderItem.product.name}
                                </div>
                                <div class="order-list-cost table-cell">
                                    ${orderItem.product.cost}
                                </div>
                                <div class="order-list-number table-cell">
                                    ${orderItem.number}
                                </div>
                                <div class="order-list-sum table-cell">
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

                    <%--<div class="order-footer-attribute" style="height: 15px;">
                        <div class="order-footer-attribute-name text-main-12">
                            Скидка:
                        </div>
                        <div class="order-footer-attribute-value text-main-12 text-bold">
                            10 руб.
                        </div>
                    </div>
                    <div class="order-footer-attribute">
                        <a href="" onclick="" class="order-discount order-footer-attribute-value text-main-12">
                            Добавить скидку
                        </a>
                    </div>--%>

                    <c:if test="${isShipment}">
                        <div class="order-footer-attribute">
                            <div class="order-footer-attribute-name text-main-12">
                                Доставка:
                            </div>
                            <div class="order-footer-attribute-value text-main-12 text-bold">
                                100 руб.
                            </div>
                        </div>
                    </c:if>


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
<script>
    document.querySelector(".delete-button").addEventListener("click", function (){
        if(confirm("Вы уверены?")){
            window.location.href = "${deleteButton}";
        }
    });
</script>
</body>
</html>
