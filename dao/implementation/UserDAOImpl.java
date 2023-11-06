package fr.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.beans.User;
import fr.dao.UserDAO;

public class UserDAOImpl implements UserDAO {

	private static final String TABLE_NAME = "user_portfolio";
	private static final String REQ_ALL = "SELECT * FROM " + TABLE_NAME;
	private static final String REQ_BY_ID = REQ_ALL + " WHERE id_user = ?";
	private static final String REQ_INSERT = "INSERT INTO " + TABLE_NAME
			+ " (id_user, user_name, email, password) VALUES (?, ?, ?, ?)";
	private static final String REQ_UPDATE = "UPDATE " + TABLE_NAME
			+ " SET user_name = ?, email = ?, password = ? WHERE id_user = ?";
	private static final String REQ_DEL = "DELETE FROM " + TABLE_NAME + " WHERE id_user = ?";
	private static final String TABLE_EXT = "user_project";
	private static final String REQ_USER_LIST = "SELECT " + TABLE_NAME + ".* FROM " + TABLE_NAME + " INNER JOIN "
			+ TABLE_EXT + " ON " + TABLE_NAME + ".id_user = " + TABLE_EXT + ".id_user " + " WHERE " + TABLE_EXT
			+ ".id_project = ?";

	private Connection user_connect;

	/**
	 * Constructor
	 * 
	 * @param database_connect : connection to database
	 */
	public UserDAOImpl(Connection database_connect) {
		this.user_connect = database_connect;
	}

	/**
	 * Find information of user from all columns of table
	 * 
	 * @return list of all users
	 */
	@Override
	public List<User> findAll() {
		try {
			PreparedStatement prep_statement = user_connect.prepareStatement(REQ_ALL);
			ResultSet user_result = prep_statement.executeQuery();
			List<User> user_list = new ArrayList<>();
			while (user_result.next()) {
				User this_user = new User(user_result.getInt("id_user"), user_result.getString("user_name"),
						user_result.getString("email"), user_result.getString("password"));
				user_list.add(this_user);
			}
			return user_list;
		} catch (SQLException ex) {
			System.out.println("Error while fetching data from all users from table " + TABLE_NAME);
			ex.getLocalizedMessage();
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Find user with a certain id from table
	 * 
	 * @param id : user's id
	 * @return user with this id
	 */
	@Override
	public User findById(int id) {
		try {
			User current_user = null;
			PreparedStatement prep_statement = user_connect.prepareStatement(REQ_BY_ID);
			prep_statement.setInt(1, id);
			ResultSet user_result = prep_statement.executeQuery();
			if (user_result.next()) {
				current_user = new User(user_result.getInt("id_user"), user_result.getString("user_name"),
						user_result.getString("email"), user_result.getString("password"));
			}
			return current_user;
		} catch (SQLException ex) {
			System.out.println("Error while fetching data using user's id from table " + TABLE_NAME);
			ex.getLocalizedMessage();
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Create a new user (or add a new user) to table
	 * 
	 * @param new_user : new user to be added
	 */
	@Override
	public void create(User new_user) {
		try {
			PreparedStatement prep_statement = user_connect.prepareStatement(REQ_INSERT);
			prep_statement.setInt(1, new_user.getId());
			prep_statement.setString(2, new_user.getName());
			prep_statement.setString(3, new_user.getEmail());
			prep_statement.setString(4, new_user.getPassword());

			prep_statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Error while creating a new user in " + TABLE_NAME + " table");
			ex.getLocalizedMessage();
			ex.printStackTrace();
		}
	}

	/**
	 * Update table with new user's info for user with a certain id
	 * 
	 * @param id       : id of user
	 * @param new_user : new user's info
	 */
	@Override
	public void update(int id, User new_user) {
		try {
			PreparedStatement prep_statement = user_connect.prepareStatement(REQ_UPDATE);
			prep_statement.setString(1, new_user.getName());
			prep_statement.setString(2, new_user.getEmail());
			prep_statement.setString(3, new_user.getPassword());
			prep_statement.setInt(4, id);

			prep_statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Error while updating info of user with id = " + id + " from table " + TABLE_NAME);
			ex.getLocalizedMessage();
			ex.printStackTrace();
		}
	}

	/**
	 * Delete a user from table given the user's id
	 * 
	 * @param id : id of user
	 */
	@Override
	public void deleteById(int id) {
		try {
			PreparedStatement prep_statement = user_connect.prepareStatement(REQ_DEL);
			prep_statement.setInt(1, id);
			prep_statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Error while deleting a user with id =" + id + " from table " + TABLE_NAME);
			ex.getLocalizedMessage();
			ex.printStackTrace();
		}
	}

	/**
	 * Get list of users for a certain project given the project id
	 * 
	 * @param id_proj : id of project
	 * @return list of users
	 */
	@Override
	public List<User> getUsersOfProj(int id_proj) {
		try {
			User current_user = null;
			List<User> user_list = new ArrayList<User>();
			PreparedStatement prep_statement = user_connect.prepareStatement(REQ_USER_LIST);
			prep_statement.setInt(1, id_proj);
			ResultSet user_result = prep_statement.executeQuery();
			if (user_result.next()) {
				current_user = new User(user_result.getInt("id_user"), user_result.getString("user_name"),
						user_result.getString("email"), user_result.getString("password"));
				user_list.add(current_user);
			}
			return user_list;
		} catch (SQLException ex) {
			System.out.println("Error while fetch list of users of project with id = " + id_proj);
			ex.getLocalizedMessage();
			ex.printStackTrace();
			return null;
		}
	}

}
