<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="shop-top-bar">

    <security:authorize access="hasAnyRole('USER', 'EMPLOYEE')">
        <a class="text-main-15" style="float: right;" href="personalPage">Личный кабинет</a>
    </security:authorize>


    <security:authorize access="hasRole('EMPLOYEE')">
        <a class="text-main-15" style="float: right; margin-left: 10px" href="admin/">Администрирование</a>
    </security:authorize>


    <security:authorize access="!hasRole('USER') && !hasRole('EMPLOYEE')">
        <a class="text-main-15" style="float: right; margin-left: 10px" href="login">Войти</a>
        <a class="text-main-15" style="float: right; margin-left: 10px" href="register">Зарегистрироваться</a>
    </security:authorize>

    <security:authorize access="hasAnyRole('USER', 'EMPLOYEE')">
        <a class="text-main-15" style="float: right; margin-left: 10px" href="logout">Выйти</a>
    </security:authorize>


</div>

<div class="shop-main-bar">
    <div onclick="window.location.href = '/'" class="shop-logo-bar">
        <img style="height: 90%;" src="<c:url value="/sources/images/logo2.png"/>" alt="">
    </div>
        <form class="shop-search-bar" action="search">
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
        <button class="shop-nav-button">

        </button>
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
            <div class="shop-info-bar-button text-main-14-white center">
                О магазине
            </div>
            <div class="shop-info-bar-button text-main-14-white center">
                Доставка
            </div>
            <div class="shop-info-bar-button text-main-14-white center">
                Контакты
            </div>
        </div>
    </div>
</div>