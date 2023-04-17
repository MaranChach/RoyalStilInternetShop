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
            <c:url var="deleteButton" value="deleteManufacturer">
                <c:param name="manufacturerId" value="${manufacturer.id}"/>
            </c:url>


                <div class="product-header">
                    <p class="text-header-values">Производитель</p>
                    <button class="delete-button" onclick="window.location.href = '${deleteButton}'">
                        Удалить
                    </button>
                <form:form modelAttribute="manufacturer" action="saveManufacturer">
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
                            <p class="attribute-name text-main-14-gray">Название</p>
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
