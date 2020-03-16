package packDao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Realiza todas las operaciones relacionadas con la tabla OrdenadorD de la base de datos
 */

public class OrdenadorDDAOImpl implements IPartidaDAO {

	public OrdenadorDDAOImpl() {}

	/**
	* Método que devuelve el ranking de las partidas jugadas en modo ordenador fácil
	* @author Nuria Lebeña
	* @return Devuelve un JSONArray con el ranking de las partidas realizadas en el modo ordenador fácil
	 */
	public JSONArray cargarRanking() throws SQLException {
		JSONArray ranking= new JSONArray();
		ConnectionManager con= new ConnectionManager();

		ResultSet resultado=con.execSQL("SELECT nombre, tiempo FROM Partida NATURAL JOIN OrdenadorDificil ORDER BY tiempo ASC Limit 10");
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
	 * El método añade una partida en modo difícil y el id de esa partida a OrdenadorDificil en la base de datos
	 * @author Naiara Maneiro
	 * @param pNombre el nombre del usuario que ha jugado la partida
	 * @param pPuntuacion el tiempo que ha durado la partida
	 * @throws SQLException no se ha podido ejecutar la sentencia sql
	 */

	public void create(String pNombre, int pPuntuacion) throws SQLException {
		ConnectionManager conexion = new ConnectionManager();
		conexion.execSQL("INSERT INTO Partida (nombre, tiempo) VALUES ('"+pNombre+"', "+pPuntuacion+")");
		ResultSet resultado = conexion.execSQL("SELECT id FROM Partida WHERE nombre='"+pNombre+"' AND tiempo="+pPuntuacion);
		boolean hayResultado=resultado.next();
		int valor=resultado.getInt("id");
		while (hayResultado){
			boolean ultimo=resultado.isLast();
			if(ultimo){
				valor=resultado.getInt("id");
			}
			hayResultado=resultado.next();
		}
		conexion.execSQL("INSERT INTO OrdenadorDificil(id) VALUES ("+valor+")");
	}
}