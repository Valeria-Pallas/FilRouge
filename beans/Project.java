package fr.beans;

import java.time.LocalDate;
import java.util.List;

public class Project {
	private int id;
	private String name;
	private String description;
	private LocalDate start_date;
	private LocalDate end_date;
	private LocalDate deadline;
	private String status;
	private List<User> list_of_users;
	private List<Task> list_of_tasks;

	/**
	 * Constructor using user inputs
	 * 
	 * @param id            : project id
	 * @param name          : project name
	 * @param description   : project's description
	 * @param d_date        : expected deadline
	 * @param status        : project status
	 * @param list_of_users : list of users
	 */
	public Project(int id, String name, String description, LocalDate d_date, String status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = LocalDate.now();
		this.end_date = null;
		this.deadline = d_date;
		this.status = status;
	}

	/**
	 * Constructor using user inputs
	 * 
	 * @param name        : project name
	 * @param description : project's description
	 * @param s_date      : starting date
	 * @param e_date      : ending date
	 * @param d_date      : expected deadline
	 * @param status      : project status
	 */
	public Project(String name, String description, LocalDate s_date, LocalDate e_date, LocalDate d_date,
			String status) {
		this.name = name;
		this.description = description;
		this.start_date = s_date;
		this.end_date = e_date;
		this.deadline = d_date;
		this.status = status;
	}

	/**
	 * Constructor using user inputs
	 * 
	 * @param id          : project id
	 * @param name        : project name
	 * @param description : project's description
	 * @param s_date      : starting date
	 * @param e_date      : ending date
	 * @param d_date      : expected deadline
	 * @param status      : project status
	 */
	public Project(int id, String name, String description, LocalDate s_date, LocalDate e_date, LocalDate d_date,
			String status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = s_date;
		this.end_date = e_date;
		this.deadline = d_date;
		this.status = status;
	}

	/**
	 * Constructor using user inputs
	 * 
	 * @param id            : project id
	 * @param name          : project name
	 * @param description   : project's description
	 * @param s_date        : starting date
	 * @param e_date        : ending date
	 * @param d_date        : expected deadline
	 * @param status        : project status
	 * @param list_of_users : list of users
	 * @param list_of_tasks : list of tasks
	 */
	public Project(int id, String name, String description, LocalDate s_date, LocalDate e_date, LocalDate d_date,
			String status, List<User> user_list, List<Task> task_list) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = s_date;
		this.end_date = e_date;
		this.deadline = d_date;
		this.status = status;
		this.list_of_users = user_list;
		this.list_of_tasks = task_list;
	}

	/**
	 * Get project id
	 * 
	 * @return project id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get project name
	 * 
	 * @return project name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get project description
	 * 
	 * @return project description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get starting date of project
	 * 
	 * @return starting date
	 */
	public LocalDate getStartDate() {
		return start_date;
	}

	/**
	 * Get ending date of project
	 * 
	 * @return ending date
	 */
	public LocalDate getEndDate() {
		return end_date;
	}

	/**
	 * Get expected deadline of project
	 * 
	 * @return expected deadline
	 */
	public LocalDate getDeadline() {
		return deadline;
	}

	/**
	 * Get status of project
	 * 
	 * @return status of project
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Get list of users
	 * 
	 * @return list of users
	 */
	public List<User> getListOfUsers() {
		return list_of_users;
	}

	/**
	 * Get list of tasks of project
	 * 
	 * @return list of tasks
	 */
	public List<Task> getListOfTasks() {
		return list_of_tasks;
	}

	/**
	 * Set list of users
	 * 
	 * @param user_list : list of users
	 */
	public void setListOfUsers(List<User> user_list) {
		this.list_of_users = user_list;
	}

	/**
	 * Set list of tasks of project
	 * 
	 * @param task_list : list of tasks
	 */
	public void setListOfTasks(List<Task> task_list) {
		this.list_of_tasks = task_list;
	}

	/**
	 * Update project status
	 * 
	 * @param new_status new status of project
	 */
	public void updateStatus(String new_status) {
		this.status = new_status;
	}

}
