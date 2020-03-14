package packDao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdenadorFDAOImpl implements IPartidaDAO {

	public OrdenadorFDAOImpl() {}

	}
	/*
	 *Método que devuelve el ranking de las partidas jugadas en modo ordenador dificil
	 * @author Nuria Lebeña
	 * @param
	 *@return Devuelve un JSONArray con el ranking de las partidas realizadas en el modo ordenador dificil

	 */
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
	public void create(String pNombre, int pPuntuacion){
		ConnectionManager conexion = new ConnectionManager();
		conexion.execSQL("INSERT INTO Partida (nombre, tiempo) VALUES ('"+pNombre+"', "+pPuntuacion+")");
		ResultSet resultado = conexion.execSQL("SELECT id FROM Partida WHERE nombre='"+pNombre+"' AND tiempo="+pPuntuacion);
		conexion.execSQL("INSERT INTO OrdenadorFacil id VALUES "+resultado+"");
	}
}