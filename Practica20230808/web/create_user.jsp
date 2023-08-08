<%-- 
    Document   : create_user
    Created on : 8 ago 2023, 11:28:17
    Author     : Alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Usuario</title>
</head>
<body>
    <h2>Crear nuevo usuario</h2>
    <form action="User" method="post">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        
        <input type="submit" value="Crear Usuario">
    </form>
</body>
</html>
