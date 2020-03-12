package packDao;

import com.mysql.cj.xdevapi.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdenadorDDAOImpl implements IPartidaDAO {

	public OrdenadorDDAOImpl() {
		// TODO - implement OrdenadorDDAOImpl.OrdenadorDDAOImpl

	}

	public JSONArray cargarRanking() throws SQLException {
		JSONArray ranking= new JSONArray();
		ConnectionManager con= new ConnectionManager();

		ResultSet resultado=con.execSQL("SELECT nombre, tiempo FROM Partida NATURAL JOIN OrdenadorDificil");
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
		// TODO - implement OrdenadorDDAOImpl.create
		throw new UnsupportedOperationException();
	}

}