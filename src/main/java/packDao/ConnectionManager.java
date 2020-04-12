package packDao;

import java.sql.*;

public class ConnectionManager {

	private static String bd="conecta4";
	private static String driverName;
	private static String username="adminConecta4";
	private static String password="adminConecta4";
	private Connection connection;

	public ConnectionManager() {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + bd,username,password);
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