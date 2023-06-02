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

        <div class="shop-content" style="flex-direction: column">
            <div class="shop-catalog-header text-product-header">
                Контакты
            </div>
            <div>
                <p class="text-main-20">
                    Наш телефон:
                </p>

                <p class="text-product-header">
                    8 (49241) 3-20-03
                </p>
                <br><br>
                <p class="text-main-20">
                    Электронная почта:
                </p>
                <p class="text-main-20">
                    royalsteel-gus@mail.ru<br>
                </p>
                <br>
                <p class="text-main-20">
                    Время работы:
                </p>
                <br>
                <p class="text-main-15">
                    Пн-Пт: 9:00 - 19:00 <br>
                    Сб: 9:00 - 17:00 <br>
                    Вс: 9:00 - 15:00 <br>
                </p>
                <br>
                <p class="text-main-20">
                    Адрес
                </p>
                <br>
                <p class="text-main-15">
                    г. Гусь-Хрустальный
                    ул. Орловская, 15<br>
                </p>
            </div>
        </div>
    </div>

    <%@include file="../modules/module-shop-footer.jsp" %>

    <%@include file="../modules/module-phone-input-mask.jsp" %>

    <script src="<c:url value="/sources/script/agreement-tabs-script.js" />"></script>

</body>
</html>
