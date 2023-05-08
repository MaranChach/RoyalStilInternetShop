<%--
  Created by IntelliJ IDEA.
  User: tpant
  Date: 4/15/2023
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заказ создан</title>
    <style><%@include file="/sources/style/style.css"%></style>
</head>
<body>
    <div class="order-created-bar text-main-20">
        Заказ №${order.id} оформлен
        <br>
        Информация отправлена на электронную почту
        <br>
        <br>
        <a href="/main">На главную</a>
    </div>
</body>
</html>
