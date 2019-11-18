<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul>
	<li><a class="active" href="terminuebersicht_friseur.jsp">Terminübersicht</a></li>
	<li><a href="kundenuebersicht_friseur.jsp">Kundenübersicht</a></li>
	<li><a href="datumBlocken_friseur.jsp">Datum blocken</a></li>
	<li style="float:right"><a href="index.jsp">Ausloggen</a></li>
</ul>
</br>
</br>
</br>

<h2>Terminübersicht (offene & bearbeitete):</h2> <br>

<table>
  <tr>
    <th>Wann:</th>
    <th>Wer:</th>
    <th>Was:</th>
    <th>Status:</th>
  </tr>
  <tr>
    <td> -- </td>
    <td> -- </td>
    <td> -- </td>
    <td> -- </td>
  </tr>
  <tr>
    <td> -- </td>
    <td> -- </td>
    <td> -- </td>
    <td> -- </td>
  </tr>
  <tr>
    <td> -- </td>
    <td> -- </td>
    <td> -- </td>
    <td> -- </td>
  </tr>
</table>
<br>

<button name="terminAnnehmen" value="terminAnnehmen">Termin annehmen</button>
<button name="terminAblehnen" value="terminAblehnen">Termin ablehnen</button>

</body>
</html>