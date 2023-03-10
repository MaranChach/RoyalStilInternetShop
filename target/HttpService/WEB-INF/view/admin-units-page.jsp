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
            <div class="side-bar-item" onclick="window.location.href = 'http://localhost:8080/main/admin/'">
                <p class="side-bar-item-text text-item" >Рабочий стол</p>
            </div>
            <div class="side-bar-item text-item">
                <p class="side-bar-item-text text-item" >Заказы</p>
            </div>
            <div class="side-bar-item" onclick="window.location.href='products'">
                <p class="side-bar-item-text text-item" >Товары</p>
            </div>
            <div class="side-bar-item">

            </div>
            <div class="side-bar-item">

            </div>
        </div>

    </div>
    <div class="main-panel">
        <div class="text-header text-header-margin">
            Единицы измерения
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
                            <p class="text-header">Единицы измерения</p>
                            <button onclick="window.location.href = 'unit'" class="input-panel-button add-button">Добавить единицу</button>
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
                                    <input type="checkbox">
                                </div>
                                <div class="name">Наименование</div>
                                <div class="activated center">Актиность</div>
                                <div class="list-delete-button center"></div>
                            </div>


                            <c:forEach var="unit" items="${units}">
                                <c:url var="updateButton" value="updateUnit">
                                    <c:param name="unitId" value="${unit.id}"/>
                                </c:url>
                                <div class="results-panel-item border-bottom-gray" onclick="window.location.href = '${updateButton}'">
                                    <div class="check-box center">
                                        <input type="checkbox">
                                    </div>
                                    <div class="name">${unit.name}</div>
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
