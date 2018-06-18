<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Apresentação</title>
    <link rel="stylesheet" type="text/css" href="./resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="./resources/css/styleOverview.css">
    <link rel="icon" type="png" href="./resources/media/images/icon.png">
</head>
<body>
    <header>
	<a href="./Overview.jsp"><img src="./resources/media/images/icon.png" height="90%"> </a>
        <a href="./Login.jsp"><button id="btnLogin" >Entrar</button></a>
    </header>
    <div id="adversiment">
        <form>
            <label id=adTitle> 
                <b>Não se perca nas Séries </b> <br>
            </label>
            <label id="adBody"> 
                Com <span>Telemaco</span> você pode acompanhar todo seu progresso nas séries. Pare de perder tempo descobrindo em qual episódio parou. Registre-se e aproveite cada momento!
            </label>
        </form>
    </div>
    <a href="./Register.jsp"><button id="btnRegister">Registre-se Agora</button></a>
</body>
</html>