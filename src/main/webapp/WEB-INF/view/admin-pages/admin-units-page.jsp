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
    <title>Единицы измерения</title>
    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">
</head>
<body>
<%@include file="../modules/module-admin-top-bar.jsp" %>
<div class="content">
    <%@include file="../modules/module-admin-side-panel.jsp"%>

    <div class="main-panel">
        <div class="text-header text-header-margin">
            Единицы измерения
        </div>
        <div style="height: 10px; background-color: white;">

        </div>
        <div class="data-panel" style="display:flex; justify-content: space-around">
            <div class="admin-menu-data-panel">
                <div class="table-header">
                    <p class="text-header"></p>
                    <button onclick="window.location.href = 'unit'" class="input-panel-button add-button">Добавить единицу</button>
                </div>
                <div class="input-group">
                    <button class="input-panel-button filter-button">Добавить фильтр</button>
                    <input class="search-input border-gray">
                    <div class="results-num-panel border-gray text">
                        Найдено результатов:
                    </div>
                </div>

                <div class="admin-table">
                        <div class="order-list-header-half table-item" style="border-top: grey 1px solid;">
                            <div class="table-marker table-cell">
                                <input class="marker" type="checkbox">
                            </div>
                            <div class="name table-cell">Наименование</div>
                            <div class="activated table-cell">Актиность</div>
                            <div class="list-delete-button table-cell"></div>
                        </div>

                        <div class="order-list-tab-all">
                            <c:forEach var="unit" items="${units}">
                                <c:url var="updateButton" value="updateUnit">
                                    <c:param name="unitId" value="${unit.id}"/>
                                </c:url>
                                <div class="table-item" onclick="window.location.href = '${updateButton}'">
                                    <div class="table-marker table-cell">
                                        <input class="marker" type="checkbox">
                                    </div>
                                    <div class="name table-cell">${unit.name}</div>
                                    <div class="activated table-cell"></div>
                                    <div class="list-delete-button table-cell"></div>
                                </div>
                            </c:forEach>
                        </div>




                </div>
            </div>
        </div>
    </div>


</div>
</body>
</html>
