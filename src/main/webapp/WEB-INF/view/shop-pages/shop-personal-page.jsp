<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
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
            Личный кабинет
        </div>

        <ul class="personal-buttons-bar" type="none">
            <li class="personal-button-bar" id="buttonOrders">
                <p class="personal-button-text">Заказы</p>
            </li>
            <li class="personal-button-bar" id="buttonInfo">
                <p class="personal-button-text">Информация</p>
            </li>
            <%--            <li class="personal-button-bar">--%>
            <%--                <p class="personal-button-text" href="">Информация</p>--%>
            <%--            </li>--%>
            <%--            <li class="personal-button-bar">--%>
            <%--                <p class="personal-button-text" href="">Информация</p>--%>
            <%--            </li>--%>
            <%--            <li class="personal-button-bar">--%>
            <%--                <p class="personal-button-text" href="">Информация</p>--%>
            <%--            </li>--%>
        </ul>

        <div id="orders">

            <c:if test="${orders.size() == 0}">
                <div class="shop-product-header text-main-20">
                    У вас пока нет заказов
                </div>
            </c:if>
            <c:if test="${orders.size() != 0}">
                <div class="shop-cart-bar-header">
                    <div class="shop-cart-item-cell cart-item-name text-gray">
                        №
                    </div>
                    <div class="shop-cart-item-cell cart-item-cost text-gray">
                        Дата
                    </div>
                    <div class="shop-cart-item-cell cart-item-number text-gray">
                        Статус
                    </div>
                    <div class="shop-cart-item-cell cart-item-final-cost text-gray">
                        Доставка
                    </div>
                    <div class="shop-cart-item-cell cart-item-delete text-gray">
                        Стоимость
                    </div>
                </div>
                <c:forEach var="item" items="${orders}">
                    <div class="shop-cart-bar-item">
                        <div class="shop-cart-item-cell cart-item-name text-black">
                                ${item.id}
                        </div>
                        <div class="shop-cart-item-cell cart-item-cost text-black">
                                ${item.orderDate}
                        </div>
                        <div class="shop-cart-item-cell cart-item-number text-black">
                                ${item.isConfirmedStr()}
                        </div>
                        <div class="shop-cart-item-cell cart-item-final-cost text-black">
                                ${item.shipmentMethodStr}
                        </div>
                        <div class="shop-cart-item-cell cart-item-delete text-black">
                                ${item.orderSum()}
                        </div>
                    </div>
                </c:forEach>
            </c:if>

        </div>

        <div style="display: none" id="info">
            <form:form modelAttribute="client" action="saveClientInfo" method="post" class="shop-client-info">
                <form:input type="hidden" value="${client.id}" path="id"></form:input>
                <input type="hidden" name="oldPhoneNumber" value="${client.phoneNumber}">
                <div class="shop-info-attribute">
                    <p class="shop-ordering-info-attribute-header text-main-15 "> Email </p>
                    <form:input name="clientEmail" class="shop-ordering-info-attribute-value text-main-15"
                                type="email" value="${client.email}" path="email" required="true"></form:input>
                </div>
                <div class="shop-info-attribute">
                    <p class="shop-ordering-info-attribute-header text-main-15 "> Имя </p>
                    <form:input name="clientName" class="shop-ordering-info-attribute-value text-main-15"
                                type="text" value="${client.name}" path="name" required="true"></form:input>
                </div>
                <div class="shop-info-attribute">
                    <p class="shop-ordering-info-attribute-header text-main-15 "> Фамилия </p>
                    <form:input name="clientSurname" class="shop-ordering-info-attribute-value text-main-15"
                                type="text" value="${client.surname}" path="surname" required="true"></form:input>
                </div>
                <div class="shop-info-attribute">
                    <p class="shop-ordering-info-attribute-header text-main-15 "> Номер телефона </p>
                    <form:input name="clientPhoneNumber" class="shop-ordering-info-attribute-value text-main-15"
                                type="text" value="${client.phoneNumber}" path="phoneNumber" required="true"
                                id="phone"></form:input>
                </div>
                <div class="shop-info-attribute">
                    <input class="shop-product-buy-button text-main-15"
                           type="submit" value="Сохранить">
                </div>

            </form:form>
        </div>


    </div>
</div>

<%@include file="../modules/module-shop-footer.jsp" %>

<script src="<c:url value="/sources/script/personal-tabs-script.js"/>"></script>

<%@include file="../modules/module-phone-input-mask.jsp" %>

</body>
</html>

