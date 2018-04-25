<%-- 
    Document   : Series
    Created on : 10/04/2018, 23:56:42
    Author     : franklin
--%>

<%@page import="com.imd.telemaco.data.UserEpisodeDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.imd.telemaco.entity.User" %>
<%@ page import="com.imd.telemaco.entity.Serie" %>
<%@ page import="com.imd.telemaco.entity.Season" %>
<%@ page import="com.imd.telemaco.entity.Episode" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<% 
    User logged = new User();
    if(session.getAttribute("logged") == null)
        response.sendRedirect("Login.jsp");
    else {    	
        logged = (User) (session.getAttribute("logged"));
    	ArrayList<Serie>   series       = (ArrayList<Serie>) session.getAttribute("series");
    	ArrayList<Episode> episodesSeen = (ArrayList<Episode>) session.getAttribute("episodesSeen");
    	
%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Series</title>
    </head>
    <body>
    	<h1>Séries</h1> <%
            for (Serie s : series) {
                %> <p> Nome: <%=s.getName() %> </p>
                <p> Ano: <%=s.getYear() %> </p>
                <p> Temporadas: <%=s.getSeasons().size() %> </p> <%
                ArrayList<Season> seasons = s.getSeasons();
                for (Season tem : seasons) {
                	%> <span> Temporada <%=tem.getNumber() %> </span>
                	<p>  ========================= </p>
                	<p> Episodes (<%=tem.getEpAmount()%>) </p> 
                	<form name="watchEpisodes" action="WatchEpisodes" method="post">
                	<% for (Episode ep : tem.getEpisodes()) {
                			String isChecked = "";
                			for (Episode epSeen : episodesSeen) { // FIXME ajeitar essa complexidade
                				if (epSeen.getId() == ep.getId()) {
                					isChecked = "checked=\"checked\"";
                					break;
                				} 
                			}
                			%>
	                		<p> Episódio <%=ep.getNumber()%>: <br> 
	                			<input name="<%=ep.getName()%>" type="checkbox" <%=isChecked%>><%=ep.getName()%></br>
	                		</p>
                		
               		<% } %>
        		    	<button type="submit"> Cadastrar episódios assistidos </button>
        		    </form>
                	<p>  ========================= </p> <%
                }%>
				<p> Sinopse: <%=s.getSynopsis() %> </p>
                <p> ------------------------------------ </p> <%
            }
        }
        %>
    </body>
</html>
