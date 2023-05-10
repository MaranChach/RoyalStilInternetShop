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
    <title>Товары</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
<%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>
    <style><%@include file="/sources/style/style.css"%></style>
</head>
<body>
<%@include file="../modules/module-admin-top-bar.jsp" %>
<div class="content">
    <%@include file="../modules/module-admin-side-panel.jsp"%>
    <div class="main-panel">
        <div class="text-header text-header-margin">
            Товары
        </div>
        <div class="data-panel">
            <div class="menus-panel">

            </div>
            <div>
                <div class="admin-categories-panel">
                    <div class="admin-categories-panel-header">

                        <button class="input-panel-button add-button" onclick="window.location.href = 'category'">
                            Добавить
                        </button>
                    </div>
                    <div class="admin-categories-items">
                        <c:forEach var="category" items="${categories}">
                            <c:url var="categoryButton" value="updateCategory">
                                <c:param name="id" value="${category.id}"/>
                            </c:url>
                            <div class="admin-categories-item text-main-15" onclick="window.location.href = '${categoryButton}'">
                                <p>${category.name}</p>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div style="width: 73%; float: right">
                    <div class="category-header">

                    </div>

                    <div class="table-panel">
                        <div class="table-header">
                            <p class="text-header">Товары</p>
                            <button onclick="window.location.href = 'product'" class="input-panel-button add-button">Добавить товар</button>
                        </div>

                        <div class="input-group">
                            <button class="input-panel-button filter-button">Добавить фильтр</button>
                            <input class="search-input border-gray">
                            <div class="results-num-panel border-gray text">
                                Найдено результатов:
                            </div>
                        </div>
                        <div class="results-panel text border-gray">
                            <div class="results-panel-header">
                                <div class="check-box center">
                                    <input id="select-all" type="checkbox">
                                </div>
                                <div class="article center">Артикул</div>
                                <div class="image center">Картинка</div>
                                <div class="name">Наименование</div>
                                <div class="price center">Цена</div>
                                <div class="count center">Количество</div>
                                <div class="unit center">Ед. изм.</div>
                                <div class="activated center">Актиность</div>
                                <div class="list-delete-button center"></div>
                            </div>


                            <c:forEach var="product" items="${products}">
                                <c:url var="updateButton" value="updateProduct">
                                    <c:param name="productId" value="${product.id}"/>
                                </c:url>
                                <div class="results-panel-item border-bottom-gray" onclick="window.location.href = '${updateButton}'">
                                    <div class="check-box center">
                                        <input class="select-product" type="checkbox">
                                    </div>
                                    <div class="article center">${product.article}</div>
                                    <div class="image center">
                                        <img class="image-thumb center" src="${product.imageUrl}" alt="" onerror="this.src = '<c:url value="/sources/images/noimage_detail.png"/>'">
                                    </div>
                                    <div class="name">${product.name}</div>
                                    <div class="price center">${product.cost}</div>
                                    <div class="count center">${product.number}</div>
                                    <div class="unit center">${product.unit.name}</div>
                                    <div class="activated center">a</div>
                                    <div class="list-delete-button center"></div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
