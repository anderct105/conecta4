package packControlador;

import com.mysql.cj.xdevapi.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packDao.OrdenadorDDAOImpl;
import packModelo.Modo;

import java.sql.SQLException;

public class Conecta4 {

	private static Conecta4 mConecta4;

	private Conecta4() {
		// TODO - implement Conecta4.Conecta4
		throw new UnsupportedOperationException();
	}

	public static Conecta4 getmConecta4() {
		if (mConecta4 == null){
			mConecta4 = new Conecta4();
		}
		return mConecta4;
	}

	/**Pre: recibe como parámetro la columna introducida por el jugador
	 * Post:devuelve un json que contiene la información de lo sucedido durante la partida
	 * @author Nuria Lebeña
	 * @param pColumna la columna en la que se va a introducir la ficha en la jugada
	 * @return Json de la forma {x:int, y:int, lleno: boolean, posicionesGanadoras {x1:,y1:,x2:,y2:...}} que contiene la
	 * 	información de lo sucedido durante la partida y nulo en caso de que no se haya podido introducir la ficha
	 */
	public JSONObject jugarPartida(int pColumna) {
		return GestorPartidas.getmGestorPartidas().jugarPartida(pColumna);
	}

	/**
	 * El método obtiene el modo en el que la aplicación esta configurada para jugar.
	 * @author Alvaro Dueñas
	 * @return El nombre del modo de juego
	 */
	public String getModoJuego() {
		return GestorPartidas.getmGestorPartidas().getModoJuego();
	}

	/**
	 * El método establece el modo en el que la aplicación estará configurada para jugar.
	 * @author Alvaro Dueñas
	 * @param pModo el nombre del modo especificado
	 */
	public void setModoJuego(String pModo) {
		Modo modo = ModoFactory.getmModoFactory().createModo(pModo);
		GestorPartidas.getmGestorPartidas().setModoJuego(modo);
	}

	public JSONArray cargarRankingFacil() {
		JSONArray ranking=null;
		try {
			ranking=GestorPartidas.getmGestorPartidas().cargarRankingFacil();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ranking;
	}

	public JSONArray cargarRankingDificil() {
		JSONArray ranking=null;
		try {
			ranking=GestorPartidas.getmGestorPartidas().cargarRankingDificil();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ranking;
	}

	/**
	 * El método se encarga de guardar las partidas de los usuarios, tanto del modo fácil como el difícil
	 * @author Naiara Maneiro
	 * @param pNombre el nombre del usuario que ha jugado la partida
	 * @param pPuntuacion el tiempo que ha durado la partida
	 */

	public void guardarPartida(String pNombre, int pPuntuacion) throws SQLException {
		GestorPartidas.getmGestorPartidas().guardarPartida(pNombre, pPuntuacion);
	}
	/**
	 * Post:El método se encarga de inicializar todas las posiciones del tablero a null
	 * @author Nuria Lebeña
	*/
	public void inicializarTablero() {
		GestorPartidas.getmGestorPartidas().inicializarTablero();
	}

}