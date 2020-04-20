package packControlador;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packDao.OrdenadorDDAOImpl;
import packDao.OrdenadorFDAOImpl;
import packModelo.*;

import java.sql.SQLException;
import java.sql.Timestamp;

public class GestorPartidas {

	private static GestorPartidas mGestorPartidas;
	private Juego juego;

	private GestorPartidas() {
		juego = new Juego();
	}

	public static GestorPartidas getmGestorPartidas() {
		if (mGestorPartidas == null) {
			mGestorPartidas = new GestorPartidas();
		}
		return mGestorPartidas;
	}

	/**Pre: recibe como parámetro la columna introducida por el jugador
	 * Post:devuelve un json que contiene la información de lo sucedido durante la partida
	 * @author Nuria Lebeña
	 * @param pColumna
	 * @return Json de la forma {x:int, y:int, lleno: boolean, posicionesGanadoras {x1:,y1:,x2:,y2:...}} que contiene la
	 * información de lo sucedido durante la partida y nulo en caso de que no se haya podido introducir la ficha
	 */
	public JSONObject jugarPartida(int pColumna) {
		return juego.jugarPartida(pColumna);
	}

	/**
	 * El método establece el modo en el que la aplicación estará configurada para jugar.
	 * @author Alvaro Dueñas
	 * @param pModo el nombre del modo especificado
	 */
	public void setModoJuego(Modo pModo) {
		juego.setModoJuego(pModo);
	}

	public JSONArray cargarRankingFacil() throws SQLException {

		OrdenadorFDAOImpl ordenador=new OrdenadorFDAOImpl();
		return ordenador.cargarRanking();
	}

	public JSONArray cargarRankingDificil() throws SQLException {
		OrdenadorDDAOImpl ordenador=new OrdenadorDDAOImpl();
		return ordenador.cargarRanking();
	}

	/**
	 * El método se encarga de guardar las partidas de los usuarios, tanto del modo fácil como el difícil
	 * @author Naiara Maneiro
	 * @param pNombre el nombre del usuario que ha jugado la partida
	 * @param pPuntuacion el tiempo que ha durado la partida
	 */

	public void guardarPartida(String pNombre, int pPuntuacion) {
		String modo = juego.getModoJuego();
		if (modo.equals("Ordenador modo Facil")){
			OrdenadorFDAOImpl of = new OrdenadorFDAOImpl();
			of.create(pNombre, pPuntuacion);
		} else if (modo.equals("Ordenador modo Dificil")) {
			OrdenadorDDAOImpl od = new OrdenadorDDAOImpl();
			od.create(pNombre, pPuntuacion);
		}
	}
	/**
	 * Post:El método se encarga de inicializar todas las posiciones del tablero a null
	 * @author Nuria Lebeña
	 */
	public void inicializarTablero() {
		juego.inicializarTablero();
	}

	/**
	 * El método obtiene el modo en el que la aplicación esta configurada para jugar.
	 * @author Alvaro Dueñas
	 * @return el modo de juego
	 */
	public String getModoJuego() {
		return juego.getModoJuego();
	}
}