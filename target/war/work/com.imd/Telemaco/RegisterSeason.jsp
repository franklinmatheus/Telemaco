<%@page import="com.imd.telemaco.entity.Season"%>
<%@page import="com.imd.telemaco.entity.User"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.imd.telemaco.entity.Serie" %>
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
    int nextSeason = serie.getSeasons().size() + 1;
%>
<html>
    <head>
        <meta charset="utf-8">
        <title>Cadastrar Temporada</title>
        <link rel="stylesheet" type="text/css" href="./resources/css/style.css">
        <link rel="stylesheet" type="text/css" href="./resources/css/styleRegisterSeason.css">
        <link rel="icon" type="png" href="./resources/media/images/icon.png">
    </head>
    <body>
        <header>
            <a href="./Index.jsp">
                <img src="./resources/media/images/icon.png" height="80%"> 
                Telemaco
            </a>
        </header>

        <div id="middle">
            <label id="title">Cadastrar Temporada</label>

            <form name="registerSeason" action="RegisterSeason" method="post">
                <label>Série Pertencente</label>
                
                <input type="text" disabled="true" value="<%=serie.getName()%>" />

                <label>Number</label>
                <input type="number" name="number" value="<%=nextSeason%>" disabled="true">

                <div id="buttons">
                    <button class="button" id="btnCancel"><a href="./Index.jsp">Cancelar</a></button>
                    <button class="button" id="btnConfirm">Confirmar</button>
                </div>
            </form>

            <footer></footer>
        </div>
    </body>
</html>