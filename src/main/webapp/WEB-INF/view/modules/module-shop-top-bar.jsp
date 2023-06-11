<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="shop-top-bar">

    <security:authorize access="hasAnyRole('USER', 'EMPLOYEE')">
        <a class="shop-top-bar-button text-main-15"  href="personalPage">Личный кабинет</a>
    </security:authorize>


    <security:authorize access="hasRole('EMPLOYEE')">
        <a class="shop-top-bar-button text-main-15"  href="admin/">Администрирование</a>
    </security:authorize>


    <security:authorize access="!hasRole('USER') && !hasRole('EMPLOYEE')">
        <a class="shop-top-bar-button text-main-15" href="loginPage">Войти</a>
        <a class="shop-top-bar-button text-main-15" href="register">Зарегистрироваться</a>
    </security:authorize>

    <security:authorize access="hasAnyRole('USER', 'EMPLOYEE')">
        <a class="shop-top-bar-button text-main-15" href="logout">Выйти</a>
    </security:authorize>


</div>

<div class="shop-main-bar">
    <div onclick="window.location.href = '/'" class="shop-logo-bar">
        <div class="shop-logo-wrapper">
            <img class="shop-logo-image" src="<c:url value="/sources/images/logo2.png"/>" alt="">
        </div>
    </div>
        <form class="shop-search-bar" action="category">
            <input name="searchText" placeholder="Поиск" type="search" class="shop-search text-item">
        </form>
    <div class="shop-phone-number-bar">
        <p class="text-header">
            8 (49241) 3-20-03
        </p>
    </div>

    <div class="shop-buttons-bar">
        <button class="shop-nav-button">
            <img class="shop-nav-button-image" src="<c:url value="/sources/images/heart-svgrepo-com.svg"/>" alt="">
        </button>
        <%--<button class="shop-nav-button">

        </button>--%>
        <button onclick="window.location.href = 'cart'" class="shop-nav-button">
            <img class="shop-nav-button-image" src="<c:url value="/sources/images/cart-svgrepo-com.svg"/>" alt="">
        </button>
    </div>
</div>

<div class="shop-navbar">
    <div class="shop-catalog-bar center">
        <button onclick="window.location.href='catalog'" class="catalog-button text-product-buttons">
            Каталог
        </button>
    </div>
    <div class="shop-info-bar center">
        <div class="shop-info-buttons-bar">
            <div class="shop-info-bar-button text-main-14-white center" onclick="window.location.href='description'">
                О магазине
            </div>
            <div class="shop-info-bar-button text-main-14-white center" onclick="window.location.href='shipmentInfo'">
                Доставка
            </div>
            <div class="shop-info-bar-button text-main-14-white center" onclick="window.location.href='contactInfo'">
                Контакты
            </div>
        </div>
    </div>
</div>