const orderingInfo = document.getElementById("ordering-info");
const shipButton = document.getElementById("ship");
const pickupButton = document.getElementById("pickup");

shipButton.addEventListener("click", function () {
    orderingInfo.classList.remove("hide");
});

pickupButton.addEventListener("click", function () {
    orderingInfo.classList.add("hide");
});

