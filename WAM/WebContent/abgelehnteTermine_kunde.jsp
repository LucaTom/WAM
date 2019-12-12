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
<h2>Alle abgelehnten Termine <img src="roter_punkt.png"  width="15" height="15"> :</h2>  <br>
 
 
  <%int i = (Integer) session.getAttribute("idBenutzer");%>
  <% ArrayList<Appointment> appointments = AppointmentDao.instance.abgelehnte_appointments(i); %>
  <% if (appointments.size() == 0){%>
 Du hast keine abgelehnten Termine!
	  <%  } else { %>
<table>
 <tr>
    <td>Datum:</td>
    <td>Uhrzeit:</td>
    <td>Was:</td>
 </tr>

 <% for(Appointment a : appointments) { %>
  <tr> 
    <td> <%=a.datum%> </td>
 	<td> <%=a.uhrzeit.substring(0,5)%> </td>
 	<td> <%=a.frisur%> </td>
  </tr>
 <% } %>
 </table>
 <br>
 direkt einen <a href="neuerTermin_kunde.jsp" class="button">neuen Termin</a> ausmachen!
 <% } %>
<br><br>
<a href="terminuebersicht_kunde.jsp" class="button">zurück</a><br>
</body>
</html>