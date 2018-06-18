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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update password</title>
    </head>
    <body>
       <p><a href="Logged.jsp">Home </a>></p>
       <h1>Update password</h1>
        <p>
        <form name="UpdatePassword" action="UpdatePassword" method="POST">
            <p><span>Current password</span><input type="password" name="coldpassword" required /></p>
            <p><span>New password</span><input type="password" name="newpassword" required /></p>
            <p><span>Confirm new password</span><input type="password" name="cnewpassword" required /></p>
            <p><input type="submit" name="UpdatePassword" /></p>
        </form>
        </p>
    </body>
</html>