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
    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">
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
            <div class="side-bar-item" onclick="window.location.href = '/main/'">
                <p class="side-bar-item-text text-item" >Рабочий стол</p>
            </div>
            <div class="side-bar-item text-item" onclick="window.location.href = '/main/products'">
                <p class="side-bar-item-text text-item" >Заказы</p>
            </div>
            <div class="side-bar-item" onclick="window.location.href = '/main/products'">
                <p class="side-bar-item-text text-item">Товары</p>
            </div>
            <div class="side-bar-item">

            </div>
            <div class="side-bar-item">

            </div>
        </div>

    </div>
    <div class="main-panel" style="display: flex; padding: 10px">
        <div class="product-nav-panel">

        </div>


            <div class="product-panel">
            <form:form modelAttribute="product" action="saveProduct">
                <div class="product-header">
                    <p class="text-header-values">Товар</p>
                    <button class="delete-button">
                        Удалить
                    </button>
                    <input type="submit" value="Сохранить" class="input-panel-button filter-button" style="float: right"/>

                </div>
                <div>

                </div>

                <div class="product-data-panel">
                    <div class="product-nav-block">
                        <p class="text-header-values-group">Основное</p>
                        <br>
                        <form:hidden path="id"/>
                        <div class="product-attribute">
                            <p class="attribute-name">Название</p>
                            <form:input path="name" class="attribute-value" type="text"/>
                        </div>

                        <div class="product-attribute">
                            <p class="attribute-name">Артикул</p>
                            <form:input path="article" class="attribute-value" type="text"/>
                        </div>

                        <div class="product-attribute">
                            <p class="attribute-name">Цена</p>
                            <form:input path="cost" class="attribute-value" type="text"/>
                        </div>

                        <div class="product-attribute">
                            <p class="attribute-name">Количество</p>
                            <form:input path="number" class="attribute-value" type="text"/>
                        </div>

                        <div class="product-attribute">
                            <p class="attribute-name">Единица измерения</p>
                            <form:select path="unit" items="${unitsMap}" class="attribute-value"/>
                        </div>

                        <div class="product-attribute">
                            <p class="attribute-name">Категория</p>
                            <form:select path="category" items="${categoriesMap}" class="attribute-value"/>
                        </div>

                        <div class="product-attribute">
                            <p class="attribute-name">Производитель</p>
                            <form:select path="manufacturer" items="${manufacturersMap}" class="attribute-value"/>
                        </div>

                        <div class="product-attribute">
                            <p class="attribute-name">Описание</p>
                            <form:input path="description" class="attribute-value" type="text"/>
                        </div>
                    </div>

                </div>
            </form:form>
            </div>


    </div>
</div>
</body>
</html>
