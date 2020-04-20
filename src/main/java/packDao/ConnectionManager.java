package packDao;

import java.sql.*;

public class ConnectionManager {

	private static String bd="conecta4";
	private static String driverName = "jdbc:mysql";
	private static String username="adminConecta4";
	private static String password="adminConecta4";
	private static String server = "localhost";
	private static String port = "3306";
	private Connection connection;

	public ConnectionManager() {
		try {
			Connection conexion = DriverManager.getConnection(driverName + "://" + server + ":" + port + "/ " + bd+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",username,password);
			this.connection = conexion;
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
			}
			else {
				query.executeUpdate(sql);
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("No se ha podido ejecutar la sql");
		}
		return res;
	}

	public void closeConnection() throws SQLException {
		connection.close();
	}
}