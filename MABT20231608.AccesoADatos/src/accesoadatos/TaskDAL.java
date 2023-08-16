/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesoadatos;
import entidades.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Alumno
 */
public class TaskDAL {  

    public static int createTask(Task task) throws SQLException {
        int result=0;
        String sql = "INSERT INTO Task (Title, Description, Status, DateCreate) VALUES (?, ?, ?, ?)";
        Connection connection=ComunDB.obtenerConexion();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setString(3, "Pending");
            statement.setTimestamp(4, new Timestamp(task.getDateCreate().getTime()));
           result= statement.executeUpdate();
        }
        connection.close();
        return result;
    }

    public static int  completeTask(int taskId) throws SQLException {
         int result=0;
        String sql = "UPDATE Task SET Status = 'Complete', DateComplete = GETDATE() WHERE Id = ?";
        Connection connection=ComunDB.obtenerConexion();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taskId);
           result= statement.executeUpdate();
        }
        connection.close();
        return result;
    }

    public static List<Task> getPendingTasks() throws SQLException {
        List<Task> pendingTasks = new ArrayList<>();
        String sql = "SELECT * FROM Task WHERE Status = 'Pending'";
        Connection connection=ComunDB.obtenerConexion();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Task task = resultSetToTask(resultSet);
                pendingTasks.add(task);
            }
        }
        return pendingTasks;
    }

    public static List<Task> getCompleteTasks() throws SQLException {
        List<Task> completeTasks = new ArrayList<>();
        String sql = "SELECT * FROM Task WHERE Status = 'Complete'";
        Connection connection=ComunDB.obtenerConexion();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Task task = resultSetToTask(resultSet);
                completeTasks.add(task);
            }
        }
        connection.close();
        return completeTasks;
    }

    private static Task resultSetToTask(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("Id");
        String title = resultSet.getString("Title");
        String description = resultSet.getString("Description");
        String status = resultSet.getString("Status");
        Date dateCreate = resultSet.getTimestamp("DateCreate");
        Date dateComplete = resultSet.getTimestamp("DateComplete");
        return new Task(id, title, description, status, dateCreate, dateComplete);
    }    
}
