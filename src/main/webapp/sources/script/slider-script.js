
let offset = 0;

const lineClass = document.querySelector(".shop-slider-line");

document.querySelector(".shop-button-right").addEventListener('click', function () {
    if (offset >= 2850){
        offset = 0;
    }
    else{
        offset += 950;
    }
    lineClass.style.right = offset + "px";
});

document.querySelector(".shop-button-left").addEventListener('click', function () {
    if (offset <= 0){
        offset = 2850;
    }
    else{
        offset -= 950;
    }
    lineClass.style.right = offset + "px";
});
