const orderDialog = document.querySelector(".shop-order-one-click-buy-dialog");
const pageShadow = document.querySelector(".page-shadow");

const orderButton = document.querySelector(".shop-order-one-click-buy");
const closeButton = document.querySelector(".order-dialog-close-button");

orderButton.addEventListener("click", function () {
    orderDialog.show();
    pageShadow.style.display = "block";
});

closeButton.addEventListener("click", function () {
    orderDialog.close();
    pageShadow.style.display = "none";
});