package fr.dao;

import java.util.List;

import fr.beans.Task;

public interface TaskDAO extends DAOInterface<Task> {

	List<Task> getTasksOfUser(int id_user);
	List<Task> getTasksOfProject(int id_proj);
	
}
