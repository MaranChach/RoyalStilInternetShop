<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tpant
  Date: 2/24/2023
  Time: 12:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
<%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>
    <style><%@include file="/sources/style/style.css"%></style>
    <script src="https://www.google.com/jsapi"></script>
    <script>
<%--        <%@include file="/sources/script/getProductsPieChart.js"%>--%>
    </script>
    <script>
        const request = new XMLHttpRequest();
        let answer;

        request.onreadystatechange = function (){
            if(this.readyState == 4 && this.status == 200){
                answer = this.responseText;
            };
        };

        request.open("GET", "http://localhost:8080/main/api/weeklyOrders", true);
        request.send();

        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {

            let jsonans = JSON.parse(answer);

            console.log(jsonans);

            jsonans.unshift(["Дата", "Заказы"]);

            let data = google.visualization.arrayToDataTable(jsonans);
            let options = {
                title: 'Продажи за неделю',
                is3D: true,
                pieResidueSliceLabel: 'Остальное',
            };
            let chart = new google.visualization.ColumnChart(document.getElementById('sales'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<%@include file="../modules/module-admin-top-bar.jsp" %>
    <div class="content">
        <%@include file="../modules/module-admin-side-panel.jsp"%>
        <div class="main-panel">
            <div id="sales" class="orders-panel panel">

            </div>

            <div id="products" class="orders-sources-panel panel text-main-12">

            </div>
        </div>
    </div>
</body>
</html>
