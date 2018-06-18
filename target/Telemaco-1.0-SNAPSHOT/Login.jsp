<%-- 
    Document   : Login
    Created on : 24/03/2018, 15:03:02
    Author     : franklin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <style type="text/css"></style>
    <link rel="stylesheet" type="text/css" href="./resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="./resources/css/styleLogin.css">
    <link rel="icon" type="png" href="./resources/media/images/icon.png">
</head>
<body>
    <header>
	<a href="./Overview.jsp">
            <img src="./resources/media/images/icon.png" height="80%"> 
            Telemaco
        </a>
    </header>

    <div id="middle">
    <form name="register" action="LoginUser" method="post">
        <label id="title"><b>Login</b></label>

		<div id="form">
            <label for="email">Email</label>
            <input type="email" name="email" class="inputText" placeholder="user@email.me" required>

            <label for="psd">Senha</label>
            <input type="password" name="password" class="inputText" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;">
        </div>

		<div id="formObs">
            <input type="checkbox" name="cbKeepConcected">
            <label for="cbKeepConcected"> Mantenha-me Conectado </label>
            <br>

            <label> Não possui cadastro ainda? 
            <a href="./Register.jsp">Cadastre-se agora!</a>
            </label>
        </div>
        
         <div id="buttons">
             <a href="./Overview.jsp" type="button"><button class="button" id="btnCancel">Cancelar</button></a>
            <button class="button" id="btnConfirm" type="submit" value="Login">Confirmar</button>
	</div>
    </form>

       
    
    </div>
</body>
</html>
