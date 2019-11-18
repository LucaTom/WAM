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
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Willkommen zurück! <br>
<form action="LoginServlet" method="post">
	<input name="benutzername" type= "text" placeholder="Benutzername"> <br> 
	<input name="passwort" type= "password" placeholder="Passwort"> <br> 
	
	<button name="anmelden" value="anmelden">Anmelden</button> <br>
</form>

<br>
<a href="index.jsp" class="button">zurück</a><br>

</body>
</html>