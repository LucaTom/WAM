<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
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
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</br>
</br>
</br>

<h2>Kundenverzeichnis</h2> <br>
<form method="post" action="User"> 
<table>
  <% ArrayList<User> user = UserDao.instance.getAll_Users(); %>
  <% for(User u : user) { %>
  <tr>
    <td><a href="einzelkundenuebersicht_friseur.jsp?id=<%= u.id %>" style="text-decoration: none; color:black "><%= u.vorname %> <%= u.nachname%></a></td>
  </tr>
  <% } %>
</table>
<br>
</form>
</body>
</html>