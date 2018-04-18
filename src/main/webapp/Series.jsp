<%-- 
    Document   : Series
    Created on : 10/04/2018, 23:56:42
    Author     : franklin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.imd.telemaco.data.SerieDAO" %>
<%@ page import="com.imd.telemaco.entity.Serie" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
        ArrayList<Serie> series = (ArrayList<Serie>) session.getAttribute("series");
        
       	for (Serie s : series) {
       		%> <p> Nome: <%=s.getName() %> </p> <%
       	    %> <p> Ano: <%=s.getYear() %> </p> <%
       	    %> <p> ------------------------------------ </p> <%
       	}
        %>
    </body>
</html>
