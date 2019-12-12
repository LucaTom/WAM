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

form {
	display: inline;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
</br>
</br>
</br>

<h2>Terminübersicht:</h2> 
Bitte bearbeite alle offenen Termine, indem du sie entweder "annimmst" oder "ablehnst"!
<br>
<br>
<table>
  <tr>
    <th>Datum:</th>
    <th>Uhrzeit:</th>
    <th>Wer:</th>
    <th>Was:</th>
    <th>Status:</th>
  </tr>
 
  <% ArrayList<Appointment> appointments = AppointmentDao.instance.getAll_Appointments(); %>
  <% for(Appointment a : appointments) { %>
  <tr>
    <td> <%=a.datum%> </td>
    <td> <%=a.uhrzeit.substring(0,5)%> </td>
     <form action="User" method="post">
      <% User u = UserDao.instance.User_name(a.idBenutzer); %>
	  <td><%=u.vorname %> <%=u.nachname %> </td> 
	</form>
    <td> <%=a.frisur%> </td>
    <td>
    <% if (a.status.equals("offen")){ %>
		<form method=post action="Status"><input type=hidden value="angenommen" name=action><input type=hidden name="idTermine" value="<%= a.idTermine %>"><input name=angenommen type=image value="angenommen"  src="gruener_punkt.png" width="15" height="15"></form> <form method=post action="Status"><input type=hidden value="abgelehnt" name=action><input type=hidden name="idTermine" value="<%= a.idTermine %>"><input name=abgelehnt type=image value="abgelehnt"  src="roter_punkt.png" width="15" height="15"></form> </td>    
	<%} %>
   	<% if (a.status.equals("angenommen")) {%> 
		<input name=angenommen type=image value="angenommen"  src="gruener_punkt.png" width="15" height="15"> 
	<%}if (a.status.equals("abgelehnt")) { %>
		<input name=abgelehnt type=image value="abgelehnt"  src="roter_punkt.png" width="15" height="15"> 
  	 <% } %>
  </tr>
  <% } %>

</table>
<br>
<br>
<br>
Alle Termine am: 
<form action="filter_friseur.jsp" method="post">
<input name="datum_filter" type=date min="2017-01-01" max="2022-12-31"><br>
<button type="submit" name="filter" value="filter">Suchen</button>

</form>

<br>
<br>
<br>
<img src="gruener_punkt.png" width="15" height="15"> & <img src="roter_punkt.png" width="15" height="15"> : Status OFFEN (der Termin muss noch bearbeitet werden) <br>
<img src="gruener_punkt.png"  width="15" height="15"> : Status ANGENOMMEN (der Termin wurde bearbeitet) <br>
<img src="roter_punkt.png"  width="15" height="15"> : <a href="abgelehnteTermine_friseur.jsp" style="text-decoration: none; color:black " class="button">ABGELEHNTE Termine </a><br>  	

</body>
</html>