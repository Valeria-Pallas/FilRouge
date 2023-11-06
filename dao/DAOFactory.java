package fr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.dao.implementation.ProjectDAOImpl;
import fr.dao.implementation.TaskDAOImpl;
import fr.dao.implementation.UserDAOImpl;

public class DAOFactory {
	private static final String DBMS = "postgresql";
	private static final String HOST = "localhost";
	private static final String PORT = "5432";
	private static final String DBNAME = "portfolio";
	private static final String db_user = "your_db_username";
	private static final String db_password = "your_db_password";
	private static final String DRIVER_CLASSNAME = "org.postgresql.Driver";

	private String db_url = "jdbc:"+ DBMS +"://" + HOST + ":" + PORT + "/" + DBNAME;
	private Connection connector;

	/**
	 * Create a single instance of this case
	 */
	private static DAOFactory INSTANCE = new DAOFactory();

	/**
	 * Constructor creating a connection to database
	 */
	private DAOFactory() {
		try {
			System.out.println(db_url);
			Class.forName(DRIVER_CLASSNAME);
			connector = DriverManager.getConnection(db_url, db_user, db_password);
			System.out.println("Fetching data from " + db_url + ", user " + db_user);
		} catch (SQLException ex) {
			System.out.println("Error SQL while creating database connection");
			ex.getStackTrace();
			connector = null;
		} catch (ClassNotFoundException ex) {
			System.out.println("Error ClassNotFound while creating database connection");
//			ex.getStackTrace();
			ex.getLocalizedMessage();
			connector = null;
		}
	}

	/**
	 * Get the class's only instance
	 * 
	 * @return INSTANCE
	 */
	public static DAOFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DAOFactory();
		}
		return INSTANCE;
	}

	/**
	 * Get attribute connector
	 * 
	 * @return connector
	 */
	public Connection getConnector() {
		return connector;
	}
	
	public UserDAO getUserDAO() {
		return new UserDAOImpl(connector);
	}
	
	public ProjectDAO getProjectDAO() {
		return new ProjectDAOImpl(connector);
	}
	
	public TaskDAO getTaskDAO() {
		return new TaskDAOImpl(connector);
	}

}
