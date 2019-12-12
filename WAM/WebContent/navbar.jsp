<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="wam.server.*"
    import="java.sql.*"%>
<% Connection con = null; 
	try { 
		con = ContextListener.getDataSource().getConnection();} 
	catch (SQLException e) 
		{response.sendError(503);} 
finally { con.close();} %>
<% String[] url = request.getRequestURL().toString().split("/"); %>
<% String page_name = url[url.length - 1]; %>
<% if(session.getAttribute("idBenutzer") == null) { %>
	<jsp:forward page="login.jsp"/>
<% } %>

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
</style>


<ul>
	<li>
		<% if(session.getAttribute("idBenutzer") != null) if(session.getAttribute("usergroupid").toString().equals("2")){ %> 
			<a class="<%= page_name.equals("terminuebersicht_friseur.jsp")?"active":""%>" href="terminuebersicht_friseur.jsp">Terminübersicht</a>
		<% } else { %>
			<a class="<%= page_name.equals("terminuebersicht_kunde.jsp")?"active":""%>" href="terminuebersicht_kunde.jsp">Terminübersicht</a>
		<% } %>
	</li>
	<li>
		<% if(session.getAttribute("idBenutzer") != null) if(session.getAttribute("usergroupid").toString().equals("2")){ %> 
			<a class="<%= page_name.equals("kundenuebersicht_friseur.jsp")?"active":""%>" href="kundenuebersicht_friseur.jsp">Kundenübersicht</a>
		<% } else { %>
			<a class="<%= page_name.equals("neuerTermin_kunde.jsp")?"active":""%>" href="neuerTermin_kunde.jsp">Neuer Termin</a>
		<% } %>
	</li>
	<li>
		<% if(session.getAttribute("idBenutzer") != null) if(session.getAttribute("usergroupid").toString().equals("2")){ %> 
			<a class="<%= page_name.equals("datumBlocken_friseur.jsp")?"active":""%>" href="datumBlocken_friseur.jsp">Datum blocken</a>
		<% } %>
	</li>
	<li style="float:right"><a href="Login?logout=1">Ausloggen</a> <%request.getSession().getAttribute("idBenutzer"); %></li>
</ul>



