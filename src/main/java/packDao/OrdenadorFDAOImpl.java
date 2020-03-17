package packDao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 * Realiza todas las operaciones relacionadas con la tabla OrdenadorF de la base de datos
 */
public class  OrdenadorFDAOImpl implements IPartidaDAO {

	public OrdenadorFDAOImpl() {}

	/**
	 * Método que devuelve el ranking de las partidas jugadas en modo ordenador dificil
	 * @author Nuria Lebeña
	 * @throws SQLException no se ha podido ejecutar la sentencia sql
	 * @return Devuelve un JSONArray con el ranking de las partidas realizadas en el modo ordenador dificil
	 */
	public JSONArray cargarRanking() throws SQLException {
		JSONArray ranking= new JSONArray();
		ConnectionManager con= new ConnectionManager();

		ResultSet resultado=con.execSQL("SELECT nombre, tiempo FROM Partida NATURAL JOIN OrdenadorFacil ORDER BY tiempo ASC Limit 10");
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
	 * El método añade una partida en modo fácil y el id de esa partida a OrdenadorFacil en la base de datos
	 * @author Naiara Maneiro
	 * @param pNombre el nombre del usuario que ha jugado la partida
	 * @param pPuntuacion el tiempo que ha durado la partida
	 * @throws SQLException no se ha podido ejecutar la sentencia sql
	 */

	public void create(String pNombre, int pPuntuacion) {
		Timestamp pFechaHora = new Timestamp(System.currentTimeMillis());
		ConnectionManager conexion = new ConnectionManager();
		try {
			conexion.execSQL("INSERT INTO Partida (nombre, tiempo, fechaHora) VALUES ('"+pNombre+"', "+pPuntuacion+", '"+pFechaHora+"')");
			ResultSet resultado = conexion.execSQL("SELECT id FROM Partida WHERE fechaHora = '"+pFechaHora+"'");
			resultado.next();
			int valor=resultado.getInt("id");
			conexion.execSQL("INSERT INTO OrdenadorFacil(id) VALUES ("+valor+")");
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}