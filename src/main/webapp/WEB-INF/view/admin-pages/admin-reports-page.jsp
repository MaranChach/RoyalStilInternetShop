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
    <title>Отчёты</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>
    <style>
        <%@include file="/sources/style/style.css" %>
    </style>

</head>
<body>
<%@include file="../modules/module-admin-top-bar.jsp" %>
<div class="content">
    <%@include file="../modules/module-admin-side-panel.jsp" %>
    <div class="main-panel">
        <div class="text-header text-header-margin">
            Отчёты
        </div>
        <div class="data-panel">
            <div style="height: 10px">

            </div>
            <form action="ordersReport" method="post" class="admin-menu-data-panel">
                <div class="admin-row-button text-main-15">
                    От: <input name="start" class="order-date-picker text-main-12" type="date"> до: <input name="end" class="order-date-picker text-main-12" type="date">
                </div>
                <input value="Отчёт по заказам в Excel" type="submit" class="admin-row-button text-main-20">
            </form>

            <form action="ordersReportPdf" target="_blank" method="post" class="admin-menu-data-panel">
                <div class="admin-row-button text-main-15">
                    От: <input name="start" class="order-date-picker text-main-12" type="date"> до: <input name="end" class="order-date-picker text-main-12" type="date">
                </div>
                <input value="Отчёт по заказам в PDF" type="submit" class="admin-row-button text-main-20">
            </form>

            <div class="admin-menu-data-panel">
                <button class="admin-row-button text-main-20" onclick="window.location.href='productsReport'">
                    Выгрузка товаров
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>

