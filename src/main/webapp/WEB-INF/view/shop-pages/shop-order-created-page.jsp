<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Заказ создан</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
    <style><%@include file="/sources/style/style.css"%></style>
</head>
<body>
    <div class="order-created-bar text-main-20">
        Заказ №${order.id} оформлен
        <br>
        Информация отправлена на электронную почту
        <br>
        <br>
        <a href="/">На главную</a>
    </div>
</body>
</html>
