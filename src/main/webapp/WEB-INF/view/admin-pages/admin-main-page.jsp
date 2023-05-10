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
    <title>Рабочий стол</title>
    <link rel="icon" href="<c:url value="/sources/images/logo-mini.png"/>">
<%--    <link rel="stylesheet" href="<c:url value="/sources/style/style.css" />">--%>
    <style><%@include file="/sources/style/style.css"%></style>
    <script src="https://www.google.com/jsapi"></script>
    <script>
        const request = new XMLHttpRequest();
        let answer;

        request.onreadystatechange = function (){
            if(this.readyState == 4 && this.status == 200){
                answer = this.responseText;
            };
        };

        request.open("GET", "http://trantinweb.site/main/api/weeklyOrders", false);
        request.send();

        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {

            let jsonans = JSON.parse(answer);
            let jsonansrotated = new Array();

            for (let i = jsonans.length - 1; i >= 0; i--) {
                jsonansrotated.push(jsonans[i]);
            }

            jsonansrotated.unshift(["Дата", "Заказы"]);

            let data = google.visualization.arrayToDataTable(jsonansrotated);
            // data.addColumn("string", "Дата");
            // data.addColumn("integer", "Заказы");
            let options = {
                title: 'Продажи за неделю',
                is3D: true,
                pieResidueSliceLabel: 'Остальное',
            };
            let chart = new google.visualization.LineChart(document.getElementById('sales'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<%@include file="../modules/module-admin-top-bar.jsp" %>
    <div class="content">
        <%@include file="../modules/module-admin-side-panel.jsp"%>
        <div class="main-panel">
            <div class="admin-main-page-column">
                <div class="info-panel panel">
                    <div class="info-panel-item">
                        <div class="info-panel-item-block info-panel-item-header text-main-15">
                            Новых заказов
                        </div>
                        <div class="info-panel-item-block info-panel-item-value text-main-24">
                            ${newOrdersNum}
                        </div>
                        <div class="info-panel-item-block info-panel-item-other text-main-12">
                            штук
                        </div>
                    </div>
                    <div class="info-panel-item">
                        <div class="info-panel-item-block info-panel-item-header text-main-15">
                            Всего товаров
                        </div>
                        <div class="info-panel-item-block info-panel-item-value text-main-24">
                            ${products.size()}
                        </div>
                        <div class="info-panel-item-block info-panel-item-other text-main-12">
                            штук
                        </div>
                    </div>
                    <div class="info-panel-item">
                        <div class="info-panel-item-block info-panel-item-header text-main-15">
                            Заказов сегодня
                        </div>
                        <div class="info-panel-item-block info-panel-item-value text-main-24">
                            ${todayOrdersNumber}
                        </div>
                        <div class="info-panel-item-block info-panel-item-other text-main-12">
                            штук
                        </div>
                    </div>
                    <div class="info-panel-item">
                        <div class="info-panel-item-block info-panel-item-header text-main-15">
                            Заказов вчера
                        </div>
                        <div class="info-panel-item-block info-panel-item-value text-main-24">
                            ${yesterdayOrdersNumber}
                        </div>
                        <div class="info-panel-item-block info-panel-item-other text-main-12">
                            штук
                        </div>
                    </div>
                </div>
                <div id="sales" class="orders-panel panel">

                </div>
            </div>
        </div>
    </div>
</body>
</html>
