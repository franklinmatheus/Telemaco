<%-- 
    Document   : Series
    Created on : 10/04/2018, 23:56:42
    Author     : franklin
--%>

<%@page import="com.imd.telemaco.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.imd.telemaco.entity.Serie" %>
<%@ page import="com.imd.telemaco.entity.Season" %>
<%@ page import="com.imd.telemaco.entity.Episode" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    User logged = new User();
    if(session.getAttribute("logged") == null)
        response.sendRedirect("Login.jsp");
    else
        logged = (User) (session.getAttribute("logged"));
%>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Series</title>
    </head>
    <body>
        <h1>Séries</h1>
        <%
            ArrayList<Serie> series;
            if (session.getAttribute("series") == null)
                response.sendRedirect("SelectAllSeries");
            else {
                series = (ArrayList<Serie>) session.getAttribute("series");

                for (Serie s : series) {
        %> 
        <p> <a href="SelectSerie?id=<%=s.getId()%>"> <%=s.getName()%> </a> </p>
        <%
                }
            }
        %>
    </body>
</html>
