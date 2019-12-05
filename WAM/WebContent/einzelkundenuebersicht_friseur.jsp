<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="wam.server.serialize.User" %>
<%@ page import="wam.server.serialize.UserDao" %>

<%@ page import="wam.server.serialize.Appointment" %>
<%@ page import="wam.server.serialize.AppointmentDao" %>

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
<h2>Kunde:</h2> <br>

 <% int i = Integer.parseInt((String) request.getParameter("id"));%>
  <% ArrayList<User> user = UserDao.instance.getUser(i); %>
  <% for(User u : user) { %>
Kundennummer: <%=u.id%> <br>
Name: <%=u.vorname%> <%=u.nachname%><br>
Adresse: <%=u.adresse %><br>
Email: <%=u.mailadresse %> <br>
Telefonnummer: <%=u.telefonnummer %><br> <br>

<h4>Termine des Kunden:</h4>   
<table>
 <tr>
    <td>Datum:</td>
    <td>Uhrzeit:</td>
    <td>Status:</td>
 </tr>
  <tr> 
  <% ArrayList<Appointment> appointments = AppointmentDao.instance.einzelkunde_appointments(i); %>
  <% for(Appointment a : appointments) { %>
    <td> <%=a.datum%> </td>
    <td> <%=a.uhrzeit.substring(0,5)%> </td>
    <td> <%=a.status%> </td>
  </tr>
  <% } %>
</table>
<% }%>
<br>

<br>
<a href="kundenuebersicht_friseur.jsp" class="button">zurück</a><br>
</body>
</html>