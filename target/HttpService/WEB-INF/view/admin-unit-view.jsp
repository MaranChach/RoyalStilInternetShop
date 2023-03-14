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
        <div class="product-nav-panel">

        </div>


        <div class="product-panel">
            <c:url var="deleteButton" value="deleteUnit">
                <c:param name="unitId" value="${unit.id}"/>
            </c:url>
            <button class="delete-button" onclick="window.location.href = '${deleteButton}'">
                Удалить
            </button>
            <form:form modelAttribute="unit" action="saveUnit">
                <div class="product-header">
                    <p class="text-header-values">Единица измерения</p>

                    <input type="submit" value="Сохранить" class="input-panel-button filter-button" style="float: right"/>

                </div>
                <div>

                </div>

                <div class="product-data-panel">
                    <div class="product-nav-block">
                        <p class="text-header-values-group">Основное</p>
                        <br>
                        <form:hidden path="id"/>
                        <div class="form-attribute">
                            <p class="attribute-name">Название</p>
                            <form:input autocomplete="off" path="name" class="attribute-value" type="text"/>
                            <form:errors cssStyle="color: red" path="name"/>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
