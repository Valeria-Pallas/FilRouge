package fr.beans;

import java.time.LocalDate;
import java.util.List;

public class Project {
    private int id_project;
    private String project_name;
    private String project_description;
    private String project_status;
    private LocalDate begin_date;
    private LocalDate end_date;
    private LocalDate deadline;

    // Constructor para proyectos sin fecha de finalización
    public Project(int id_project, String project_name, String project_description, String project_status,
                   LocalDate begin_date, LocalDate deadline) {
        this.id_project = id_project;
        this.project_name = project_name;
        this.project_description = project_description;
        this.project_status = project_status;
        this.begin_date = begin_date;
        this.deadline = deadline;
    }

    // Constructor para proyectos con fecha de finalización
    public Project(int id_project, String project_name, String project_description, String project_status,
                   LocalDate begin_date, LocalDate end_date, LocalDate deadline) {
        this.id_project = id_project;
        this.project_name = project_name;
        this.project_description = project_description;
        this.project_status = project_status;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.deadline = deadline;
    }

    // Getters y setters para todos los campos

    public int getIdProject() {
        return id_project;
    }

    public String getProjectName() {
        return project_name;
    }

    public String getProjectDescription() {
        return project_description;
    }

    public String getProjectStatus() {
        return project_status;
    }

    public LocalDate getBeginDate() {
        return begin_date;
    }

    public LocalDate getEndDate() {
        return end_date;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

  
}
