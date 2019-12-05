<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="navbar.jsp"/>

<style>
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
p {
	color: red;
	font-size: 11px;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</br>
</br>
</br>

<h2>neuer Termin:</h2> <br>
Öffnungszeiten: <br> <br>
Mo-Fr: 	08:00 - 19:00 <br>
Sa-So: 	geschlossen <br> <br> <br>
Wann? <br><br>
<form action="Appointment" method="post">
	Datum:  <input name="datum" type=date min="2017-01-01" max="2022-12-31" required> 
	Uhrzeit:  <input name="uhrzeit" type=time value="00:00" min="08:00" max="19:00" required> <br> 
	
<br><br>

Was?<br><br>
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
<p><%=request.getAttribute("error_msg")==null?"":request.getAttribute("error_msg") %></p>	
<button name="anfrageAbschicken" value="anfrageAbschicken">Anfrage abschicken</button> <br>
</form>
</body>
</html>