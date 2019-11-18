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

/* The container */
.container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 15px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* Hide the browser's default radio button */
.container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
}

/* Create a custom radio button */
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 25px;
  width: 25px;
  background-color: #eee;
  border-radius: 50%;
}

/* On mouse-over, add a grey background color */
.container:hover input ~ .checkmark {
  background-color: #ccc;
}

/* When the radio button is checked, add a blue background */
.container input:checked ~ .checkmark {
  background-color: #2196F3;
}

/* Create the indicator (the dot/circle - hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/* Show the indicator (dot/circle) when checked */
.container input:checked ~ .checkmark:after {
  display: block;
}

/* Style the indicator (dot/circle) */
.container .checkmark:after {
 	top: 9px;
	left: 9px;
	width: 8px;
	height: 8px;
	border-radius: 50%;
	background: white;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul>
	<li><a href="terminuebersicht_kunde.jsp">Terminübersicht</a></li>
	<li><a class="active" href="neuerTermin_kunde.jsp">Neuer Termin</a></li>
	<li style="float:right"><a href="index.jsp">Ausloggen</a></li>
</ul>
</br>
</br>
</br>

<h2>neuer Termin:</h2> <br>
wann? <br>
<form action="AppointmentServlet" method="post">
	<input name="datum" type=text placeholder="dd/mm/yyyy"> <br> 

was?<br>
<label class="container">Spitzen schneiden
  <input type="radio" checked="checked" name="frisur" value="Spitzen schneiden">
  <span class="checkmark"></span>
</label>
<label class="container">Haare färben
  <input type="radio" name="frisur" value="Haare f&aumlrben">
  <span class="checkmark"></span>
</label>
<label class="container">Waschen & Schneiden
  <input type="radio" name="frisur" value="Waschen & Schneiden">
  <span class="checkmark"></span>
</label>
<label class="container"> All-In Paket
  <input type="radio" name="frisur" value="All-In Paket">
  <span class="checkmark"></span>
</label>

<button name="anfrageAbschicken" value="anfrageAbschicken">Anfrage abschicken</button> <br>
</form>
</body>
</html>