const orderingInfo = document.getElementById("ordering-info");
const shipButton = document.getElementById("ship");
const pickupButton = document.getElementById("pickup");

const shipPricing = document.getElementById("shipment");
const selfPricing = document.getElementById("selfShipment");

shipButton.addEventListener("click", function () {
    orderingInfo.classList.remove("hide");
    shipPricing.classList.remove("hide");
    selfPricing.classList.add("hide");
});

pickupButton.addEventListener("click", function () {
    orderingInfo.classList.add("hide");
    shipPricing.classList.add("hide");
    selfPricing.classList.remove("hide");
});

