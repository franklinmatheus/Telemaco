<%-- 
    Document   : Logado
    Created on : 28/03/2018, 09:54:43
    Author     : franklin
--%>


<%@page import="com.imd.telemaco.entity.Serie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.imd.telemaco.entity.User"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    User logged = new User();
    if (session.getAttribute("logged") == null) {
        response.sendRedirect("Login.jsp");
    } else {
        logged = (User) (session.getAttribute("logged"));
    }

    ArrayList<Serie> list = (ArrayList<Serie>) session.getAttribute("seriesList");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./resources/css/style.css">
        <link rel="stylesheet" type="text/css" href="./resources/css/styleLogged.css">
        <link rel="icon" type="png" href="./resources/media/images/icon.png">
        <title>Telemaco</title>
    </head>
    <body>
		<header>
	        <button id="btn-menu">
	            <img src="./resources/media/images/menu.png">
	        </button>
	        <a id="icon" href="./Index.html"><img src="./resources/media/images/icon.png" height="60%"></a>
	        <ul>
	            <li><a href="">CATEGORIAS</a>
	                <ul>
	                    <li><a href="">Ação</a></li>
	                    <li><a href="">Animação</a></li>
	                    <li><a href="">Aventura</a></li>
	                    <li><a href="">Biografia</a></li>
	                    <li><a href="">Comédia</a></li>
	                    <li><a href="">Comédia Romântica</a></li>
	                    <li><a href="">Documentário</a></li>
	                    <li><a href="">Drama</a></li>
	                    <li><a href="">Esportes</a></li>
	                    <li><a href="">Ficção Científica</a></li>
	                    <li><a href="">Infantil</a></li>
	                    <li><a href="">Musical</a></li>
	                    <li><a href="">Romance</a></li>
	                    <li><a href="">Suspense</a></li>
	                    <li><a href="">Terror</a></li>
	                </ul>
	            </li>
	            <li><a href="">MINHA LISTA</a></li>
	            <li><a href="">MAIS VISTOS</a></li>
	            <li><a href="">LANÇAMENTOS</a></li>
	            <li><a href="">RECOMENDADOS</a></li>
	            <li id="li-search">
	                <input type="text" name="search" placeholder="Procurar...">
	                <button>    
	                    <img src="./resources/media/images/search.png" id="searchImg">
	                </button>
	            </li>
	        </ul>
	
	        <div id="profile"> 
	            <img src="./resources/media/images/profile.png"> 
	
	            <ul id="profile-ul">
	                <li><a href="">Perfil</a></li>
	                <li><a href="">Configurações</a></li>
	                <li><a href="">Sair</a></li>
	            </ul>
	
	        </div>
	    </header>        

        <a href="RegisterSerie.jsp">Register serie</a><br/>
        <a href="UpdatePassword.jsp">Update password</a><br/>

        <form action="SelectAllSeries" method="post">
            <button type="submit"> ver series </button>
        </form>
        <br/>
		<%--<h2>Your informations:</h2>
        <b>Birth: </b><%=logged.getBirth()%><br/>
        <b>Id: </b><%=logged.getId()%><br/>
        <b>Last name: </b><%=logged.getLastName()%><br/>
        <b>Email: </b><%=logged.getEmail()%><br/>
        <b>Password: </b><%=logged.getPassword()%><br/>
        <b>Gender: </b><%=logged.getGender()%><br/> --%>

        <form name="search" action="SearchSerie">
            <input type="search" required name="input" />
            <input type="submit" value="Search" />
        </form>

        <div>
            <h2>Your series</h2>
            <%
            if (list.isEmpty()) {
            %>
            <p>You have no series in your list!</p>
            <%
            } else {
                for (Serie serie : list) {
            %>
            <p> 
            <form action="RemoveSerieFromList" method="GET">
                <a href="SelectSerie?id=<%=serie.getId()%>"> <%=serie.getName()%> </a> 
                <input type="hidden" name="idSerie" value="<%=serie.getId()%>" />
                <input type="hidden" name="idUser" value="<%=logged.getId()%>" />
                <input type="submit" value="Remove from list" />
            </form>
            </p>
            <%
                }
            }
            %>
        </div>

        <form name="logout" action="LogoutUser" method="POST">
            <input type="submit" value="Logout" />
        </form>
    </body>
</html>