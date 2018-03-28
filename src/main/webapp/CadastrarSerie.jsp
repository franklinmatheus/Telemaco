<%-- 
    Document   : CadastrarSerie
    Created on : Mar 28, 2018, 9:29:30 AM
    Author     : valmir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="serie" scope="session" class="com.imd.telemaco.model.Usuario" />
<jsp:setProperty name="serie" property="nome" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Serie</title>
    </head>
    <body>
       <p><a href="Index.jsp">Home </a>></p>
       <h1>Cadastro de Serie</h1>
        <p>
        <form name="CadastrarSerie" action="CadastrarSerie" method="POST">
            <p><span>Nome</span><input type="text" name="nome" required /></p>
            <p><input type="submit" name="CadastrarSerie" /></p>
        </form>
        </p>
    </body>
</html>
