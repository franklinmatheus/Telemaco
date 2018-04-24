<%-- 
    Document   : SearchResult
    Created on : 23/04/2018, 20:22:14
    Author     : franklin
--%>

<%@page import="com.imd.telemaco.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.imd.telemaco.entity.Serie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Result</title>
    </head>
    <body>
        <%
            ArrayList<Serie> results = (ArrayList<Serie>) session.getAttribute("results");
            
            if(results == null) {
                %>
                    <p>No results, <a href="Logged.jsp"> return no home page </a></p>
                <%
            } else {
                for(Serie result : results) {
                %>
                    <p><a href="SelectSerie?id=<%=result.getId()%>"> <%=result.getName() %> </a></p>
                <%
                }    
            }
        %>
    </body>
</html>
