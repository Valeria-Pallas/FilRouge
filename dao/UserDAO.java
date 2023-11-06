package fr.dao;

import java.util.List;

import fr.beans.User;

public interface UserDAO extends DAOInterface<User> {

	List<User> getUsersOfProj(int id_proj);
	
}
