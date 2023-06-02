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
                О магазине
            </div>
            <div class="shop-info">

                <div class="shop-info-map">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d473.73637430546694!2d40.66198995450834!3d55.61101674910795!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x414be41223ca9219%3A0x22a08d4fc7f164b3!2z0YPQuy4g0J7RgNC70L7QstGB0LrQsNGPLCAxNSwg0JPRg9GB0Ywt0KXRgNGD0YHRgtCw0LvRjNC90YvQuSwg0JLQu9Cw0LTQuNC80LjRgNGB0LrQsNGPINC-0LHQuy4sIDYwMTUwMQ!5e0!3m2!1sru!2sru!4v1684765347333!5m2!1sru!2sru" width="100%" height="400px" style="border:0; margin: 5px" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>
                <div class="shop-info-text text-main-15">
                    <p class="text-main-20">Магазин находится по адресу:</p>
                    <br>
                    <p class="text-main-20">
                        г. Гусь-Хрустальный
                        <br>
                        ул. Орловская, 15
                    </p>
                    <br>
                    <br>
                    ${description}
                </div>
            </div>
        </div>
    </div>

    <%@include file="../modules/module-shop-footer.jsp" %>

    <%@include file="../modules/module-phone-input-mask.jsp" %>

    <script src="<c:url value="/sources/script/agreement-tabs-script.js" />"></script>

</body>
</html>
