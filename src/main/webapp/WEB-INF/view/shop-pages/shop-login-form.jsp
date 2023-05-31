<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
<%--    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">--%>
    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>

    <style>
        <%@include file="/sources/style/style.css"%>
    </style>
</head>
<body>
    <div class="shop-main-container">
        <%@include file="../modules/module-shop-top-bar.jsp" %>

        <div class="shop-content">
            <div class="shop-login-form-container">
                <div class="text-error-12 shop-login-error">
                    ${error}
                </div>

                <form action="/login" method="post" class="shop-login-form">
                    <input name="username" placeholder="Номер телефона"  type="text" class="shop-login-form-attribute text-main-15-gray" id="phone">
                    <input name="password" placeholder="Пароль" type="password" class="shop-login-form-attribute text-main-15-gray">

                    <input value="Войти" type="submit" class="shop-login-form-submit input-panel-button text-main-12">
                </form>
            </div>
        </div>
    </div>

    <%@include file="../modules/module-shop-footer.jsp" %>
    <%@include file="../modules/module-phone-input-mask.jsp" %>

</body>
</html>
