<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ page import="java.util.ArrayList" %>
<%@ page import="wam.server.serialize.Blocker" %>
<%@ page import="wam.server.serialize.BlockerDao" %>
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
p {
	color: red;
	font-size: 9.5px;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</br>
</br>
</br>

Regulär geblocked: <br> <br>
Mo-Fr: 	00:00 - 7:59 || 19:01 - 23:59 <br>
Sa-So: 	00:00 - 23:59 <br> <br> <br>

Irregulär geblocked: <br> <br>

<form method="post" action="Blocken"> 

Am: 
 <input name="datum" type=date value="2019-12-24" min="2017-01-01" max="2022-12-31"required> <br> 
von <input name="startzeit" type=time value="00:00" min="08:00" max="19:00" required> bis  <input name="endzeit" type=time value="00:01" min="08:00" max="19:00" required>

 <br>
 <p><%=request.getAttribute("error_msg")==null?"":request.getAttribute("error_msg") %></p>	
	<button type="submit" name="blockieren" value="blockieren">Blocker setzen</button>
</form>

 <%int i = (Integer) session.getAttribute("idBenutzer");%>
 <% ArrayList<Blocker> blocker = BlockerDao.instance.getBlocker(i); %>
 <% if (blocker.size() == 0){%>
 Du hast keine Blocker gesetzt!
	 
  <%  } else { %>
 <table>
 <tr>
    <td>Datum:</td>
    <td>Von:</td>
    <td>Bis:</td>
    <td> </td>
 </tr>
  <% for(Blocker b : blocker) { %>
   <form method="post" action="BlockerLoeschen">
  <tr> 
    <td> <%=b.datum%> </td>
 	<td> <%=b.startzeit.substring(0,5)%> </td>
 	<td> <%=b.endzeit.substring(0,5)%> </td>
	<td><button type="submit" name="loeschen" value="<%= b.idBlocker %>">löschen</button> </td>
  </tr>
  	</form>
     <%} %>
     
</table>


<% } %>

<br> <br> <br> <br>





</body>
</html>