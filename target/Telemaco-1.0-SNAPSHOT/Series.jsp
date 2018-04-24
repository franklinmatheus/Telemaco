<%-- 
    Document   : Series
    Created on : 10/04/2018, 23:56:42
    Author     : franklin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.imd.telemaco.entity.Serie" %>
<%@ page import="com.imd.telemaco.entity.Season" %>
<%@ page import="com.imd.telemaco.entity.Episode" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
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
        <p> Ano: <%=s.getYear()%> </p>
        <p> Temporadas: <%=s.getSeasons().size()%> </p> 
        <%
            ArrayList<Season> seasons = s.getSeasons();
            for (Season tem : seasons) {
        %> 
        <span> Temporada <%=tem.getNumber()%> </span>
        <p>  ========================= </p>
        <p> Episodes (<%=tem.getEpAmount()%>) </p> 
        <%
            for (Episode ep : tem.getEpisodes()) {
        %>
        <form name="watchEpisodes" action="WatchEpisodes" method="post">
            <p> Episódio <%=ep.getNumber()%>: <br> 
                <input name="<%=ep.getName()%>" type="checkbox"> <%=ep.getName()%>
                <button type="submit"> Confirmar episódios assistidos </button>
            </p>
        </form>
        <%
            }
        %>
        <p>  ========================= </p> 
        <%
            }
        %>
        <p> Sinopse: <%=s.getSynopsis()%> </p>
        <p> ------------------------------------ </p> 
        <%
                }
            }
        %>
    </body>
</html>
