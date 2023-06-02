<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Юридическая информация</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>

    <style>
        <%@include file="/sources/style/style.css"%>
    </style>
    <script src="https://cdn.jsdelivr.net/npm/inputmask@5/dist/inputmask.min.js"></script>

</head>
<body>
    <div class="shop-main-container">
        <%@include file="../modules/module-shop-top-bar.jsp" %>

        <div class="shop-content text-main-15" style="flex-direction: column">
            <br>Получить заказ Вы можете:<br>
        <br><br>
            <strong>Самовывозом</strong><br>
            Вы всегда сможете забрать заказ сами из Пункта выдачи заказов. Ближайший пункт самовывоза в Вашем городе сообщит Вам менеджер во время звонка для подтверждения заказа. Оплата осуществляется наличными или банковской картой.<br>
        <br><br>
            <strong>Курьером</strong><br>
            В нашем магазине Вы можете заказать доставку до двери курьером и оплатить товар сразу при получении. Менеджеры нашего магазина свяжутся с Вами в течение суток после получения заказа и согласуют возможное время доставки заказа. В день доставки с Вами свяжется курьер для уточнения интервала времени доставки и точного адреса. Вы сможете в присутствии курьера проверить внешний вид товара. Для данного вида доставки доступна оплата наличными и банковской картой при получении заказа.<br>
        </div>
    </div>

    <%@include file="../modules/module-shop-footer.jsp" %>

    <%@include file="../modules/module-phone-input-mask.jsp" %>

    <script src="<c:url value="/sources/script/agreement-tabs-script.js" />"></script>

</body>
</html>
