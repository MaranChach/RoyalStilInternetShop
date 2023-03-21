const all = document.querySelector(".order-list-tab-all");
const conf = document.querySelector(".order-list-tab-conf");
const notConf = document.querySelector(".order-list-tab-not-conf");

const allButton = document.querySelector(".tab-all-orders");
const confButton = document.querySelector(".tab-conf-orders");
const notConfButton = document.querySelector(".tab-not-conf-orders");

const defaultColor = allButton.style.backgroundColor;

allButton.addEventListener("click", function () {
    all.style.display =     "block";
    conf.style.display =    "none";
    notConf.style.display = "none";

    allButton.style.backgroundColor =       "gainsboro";
    confButton.style.backgroundColor =      defaultColor;
    notConfButton.style.backgroundColor =   defaultColor;
});

confButton.addEventListener("click", function () {
    all.style.display =     "none";
    conf.style.display =    "block";
    notConf.style.display = "none";

    allButton.style.backgroundColor =       defaultColor;
    confButton.style.backgroundColor =      "gainsboro";
    notConfButton.style.backgroundColor =   defaultColor;
});

notConfButton.addEventListener("click", function () {
    all.style.display =     "none";
    conf.style.display =    "none";
    notConf.style.display = "block";

    allButton.style.backgroundColor =       defaultColor;
    confButton.style.backgroundColor =      defaultColor;
    notConfButton.style.backgroundColor =   "gainsboro";
});