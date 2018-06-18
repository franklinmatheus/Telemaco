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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="./resources/css/style.css?version=13">
        <link rel="stylesheet" type="text/css" href="./resources/css/styleLogged.css?version=13">
        <link rel="icon" type="png" href="./resources/media/images/icon.png">
        <title>Telemaco</title>
    </head>
    <body>
		<header>
	        <button id="btn-menu">
	            <img src="./resources/media/images/menu.png" height="70%">
	        </button>
	        <a id="icon" href="./Logged.jsp"><img src="./resources/media/images/icon.png" height="80%"></a>
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
	                <form name="search" action="SearchSerie">
			            <input type="search" required name="input" />
			            <button type="submit" value="Search">
	                    	<img src="./resources/media/images/search.png" id="searchImg">
	                	</button>
			        </form>
	            </li>
	        </ul>
	
	        <div id="profile"> 
	            <img src="./resources/media/images/profile.png" height="50%"> 
	
	            <ul id="profile-ul">
	                <li><a href="./UpdatePassword.jsp">Atualizar Senha</a></li>
	                <!-- <li><a href="">Configurações</a></li> -->
	                <li>
	                	<form name="logout" action="LogoutUser" method="POST">
			                <input type="submit" value="Sair" />
			            </form>
	                </li>
	                <!-- <form name="logout" action="LogoutUser" method="POST">
		                <button type="submit" class=".button"> Sair </button> 
		            </form> -->
	            </ul>
	
	        </div>
	    </header>        

        <div id="middle">  
            <%-- <div>
                <%
                if (!list.isEmpty()) { %> 
                	<h2>Your series</h2> <%
                    for (Serie serie : list) { %>
	                <p> 
	                <form action="RemoveSerieFromList" method="GET">
	                    <a href="SelectSerie?id=<%=serie.getId()%>"> <%=serie.getName()%> </a> 
	                    <input type="hidden" name="idSerie" value="<%=serie.getId()%>" />
	                    <input type="hidden" name="idUser" value="<%=logged.getId()%>" />
	                    <input type="submit" value="Remove from list" />
	                </form>
	                </p> <%
                    }
                }
                %>
            </div> --%>
            
           	<%
            ArrayList<Serie> series;
            if (session.getAttribute("series") == null)
                response.sendRedirect("Logged");
            else {
                series = (ArrayList<Serie>) session.getAttribute("series"); %>
                <div class="middle-div">
                	<div class="middle-div-title"> Testezin do Terror </div> 
                	<div class="middle-div-series">  <%
	                for (int i = 0; i < 7; i++) { %>
	        			<div class="middle-div-serie"> 
	        				<%-- <a href="SelectSerie?id=<%=s.getId()%>"> <%=s.getName()%> </a> --%> 
	        			</div> <%
	                } %>
			        </div>      
                </div> <%
            }
        	%>
        </div>
        
        <footer>
        	
        </footer>
    </body>
</html>