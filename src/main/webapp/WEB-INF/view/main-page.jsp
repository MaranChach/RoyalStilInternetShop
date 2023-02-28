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
    <title>Title</title>
    <style>
        <%@include file="static/style/style.css"%>
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
                <div class="side-bar-item text-item">
                    <p class="side-bar-item-text text-item" >fdasfdas</p>
                </div>
                <div class="side-bar-item">

                </div>
                <div class="side-bar-item">

                </div>
                <div class="side-bar-item">

                </div>
            </div>

        </div>
        <div class="main-panel">
<%--            <div class="orders-panel panel">--%>

<%--            </div>--%>

<%--            <div class="orders-sources-panel panel">--%>

<%--            </div>--%>
            <div class="text-header text-header-margin">
                Товары
            </div>
            <div class="data-panel">
                <div class="menus-panel">

                </div>
                <div>
                    <div class="sort-panel">

                    </div>

                    <div style="float: right">
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

                                </div>


                                <c:forEach var="product" items="${products}">
                                    <div class="results-panel-item border-bottom-gray">
                                        <div class="check-box center">
                                            <input type="checkbox">
                                        </div>
                                        <div class="article center">${product.id}</div>
                                        <div class="image center">img</div>
                                        <div class="name">${product.name}</div>
                                        <div class="price center">cost</div>
                                        <div class="count center">${product.number}</div>
                                        <div class="unit center">${product.unit.name}</div>
                                        <div class="activated center">a</div>
                                        <div class="delete-button center"></div>
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
