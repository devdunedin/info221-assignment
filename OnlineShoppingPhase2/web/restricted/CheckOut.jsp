<%@page import="java.text.NumberFormat"%>
<%@page import="java.awt.event.ItemEvent"%>
<%@page import="domain.OrderItem"%>
<%@page import="domain.*"%>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
   <head>
      <link rel="stylesheet" type="text/css" href="/OnlineShoppingPhase2/style.css">
      <title>Checkout</title>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   </head>
   <body>
      <%@include file="/WEB-INF/jspf/NavigationMenu.jspf" %>
      <%
         Order order = (Order)session.getAttribute("order");
     
      %>
      <%
      
      NumberFormat currency = NumberFormat.getCurrencyInstance();
      %>
      <h1>Checkout</h1>
      <p>Your order currently consists of:</p>
            <table border ="1">
         <thead>
            <tr>
               <th>Product</th>
               <th>Price</th>
               <th>Quantity Ordered</th>
               <th>Item Total</th>
            </tr>
         </thead> 
         
         <%
         double total = 0;
         for(OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            total += item.getItemTotal();
           
         %>
         <tr>
               <td><%= product.getProductId()%></td>
               <td><%= currency.format(product.getPrice())%></td>
              <td><%= item.getQuantityPurchased()%></td>
              <td><%= currency.format(item.getItemTotal())%></td>
             
         </tr>
         
         <%}%>
         
        
            </table>
         <p><b>Total amount is:</b><b><%= currency.format(total)%></b></p> 
         <a href="/OnlineShoppingPhase2/CheckoutServlet"><button type="submit"><b>Confirm Order</b></button> </a>
                
   </body>
</html>
 
