<!--To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>



<html>
   <head>
      <title></title>
       <link rel="stylesheet" type="text/css" href="/OnlineShoppingPhase2/style.css">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   </head>
   <body>
      <h1> LOGIN </h1>
      <%
// get the HTTP status code
Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
String message = "Please log in to continue.";
if (statusCode != null && statusCode == HttpServletResponse.SC_UNAUTHORIZED) {
// if the status code was 401 then also get the error message
message = request.getAttribute("javax.servlet.error.message").toString();
}
%>
<p><%=message%></p>

       <form action="/OnlineShoppingPhase2/LoginCustomerServlet" method="post">
         <fieldset>
            <legend>Login Details</legend>
            
            <label>User Name:<input type="text" name="username"></label>
            <label>Password:<input type="password" name="password"></label>
            <button type="submit">Login</button>
         </fieldset>
      </form>
      
      
    
   </body>
</html>
