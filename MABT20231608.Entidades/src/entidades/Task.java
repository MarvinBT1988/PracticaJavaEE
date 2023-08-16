/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
import java.util.Date;
/**
 *
 * @author Alumno
 */
public class Task {
     private int id;
    private String title;
    private String description;
    private String status;
    private Date dateCreate;
    private Date dateComplete;

    public Task() {
        // Constructor vacío
    }

    public Task(int id, String title, String description, String status, Date dateCreate, Date dateComplete) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dateCreate = dateCreate;
        this.dateComplete = dateComplete;
    }

    // Getters y setters para cada campo
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(Date dateComplete) {
        this.dateComplete = dateComplete;
    }

}
