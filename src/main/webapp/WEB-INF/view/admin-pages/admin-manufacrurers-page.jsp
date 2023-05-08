<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Производители</title>
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
        <div class="data-panel">
            <div class="menus-panel">

            </div>
            <div>
                <div style="float: right">
                    <div class="category-header">

                    </div>

                    <div class="table-panel">
                        <div class="table-header">
                            <p class="text-header">Производители</p>
                            <button onclick="window.location.href = 'manufacturer'" class="input-panel-button add-button">Добавить</button>
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


                            <c:forEach var="manufacturer" items="${manufacturers}">
                                <c:url var="updateButton" value="updateManufacturer">
                                    <c:param name="manufacturerId" value="${manufacturer.id}"/>
                                </c:url>
                                <div class="results-panel-item border-bottom-gray" onclick="window.location.href = '${updateButton}'">
                                    <div class="check-box center">
                                        <input type="checkbox">
                                    </div>
                                    <div class="name">${manufacturer.name}</div>
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
