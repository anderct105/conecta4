package packDao;

import org.json.simple.JSONArray;

import java.sql.SQLException;

public interface IPartidaDAO {

	JSONArray cargarRanking() throws SQLException;

	/**
	 * 
	 * @param pNombre
	 * @param pPuntuacion
	 */
	void create(String pNombre, int pPuntuacion) throws SQLException;

}