<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
von <input name="startzeit" type=time value="--:--" min="08:00" max="19:00" required> bis  <input name="endzeit" type=time value="--:--" min="08:00" max="19:00" required>

 <br>
 <p><%=request.getAttribute("error_msg")==null?"":request.getAttribute("error_msg") %></p>	
	<button type="submit" name="blockieren" value="blockieren">Blocker setzen</button>
</form>
<br> <br> <br> <br>





</body>
</html>