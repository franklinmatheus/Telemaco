<%-- 
    Document   : CadastrarSerie
    Created on : Mar 28, 2018, 9:29:30 AM
    Author     : valmir
--%>

<%@page import="com.imd.telemaco.model.Usuario"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="serie" scope="session" class="com.imd.telemaco.model.Usuario" />
<jsp:setProperty name="serie" property="nome" />
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
        <title>Cadastrar Serie</title>
    </head>
    <body>
       <p><a href="Logado.jsp">Home </a>></p>
       <h1>Cadastro de Serie</h1>
        <p>
        <form name="CadastrarSerie" action="CadastrarSerie" method="POST">
            <p><span>Nome</span><input type="text" name="nome" required /></p>
            <p><input type="submit" name="CadastrarSerie" /></p>
        </form>
        </p>
    </body>
</html>
