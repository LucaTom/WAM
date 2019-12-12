<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="wam.server.serialize.Appointment" %>
<%@ page import="wam.server.serialize.AppointmentDao" %>
<%@ page import="wam.server.serialize.User" %>
<%@ page import="wam.server.serialize.UserDao" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="navbar.jsp"/>

<style>
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
<br>
<br>
<br>
<%int i = (Integer) session.getAttribute("idBenutzer");%>
<% User u = UserDao.instance.User_name(i); %>
<h1>Hallo <%=u.vorname %> <%=u.nachname %>,</h1>
<h2>deine Termine sind:</h2> <br>
 
<table>
  <tr>
    <th>Datum:</th>
    <th>Uhrzeit:</th>
    <th>Was:</th>
  	<th>Status:</th>
 	<th> </th>
  </tr>

  <% ArrayList<Appointment> appointments = AppointmentDao.instance.getAppointments(i); %>
  <% for(Appointment a : appointments) { %>

  <tr>
    <td> <%=a.datum%> </td>
    <td> <%=a.uhrzeit.substring(0,5)%> </td>
    <td> <%=a.frisur%> </td>
    <td> 
    <%	if (a.status.equals("angenommen")) {%> 
		<input name=angenommen type=image value="angenommen"  src="gruener_punkt.png" width="15" height="15"> 
	<%}if (a.status.equals("abgelehnt"))  { %>
		<input name=abgelehnt type=image value="abgelehnt"  src="roter_punkt.png" width="15" height="15"> 
  	<%}if (a.status.equals("offen"))  { %>
		<input name=offen type=image value="offen"  src="grauer_punkt.png" width="15" height="15"> 
 <% } %> </td>
 <td>
 <form method="post" action="Stornieren"> 
 <%if (a.status.equals("angenommen")){%>
  	 	<input type=hidden name="idTermine" value="<%= a.idTermine %>"><button type="submit" name="stornieren" value="<%= a.idTermine %>">stornieren</button><%} %>  
  	 	</form>
 </td>
  </tr>
  <%} %>
</table>
<br>
<a href="neuerTermin_kunde.jsp" class="button">Neuer Termin</a>


<br>
<br>
<img src="grauer_punkt.png" width="15" height="15"> : Status OFFEN (der Termin muss noch bearbeitet werden) <br>
<img src="gruener_punkt.png"  width="15" height="15"> : Status ANGENOMMEN (der Termin wurde bearbeitet) <br>
<img src="roter_punkt.png"  width="15" height="15"> : <a href="abgelehnteTermine_kunde.jsp" style="text-decoration: none; color:black " class="button">ABGELEHNTE Termine </a><br>  	
 

</body>
</html>