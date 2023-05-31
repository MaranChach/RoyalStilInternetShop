<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
<%--    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">--%>
    <%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>

    <style>
        <%@include file="/sources/style/style.css"%>
    </style>
    <script src="https://cdn.jsdelivr.net/npm/inputmask@5/dist/inputmask.min.js"></script>

</head>
<body>
    <div class="shop-main-container">
        <%@include file="../modules/module-shop-top-bar.jsp" %>

        <div class="shop-content">
            <div class="shop-login-form-container">
                <form:form action="registration" modelAttribute="client" class="shop-login-form">
                    <form:input path="email" placeholder="Электронная почта" name="email" type="email" class="shop-login-form-attribute text-main-15-gray"/>
                    <form:input path="phoneNumber" placeholder="Номер телефона" name="phoneNumber" type="text" class="shop-login-form-attribute text-main-15-gray" id="phone"/>
                    <form:input path="surname" placeholder="Фамилия" name="surname" type="text" class="shop-login-form-attribute text-main-15-gray"/>
                    <form:input path="name" placeholder="Имя" name="name" type="text" class="shop-login-form-attribute text-main-15-gray"/>
                    <input name="password" placeholder="Пароль" type="password" class="shop-login-form-attribute text-main-15-gray">
                    <input name="passwordConfirm" placeholder="Подтверждение пароля" type="password" class="shop-login-form-attribute text-main-15-gray">
                        <c:if test="${param.containsKey(\"passwordNotMatch\")}">
                            <div class="text-error-12 shop-login-error">
                                Пароли не совпадают
                            </div>
                        </c:if>
                        <c:if test="${param.containsKey(\"passwordNotProvided\")}">
                            <div class="text-error-12 shop-login-error">
                                Заполните пароль
                            </div>
                        </c:if>

                    <input value="Зарегистрироваться" type="submit" class="shop-login-form-submit input-panel-button text-main-12">

                </form:form>
            </div>
        </div>
    </div>

    <%@include file="../modules/module-shop-footer.jsp" %>

    <%@include file="../modules/module-phone-input-mask.jsp" %>

</body>
</html>
