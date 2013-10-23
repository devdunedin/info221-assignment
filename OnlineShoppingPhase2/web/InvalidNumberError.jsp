<%-- 
    Document   : InvalidNumberFormat
    Created on : 18/09/2012, 4:23:00 PM
    Author     : devpu550
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
   <head>
       <link rel="stylesheet" type="text/css" href="/OnlineShoppingPhase2/style.css">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Invalid Number Error</title>
   </head>
   <body>
      <h1>Your input is invalid. Please Check</h1>
      <p><%=exception.getMessage()%></p>
      <a href="javascript:history.back()">Back</a>
   </body>
</html>
