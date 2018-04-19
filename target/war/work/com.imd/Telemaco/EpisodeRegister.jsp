<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.imd.telemaco.entity.enums.Classification"%>
<%@page import="com.imd.telemaco.entity.Rating"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.imd.telemaco.data.SeasonDAO" %>
<%@ page import="com.imd.telemaco.data.SerieDAO" %>
<%@ page import="com.imd.telemaco.entity.Serie" %>
<%@ page import="com.imd.telemaco.entity.Season" %>
<%@ page import="com.imd.telemaco.entity.Episode" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>Cadastrar Epis�dio</title>
	<link rel="stylesheet" type="text/css" href="./resources/css/style.css">
	<link rel="stylesheet" type="text/css" href="./resources/css/styleEpisodeRegister.css">
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
		<label id="title">Cadastrar Epis�dio</label>

		<form name="register" action="ResgisterEpisode" method="post">
			<label>S�rie Pertencente</label>
			<select id="serieName" name="serieName" onchange="disableSeasons()">
				<%
				SerieDAO serieDAO = new SerieDAO();
				ArrayList <Serie> series = serieDAO.selectAllSeries();
				//TODO try catch
				
				// -------------------------------------------------------------------------------
				/* It's just a test */
				/* ArrayList <Serie> series = new ArrayList <Serie>();   
				Classification classification = Classification.P18;
				ArrayList <Season> seasons = new ArrayList<Season>();
				seasons.add(new Season(123, 1, new ArrayList<Episode>(), 1));
				series.add(new Serie(1, "Game of Thrones", "2011", seasons, new Rating(), 1, classification));
				seasons.clear();
				seasons.add(new Season(123, 1, new ArrayList<Episode>(), 2));
				series.add(new Serie(2, "Breaking Bad", "2011", seasons, new Rating(), 1, classification)); */
				//--------------------------------------------------------------------------------
				
				%>
				<option value="" selected disabled> -- </option> 
				<%
				for (Serie s: series) {
					%> <option value=<%=s.getId()%>> <%=s.getName()%> </option><%
				}
				%>
			</select>

			<label>Temporada Pertencente</label>
			<select id="seasonNumber" name="seasonNumber" disabled onchange="enableAllInputs()">
				<% //TODO escolher os epis�dios a partir da s�rie escolhida %>
				<option value="" selected disabled> -- </option>
				
				<!-- ------------ Just to example ------------ -->
				<option value="1"> 1 </option>
				<option value="2"> 2 </option>

				<!-- ------------ Just to example ------------ -->
				
				<!-- ------------ The real implementation ------------ -->
				<%
				String idSerieChosen = request.getParameter("serieName");
				%>
				<!-- ------------ The real implementation ------------ -->
			</select>

			<label> Nome </label>
			<input id="epName" type="input" name="epName" placeholder="ex.: Pilot" disabled>

			<label>N�mero</label>
			<input id="epNumber" type="number" min="1" name="epNumber" disabled>

			<label>Sinopse</label>
			<textarea id="epSynopsis" name="epSynopsis" disabled></textarea>

			<label>Temokpo (minutos)</label>
			<input id="epTime" type="number" min="1" name="epTime" disabled>

			<div id="buttons">
				<button class="button" id="btnCancel" ><a href="./Index.jsp">Cancelar</a></button>
				<button class="button" id="btnConfirm">Confirmar</button>
			</div>
		</form>

		<footer></footer>
	</div>
</body>
</html>