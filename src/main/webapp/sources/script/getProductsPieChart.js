const request = new XMLHttpRequest();
let answer;

request.onreadystatechange = function (){
    if(this.readyState == 4 && this.status == 200){
        answer = this.responseText;
    };
};

request.open("GET", "http://localhost:8080/main/api/products", true);
request.send();

google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart);
function drawChart() {

    let jsonans = JSON.parse(answer);

    jsonans.unshift(["Продукт", "Количество"]);

    console.log(jsonans);

    var data = google.visualization.arrayToDataTable(jsonans);
    var options = {
        title: 'Остатки товара',
        is3D: true,
        pieResidueSliceLabel: 'Остальное'
    };
    var chart = new google.visualization.PieChart(document.getElementById('products'));
    chart.draw(data, options);
}