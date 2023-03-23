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
            <div class="side-bar-item text-item" onclick="window.location.href = '/main/admin/products'">
                <p class="side-bar-item-text text-item" >Заказы</p>
            </div>
            <div class="side-bar-item" onclick="window.location.href = '/main/admin/products'">
                <p class="side-bar-item-text text-item">Товары</p>
            </div>
            <div class="side-bar-item">

            </div>
            <div class="side-bar-item">

            </div>
        </div>

    </div>
    <div class="main-panel" style="display: flex; padding: 10px">
        <div class="product-panel">
            <form:form modelAttribute="product" action="saveProduct">
                <div class="product-header">
                    <p class="text-header-values">Товар</p>
                    <button class="delete-button">
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
                                <form:option cssStyle="background-color: grey" value="${product.unit.id}" label="${product.unit.name}"></form:option>
                                <form:options items="${unitsMap}" ></form:options>
                            </form:select>
                        </div>

                        <div class="form-attribute text-main-14-gray">
                            <p class="attribute-name">Категория</p>
                            <form:select path="category" class="attribute-value">
                                <form:option cssStyle="background-color: grey" value="${product.category.id}" label="${product.category.name}"></form:option>
                                <form:options items="${categoriesMap}" ></form:options>
                            </form:select>
                        </div>

                        <div class="form-attribute text-main-14-gray">
                            <p class="attribute-name">Производитель</p>
                            <form:select path="manufacturer" class="attribute-value">
                                <form:option cssStyle="background-color: grey" value="${product.manufacturer.id}" label="${product.manufacturer.name}"></form:option>
                                <form:options items="${manufacturersMap}" ></form:options>
                            </form:select>
                        </div>

                        <div class="form-attribute text-main-14-gray">
                            <p class="attribute-name">Ссылка на картинку</p>
                            <div style="flex: 1">
                                <form:textarea type="text" autocomplete="off" path="imageUrl" class="attribute-value" id="url"/>
                            </div>
                        </div>

                        <div class="form-attribute text-main-14-gray">
                            <p class="attribute-name">Описание</p>
                            <form:textarea autocomplete="off" path="description" class="attribute-value"/>
                        </div>
                    </div>

                </div>
            </form:form>
            </div>

        <div class="product-nav-panel">
            <img class="admin-product-image" src="${product.imageUrl}" alt="">
        </div>
    </div>
</div>

    <script type="text/javascript" charset="utf-8">
        <%@include file="/sources/script/image-script.js"%>
    </script>

</body>
</html>
