<%-- 
    Document   : InputValidation
    Created on : 15/09/2012, 2:58:30 PM
    Author     : delfe834
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
   <head>
       <link rel="stylesheet" type="text/css" href="/OnlineShoppingPhase2/style.css">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title> Invalid Input </title>
   </head>
   <body>
      <h1>Invalid Input</h1>
      <p><%=exception.getMessage()%></p>
      <a href="javascript:history.back()">Back</a>
   </body>
</html>