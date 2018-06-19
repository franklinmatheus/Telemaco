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
        <link rel="stylesheet" type="text/css" href="./resources/css/style.css">
		<link rel="stylesheet" type="text/css" href="./resources/css/styleSearchResult.css">
        <link rel="icon" type="png" href="./resources/media/images/icon.png">
        <title>Resultado</title>
    </head>
    <body>
	    <header>
			<a href="./Logged.jsp">
				<img src="./resources/media/images/icon.png" height="80%"> 
				Telemaco
			</a>
		</header>
		<div id="middle"> 
	        <%
	            ArrayList<Serie> results = (ArrayList<Serie>) session.getAttribute("results"); 
	            String search = (String) session.getAttribute("input"); %>
	        	<h1>Resultados para "<%=search%>"</h1>    <%
	        
	            if(results == null) { %>
	                    <p>Sem resultados, <a href="Logged.jsp"> returnar para a página inicial</a></p>  <%
	            } else {
	                for(Serie result : results) { %>
	                    <p><a href="SelectSerie?id=<%=result.getId()%>"> <%=result.getName() %> </a></p> <%
	                }    
	            }
	        %>
	   </div>
    </body>
</html>
