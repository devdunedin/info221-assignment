<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
<head>
   <link rel="stylesheet" type="text/css" href="/OnlineShoppingPhase2/style.css">
   <title>Customer Management System</title>
</head>
<body>
<h1>Create an Account</h1>
<form action="/OnlineShoppingPhase2/AddCustomerServlet" method="post">
<fieldset>
<legend>Account Details</legend>
<label>User Name:<input type="text" name="userName"></label>
<label>Name:<input type="text" name="customerName"></label>
<label>Address:
<textarea name="address" rows="4" cols="1"></textarea></label>
<label>Credit Card:<input type="text" name="creditCardDetails"></label>
<label>Password:<input type="password" name="password"></label>
<button type="submit">Create Account</button>
</fieldset>
</form>

</body>
</html>