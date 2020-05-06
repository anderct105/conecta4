package packControlador;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packModelo.Modo;

public class Conecta4 {

	private static Conecta4 mConecta4;

	private Conecta4() {
	}

	public static Conecta4 getmConecta4() {
		if (mConecta4 == null) {
			mConecta4 = new Conecta4();
		}
		return mConecta4;
	}

	/**
	 * Pre: recibe como parámetro la columna introducida por el jugador
	 * Post:devuelve un json que contiene la información de lo sucedido durante la partida
	 *
	 * @param pColumna la columna en la que se va a introducir la ficha en la jugada
	 * @return Json de la forma {x:int, y:int, lleno: boolean, posicionesGanadoras {x1:,y1:,x2:,y2:...}} que contiene la
	 * información de lo sucedido durante la partida y nulo en caso de que no se haya podido introducir la ficha
	 * @author Nuria Lebeña
	 */
	public JSONObject jugarPartida(int pColumna) {
		return GestorPartidas.getmGestorPartidas().jugarPartida(pColumna);
	}

	/**
	 * El método obtiene el modo en el que la aplicación esta configurada para jugar.
	 *
	 * @return El nombre del modo de juego
	 * @author Alvaro Dueñas
	 */
	public String getModoJuego() {
		return GestorPartidas.getmGestorPartidas().getModoJuego();
	}

	/**
	 * El método establece el modo en el que la aplicación estará configurada para jugar.
	 *
	 * @param pModo el nombre del modo especificado
	 * @author Alvaro Dueñas
	 */
	public void setModoJuego(String pModo) {
		Modo modo = ModoFactory.getmModoFactory().createModo(pModo);
		GestorPartidas.getmGestorPartidas().setModoJuego(modo);
	}

	public JSONArray cargarRankingFacil() {
		return GestorPartidas.getmGestorPartidas().cargarRankingFacil();
	}

	public JSONArray cargarRankingDificil() {
		return GestorPartidas.getmGestorPartidas().cargarRankingDificil();
	}

	/**
	 * El método se encarga de guardar las partidas de los usuarios, tanto del modo fácil como el difícil
	 *
	 * @param pNombre     el nombre del usuario que ha jugado la partida
	 * @param pPuntuacion el tiempo que ha durado la partida
	 * @author Naiara Maneiro
	 */

	public void guardarPartida(String pNombre, int pPuntuacion) {
		GestorPartidas.getmGestorPartidas().guardarPartida(pNombre, pPuntuacion);
	}

	/**
	 * Post:El método se encarga de inicializar todas las posiciones del tablero a null
	 *
	 * @author Nuria Lebeña
	 */
	public void inicializarTablero() {
		GestorPartidas.getmGestorPartidas().inicializarTablero();
	}
}