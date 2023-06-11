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
    <title>Корзина</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>

    <style>
        <%@include file="/sources/style/style.css"%>
    </style>

</head>
<body>


<div class="shop-main-container">
    <%@include file="../modules/module-shop-top-bar.jsp" %>
    <div class="page-shadow">

    </div>
    <div style="flex-direction: column" class="shop-content">
        <div class="shop-product-header text-product-header">
            Корзина
        </div>

        <div class="shop-cart-bar">
            <c:choose>

                <c:when test="${empty orderCart.items}">
                    <p class="text-product">
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
                        <c:url var="deleteButton" value="deleteFromCart">
                            <c:param name="cartItemId" value="${item.id}"/>
                        </c:url>
                        <c:url var="productButton" value="product">
                            <c:param name="productId" value="${item.product.id}"/>
                        </c:url>
                        <div class="shop-cart-bar-item">
                            <div class="shop-cart-item-cell cart-item-name text-black" onclick="window.location.href='${productButton}'">
                                    ${item.product.name}
                            </div>
                            <div class="shop-cart-item-cell cart-item-cost text-black">
                                    ${item.product.cost}
                            </div>
                            <div class="shop-cart-item-cell cart-item-number text-black">
                                <c:url var="productButton" value="product">
                                    <c:param name="productId" value="${item.product.id}"/>
                                </c:url>
                                <c:url var="productButton" value="product">
                                    <c:param name="productId" value="${item.product.id}"/>
                                </c:url>
                                <img onclick="incrementItem(${item.id})" class="shop-order-cart-change-button plus" height="25px" src="<c:url value="/sources/images/plus-button.png"/>">
                                    <div id="itemCount" class="text-black">${item.number}</div>
                                <img onclick="decrementItem(${item.id})" class="shop-order-cart-change-button minus" height="25px" src="<c:url value="/sources/images/minus-button.png"/>">
                            </div>
                            <div id="itemSum" class="shop-cart-item-cell cart-item-final-cost text-black">
                                    ${item.number * item.product.cost}
                            </div>
                            <div class="shop-cart-item-cell cart-item-delete text-black">
                                <button class="cart-delete-button" onclick="window.location.href = '${deleteButton}'">
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
                        <form action="sendOrder">
                            <input name="orderCartId" type="hidden" value="${orderCart.id}">
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
                                        Пожалуйста, укажите свои данные, чтобы мы могли с вами связаться
                                    </div>
                                    <div class="dialog-body-attribute-header text-main-15">
                                        Ваше имя
                                    </div>
                                    <input name="name" class="dialog-body-attribute" type="text"/>

                                    <div class="dialog-body-attribute-header text-main-15">
                                        Номер телефона
                                    </div>
                                    <input name="phoneNumber" class="dialog-body-attribute" type="text"/>

                                    <div class="dialog-body-attribute-header text-main-15">
                                        Комментарий
                                    </div>
                                    <input name="comment" class="dialog-body-attribute" type="text"/>

                                    <div style="flex-direction: column; flex: 1;">

                                    </div>

                                    <input type="submit" value="Заказать" class="shop-product-buy-button">
                                </div>
                            </dialog>
                        </form>
                    </div>

                </c:otherwise>

            </c:choose>


        </div>
    </div>
</div>

<%@include file="../modules/module-shop-footer.jsp" %>

<script>
    <%@include file="/sources/script/open-order-dialog-script.js"%>
</script>

<script>

    document.querySelector(".plus").addEventListener("click", function (e){

    });


    const countBar = document.getElementById("itemCount");
    const sumBar = document.getElementById("itemSum");
    const url = new URL(window.location).host;
    function incrementItem (id){
        const request = new XMLHttpRequest();
        let answer;

        request.onreadystatechange = function (){
            if(this.readyState == 4 && this.status == 200){
                answer = this.responseText;
                let count = JSON.parse(answer);
                countBar.textContent = count[0];
                sumBar.textContent = count[1];
            };
        };

        request.open("POST", "http://" + url + "/incrementItem?itemCartId=" + id, false);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request.send();
    }

    function decrementItem (id){
        const request = new XMLHttpRequest();
        let answer;

        request.onreadystatechange = function (){
            if(this.readyState == 4 && this.status == 200){
                answer = this.responseText;
                let count = JSON.parse(answer);
                if (count == -1){
                    countBar.textContent = "0";
                    sumBar.textContent = "0";
                    return;
                }
                countBar.textContent = count[0];
                sumBar.textContent = count[1]
            };
        };

        request.open("POST", "http://" + url + "/decrementItem?itemCartId=" + id, false);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request.send();
    }
</script>
</body>
</html>

