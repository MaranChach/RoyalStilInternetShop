<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tpant
  Date: 3/14/2023
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:choose>

        <c:when test="${empty orderCart}">
            <p>
                Корзина пуста
            </p>
        </c:when>

        <c:otherwise>
            <c:forEach var="item" items="${orderCart}">
                <p>${item.product.name}</p>
                <br>
            </c:forEach>
        </c:otherwise>

    </c:choose>


</body>
</html>
