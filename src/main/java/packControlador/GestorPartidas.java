package packControlador;

import org.json.simple.JSONObject;
import packDao.OrdenadorDDAOImpl;
import packDao.OrdenadorFDAOImpl;
import packModelo.*;

import java.sql.SQLException;
import java.sql.Timestamp;

public class GestorPartidas {

	private static GestorPartidas mGestorPartidas;
	private Juego juego;

	private void GestorPartidas() {
		// TODO - implement GestorPartidas.Conecta4
		throw new UnsupportedOperationException();
	}

	public static GestorPartidas getmGestorPartidas() {
		if (mGestorPartidas == null) {
			mGestorPartidas = new GestorPartidas();
		}
		return mGestorPartidas;
	}

	/**
	 * 
	 * @param pColumna
	 */
	public JSONObject jugarPartida(int pColumna) {
		// TODO - implement GestorPartidas.jugarPartida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pModo
	 */
	public void setModoJuego(Modo pModo) {
		juego.setModoJuego(pModo);
	}

	public JSONObject cargarRankingFacil() {
		// TODO - implement GestorPartidas.cargarRankingFacil
		throw new UnsupportedOperationException();
	}

	public JSONObject cargarRankingDificil() {
		// TODO - implement GestorPartidas.cargarRankingDificil
		throw new UnsupportedOperationException();
	}

	/**
	 * El método se encarga de guardar las partidas de los usuarios, tanto del modo fácil como el difícil
	 * @author Naiara Maneiro
	 * @param pNombre el nombre del usuario que ha jugado la partida
	 * @param pPuntuacion el tiempo que ha durado la partida
	 */

	public void guardarPartida(String pNombre, int pPuntuacion) throws SQLException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String modo = juego.getModoJuego();
		if (modo == "OrdenadorF"){
			OrdenadorFDAOImpl of = new OrdenadorFDAOImpl();
			of.create(timestamp, pNombre, pPuntuacion);
		} else if (modo == "OrdenadorD") {
			OrdenadorDDAOImpl od = new OrdenadorDDAOImpl();
			od.create(timestamp, pNombre, pPuntuacion);
		}
	}

	public void inicializarTablero() {
		// TODO - implement GestorPartidas.inicializarTablero
		throw new UnsupportedOperationException();
	}

}