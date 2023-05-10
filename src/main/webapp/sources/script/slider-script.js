
function getImageNumber(){
    const request = new XMLHttpRequest();
    let answer;

    request.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            answer = this.responseText;
        };
    };

    request.open("GET", "http://trantinweb.site/main/api/mainPageImagesNumber", false);
    request.send();

    console.log(answer)

    let number = JSON.parse(answer);

    return number;
}



let offset = 0;
let maxOffset = getImageNumber() * 950 - 950;
let allLength = getImageNumber() * 950;

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
