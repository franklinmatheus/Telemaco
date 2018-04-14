<%-- 
    Document   : Register
    Created on : 24/03/2018, 15:02:46
    Author     : franklin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <p><a href="Overview.jsp">Home </a></p>
        <h1>Register</h1>
        <p>
        <form name="register" action="RegisterUser" method="POST">
            <p><span>Name</span><input type="text" name="name" required /></p>
            <p><span>Last name</span><input type="text" name="lastname" required /></p>
            <p><span>Password</span><input type="password" name="password" required /></p>
            <p><span>Confirm password</span><input type="password" name="cpassword" required /></p>
            <p><span>Email</span><input type="email" name="email" required /></p>
            <p><span>Confirm email</span><input type="email" name="cemail" required /></p>
            <p>
                <span>Gênero</span>
                <input type="radio" name="gender" value="m" required />Male
                <input type="radio" name="gender" value="f" required />Female
            </p>
            <p><span>Birth</span><input type="date" name="date" required /></p>
            <p><input type="submit" value="Submit" /></p>
        </form>
        </p>
    </body>
</html>
