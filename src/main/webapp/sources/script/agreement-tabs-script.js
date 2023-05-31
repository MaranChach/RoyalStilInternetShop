const user = document.getElementById("useram");
const personal = document.getElementById("personalam");

const buttonUser = document.getElementById("buttonUserAggr").addEventListener("click", function () {
    user.style.display = "block";
    personal.style.display = "none";
});

const buttonPersonal = document.getElementById("buttonPersonalDataAggr").addEventListener("click", function () {
    user.style.display = "none";
    personal.style.display = "block";
});