<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Характеристики</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>
    <style>
        <%@include file="/sources/style/style.css"%>
    </style>
</head>
<body>
<%@include file="../modules/module-admin-top-bar.jsp" %>
<div class="content">
    <%@include file="../modules/module-admin-side-panel.jsp" %>
    <div class="main-panel">
        <div class="text-header text-header-margin">
            Характеристики
        </div>
        <div style="height: 10px; background-color: white;">

        </div>
        <div class="data-panel" style="display:flex; justify-content: space-around">
            <div class="admin-menu-data-panel">
                <div class="panel-half-header text-header text-header-margin">
                    Виды характеристик
                </div>
                <form:form action="saveDetailsParameter" modelAttribute="newParameter">
                    <div class="fast-form">

                        <div class="fast-form-attribute">
                            <p class="fast-attribute-name text-main-14-gray">Имя</p>
                            <form:input class="fast-attribute-value" path="name" autocomplete="off" type="text"/>
                        </div>
                        <div class="fast-form-attribute">
                            <p class="fast-attribute-name text-main-14-gray">Единица измерения</p>
                            <form:select class="fast-attribute-value" path="unit">
                                <form:options items="${units}"/>
                            </form:select>
                        </div>
                        <input class="input-panel-button" value="Добавить" type="submit"/>

                    </div>
                </form:form>

                <div class="admin-table">
                    <div class="order-list-header-half table-item" style="border-top: grey 1px solid;">
                        <div class="table-marker table-cell">
                            <input class="marker" type="checkbox">
                        </div>
                        <div class="table-name-cell table-cell">
                            Наименование
                        </div>
                        <div class="details-unit table-cell">
                            Единица измерения
                        </div>
                        <div class="table-marker table-cell">
                            Удалить
                        </div>
                    </div>
                    <div class="order-list-tab-all">
                        <c:forEach var="parameter" items="${detailsParameters}">
                            <div class="table-item">
                                <div class="table-marker table-cell">
                                    <input class="marker" type="checkbox">
                                </div>
                                <div class="table-name-cell table-cell">
                                        ${parameter.name}
                                </div>
                                <div class="details-unit table-cell">
                                        ${parameter.unit.name}
                                </div>
                                <div class="table-marker table-cell">
                                    <c:url var="deleteButton" value="deleteDetailsParameter">
                                        <c:param name="parameterId" value="${parameter.id}"/>
                                    </c:url>
                                    <button class="delete-button-icon" onclick="window.location.href = '${deleteButton}'">
                                        <img style="height: 100%" src="<c:url value="/sources/images/garbage-trash-svgrepo-com.svg"/>" alt="">
                                    </button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    <%@include file="/sources/script/orders-tabs-script.js"%>
</script>

</body>
</html>
