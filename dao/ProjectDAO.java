package fr.dao;

import java.util.List;

import fr.beans.Project;

public interface ProjectDAO extends DAOInterface<Project> {

	List<Project> getProjectsOfUser(int id_user);
	
}
