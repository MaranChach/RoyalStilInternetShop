<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tpant
  Date: 2/28/2023
  Time: 1:51 PM
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
    <div class="main-panel" style="display: flex; padding: 10px">
        <div class="product-panel">
            <form:form modelAttribute="product" action="saveProduct">
            <div class="product-header">
                <p class="text-header-values">Товар</p>

                <c:url var="deleteButton" value="deleteProduct">
                    <c:param name="productId" value="${product.id}"/>
                </c:url>
                <button class="delete-button" onclick="window.location.href = '${deleteButton}'">
                    Удалить
                </button>
                <input type="submit" value="Сохранить" class="input-panel-button filter-button" style="float: right"/>
            </div>

            <div class="product-data-panel">
                <div class="product-nav-block">
                    <p class="text-header-values-group">Основное</p>
                    <br>
                    <form:hidden path="id"/>
                    <div class="form-attribute">
                        <p class="attribute-name text-main-14-gray">Название</p>
                        <form:input autocomplete="off" path="name" class="attribute-value" type="text"/>
                        <form:errors cssStyle="color: red" path="name"/>
                    </div>

                    <div class="form-attribute">
                        <p class="attribute-name text-main-14-gray">Артикул</p>
                        <form:input autocomplete="off" path="article" class="attribute-value" type="text"/>
                    </div>

                    <div class="form-attribute">
                        <p class="attribute-name text-main-14-gray">Цена</p>
                        <form:input autocomplete="off" path="cost" class="attribute-value" type="text"/>
                    </div>

                    <div class="form-attribute">
                        <p class="attribute-name text-main-14-gray">Количество</p>
                        <form:input path="number" class="attribute-value" type="number"/>
                    </div>

                    <div class="form-attribute text-main-14-gray">
                        <p class="attribute-name">Единица измерения</p>
                        <form:select path="unit" class="attribute-value">
                            <form:option cssStyle="background-color: grey" value="${product.unit.id}"
                                         label="${product.unit.name}"></form:option>
                            <form:options items="${unitsMap}"></form:options>
                        </form:select>
                    </div>

                    <div class="form-attribute text-main-14-gray">
                        <p class="attribute-name">Категория</p>
                        <form:select path="category" class="attribute-value">
                            <form:option cssStyle="background-color: grey" value="${product.category.id}"
                                         label="${product.category.name}"></form:option>
                            <form:options items="${categoriesMap}"></form:options>
                        </form:select>
                    </div>

                    <div class="form-attribute text-main-14-gray">
                        <p class="attribute-name">Производитель</p>
                        <form:select path="manufacturer" class="attribute-value">
                            <form:option cssStyle="background-color: grey" value="${product.manufacturer.id}"
                                         label="${product.manufacturer.name}"></form:option>
                            <form:options items="${manufacturersMap}"></form:options>
                        </form:select>
                    </div>

                    <div class="form-attribute text-main-14-gray">
                        <p class="attribute-name">Ссылка на картинку</p>
                        <div style="flex: 1">
                            <form:textarea type="text" autocomplete="off" path="imageUrl" class="attribute-value"
                                           id="url"/>
                        </div>
                    </div>

                    <div class="form-attribute text-main-14-gray">
                        <p class="attribute-name">Описание</p>
                        <form:textarea autocomplete="off" path="description" class="attribute-value"/>
                    </div>
                </div>
                </form:form>

                <div class="product-nav-block">
                    <p class="text-header-values-group">Характеристики</p>

                    <form action="saveDetailsAttribute">
                        <div class="fast-form">

                            <input type="hidden" name="id" value="${attribute.id}" />
                            <input type="hidden" name="productId" value="${product.id}">

                            <div class="fast-form-attribute">
                                <p class="fast-attribute-name text-main-14-gray">Параметр</p>
                                <select class="fast-attribute-value" name="parameter">
                                    <c:forEach var="detail" items="${detailsMap}">
                                        <option value="${detail.key}">${detail.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="fast-form-attribute">
                                <p class="fast-attribute-name text-main-14-gray">Значение</p>
                                <input class="fast-attribute-value" name="value" type="number"/>
                            </div>
                            <input class="input-panel-button" value="Добавить" type="submit"/>
                        </div>
                    </form>

                    <div class="admin-table">
                        <div id="table-list-header" class="table-item">
                            <div class="table-marker table-cell">
                                <input class="marker" type="checkbox">
                            </div>
                            <div class="order-number table-cell">
                                Параметр
                            </div>
                            <div class="order-status table-cell">
                                Значение
                            </div>
                            <div class="order-client table-cell">
                                Единица измерения
                            </div>

                            <div class="order-delete-button table-cell">

                            </div>
                        </div>
                        <div class="order-list-tab-all">
                            <c:forEach var="attribute" items="${product.details.attributes}">

                                <div class="table-item">
                                    <div class="table-marker table-cell">
                                        <input class="marker" type="checkbox">
                                    </div>
                                    <div class="order-number table-cell">
                                            ${attribute.parameter.name}
                                    </div>
                                    <div class="order-status table-cell">
                                            ${attribute.value}
                                    </div>
                                    <div class="order-client table-cell">
                                            ${attribute.parameter.unit.name}
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

        <div class="product-nav-panel">
            <div class="admin-product-image-bar">
                <img class="admin-product-image" src="${product.imageUrl}"
                     onerror="this.src = '<c:url value="/sources/images/noimage_detail.png"/>'" alt="">
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" charset="utf-8">
    <%@include file="/sources/script/image-script.js"%>
</script>

</body>
</html>
