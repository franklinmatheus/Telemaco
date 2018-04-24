<%@page import="com.imd.telemaco.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.imd.telemaco.entity.Rating"%>
<%@ page import="com.imd.telemaco.entity.Serie" %>
<%@ page import="com.imd.telemaco.entity.Season" %>
<%@ page import="com.imd.telemaco.entity.Episode" %>
<%@ page import="java.util.ArrayList" %>
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
    ArrayList<Season> seasons = serie.getSeasons();
%>
<html>
    <head>
        <meta charset="utf-8">
        <title>Cadastrar Episódio</title>
        <link rel="stylesheet" type="text/css" href="./resources/css/style.css">
        <link rel="stylesheet" type="text/css" href="./resources/css/styleRegisterEpisode.css">
        <link rel="icon" type="png" href="./resources/media/images/icon.png">
        <script type="text/javascript" src="./resources/js/inputsOperations.js"></script>
    </head>
    <body>
        <header>
            <a href="./Index.jsp">
                <img src="./resources/media/images/icon.png" height="80%"> 
                Telemaco
            </a>
        </header>

        <div id="middle">
            <label id="title">Cadastrar Episódio</label>

            <form name="registerEpisode" action="RegisterEpisode" method="post">
                <label>Série Pertencente</label>
                <input type="text" disabled="true" value="<%=serie.getName()%>" />

                <label>Temporada Pertencente</label>

                <select id="seasonNumber" name="seasonNumber" onchange="enableAllInputs()" required>
                    <option value="" selected disabled> -- </option>
                    <%
                        for (Season s : seasons) {
                        %>
                        <option value="<%=s.getNumber()%>"><%=s.getNumber()%> </option>
                        <%
                        }
                    %>
                </select>

                <label> Nome </label>
                <input id="epName" type="input" name="epName" placeholder="ex.: Pilot" required>

                <label>Número</label>
                <input id="epNumber" type="number" min="1" name="epNumber" required>

                <label>Sinopse</label>
                <textarea id="epSynopsis" name="epSynopsis" required></textarea>

                <label>Tempo (minutos)</label>
                <input id="epTime" type="number" min="1" name="epTime" required>

                <div id="buttons">
                    <button class="button" id="btnCancel" ><a href="./Index.jsp">Cancelar</a></button>
                    <button class="button" id="btnConfirm">Confirmar</button>
                </div>
            </form>

            <footer></footer>
        </div>
    </body>
</html>