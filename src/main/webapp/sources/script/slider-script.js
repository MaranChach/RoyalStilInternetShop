
function getImageNumber(){
    const number = document.querySelectorAll(".slider-image").length;

    /*const request = new XMLHttpRequest();
    let answer;

    request.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            answer = this.responseText;
        };
    };

    request.open("GET", "http://royal-steel.ru/api/mainPageImagesNumber", false);
    request.send();

    console.log(answer)

    let number = JSON.parse(answer);*/

    return number;
}

const image = document.querySelector(".slider-image");

let offset = 0;
let width = image.offsetWidth;
let maxOffset = getImageNumber() * width - width;
let allLength = getImageNumber() * width;

console.log(maxOffset);

const lineClass = document.querySelector(".shop-slider-line");
lineClass.style.width = allLength + "px";
document.querySelector(".shop-button-right").addEventListener('click', function () {
    if (offset >= maxOffset){
        offset = 0;
    }
    else{
        offset += 950;
    }
    lineClass.style.right = offset + "px";
});

document.querySelector(".shop-button-left").addEventListener('click', function () {
    if (offset <= 0){
        offset = maxOffset;
    }
    else{
        offset -= 950;
    }
    lineClass.style.right = offset + "px";
});
