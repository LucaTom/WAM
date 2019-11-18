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
Herzlich Willkommen! <br> 
Jetzt registrieren & dann loslegen: <br>
<form action="RegisterServlet" method="post">
	<input name="vorname" type="text" placeholder="Vorname" required> <br> 
	<input name="nachname" type="text" placeholder="Nachname" required> <br> 
	<input name="adresse" type="text" placeholder="Adresse" required> <br> 
	<input name="mailadresse" type="text" placeholder="Mailadresse" required> <br> 
	<input name="telefonnummer" type="text" placeholder="Telefonnummer" required> <br>
	<input name="benutzername" type="text" placeholder="Benutzername" required> <br> 
	<input name="passwort" type="password" placeholder="Passwort" required> <br> 
	
	<button type="submit" name="registrieren" value="registrieren">Registrieren</button> <br>
</form>
<br>
<a href="index.jsp" class="button">zurück</a><br>
</body>
</html>