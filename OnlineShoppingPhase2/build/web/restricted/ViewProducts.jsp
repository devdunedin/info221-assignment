<%@page import="java.text.NumberFormat"%>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*,dao.*,domain.*" %>

<!DOCTYPE html>
<html>
   <head>
      <link rel="stylesheet" type="text/css" href="/OnlineShoppingPhase2/style.css">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Products</title>
   </head>
   <body>
      <%@include file="/WEB-INF/jspf/NavigationMenu.jspf" %>
      <h1>Products</h1>
      <%
         Collection<Product> products =
               new OnlineJdbcDAO().getAllProducts();
      %>
      <%
      
      NumberFormat currency = NumberFormat.getCurrencyInstance();
      %>
      <table border ="1">
         <thead>
            <tr>
               <th>Product ID</th>
               <th>Name</th>
               <th>Description</th>
               <th>Category</th>
               <th>price</th>
               <th>Quantity in Stock</th>
            </tr>
         </thead>
         <tbody>
            <% for (Product product : products) {%>
            <tr>
               <td><%= product.getProductId()%></td>
               <td><%= product.getProductName()%></td>
               <td><%= product.getDescription()%></td>
               <td><%=product.getCategory()%></td>
               <td><%= currency.format(product.getPrice())%></td>
               <td><%= product.getQuantityInStock()%></td>
               <td><form action ="/OnlineShoppingPhase2/BuyServlet"><input type ="hidden" name ="productId" value ="<%=product.getProductId()%>">
                     <b> <input type ="submit" value = "Buy"></b></form></td>
            </tr>
            <%}%>
         </tbody>
      </table>
   </body>
</html>

