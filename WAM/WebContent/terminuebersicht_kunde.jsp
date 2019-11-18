<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="wam.server.serialize.Appointment" %>
<%@ page import="wam.server.serialize.AppointmentDao" %>
<!DOCTYPE html>
<html>
<head>

<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
  position: fixed;
  top: 0;
  width: 100%;
}

li {
  float: left;
  border-right:1px solid #bbb;
}

li:last-child {
  border-right: none;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #111;
}

.active {
  background-color: #666;
}

table {
  border-collapse: collapse;
  width: 100%;
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

<ul>
	<li><a class="active" href="terminuebersicht_kunde.jsp">Terminübersicht</a></li>
	<li><a href="neuerTermin_kunde.jsp">Neuer Termin</a></li>
	<li style="float:right"><a href="index.jsp">Ausloggen</a></li>
</ul>
</br>
</br>
</br>

<h2>deine Termine:</h2> <br>
<form method="post" action="AppointmentServlet.java"> 
<table>
  <tr>
    <th>Wann:</th>
    <th>Was:</th>
  <th>Status:</th>
  </tr>
  <%int i = (Integer) session.getAttribute("idBenutzer");%>
  <% ArrayList<Appointment> appointments = AppointmentDao.instance.getAppointments(i); %>
  <% for(int a=0;a<appointments.size();a++) { %>
  <tr>
    <td> <%=appointments.get(a).datum%> </td>
    <td> <%=appointments.get(a).frisur%> </td>
    <td> <%=appointments.get(a).status%> </td>
  </tr>
  <% } %>
</table>
<br>
<a href="neuerTermin_kunde.jsp" class="button">Neuer Termin</a>
<button type="submit" name="terminStornieren" value="terminStornieren">Termin stornieren</button>
</form>
</body>
</html>