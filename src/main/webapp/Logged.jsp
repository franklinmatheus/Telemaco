<%-- 
    Document   : Logado
    Created on : 28/03/2018, 09:54:43
    Author     : franklin
--%>


<%@page import="com.imd.telemaco.entity.User"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    User logged = new User();
    if(session.getAttribute("logged") == null)
        response.sendRedirect("Login.jsp");
    else
        logged = (User) (session.getAttribute("logged"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome!</title>
    </head>
    <body>
        <h1>This is your logged page! </h1><br/>
        Welcome, <%=logged.getName()%><br/>
        <a href="RegisterSerie.jsp">Register serie</a><br/>
        <a href="RegisterSeason.jsp">Register season</a><br>
        <a href="RegisterEpisode.jsp">Register episode</a><br>
        <a href="UpdatePassword.jsp">Update password</a><br/>
        
        
        <form action="SelectAllSeries" method="post">
        	<button type="submit"> ver series </button>
        </form>
        <br/>
        <h2>Your informations:</h2>
        <b>Birth: </b><%=logged.getBirth()%><br/>
        <b>Id: </b><%=logged.getId()%><br/>
        <b>Last name: </b><%=logged.getLastName()%><br/>
        <b>Email: </b><%=logged.getEmail()%><br/>
        <b>Password: </b><%=logged.getPassword()%><br/>
        <b>Gender: </b><%=logged.getGender()%><br/>
        
        <form name="logout" action="LogoutUser" method="POST">
            <input type="submit" value="Logout" />
        </form>
    </body>
</html>