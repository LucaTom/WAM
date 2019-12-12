<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="wam.server.serialize.User" %>
<%@ page import="wam.server.serialize.UserDao" %>

<%@ page import="wam.server.serialize.Appointment" %>
<%@ page import="wam.server.serialize.AppointmentDao" %>
<%@ page import="wam.server.serialize.User" %>
<%@ page import="wam.server.serialize.UserDao" %>

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
table {
  border-collapse: collapse;
  width: 50%;
}

th, td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

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
<h2>Alle abgelehnten Termine <img src="roter_punkt.png"  width="15" height="15"> :</h2>  <br>
 
<table>
 <tr>
    <td>Datum:</td>
    <td>Uhrzeit:</td>
    <td>Kunde:</td>
 </tr>
  <tr> 
  <% ArrayList<Appointment> appointments = AppointmentDao.instance.abgelehnte_Allappointments(); %>
  <% for(Appointment a : appointments) { %>
    <td> <%=a.datum%> </td>
 	<td> <%=a.uhrzeit.substring(0,5)%> </td>
 	 <form action="User" method="post">
      <% ArrayList<User> user = UserDao.instance.User_name(a.idBenutzer); %>
	  <% for(User u : user) { %>
	  <td><%=u.vorname %> <%=u.nachname %> <% } %> </td> 
	</form>
  </tr>
  <% } %>
</table>
<br>

<br>
<a href="terminuebersicht_friseur.jsp" class="button">zurück</a><br>
</body>
</html>