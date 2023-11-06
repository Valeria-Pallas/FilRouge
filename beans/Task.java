package fr.beans;

import java.time.LocalDate;

public class Task {
	private int id;
	private String name;
	private String description;
	private String status;
	private User user;
	private Project project;
	private LocalDate deadline;

	/**
	 * Constructor using user inputs
	 * 
	 * @param id_task     : task id
	 * @param name        : task name
	 * @param description : task description
	 * @param status      : task status
	 * @param user        : user associated to task
	 * @param proj        : project of task
	 */
	public Task(int id_task, String name, String description, String status, User user, Project proj) {
		this.id = id_task;
		this.name = name;
		this.description = description;
		this.status = status;
		this.user = user;
		this.project = proj;
	}

	/**
	 * Full constructor using user inputs
	 * 
	 * @param id_task     : task id
	 * @param name        : task name
	 * @param description : task description
	 * @param status      : task status
	 * @param user        : user associated to task
	 * @param proj        : project of task
	 * @param deadline    : deadline of task
	 */
	public Task(int id_task, String name, String description, String status, User user, Project proj,
			LocalDate deadline) {
		this.id = id_task;
		this.name = name;
		this.description = description;
		this.status = status;
		this.user = user;
		this.project = proj;
		this.deadline = deadline;
	}

	/**
	 * Get task id
	 * 
	 * @return task id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get task name
	 * 
	 * @return task name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get task description
	 * 
	 * @return task description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get task status
	 * 
	 * @return task status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Get user who will do task
	 * 
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Get project to which task belongs
	 * 
	 * @return project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Get deadline of task
	 * 
	 * @return deadline
	 */
	public LocalDate getDeadline() {
		return deadline;
	}

	/**
	 * Set new status for task
	 * 
	 * @param new_status
	 */
	public void updateStatus(String new_status) {
		this.status = new_status;
	}

	/**
	 * Update id of new user assigned to this task
	 * 
	 * @param new_user : new user's id
	 */
	public void updateUser(User new_user) {
		this.user = new_user;
	}

}
