package java.packDao;

import java.sql.Connection;

public class ConnectionManager {

	private static String url;
	private static String driverName;
	private static String username;
	private static String password;
	private Connection connection;

	public ConnectionManager() {
		// TODO - implement ConnectionManager.ConnectionManager
		throw new UnsupportedOperationException();
	}

	public Connection getConnection() {
		return this.connection;
	}

}