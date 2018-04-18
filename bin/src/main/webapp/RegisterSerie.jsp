<%-- 
    Document   : CadastrarSerie
    Created on : Mar 28, 2018, 9:29:30 AM
    Author     : valmir
--%>

<%@page import="com.imd.telemaco.entity.User"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    User logged = new User();
    if(session.getAttribute("logged") == null)
        response.sendRedirect("Login.jsp");
    else
        logged = (User) (session.getAttribute("logged"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar serie</title>
    </head>
    <body>
       <p><a href="Logged.jsp">Home </a>></p>
       <h1>Register serie</h1>
        <p>
        <form name="RegisterSerie" action="RegisterSerie" method="POST">
            <p><span>Name</span><input type="text" name="name" required /></p>
            <p><input type="submit" name="Submit" value="submit" /></p>
        </form>
        </p>
    </body>
</html>
