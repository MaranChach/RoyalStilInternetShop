<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${curCategory.name}</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>

    <style>
        <%@include file="/sources/style/style.css"%>
    </style>
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

            <div class="shop-category-filters-bar">
                <div class="shop-category-filters-header text-main-15">
                    Фильтры
                </div>

                <div class="shop-categories-filters">

                    <div class="shop-filters">

                    </div>


                    <div class="shop-filters-buttons-bar">
                        <button class="shop-filter-button shop-filter-apply">Применить</button>

                        <button class="shop-filter-button shop-filter-clear">Сбросить</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="shop-playground">
            <div class="text-header shop-catalog-header">
                <div>
                    ${curCategory.name}
                </div>

                <form action="category" class="text-main-15 shop-catalog-sorter-bar">

                    Сортировка

                    <input name="categoryId" type="hidden" value="${curCategory.id}">
                    <input name="searchText" type="hidden" value="${searchText}">

                    <select id="sortSelect" onchange="document.forms[1].submit()" class="shop-sort-select text-main-12" name="sortType">
                        <option label="" value="none">
                            Нет
                        </option>
                        <option label="" value="costAsc">
                            По возрастанию цены
                        </option>
                        <option label="" value="costDesc">
                            По убыванию цены
                        </option>
                    </select>
                </form>
            </div>

            <div class="shop-catalog-products-bar">
                <c:forEach var="product" items="${products}">
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
    </div>
</div>

<%@include file="../modules/module-shop-footer.jsp" %>

<script src="<c:url value="/sources/script/sortTypeScript.js" />"></script>


</body>
</html>
