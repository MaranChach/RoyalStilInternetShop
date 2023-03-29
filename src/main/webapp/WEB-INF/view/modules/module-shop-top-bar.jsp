<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="shop-top-bar">
  <a class="text-main-15" style="float: right;" href="personal-page">Личный кабинет</a>
  <a class="text-main-15" style="float: right; margin-left: 10px" href="admin/">Администрирование</a>
</div>

<div class="shop-main-bar">
  <div onclick="window.location.href = '/main'"  class="shop-logo-bar">
    <img style="height: 90%;" src="/main/sources/images/logo2.png" alt="">
  </div>

  <div class="shop-search-bar">
    <input placeholder="Поиск" type="search" class="shop-search text-item">
  </div>

  <div class="shop-phone-number-bar">
    <p class="text-header">
      8 (492) 232-27-52
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

    </div>
  </div>
</div>