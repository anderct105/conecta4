package packDao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdenadorFDAOImpl implements IPartidaDAO {

	public OrdenadorFDAOImpl() {
		// TODO - implement OrdenadorFDAOImpl.Ordenhrow new UnsupportedOperationException();

	}

	public JSONArray cargarRanking() throws SQLException {
		JSONArray ranking= new JSONArray();
		ConnectionManager con= new ConnectionManager();

		ResultSet resultado=con.execSQL("SELECT nombre, tiempo FROM Partida NATURAL JOIN OrdenadorFacil");
		Boolean hayResultado=resultado.next();
		while (hayResultado){
			String nombre=resultado.getString("nombre");
			int puntuacion=resultado.getInt("tiempo");
			JSONObject json=new JSONObject();
			json.put("nombre",nombre);
			json.put("puntuacion",puntuacion);
			ranking.add(json);
			hayResultado=resultado.next();

		}

		return ranking;
	}

	/**
	 * 
	 * @param pNombre
	 * @param pPuntuacion
	 */
	public void create(String pNombre, int pPuntuacion) {
		// TODO - implement OrdenadorFDAOImpl.create
		throw new UnsupportedOperationException();
	}

}