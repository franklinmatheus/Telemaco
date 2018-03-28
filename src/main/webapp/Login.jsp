<%-- 
    Document   : Login
    Created on : 24/03/2018, 15:03:02
    Author     : franklin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <p><a href="Index.jsp">Home </a>></p>
        <h1>Login</h1>
        <p>
        <form name="cadastro" action="LoginUsuario" method="POST">
            <p><span>Email</span><input type="email" name="email" required /></p>
            <p><span>Senha</span><input type="password" name="senha" required /></p>
            <p><input type="submit" value="Login" /></p>
        </form>
        </p>
    </body>
</html>
