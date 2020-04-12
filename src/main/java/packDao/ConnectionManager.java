package packDao;

import java.sql.*;

public class ConnectionManager {

	private static String bd = "conecta4";
	private static String driverName = "jdbc:mysql://";
	private static String server = "146.148.125.186";
	private static String username = "admin";
	private static String password = "12345678";
	private Connection connection;

	public ConnectionManager() {
		try {
			Connection conexion = DriverManager.getConnection(driverName + server + "/" + bd,username,password);
			this.connection = conexion;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection(){
		return this.connection;
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