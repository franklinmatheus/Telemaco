<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.imd.telemaco.entity.Rating"%>
<%@ page import="com.imd.telemaco.entity.Serie" %>
<%@ page import="com.imd.telemaco.entity.Season" %>
<%@ page import="com.imd.telemaco.entity.Episode" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
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

		<form name="register" action="ResgisterEpisode" method="post">
			<label>Série Pertencente</label>
			<select id="serieName" name="serieName" onchange="disableSeasons()">
				<option value="" selected disabled> -- </option>
				<%
					ArrayList<Serie> series;
					if (session.getAttribute("series") == null)
						response.sendRedirect("SelectAllSeries");
					else {
						series = (ArrayList<Serie>) session.getAttribute("series");
						
						for (Serie s : series) {
							%><option value="<%=s.getName()%>"><%=s.getName()%> </option><%
						}
					}
				%>
			</select>

			<label>Temporada Pertencente</label>
			<select id="seasonNumber" name="seasonNumber" disabled onchange="enableAllInputs()" required>
				<option value="" selected disabled> -- </option>
				<%
					//FIXME
					ArrayList<Season> seasons;
					if (session.getAttribute("seasons") == null)
						response.sendRedirect("SelectSeasonsAtSerie");
					else {
						seasons = (ArrayList<Season>) session.getAttribute("seasons");
						
						for (Season s : seasons) {
							%><option value="<%=s.getNumber()%>"> Temporada <%=s.getNumber()%> </option><%
						}
					}
				%>
			</select>

			<label> Nome </label>
			<input id="epName" type="input" name="epName" placeholder="ex.: Pilot" disabled required>

			<label>Número</label>
			<input id="epNumber" type="number" min="1" name="epNumber" disabled required>

			<label>Sinopse</label>
			<textarea id="epSynopsis" name="epSynopsis" disabled required></textarea>

			<label>Tempo (minutos)</label>
			<input id="epTime" type="number" min="1" name="epTime" disabled required>

			<div id="buttons">
				<button class="button" id="btnCancel" ><a href="./Index.jsp">Cancelar</a></button>
				<button class="button" id="btnConfirm">Confirmar</button>
			</div>
		</form>

		<footer></footer>
	</div>
</body>
</html>