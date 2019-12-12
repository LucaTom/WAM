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
 <% String d = (String) request.getParameter("datum_filter");%>
 <% ArrayList<Appointment> appointments = AppointmentDao.instance.getTerminByDate(d); %>
<h2>Alle Termine am: <%= d%> </h2>

<table>
 <tr>
    <td>Uhrzeit:</td>
    <td>Was:</td>
    <td>Kunde:</td>
    <td>Status:</td>
    
 </tr>
  <tr> 
  <% for(Appointment a : appointments) { %>
    <td> <%=a.uhrzeit.substring(0,5)%> </td>
    <td> <%=a.frisur%> </td>
    
  <form action="User" method="post">
 <% ArrayList<User> user = UserDao.instance.User_name(a.idBenutzer); %>
 <% for(User u : user) { %>
	<td><%=u.vorname %> <%=u.nachname %> <% } %> </td> 
	</form>
	<td> <%=a.status%> </td>
  </tr>
  <% } %>

</table>
<br>

<br>
<a href="terminuebersicht_friseur.jsp" class="button">zurück</a><br>
</body>
</html>