package fr.dao.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.beans.Project;
import fr.dao.ProjectDAO;

public class ProjectDAOImpl implements ProjectDAO {

    private static final String TABLE_NAME = "project";
    private static final String REQ_ALL = "SELECT * FROM " + TABLE_NAME;
    private static final String REQ_BY_ID = REQ_ALL + " WHERE id_project = ?";
    private static final String REQ_INSERT = "INSERT INTO " + TABLE_NAME
            + " (id_project, project_name, project_description, project_status, begin_date, end_date, deadline)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String REQ_UPDATE_ALL = "UPDATE " + TABLE_NAME
            + " SET project_name = ?, project_description = ?, project_status = ?, "
            + " begin_date = ?, end_date = ?, deadline = ? " + " WHERE id_project = ?";
    private static final String REQ_DEL = "DELETE FROM " + TABLE_NAME + " WHERE id_project = ?";

    private Connection project_connect;

    public ProjectDAOImpl(Connection database_connection) {
        this.project_connect = database_connection;
    }

    @Override
    public List<Project> findAll() {
        try {
            PreparedStatement prep_statement = project_connect.prepareStatement(REQ_ALL);
            ResultSet project_result = prep_statement.executeQuery();
            List<Project> project_list = new ArrayList<>();
            while (project_result.next()) {
                Project this_project = new Project(project_result.getInt("id_project"),
                        project_result.getString("project_name"), project_result.getString("project_description"),
                        project_result.getString("project_status"));
                LocalDate beginDate = project_result.getDate("begin_date") != null
                        ? project_result.getDate("begin_date").toLocalDate()
                        : null;
                LocalDate endDate = project_result.getDate("end_date") != null
                        ? project_result.getDate("end_date").toLocalDate()
                        : null;
                LocalDate deadline = project_result.getDate("deadline") != null
                        ? project_result.getDate("deadline").toLocalDate()
                        : null;
                this_project.setStartDate(beginDate);
                this_project.setEndDate(endDate);
                this_project.setDeadline(deadline);
                project_list.add(this_project);
            }
            return project_list;
        } catch (SQLException ex) {
            System.out.println("Error while fetching data from all projects");
            ex.printStackTrace();
            return null;
        }
    }

   


	/**
	 * Find project with a certain id from table
	 *
	 * @param id : project's id
	 * @return project with this id
	 */
	@Override
	public Project findById(int id) {
		try {
			Project current_project = null;
			PreparedStatement prep_statement = project_connect.prepareStatement(REQ_BY_ID);
			prep_statement.setInt(1, id);
			ResultSet project_result = prep_statement.executeQuery();
			if (project_result.next()) {
				current_project = new Project(project_result.getInt("id_project"),
						project_result.getString("project_name"),
						project_result.getString("project_description"),
						project_result.getString("project_status"));
				LocalDate beginDate = project_result.getDate("begin_date") != null
						? project_result.getDate("begin_date").toLocalDate()
						: null;
				LocalDate endDate = project_result.getDate("end_date") != null
						? project_result.getDate("end_date").toLocalDate()
						: null;
				LocalDate deadline = project_result.getDate("deadline") != null
						? project_result.getDate("deadline").toLocalDate()
						: null;
				current_project.setStartDate(beginDate);
				current_project.setEndDate(endDate);
				current_project.setDeadline(deadline);
			}
			return current_project;
		} catch (SQLException ex) {
			System.out.println("Error while fetching data using project's id");
			ex.getLocalizedMessage();
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Create a new project (or add a new project) to table
	 *
	 * @param new_project : new project to be added
	 */
	@Override
	public void create(Project new_project) {
		try {
			PreparedStatement prep_statement = project_connect.prepareStatement(REQ_INSERT);
			prep_statement.setInt(1, new_project.getId());
			prep_statement.setString(2, new_project.getName());
			prep_statement.setString(3, new_project.getDescription());
			prep_statement.setString(4, new_project.getStatus());
			prep_statement.setDate(5, Date.valueOf(new_project.getStartDate()));
			prep_statement.setDate(6, Date.valueOf(new_project.getEndDate()));
			prep_statement.setDate(7, Date.valueOf(new_project.getDeadline()));

			prep_statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Error while creating a new project in " + TABLE_NAME + " table");
			ex.getLocalizedMessage();
			ex.printStackTrace();
		}
	}


	/**
	 * Update table with new project's info for project with a certain id
	 *
	 * @param id        : id of project
	 * @param new_project : new project's info
	 */
	@Override
	public void update(int id, Project new_project) {
		try {
			PreparedStatement prep_statement = project_connect.prepareStatement(REQ_UPDATE_ALL);
			prep_statement.setString(1, new_project.getName());
			prep_statement.setString(2, new_project.getDescription());
			prep_statement.setString(3, new_project.getStatus());
			prep_statement.setDate(4, Date.valueOf(new_project.getStartDate()));
			prep_statement.setDate(5, Date.valueOf(new_project.getEndDate()));
			prep_statement.setDate(6, Date.valueOf(new_project.getDeadline()));
			prep_statement.setInt(7, id);

			prep_statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(
					"Error while updating all fields of a project with id = " + id + " in " + TABLE_NAME + " table");
			ex.getLocalizedMessage();
			ex.printStackTrace();
		}
	}



	/**
	 * Delete a project from table given the project's id
	 *
	 * @param id : id of project
	 */
	@Override
	public void deleteById(int id) {
		try {
			PreparedStatement prep_statement = project_connect.prepareStatement(REQ_DEL);
			prep_statement.setInt(1, id);
			prep_statement.executeUpdate();
		} catch (SQLException ex) {
			ex.getLocalizedMessage();
			ex.printStackTrace();
		}
	}


	/**
	 * Get list of projects of a certain user given the user id
	 *
	 * @param id_user : id of user
	 * @return list of projects
	 */
	@Override
	public List<Project> getProjectsOfUser(int id_user) {
		try {
			PreparedStatement prep_statement = project_connect.prepareStatement(REQ_LIST_BY_USERID);
			prep_statement.setInt(1, id_user);
			ResultSet project_result = prep_statement.executeQuery();
			List<Project> project_list = new ArrayList<>();
			UserDAO user_dao = DAOFactory.getInstance().getUserDAO();
			while (project_result.next()) {
				Project current_project = new Project(project_result.getInt("id_project"),
						project_result.getString("project_name"), project_result.getString("project_description"),
						project_result.getString("project_status"), project_result.getDate("begin_date").toLocalDate(),
						project_result.getDate("end_date") != null
								? project_result.getDate("end_date").toLocalDate()
								: null,
						project_result.getDate("deadline") != null
								? project_result.getDate("deadline").toLocalDate()
								: null);
				project_list.add(current_project);
			}
			return project_list;
		} catch (SQLException ex) {
			System.out.println("Error while fetching list of projects of user with id = " + id_user);
			ex.getLocalizedMessage();
			ex.printStackTrace();
			return null;
		}
	}


	/**
	 * Get list of tasks of a certain project given the project id
	 *
	 * @param id_proj : id of project
	 * @return list of tasks
	 */
	@Override
	public List<Project> getTasksOfProject(int id_proj) {
		try {
			PreparedStatement prep_statement = project_connect.prepareStatement(REQ_LIST_BY_PROJID);
			prep_statement.setInt(1, id_proj);
			ResultSet project_result = prep_statement.executeQuery();
			List<Project> project_list = new ArrayList<>();
			UserDAO user_dao = DAOFactory.getInstance().getUserDAO();
			while (project_result.next()) {
				Project current_project = new Project(project_result.getInt("id_project"),
						project_result.getString("project_name"), project_result.getString("project_description"),
						project_result.getString("project_status"), project_result.getDate("begin_date").toLocalDate(),
						project_result.getDate("end_date") != null
								? project_result.getDate("end_date").toLocalDate()
								: null,
						project_result.getDate("deadline") != null
								? project_result.getDate("deadline").toLocalDate()
								: null);
				project_list.add(current_project);
			}
			return project_list;
		} catch (SQLException ex) {
			System.out.println("Error while fetching list of projects of project with id = " + id_proj);
			ex.getLocalizedMessage();
			ex.printStackTrace();
			return null;
		}
	}

}
