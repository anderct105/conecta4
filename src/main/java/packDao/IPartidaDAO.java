package packDao;

import org.json.simple.JSONObject;

public interface IPartidaDAO {

	JSONObject cargarRanking();

	/**
	 * 
	 * @param pNombre
	 * @param pPuntuacion
	 */
	void create(String pNombre, int pPuntuacion);

}