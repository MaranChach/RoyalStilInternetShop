const tabOrders = document.getElementById("orders");
const tabInfo = document.getElementById("info");

const buttonOrders = document.getElementById("buttonOrders");
const buttonInfo = document.getElementById("buttonInfo");

buttonOrders.addEventListener("click", function (){
    tabOrders.style.display = "block";
    tabInfo.style.display = "none";
});

buttonInfo.addEventListener("click", function (){
    tabOrders.style.display = "none";
    tabInfo.style.display = "block";
});