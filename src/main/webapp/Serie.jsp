<%-- 
    Document   : Serie
    Created on : 10/04/2018, 23:56:54
    Author     : franklin
--%>

<%@page import="com.imd.telemaco.entity.Episode"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.imd.telemaco.entity.Season"%>
<%@page import="com.imd.telemaco.entity.Serie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Serie serie = (Serie) session.getAttribute("serie");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <p> <%=serie.getName()%> </p>
        <p> Ano: <%=serie.getYear()%> </p>
        <p> Temporadas: <%=serie.getSeasons().size()%> </p> <%
            ArrayList<Season> seasons = serie.getSeasons();
            for (Season tem : seasons) {
        %> <span> Temporada <%=tem.getNumber()%> </span>
        <p>  ========================= </p>
        <p> Episodes (<%=tem.getEpAmount()%>) </p> <%
                            for (Episode ep : tem.getEpisodes()) {%>
        <form name="watchEpisodes" action="WatchEpisodes" method="post">
            <p> Episódio <%=ep.getNumber()%>: <br> 
                <input name="<%=ep.getName()%>" type="checkbox"> <%=ep.getName()%>
                <button type="submit"> Confirmar episódios assistidos </button>
            </p>
        </form><%
                                    } %>
        <p>  ========================= </p> <%
                            }%>
        <p> Sinopse: <%=serie.getSynopsis()%> </p>
    </body>
</html>
