const descTab = document.querySelector(".shop-product-description");
const detailsTab = document.querySelector(".shop-product-details");
const reviewsTab = document.querySelector(".shop-product-reviews");

const tabDesc = document.querySelector(".tab-desc");
const tabDetails = document.querySelector(".tab-details");
const tabReviews = document.querySelector(".tab-reviews");

let curTab;

tabDesc.addEventListener("click", function () {
    tabDesc.style.borderBottom = "dodgerblue solid 3px";
    tabDetails.style.borderBottom = "none";
    //tabReviews.style.borderBottom = "none";

    descTab.style = "display: block;";
    detailsTab.style = "display: none;";
    //reviewsTab.style = "display: none;";
});

tabDetails.addEventListener("click", function () {
    tabDesc.style.borderBottom = "none";
    tabDetails.style.borderBottom = "dodgerblue solid 3px";
    //tabReviews.style.borderBottom = "none";

    descTab.style = "display: none;";
    detailsTab.style = "display: block;";
    //reviewsTab.style = "display: none;";
});

tabReviews.addEventListener("click", function () {
    tabDesc.style.borderBottom = "none";
    tabDetails.style.borderBottom = "none";
    //tabReviews.style.borderBottom = "dodgerblue solid 3px";

    descTab.style = "display: none;";
    detailsTab.style = "display: none;";
    //reviewsTab.style = "display: block;";
});