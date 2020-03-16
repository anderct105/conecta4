package packDao;

import org.json.simple.JSONArray;

import java.sql.SQLException;
import java.sql.Timestamp;

public interface IPartidaDAO {

	JSONArray cargarRanking() throws SQLException;

	/**
	 *
	 * @param pFechaHora
	 * @param pNombre
	 * @param pPuntuacion
	 */
	void create(Timestamp pFechaHora, String pNombre, int pPuntuacion) throws SQLException;

}