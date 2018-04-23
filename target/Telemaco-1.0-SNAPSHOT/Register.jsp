<%-- 
    Document   : Register
    Created on : 24/03/2018, 15:02:46
    Author     : franklin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="./resources/css/styleRegister.css">
        <link rel="icon" type="png" href="./resources/media/images/icon.png">
    
    <body>
        <header>
            <a href="./Overview.jsp">
                <img src="./resources/media/images/icon.png" height="80%">
                Telemaco
            </a>
        </header>

            <div id="middle">
                <form name="register">
                    <label id="title"><b>Cadastro</b></label>

                        <div id="form">
                            <div id="formLeft">

                                <form name="RegisterUser" action="RegisterUser" method="POST">

                                <label for="name">Nome</label>
                                <input type="text" name="name" class="inputText" placeholder="Ex.: João" required >

                                <div id="genretoLeft">
                                    <label for="sex">Sexo</label> <br>

                                    <label for="male">Masculino</label>
                                    <div class="gender"> <input type="radio" name="gender" value="male"> </div>
                                    <br>
                                    <label for="female">Feminino</label>
                                    <div class="gender"> <input type="radio" name="gender" value="female"> </div>

                                    <!-- <label for="sex">Outro</label>
                                    <div class="gender"> <input type="radio" name="gender" value="other"> </div> -->
                                </div>

                                <div id="dateToRight">
                                    <label for="birthdayDate">Data de Nascimento</label>
                                    <input type="date" name="date" required>
                                </div>

                                <label for="psw">Senha</label>
                                <input type="password" name="password" class="inputText" placeholder="* * * * * *" required >

                                <div id="buttons"> 
                                    <button class="button" id="btnCancel">Cancelar</button>
                                </div>
                                </form>
                            </div>

                        <div id="formRight">
                            <label for="lastname">Sobrenome</label>
                            <input type="text" name="lastname" class="inputText" placeholder="Ex.: Carlos" required >

                            <label for="email">Email</label>
                            <input type="email" name="email" class="inputText" placeholder="exemplo@mail.com" required >

                            <label for="cpassword">Confirmar Senha</label>
                            <input type="password" name="cpassword" class="confSenha" placeholder="* * * * * *" required >

                            <div id="buttons">
                                <button class="button" id="btnRegister">Cadastrar</button>
                            </div>
                        </div>				
                    </div>
                    
                    <br>

                </form>
            </div>
    </body>

