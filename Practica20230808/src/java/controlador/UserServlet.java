/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 *
 * @author Alumno
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/User"})
public class UserServlet extends HttpServlet {
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        // Configuración de la base de datos
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=test;loginTimeout=30;encrypt=false;trustServerCertificate=false";
        String dbUser = "docentejdk17";
        String dbPassword = "12345";
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            
            String insertQuery = "INSERT INTO Users (Login, Password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            
            response.sendRedirect("create_user.jsp?message=Usuario creado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("create_user.jsp?message=Error al crear usuario");
        }
    }
   
}
