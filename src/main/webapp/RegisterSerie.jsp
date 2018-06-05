<%-- 
    Document   : CadastrarSerie
    Created on : Mar 28, 2018, 9:29:30 AM
    Author     : valmir
--%>
<%@page import="com.imd.telemaco.entity.User"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    User logged = new User();
    if(session.getAttribute("logged") == null)
        response.sendRedirect("Login.jsp");
    else
        logged = (User) (session.getAttribute("logged"));
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Cadastrar Série</title>
	<link rel="stylesheet" type="text/css" href="./resources/css/style.css">
	<link rel="stylesheet" type="text/css" href="./resources/css/styleRegisterSerie.css">
	<link rel="icon" type="png" href="./resources/media/images/icon.png">
	<script type="text/javascript" src="./resources/js/inputOperations.js"></script>
	<script type="text/javascript">
		function changeImgNameFile () {
			var inputFile = document.getElementById("image");
			var outputFile = document.getElementById("txtImgFile");
			
			
			if (inputFile.value == "Nenhum arquivo selecionado" || inputFile.value == "")
				outputFile.innerHTML = "Nenhum arquivo selecionado";
			else 
				outputFile.innerHTML = inputFile.value;//.split('\\').pop();
		}
	</script>
	
</head>
<body>
	<header>
		<a href="./Index.jsp">
			<img src="./resources/media/images/icon.png" height="80%"> 
			Telemaco
		</a>
	</header>

	<div id="middle">
		<label id="title">Cadastrar Série</label>

		<form name="RegisterSerie" action="RegisterSerie" method="post">
			<label>Name</label>
			<input type="input" name="name" required>

			<label>Ano</label>
			<input type="number" name="year" max="2018" min="1970" required>
			<!-- Ajeitar para pegar o ano atual -->

			<label>Status</label>
			<input type="input" name="status" placeholder="ex.: Em adamento..." required>

			<label>Criador</label>
			<input type="input" name="creator" required>

			<label>Classificacão de Idade</label>
			<select name="classification" id="classification" required>
				<option value="L" icon="./resources/media/images/classification/L.png"> Livre </option>
				<option value="10" icon="./resources/media/images/classification/10.png"> 10 </option>
				<option value="12" icon="./resources/media/images/classification/12.png"> 12 </option>
				<option value="14" icon="./resources/media/images/classification/14.png"> 14 </option>
				<option value="16" icon="./resources/media/images/classification/16.png"> 16 </option>
				<option value="18" icon="./resources/media/images/classification/18.png"> 18 </option>
			</select>

			<label>Categoria</label>
			<select name="genre" id="classification" required>
				<option value="action"> Ação </option>
				<option value="animation"> Animação </option>
				<option value="adventure"> Aventura </option>
				<option value="biograph"> Biografia </option>
				<option value="comedy"> Comédia </option>
				<option value="romanticComedy"> Comédia Romântica </option>
				<option value="documentary"> Documentário </option>
				<option value="drama"> Drama </option>
				<option value="sports"> Esportes </option>
				<option value="sci-fi"> Ficção Científica </option>
				<option value="childlike"> Infantil </option>
				<option value="musical"> Musical </option>
				<option value="romance"> Romance </option>
				<option value="thriller"> Suspense </option>
				<option value="horror"> Terror </option>
			
			</select>

			<label>Sinopse</label>
			<textarea name="synopsis"></textarea>

			<label>Imagem</label>
			<span class="inputfile">
				<span id="txtImgFile" name="txtImgFile">Nenhum arquivo inserido</span>
				<input id="image" size="50" type="file" name="image" accept="image/*" onchange="changeImgNameFile()">
				<img src="./resources/media/images/upload.png">
			</span>

			<div id="buttons">
				<button class="button" id="btnCancel"><a href="./Index.jsp">Cancelar</a></button>
				<button class="button" id="btnConfirm">Confirmar</button>
			</div>
		</form>

		<footer></footer>
	</div>
</body>
</html>