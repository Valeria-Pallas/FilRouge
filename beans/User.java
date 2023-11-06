package fr.beans;

import java.util.List;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private List<Task> list_of_tasks;
	private List<Project> list_of_projects;

	/**
	 * Constructor using user inputs
	 * 
	 * @param name     : user name
	 * @param email    : email address
	 * @param password : user password
	 */
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Constructor using user inputs
	 * 
	 * @param id       : ID for user
	 * @param name     : user name
	 * @param email    : email address
	 * @param password : user password
	 */
	public User(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Constructor using user inputs
	 * 
	 * @param id       : ID for user
	 * @param name     : user name
	 * @param email    : email address
	 * @param password : user password
	 */
	public User(int id, String name, String email, String password, List<Task> task_list, List<Project> project_list) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.list_of_tasks = task_list;
		this.list_of_projects = project_list;
	}

	/**
	 * Get user id
	 * 
	 * @return user id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get user name
	 * 
	 * @return user name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get user's email
	 * 
	 * @return user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Get user's password
	 * @return user's password
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get list of tasks
	 * 
	 * @return list of tasks
	 */
	public List<Task> getListOfTasks() {
		return list_of_tasks;
	}

	/**
	 * Get list of projects
	 * 
	 * @return list of projects
	 */
	public List<Project> getListOfProjects() {
		return list_of_projects;
	}

	/**
	 * Set new password
	 * 
	 * @param new_pwd : new password
	 */
	public void updatePassword(String new_pwd) {
		this.password = new_pwd;
	}

	/**
	 * Set list of tasks
	 * 
	 * @param task_list : list of tasks
	 */
	public void setListOfTasks(List<Task> task_list) {
		this.list_of_tasks = task_list;
	}

	/**
	 * Set list of projects
	 * 
	 * @param project_list : list of projects
	 */
	public void setList_of_projects(List<Project> project_list) {
		this.list_of_projects = project_list;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}