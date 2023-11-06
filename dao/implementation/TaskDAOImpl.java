package fr.dao.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.beans.Task;
import fr.dao.DAOFactory;
import fr.dao.ProjectDAO;
import fr.dao.TaskDAO;
import fr.dao.UserDAO;

public class TaskDAOImpl implements TaskDAO {

	private static final String TABLE_NAME = "task";
	private static final String REQ_ALL = "SELECT * FROM " + TABLE_NAME;
	private static final String REQ_BY_ID = REQ_ALL + " WHERE id_task = ?";
	private static final String REQ_INSERT = "INSERT INTO " + TABLE_NAME
			+ " (id_task, task_name, task_description, task_status, id_user, id_project, deadline)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String REQ_UPDATE_ALL = "UPDATE " + TABLE_NAME
			+ " SET task_name = ?, task_description = ?, task_status = ?, "
			+ " id_user = ?, id_project = ?, deadline = ? " + " WHERE id_task = ?";
	private static final String REQ_DEL = "DELETE FROM " + TABLE_NAME + " WHERE id_task = ?";
	private static final String REQ_LIST_BY_USERID = REQ_ALL + " WHERE id_user = ?";
	private static final String REQ_LIST_BY_PROJID = REQ_ALL + " WHERE id_project = ?";

	private Connection task_connect;

	/**
	 * Constructor
	 * 
	 * @param database_connection : connection to database
	 */
	public TaskDAOImpl(Connection database_connection) {
		this.task_connect = database_connection;
	}

	/**
	 * Find information of tasks from all columns of table
	 * 
	 * @return list of all tasks
	 */
	@Override
	public List<Task> findAll() {
		try {
			PreparedStatement prep_statement = task_connect.prepareStatement(REQ_ALL);
			ResultSet task_result = prep_statement.executeQuery();
			List<Task> task_list = new ArrayList<>();
			UserDAO user_dao = DAOFactory.getInstance().getUserDAO();
			ProjectDAO proj_dao = DAOFactory.getInstance().getProjectDAO();
			while (task_result.next()) {
				Task this_task = new Task(task_result.getInt("id_task"), task_result.getString("task_name"),
						task_result.getString("description"), task_result.getString("status"),
						user_dao.findById(task_result.getInt("id_user")),
						proj_dao.findById(task_result.getInt("id_project")));
				task_list.add(this_task);
			}
			return task_list;
		} catch (SQLException ex) {
			System.out.println("Error while fetching data from all tasks");
			ex.getLocalizedMessage();
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Find task with a certain id from table
	 * 
	 * @param id : task's id
	 * @return user with this id
	 */
	@Override
	public Task findById(int id) {
		try {
			Task current_task = null;
			PreparedStatement prep_statement = task_connect.prepareStatement(REQ_BY_ID);
			prep_statement.setInt(1, id);
			ResultSet task_result = prep_statement.executeQuery();
			UserDAO user_dao = DAOFactory.getInstance().getUserDAO();
			ProjectDAO proj_dao = DAOFactory.getInstance().getProjectDAO();
			if (task_result.next()) {
				current_task = new Task(task_result.getInt("id_task"), task_result.getString("task_name"),
						task_result.getString("description"), task_result.getString("status"),
						user_dao.findById(task_result.getInt("id_user")),
						proj_dao.findById(task_result.getInt("id_project")));
			}
			return current_task;
		} catch (SQLException ex) {
			System.out.println("Error while fetching data using task's id");
			ex.getLocalizedMessage();
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Create a new task (or add a new task) to table
	 * 
	 * @param new_task : new task to be added
	 */
	@Override
	public void create(Task new_task) {
		try {
			PreparedStatement prep_statement = task_connect.prepareStatement(REQ_INSERT);
			prep_statement.setInt(1, new_task.getId());
			prep_statement.setString(2, new_task.getName());
			prep_statement.setString(3, new_task.getDescription());
			prep_statement.setString(4, new_task.getStatus());
			prep_statement.setInt(5, new_task.getUser().getId());
			prep_statement.setInt(6, new_task.getProject().getId());
			prep_statement.setDate(7, Date.valueOf(new_task.getDeadline()));

			prep_statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Error while creating a new task in " + TABLE_NAME + " table");
			ex.getLocalizedMessage();
			ex.printStackTrace();
		}
	}

	/**
	 * Update table with new task's info for task with a certain id
	 * 
	 * @param id       : id of task
	 * @param new_user : new task's info
	 */
	@Override
	public void update(int id, Task new_task) {
		try {
			PreparedStatement prep_statement = task_connect.prepareStatement(REQ_UPDATE_ALL);
			prep_statement.setString(1, new_task.getName());
			prep_statement.setString(2, new_task.getDescription());
			prep_statement.setString(3, new_task.getStatus());
			prep_statement.setInt(4, new_task.getUser().getId());
			prep_statement.setInt(5, new_task.getProject().getId());
			prep_statement.setDate(6, Date.valueOf(new_task.getDeadline()));
			prep_statement.setInt(7, id);

			prep_statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(
					"Error while updating all fields of a task with id = " + id + " in " + TABLE_NAME + " table");
			ex.getLocalizedMessage();
			ex.printStackTrace();
		}
	}

//	public String get_req_update(String column_name) {
//		return "UPDATE " + TABLE_NAME + " SET " + column_name + " = ? WHERE id_task = ?";
//	}
//
//	public void update(int id, Task new_task, String column_name) {
//		try {
//			String req_update = get_req_update(column_name);
//			PreparedStatement prep_statement = task_connect.prepareStatement(req_update);
//			prep_statement.setString(1, new_task.getName());
//			prep_statement.setString(2, new_task.getDescription());
//			prep_statement.setString(3, new_task.getStatus());
//			prep_statement.setInt(4, new_task.getUser().getId());
//			prep_statement.setInt(5, new_task.getProject().getId());
//			prep_statement.setDate(6, Date.valueOf(new_task.getDeadline()));
//			prep_statement.setInt(7, id);
//
//			prep_statement.executeUpdate();
//		} catch (SQLException sql_ex) {
//			System.out.println("Error while updating all fields of a task with id = " + id + " in " + TABLE_NAME + " table");
//			sql_ex.getLocalizedMessage();
//			sql_ex.printStackTrace();
//		}
//	}

	/**
	 * Delete a task from table given the task's id
	 * 
	 * @param id : id of task
	 */
	@Override
	public void deleteById(int id) {
		try {
			PreparedStatement prep_statement = task_connect.prepareStatement(REQ_DEL);
			prep_statement.setInt(1, id);
			prep_statement.executeUpdate();
		} catch (SQLException ex) {
			ex.getLocalizedMessage();
			ex.printStackTrace();
		}
	}

	/**
	 * Get list of tasks of a certain user given the user id
	 * 
	 * @param id_user : id of user
	 * @return list of tasks
	 */
	@Override
	public List<Task> getTasksOfUser(int id_user) {
		try {
			PreparedStatement prep_statement = task_connect.prepareStatement(REQ_LIST_BY_USERID);
			prep_statement.setInt(1, id_user);
			ResultSet task_result = prep_statement.executeQuery();
			List<Task> task_list = new ArrayList<>();
			UserDAO user_dao = DAOFactory.getInstance().getUserDAO();
			ProjectDAO proj_dao = DAOFactory.getInstance().getProjectDAO();
			while (task_result.next()) {
				Task current_task = new Task(task_result.getInt("id_task"), task_result.getString("task_name"),
						task_result.getString("task_description"), task_result.getString("task_status"),
						user_dao.findById(task_result.getInt("id_user")),
						proj_dao.findById(task_result.getInt("id_project")),
						task_result.getDate("deadline").toLocalDate());
				task_list.add(current_task);
			}
			return task_list;
		} catch (SQLException ex) {
			System.out.println("Error while fetching list of tasks of user with id = " + id_user);
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
	public List<Task> getTasksOfProject(int id_proj) {
		try {
			PreparedStatement prep_statement = task_connect.prepareStatement(REQ_LIST_BY_PROJID);
			prep_statement.setInt(1, id_proj);
			ResultSet task_result = prep_statement.executeQuery();
			List<Task> task_list = new ArrayList<>();
			UserDAO user_dao = DAOFactory.getInstance().getUserDAO();
			ProjectDAO proj_dao = DAOFactory.getInstance().getProjectDAO();
			while (task_result.next()) {
				Task current_task = new Task(task_result.getInt("id_task"), task_result.getString("task_name"),
						task_result.getString("task_description"), task_result.getString("task_status"),
						user_dao.findById(task_result.getInt("id_user")),
						proj_dao.findById(task_result.getInt("id_project")),
						task_result.getDate("deadline").toLocalDate());
				task_list.add(current_task);
			}
			return task_list;
		} catch (SQLException ex) {
			System.out.println("Error while fetching list of tasks of project with id = " + id_proj);
			ex.getLocalizedMessage();
			ex.printStackTrace();
			return null;
		}
	}

}
