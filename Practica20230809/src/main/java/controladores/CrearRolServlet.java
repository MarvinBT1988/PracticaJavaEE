/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import dal.HibernateUtil;
import entidades.Rol;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Alumno
 */
@WebServlet(name = "CrearRolServlet", urlPatterns = {"/CrearRol"})
public class CrearRolServlet extends HttpServlet {

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String descripcion = request.getParameter("descripcion");
        
        Rol rol = new Rol();
        rol.setName(name);
        rol.setDescripcion(descripcion);
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        session.save(rol);
        
        transaction.commit();
        session.close();
        
        response.sendRedirect("listaRoles.jsp"); // Redirecciona a la lista de roles
    }
}
