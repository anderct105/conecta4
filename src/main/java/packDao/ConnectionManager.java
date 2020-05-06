package packDao;

import java.sql.*;

public class ConnectionManager {

	private static final String bd = "conecta4";
	private static final String driverName = "jdbc:mysql";
	private static final String username = "adminConecta4";
	private static final String password = "adminConecta4";
	private static final String server = "localhost";
	private static final String port = "3306";
	private Connection connection;

	public ConnectionManager() {
		try {
			this.connection = DriverManager.getConnection(driverName + "://" + server + ":" + port + "/ " + bd + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet execSQL(String sql) {
		ResultSet res = null;
		try {
			Statement query = connection.createStatement();
			if (sql.toLowerCase().contains("select")) {
				res = query.executeQuery(sql);
			} else {
				query.executeUpdate(sql);
			}
		} catch (SQLException | NullPointerException e) {
			System.out.println(e);
			System.out.println("No se ha podido ejecutar la sql");
		}
		return res;
	}

	public void closeConnection() throws SQLException {
		connection.close();
	}
}