<%-- 
    Document   : Other Error
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
      <title>Other Error</title>
   </head>
   <body>
      <h1>there was an error mate !!</h1>
      
       <img src="/OnlineShoppingPhase2/error.JPG" alt ="Sundae2" height="400" width ="330">
        <img src="/OnlineShoppingPhase2/error2.JPG" alt ="Sundae2" height="400" width ="600">
         <img src="/OnlineShoppingPhase2/error3.JPG" alt ="Sundae2" height="400" width ="400">
          <img src="/OnlineShoppingPhase2/error4.JPG" alt ="Sundae2" height="400" width ="305">
      <p><%=exception.getMessage()%></p>
      <p><%=exception.toString()%></p>
      <a href="javascript:history.back()">Back</a>
   </body>
</html>
