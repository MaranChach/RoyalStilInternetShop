const descTab = document.querySelector(".shop-product-description");
const detailsTab = document.querySelector(".shop-product-details");
const reviewsTab = document.querySelector(".shop-product-reviews");


document.querySelector(".tab-desc").addEventListener("click", function () {
    descTab.style = "display: block;";
    detailsTab.style = "display: none;";
    reviewsTab.style = "display: none;";
});

document.querySelector(".tab-details").addEventListener("click", function () {
    descTab.style = "display: none;";
    detailsTab.style = "display: block;";
    reviewsTab.style = "display: none;";
});

document.querySelector(".tab-reviews").addEventListener("click", function () {
    descTab.style = "display: none;";
    detailsTab.style = "display: none;";
    reviewsTab.style = "display: block;";
});