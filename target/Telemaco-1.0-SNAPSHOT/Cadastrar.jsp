<%-- 
    Document   : Cadastrar
    Created on : 24/03/2018, 15:02:46
    Author     : franklin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
            <p><span>Nome</span><input type="text" name="name" required /></p>
            <p><span>Sobrenome</span><input type="text" name="lastname" required /></p>
            <p><span>Senha</span><input type="password" name="password" required /></p>
            <p><span>Confirmar senha</span><input type="password" name="cpassword" required /></p>
            <p><span>Email</span><input type="email" name="email" required /></p>
            <p><span>Confirmar email</span><input type="email" name="cemail" required /></p>
            <p>
                <span>Gênero</span>
                <input type="radio" name="gender" value="m" required />Masculino 
                <input type="radio" name="gender" value="f" required />Feminino
            </p>
            <p><span>Data de nascimento</span><input type="date" name="date" required /></p>
            <p><input type="submit" value="Cadastrar" /></p>
        </form>
        </p>
    </body>
</html>
