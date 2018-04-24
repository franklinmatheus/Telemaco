<%-- 
    Document   : Serie
    Created on : 10/04/2018, 23:56:54
    Author     : franklin
--%>

<%@page import="com.imd.telemaco.entity.User"%>
<%@page import="com.imd.telemaco.entity.Comment"%>
<%@page import="com.imd.telemaco.entity.Episode"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.imd.telemaco.entity.Season"%>
<%@page import="com.imd.telemaco.entity.Serie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    User logged = new User();
    if (session.getAttribute("logged") == null) {
        response.sendRedirect("Login.jsp");
    } else {
        logged = (User) (session.getAttribute("logged"));
    }

    Serie serie = (Serie) session.getAttribute("serie");
    
    if(serie == null)
        response.sendRedirect("Logged.jsp");
    
    ArrayList<Comment> comments = (ArrayList<Comment>) session.getAttribute("comments");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <p> <%=serie.getName()%> </p>
        <p> Ano: <%=serie.getYear()%> </p>
        <a href="RegisterSeason.jsp">Register season</a><br>
        <a href="RegisterEpisode.jsp">Register episode</a><br>
        <hr>
        <p> Temporadas: <%=serie.getSeasons().size()%> </p> 
        <%
            ArrayList<Season> seasons = serie.getSeasons();
            for (Season tem : seasons) {
        %> 
        <span> Temporada <%=tem.getNumber()%> </span>
        <p>  ========================= </p>
        <p> Episodes (<%=tem.getEpAmount()%>) </p> <%
            for (Episode ep : tem.getEpisodes()) {%>
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
        <p> Sinopse: <%=serie.getSynopsis()%> </p>
        <hr>
        <p>Comentários</p>
        <table>
            <%
                if (comments.isEmpty()) {
            %>
            <b>No comments yet!</b>
            <%
            } else {
                for (Comment comment : comments) {
            %>
            <tr>
                <td><%=comment.getUser().getName()%></td>
                <td><%=comment.getDate()%></td>
                <td><%=comment.getContent()%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <hr>

        <form action="AddComment" method="GET">
            <label>Add your comment here </label>
            <br>
            <textarea name="content" required maxlength="500" placeholder="..."></textarea>
            <br>
            <input type="submit" value="Add comment" />
            <input type="hidden" name="idSerie" value="${serie.getId()}"/>
            <input type="hidden" name="idUser" value="${logged.getId()}"/>
        </form>
        <hr>
    </table>
</body>
</html>
