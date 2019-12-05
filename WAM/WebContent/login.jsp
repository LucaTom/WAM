<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
a.button {
    -webkit-appearance: button;
    -moz-appearance: button;
    appearance: button;

    text-decoration: none;
    color: initial;
}

p {
	color: red;
	font-size: 9.5px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Willkommen zurück! <br>
<form action="Login" method="post">

	<input name="benutzername" type= "text" placeholder="Benutzername" required> <br> 
	<input name="passwort" type= "password" placeholder="Passwort" required> <br> 
	<p><%=request.getAttribute("error_msg")==null?"":request.getAttribute("error_msg") %></p>	
	<button name="anmelden" value="anmelden">Anmelden</button> <br>
</form>



<br>
<a href="index.jsp" class="button">zurück</a><br>

</body>
</html>