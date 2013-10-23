<%-- 
    Document   : index
    Created on : 4/09/2012, 2:46:10 PM
    Author     : devpu550
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/NavigationMenu.jspf" %>
<!DOCTYPE html>
<html>
   <head>
       <link rel="stylesheet" type="text/css" href="/OnlineShoppingPhase2/style.css">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>sundae shop</title>
   </head>
   <body>
      <h1>Welcome to the Sundae Place: Everyday feels like a Sunday here</h1>
      <p>
      
         <img src="/OnlineShoppingPhase2/sundae5.JPG" alt ="Sundae2" height="400" width ="400">
         <img src="/OnlineShoppingPhase2/sundae3.JPG" alt ="Sundae2" height="260" width ="400">
       <img src="/OnlineShoppingPhase2/sundae4.JPG" alt ="Sundae2" height="260" width ="400">
       
       <img src="/OnlineShoppingPhase2/sundae.jpg" alt ="Sundae" height="260" width ="400">
       
      
      </p>
       

     <%if(session.getAttribute("customer")== null){%>
          <p>
         You can either <a href="/OnlineShoppingPhase2/CustomerLogin.jsp">Log In</a>
         or
         <a href="/OnlineShoppingPhase2/AddCustomer.jsp">Create an Account</a>
         <%}%>
      </p>

         
         
      </body>
   
   
</html>
