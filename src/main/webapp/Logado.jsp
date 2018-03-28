<%-- 
    Document   : Logado
    Created on : 28/03/2018, 09:54:43
    Author     : franklin
--%>

<%@page import="com.imd.telemaco.model.Usuario"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    Usuario logado = new Usuario();
    if(session.getAttribute("logado") == null)
        response.sendRedirect("Login.jsp");
    else
        logado = (Usuario) (session.getAttribute("logado"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bem vindo!</title>
    </head>
    <body>
        Esta é sua página de logado! Seja bem vindo, <%=logado.getNome()%>
        <form name="logout" action="LogoutUsuario" method="POST">
            <input type="submit" value="Sair" />
        </form>
    </body>
</html>
