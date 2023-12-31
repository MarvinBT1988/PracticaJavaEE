/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import accesoadatos.TaskDAL;
import entidades.Task;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import utils.Utilidad;

/**
 *
 * @author Alumno
 */
@WebServlet(name = "TaskServlet", urlPatterns = {"/Task"})
public class TaskServlet extends HttpServlet {

    private Task obtenerTask(HttpServletRequest request) {
        // Obtener el parámetro accion del request
        String accion = Utilidad.getParameter(request, "accion", "index");
        Task task = new Task();
        if (accion.equals("create") == false) { // Si la accion no es create.
            // Obtener el parámetro id del request  y asignar ese valor a la propiedad Id de Rol.
            task.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
        task.setTitle(Utilidad.getParameter(request, "title", ""));
         task.setDescription(Utilidad.getParameter(request, "description", ""));        
        // Devolver la instancia de la entidad Rol con los valores obtenidos del request.
        return task;
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Rol, y el parámetro accion sea igual index. Este método se encargara de
     * enviar los datos de los roles al jsp de index de Rol.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Rol
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Rol que utlizaremos para enviar el jsp
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
           
            List<Task> tasksPending = TaskDAL.getPendingTasks(); // Ir a la capa de acceso a datos y buscar los registros de Rol.
            // El request.setAttribute se utiliza para enviar datos desde un servlet a un jsp.
            request.setAttribute("taskPending", tasksPending); // Enviar los roles al jsp utilizando el request.setAttribute con el nombre del atributo roles.
            List<Task> tasksComplete = TaskDAL.getCompleteTasks(); // Ir a la capa de acceso a datos y buscar los registros de Rol.
            // El request.setAttribute se utiliza para enviar datos desde un servlet a un jsp.
            request.setAttribute("taskComplete", tasksComplete);
            // Enviar el Top_aux de Rol al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.          
            // El request.getRequestDispatcher nos permite direccionar a un jsp desde un servlet.              
            request.getRequestDispatcher("Views/Task/index.jsp").forward(request, response); // Direccionar al jsp index de Rol.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    }    
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // direccionar al jsp create de Rol
        request.getRequestDispatcher("Views/Task/create.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Rol , y el parámetro accion sea igual create.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Rol
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Task task = obtenerTask(request); // Llenar la instancia de Rol con los parámetros enviados en el request.
            // Enviar los datos de Rol a la capa de accesoa a datos para que lo almacene en la base de datos el registro.
            int result = TaskDAL.createTask(task);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron ingresados correctamente.
               request.setAttribute("confirmacion", "La tarea se creo correctamente");                         
               request.getRequestDispatcher("Views/Task/confirmacion.jsp").forward(request, response);
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro registrar un nuevo registro
                Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }

    }   
    private void doPostRequestComplete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Task task = obtenerTask(request); // Llenar la instancia de Rol con los parámetros enviados en el request.
            // Enviar los datos de Rol a la capa de accesoa a datos para modificar el registro.
            int result = TaskDAL.completeTask(task.getId());
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron modificado correctamente.
               request.setAttribute("confirmacion", "La tarea se cambio a completada correctamente");                         
               request.getRequestDispatcher("Views/Task/confirmacion.jsp").forward(request, response);
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro actualizar el registro.
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

   

    

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Este método es un override al método de la clase HttpServlet para recibir
     * todas las peticiones get que se realice al Servlet Rol
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Rol
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Rol que utlizaremos para enviar el jsp al
     * navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          doGetRequestIndex(request, response); // Ir al método doGetRequestIndex.
    }

    /**
     * Este método es un override al método de la clase HttpServlet para recibir
     * todas las peticiones post que se realice al Servlet Rol
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Rol
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Rol que utlizaremos para enviar el jsp al
     * navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String accion = Utilidad.getParameter(request, "accion", "index");
            // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
            switch (accion) {
                case "create":                   
                    doPostRequestCreate(request, response); // Ir al metodo doGetRequestCreate.
                    break;
                case "complete":                   
                     doPostRequestComplete(request, response); // Ir al metodo doPostRequestCreate.
                    break;               
                default:
                    doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
            }
    }

}
