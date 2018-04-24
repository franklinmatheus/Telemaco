<%-- 
    Document   : SearchResult
    Created on : 23/04/2018, 20:22:14
    Author     : franklin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.imd.telemaco.entity.Serie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
