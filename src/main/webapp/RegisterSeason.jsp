<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.imd.telemaco.entity.Serie" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
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

		<form name="registerSeason" action="ResgisterSeason" method="post">
			<label>Série Pertencente</label>
			<select name="serieName">
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

			<label>Number</label>
			<input type="number" min="1" name="number">

			<div id="buttons">
				<button class="button" id="btnCancel"><a href="./Index.jsp">Cancelar</a></button>
				<button class="button" id="btnConfirm">Confirmar</button>
			</div>
		</form>

		<footer></footer>
	</div>
</body>
</html>