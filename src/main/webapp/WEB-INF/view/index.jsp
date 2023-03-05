<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
   <%--<meta charset="UTF-8">--%>
</head>

<body>
<h3>Products</h3>

<div>
   <div>
      <form:form action="saveProduct" modelAttribute="newProduct"
                 cssStyle="background-color: gray; ">
            <form:input path="name" label="Name"/> <br>

            <form:input path="number" label="Number"/> <br>

            <form:select path="unit">
               <form:options items="${unitsMap}" />
            </form:select> <br>

            <form:select path="category" label="Category">
               <form:options items="${categoriesMap}" />
            </form:select> <br>

            <form:select path="manufacturer" label="Manufacturer">
               <form:options items="${manufacturersMap}" />
            </form:select> <br>

            <form:input path="description" label="Description"/> <br>

            <input type="submit"/> <br><br>
         </form:form>
      <table border="1" style="">
         <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Number</th>
            <th>Unit</th>
            <th>Category</th>
            <th>Details</th>
            <th>Description</th>
         </tr>
         <c:forEach var="product" items="${products}">
            <tr>
               <td>${product.id}</td>
               <td>${product.name}</td>
               <td>${product.number}</td>
               <td>${product.unit.name}</td>
               <td>${product.category.name}</td>
               <td>
                  <c:forEach var="detail" items="${product.details.attributes}">
                     ${detail.parameter.name} = ${detail.value} ${detail.unit.name} <br>
                  </c:forEach>
               </td>
               <td>${product.description}</td>
            </tr>
         </c:forEach>
      </table>
      <br>
   </div>

   <div>
      <form:form action="saveUnit" modelAttribute="newUnit"
                 cssStyle="background-color: gray;">
         <form:input path="name"/>
         <input type="submit"/>
      </form:form>
      <table border="1" style="">
         <tr>
            <th>ID</th>
            <th>Name</th>
         </tr>
         <c:forEach var="unit" items="${units}">
            <tr>
               <td>${unit.id}</td>
               <td>${unit.name}</td>
            </tr>
         </c:forEach>
      </table>
   </div>

   <div>
      <form:form action="saveManufacturer" modelAttribute="newManufacturer"
                 cssStyle="background-color: gray; ">
         <form:input path="name" label="Name"/> <br>

         <input type="submit"/> <br><br>
      </form:form>
   </div>

   <div>
      <table border="1" style="">
         <tr>
            <th>ID</th>
            <th>Name</th>
         </tr>
         <c:forEach var="manufacturer" items="${manufacturers}">
            <tr>
               <td>${manufacturer.id}</td>
               <td>${manufacturer.name}</td>
            </tr>
         </c:forEach>
      </table>
   </div>



   <div>
      <form:form action="saveCategory" modelAttribute="newCategory"
                 cssStyle="background-color: gray; ">
         <form:input path="name" label="Name"/> <br>

         <input type="submit"/> <br><br>
      </form:form>
   </div>

   <div>
      <table border="1" style="">
         <tr>
            <th>ID</th>
            <th>Name</th>
         </tr>
         <c:forEach var="category" items="${categories}">
            <tr>
               <td>${category.id}</td>
               <td>${category.name}</td>
            </tr>
         </c:forEach>
      </table>
   </div>



</div>

<script charset="UTF-8" type="text/javascript">
   <%@include file="../../sources/script/script.js"%>
</script>
</body>
</html>
