<%-- 
    Document   : UpdatePassword
    Created on : 05/04/2018, 17:19:39
    Author     : franklin
--%>

<%@page import="com.imd.telemaco.entity.User"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    User logado = new User();
    if(session.getAttribute("logged") == null)
        response.sendRedirect("Login.jsp");
    else
        logado = (User) (session.getAttribute("logged"));
%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <style type="text/css"></style>
    <link rel="stylesheet" type="text/css" href="./resources/css/styleUpdatePsw.css">
    <link rel="stylesheet" type="text/css" href="./resources/css/style.css">
    <link rel="icon" type="png" href="./resources/media/images/icon.png">
</head>
<body>
    <header>
        <a href="./Logged.jsp">
            <img src="./resources/media/images/icon.png" height="80%"> 
            Telemaco
        </a>
    </header>

    <div id="middle">
        <form action="" method="post">
            <label id="title"><b>Atualizar senha</b></label>

            <div id="form">
                <label for="senha_atual">Senha atual</label>
                <input type="password" name="senha_atual" class="inputText" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;">

                <label for="senha">Nova senha</label>
                <input type="password" name="senha" class="inputText" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;">

                <label for="csenha">Confirme a nova senha</label>
                <input type="password" name="csenha" class="inputText" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;">
            </div>

            <div id="buttons">
                <button class="button" id="btnConfirm">Atualizar</button>
            </div>
        </form>
    </div>
</body>
</html>