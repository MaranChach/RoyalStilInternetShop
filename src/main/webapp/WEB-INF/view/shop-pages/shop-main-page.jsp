<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">

    <%--<style>
        <%@include file="/sources/style/style.css"%>
    </style>--%>
</head>
<body style="">
<div class="shop-main-container">
    <%@include file="../modules/module-shop-top-bar.jsp" %>

    <div class="shop-content">
        <div class="shop-categories-bar">
            <div class="shop-categories-buttons-bar">
                <c:forEach var="category" items="${categories}">
                    <c:url var="categoryButton" value="category">
                        <c:param name="categoryId" value="${category.id}"/>
                    </c:url>
                    <div onclick="window.location.href = '${categoryButton}'"
                         class="shop-categories-button shop-radius-catalog">
                        <p class="text-main-15 shop-categories-button-text">${category.name}</p>
                    </div>
                </c:forEach>
            </div>
<%--            <%@include file="../modules/module-shop-news-bar.jsp"%>--%>
        </div>
        <div class="shop-playground">

            <div class="shop-images-slider">
                <div class="shop-slider-line">
                    <c:forEach var="image" items="${images}">
                        <img class="slider-image" src="${image.url}" alt="">
                    </c:forEach>
                </div>
            </div>
            <div class="shop-slider-buttons-bar">
                <button class="shop-slider-button shop-button-left">
                    <img class="shop-slider-button-image"
                         src="<c:url value="/sources/images/arrow-left-circle-svgrepo-com.svg"/>">
                </button>


                <button class="shop-slider-button shop-button-right">
                    <img class="shop-slider-button-image"
                         src="<c:url value="/sources/images/arrow-right-circle-svgrepo-com.svg"/>">
                </button>
            </div>


            <div class="shop-benefits-bar">
                <div class="shop-benefits-bar-item">
                    <div class="shop-benefits-bar-item-image">
                        <image class="shop-benefits-image"
                               src="https://cdn-icons-png.flaticon.com/512/102/102660.png"></image>
                    </div>
                    <div class="shop-benefits-bar-item-text">
                        Акции и скидки для
                        постоянных клиентов
                    </div>
                </div>
                <div class="shop-benefits-bar-item">
                    <div class="shop-benefits-bar-item-image">
                        <image class="shop-benefits-image"
                               src="https://cdn-icons-png.flaticon.com/512/82/82477.png"></image>
                    </div>
                    <div class="shop-benefits-bar-item-text">
                        Качественные услуги
                        и сервис
                    </div>
                </div>
                <div class="shop-benefits-bar-item">
                    <div class="shop-benefits-bar-item-image">
                        <image class="shop-benefits-image"
                               src="https://cdn-icons-png.flaticon.com/512/1170/1170679.png"></image>
                    </div>
                    <div class="shop-benefits-bar-item-text">
                        Широкий ассортимент
                        товаров
                    </div>
                </div>
<%--                <div class="shop-benefits-bar-item">--%>
<%--                    <div class="shop-benefits-bar-item-image">--%>
<%--                        <image class="shop-benefits-image"--%>
<%--                               src="https://cdn-icons-png.flaticon.com/512/4437/4437655.png"></image>--%>
<%--                    </div>--%>
<%--                    <div class="shop-benefits-bar-item-text">--%>
<%--                        Широкий ассортимент--%>
<%--                        товаров--%>
<%--                    </div>--%>
<%--                </div>--%>
            </div>

            <div class="shop-new-products-bar">
                <div class="shop-new-products-header">
                    Новинки
                </div>

                <div class="shop-products-row">
                    <c:forEach var="product" items="${newProducts}">
                        <c:url var="productButton" value="product">
                            <c:param name="productId" value="${product.id}"/>
                        </c:url>
                        <c:url var="saveProduct" value="saveToCart">
                            <c:param name="productId" value="${product.id}"/>
                        </c:url>
                        <div class="shop-product">
                            <div onclick="window.location.href = '${productButton}'" class="shop-product-image-bar">
                                <img class="shop-catalog-product-image"
                                     onerror="this.src = '<c:url value="/sources/images/noimage_detail.png"/>'"
                                     src="${product.imageUrl}" alt="">
                            </div>
                            <div class="shop-product-info-bar">
                                <div onclick="window.location.href = '${productButton}'">
                                    <div class="shop-product-title text-product">
                                            ${product.name}
                                    </div>
                                    <div class="shop-product-cost text-product">
                                            ${product.cost} руб.
                                    </div>
                                </div>


                                <div class="shop-product-buy-button-bar">
                                    <button onclick="window.location.href = '${saveProduct}'"
                                            class="shop-product-buy-button text-product-buttons">
                                        В корзину
                                    </button>
                                    <%--<button class="shop-product-buy-button text-product-buttons">
                                        Купить
                                    </button>--%>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="shop-description-bar text-main-15">

                <p>
                    ${description.value}
                </p>

                <%--<p>Наш интернет-магазин сделан для того, чтобы Вы смогли удобно, без лишних забот найти и заказать то,
                    что Вас интересует. Мы заботимся о том, чтобы ассортимент в нашем интернет-магазине был всегда
                    актуальным, цены доступными, сервис лучшим.</p>
                <br>
                <p>Предлагаем Вам убедится в этом и сделать заказ в нашем интернет-магазине. Вы сможете быстро оплатить
                    и получить заказ. Подробнее о вариантах оплаты и доставки Вы сможете узнать на соответствующих
                    страницах.</p>
                <br>
                <p>Для всех постоянных клиентов мы делаем скидки и заботимся о том, чтобы интернет-магазин Вам нравился
                    и Вы приходили в него снова и снова.</p>
                <br>--%>
            </div>
        </div>
    </div>


</div>

<%@include file="../modules/module-shop-footer.jsp" %>

<script>
    <%@include file="/sources/script/slider-script.js"%>
</script>
</body>
</html>
