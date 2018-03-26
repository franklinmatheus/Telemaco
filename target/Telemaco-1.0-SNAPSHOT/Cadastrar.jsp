<%-- 
    Document   : Cadastrar
    Created on : 24/03/2018, 15:02:46
    Author     : franklin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario" scope="session" class="com.imd.telemaco.model.Usuario" />
<jsp:setProperty name="usuario" property="nome" />
<jsp:setProperty name="usuario" property="senha" />
<jsp:setProperty name="usuario" property="email" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar</title>
    </head>
    <body>
        <p><a href="Index.jsp">Home </a>></p>
        <h1>Cadastro</h1>
        <p>
        <form name="cadastro" action="CadastrarUsuario" method="POST">
            <p><span>Nome</span><input type="text" name="nome" required /></p>
            <p><span>Senha</span><input type="password" name="senha" required /></p>
            <p><span>Email</span><input type="email" name="email" required /></p>
            <p><input type="submit" name="Cadastrar" /></p>
        </form>
        </p>
    </body>
</html>
