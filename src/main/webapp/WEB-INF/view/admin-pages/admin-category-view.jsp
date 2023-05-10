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
    <title>${category.name}</title>
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
    <div class="main-panel" style="display: flex; padding: 10px">
        <div class="product-panel">

            <div class="product-header">
                <p class="text-header-values">Товар</p>

                <c:url var="deleteButton" value="deleteCategory">
                    <c:param name="categoryId" value="${category.id}"/>
                </c:url>
                <button class="delete-button" onclick="window.location.href = '${deleteButton}'">
                    Удалить
                </button>
                <input type="button" onclick="document.forms[0].submit()" value="Сохранить" class="input-panel-button filter-button" style="float: right"/>
            </div>
            <form:form modelAttribute="category" action="saveCategory">
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

                        <div class="form-attribute text-main-14-gray">
                            <p class="attribute-name">Ссылка на картинку</p>
                            <div style="flex: 1">
                                <form:textarea type="text" autocomplete="off" path="imageUrl" class="attribute-value"
                                               id="url"/>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>

        <div class="product-nav-panel">
            <div class="admin-product-image-bar">
                <img class="admin-product-image" src="${category.imageUrl}"
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
