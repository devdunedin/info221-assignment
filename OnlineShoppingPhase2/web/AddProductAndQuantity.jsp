<%@page import="domain.OrderItem"%>
<%@page import="domain.Product"%>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
 
<html>
   <head>
      <link rel="stylesheet" type="text/css" href="/OnlineShoppingPhase2/style.css">
      <title>Flanges and Widgets Shop</title>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   </head>
   <body>
      <%@include file="/WEB-INF/jspf/NavigationMenu.jspf" %>
      <h1>How many do you want to buy? </h1>
      
      <%Product product = (Product) session.getAttribute("product");%>
      
      <%if (product != null) {%>
      <p>You have selected: <%=product.getProductName()%></p>
      <p>The stock available is: <%=product.getQuantityInStock()%></p>
      
      
             <form action="/OnlineShoppingPhase2/AddProductAndQuantityServlet" method="post">
         <fieldset>
            <legend>Quantity to buy</legend>
            <label>How many?:<input type="text" name="quantityPurchased"></label><br>
          <input type ="submit" value = "Add Order">
         
           </fieldset>
      </form>
             <% } else { %>
             <p><a href="/OnlineShoppingPhase2/OtherError.jsp">Error has occurred</a></p>
            
             <% }%>
   </body>
</html>
