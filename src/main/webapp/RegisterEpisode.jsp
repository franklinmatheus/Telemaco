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

		<form name="registerEpisode" action="RegisterEpisode" method="post">
			<label>Série Pertencente</label>
			<select id="serieName" name="serieName">
				<option name="" value="" selected disabled> -- </option>
				<%
					ArrayList<Serie> series;
					if (session.getAttribute("series") == null)
						response.sendRedirect("SelectAllSeries");
					else {
						series = (ArrayList<Serie>) session.getAttribute("series");
						
						for (Serie s : series) {
							%> <option value="<%=s.getName()%>"><%=s.getName()%> </option> <%
						}
					}
				%>
			</select>

			<label>Temporada Pertencente</label>
			<input name="seasonNumber" type="number" min="1" value="1"  required>
			
			<%-- <select id="seasonNumber" name="seasonNumber" disabled onchange="enableAllInputs()" required>
				<option value="" selected disabled> -- </option>
			
					ArrayList<Season> seasons;
					if (session.getAttribute("seasons") == null)
						response.sendRedirect("RegisterEpisode");
					else {
						seasons = (ArrayList<Season>) session.getAttribute("seasons");
						
						for (Season s : seasons) {
							%><option value="<%=s.getNumber()%>"><%=s.getNumber()%> </option><%
						}
					}
			// FIXME 
			</select> --%>

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