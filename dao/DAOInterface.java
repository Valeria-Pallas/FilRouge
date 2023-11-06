package fr.dao;

import java.util.List;

public interface DAOInterface<T> {

	List<T> findAll();
	T findById(int id);
	void create(T row);
	void update(int id, T new_obj);
	void deleteById(int id);

}
