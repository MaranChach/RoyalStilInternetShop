<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%@include file="../modules/module-shop-top-bar.jsp" %>

    <div style="flex-direction: column" class="shop-content">
        <div class="shop-product-header text-product-header">
            ${product.name}
        </div>
        <div class="shop-product-rating-bar">
            <div class="shop-product-article text-main-15">
                Артикул: ${product.article}
            </div>
            <div class="shop-product-reviews-number text-main-15">
                Отзывов: 0
            </div>
        </div>
        <div class="shop-product-content">
            <div class="shop-product-content-image-bar">
                <img class="shop-product-content-image"
                     onerror="this.src = '<c:url value="/sources/images/noimage_detail.png"/>'"
                     src="${product.imageUrl}" alt="">
            </div>
            <div class="shop-product-content-cost-bar">
                <div class="shop-product-content-number">
                    <div class="text-main-15">
                        Количество:
                    </div>
                    <input class="shop-product-number-input text-main-15" type="number">
                </div>

                <div class="shop-product-details-payment-bar">
                    <div class="shop-product-content-cost text-product-header">
                        ${product.cost} руб.
                    </div>

                    <c:choose>

                        <c:when test="${product.number <= '0'}">
                            <div style="color: red" class="shop-product-content-availability text-availability">
                                Нет в наличии
                            </div>

                            <button onclick="window.location.href = '${addToCartButton}'"
                                    style="background-color: gainsboro" disabled="disabled"
                                    class="shop-product-content-buy-button text-product-buttons">
                                Добавить
                            </button>
                        </c:when>

                        <c:otherwise>
                            <div class="shop-product-content-availability text-availability">
                                Есть в наличии
                            </div>

                            <c:url var="addToCartButton" value="saveToCart">
                                <c:param name="productId" value="${product.id}"/>
                            </c:url>

                            <button onclick="window.location.href='${addToCartButton}'"
                                    class="shop-product-content-buy-button text-product-buttons">
                                Добавить
                            </button>

                            <div class="shop-product-content-oneclick-buy-button text-product-buy">Купить в один клик
                            </div>
                        </c:otherwise>

                    </c:choose>

                </div>
            </div>
            <div class="shop-product-content-delivery-info-bar">
                <div class="shop-product-content-delivery-info">

                </div>
            </div>
        </div>

        <div class="shop-product-tabs-bar">
            <div class="shop-product-tab tab-desc text-header">
                Описание
            </div>
            <div class="shop-product-tab tab-details text-header">
                Характеристики
            </div>
            <div class="shop-product-tab tab-reviews text-header">
                Отзывы
            </div>
        </div>

        <div class="shop-product-tab-bar shop-product-description text-main-15">
            ${product.description}
        </div>

        <div class="shop-product-tab-bar shop-product-details text-main-15">
            <div style="display:flex;">
                <div class="details-attribute-row">
                    <div class="details-attribute-name">Производитель</div>
                    <div class="details-attribute-value">${product.manufacturer.name}</div>
                </div>
            </div>
            <c:forEach var="attribute" items="${product.details.attributes}">
                <div style="display:flex;">
                    <div class="details-attribute-row">
                        <div class="details-attribute-name">${attribute.parameter.name}</div>
                        <div class="details-attribute-value">${attribute.value} ${attribute.parameter.unit.name}</div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="shop-product-tab-bar shop-product-reviews text-main-15">
            <p>Отзывы Отзывы Отзывы </p>
            <p>Отзывы Отзывы Отзывы </p>
            <p>Отзывы Отзывы Отзывы </p>
        </div>
    </div>
</div>

<%@include file="../modules/module-shop-footer.jsp" %>

<script>
    <%@include file="/sources/script/product-tabs-script.js"%>
</script>
</body>
</html>
