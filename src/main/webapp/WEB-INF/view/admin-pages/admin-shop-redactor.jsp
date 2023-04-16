<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>
    <style><%@include file="/sources/style/style.css"%></style>
    <script type="text/javascript" charset="utf-8">
        <%@include file="/sources/script/image-script.js"%>
    </script>
</head>
<body>
<%@include file="../modules/module-admin-top-bar.jsp" %>
<div class="content">
    <%@include file="../modules/module-admin-side-panel.jsp"%>
    <div class="main-panel">
        <div class="text-header text-header-margin">
            Редактор
        </div>
        <div class="data-panel">
            <div style="height: 10px">

            </div>
            <div class="admin-menu-data-panel">
                <div class="panel-half-header text-header text-header-margin">
                    Картинки на главной странице
                </div>
                <form:form action="saveImage" modelAttribute="newImage">
                    <div class="fast-form">

                        <div class="fast-form-attribute">
                            <p class="fast-attribute-name text-main-14-gray">Ссылка</p>
                            <form:textarea id="url" class="fast-attribute-value" path="url" autocomplete="off" type="text"/>
                        </div>
                        <input class="input-panel-button" value="Добавить" type="submit"/>

                    </div>
                </form:form>
                <div class="admin-images-bar">
                    <c:forEach var="image" items="${images}">
                        <div class="admin-image-item">
                            <div class="image-item-header">
                                <c:url var="deleteButton" value="deleteImage">
                                    <c:param name="imageId" value="${image.id}"/>
                                </c:url>
                                <button style="height: 100%" onclick="window.location.href = '${deleteButton}'">
                                    <img style="height: 100%" src="/main/sources/images/garbage-trash-svgrepo-com.svg" alt="">
                                </button>
                            </div>
                            <div class="image-item-bar">
                                <img class="item-image" src="${image.url}" alt="">
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

