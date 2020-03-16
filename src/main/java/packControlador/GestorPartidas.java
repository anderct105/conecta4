package packControlador;

import org.json.simple.JSONObject;
import packDao.ConnectionManager;
import packModelo.*;

import java.sql.ResultSet;
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

	public void guardarPartida(String pNombre, int pPuntuacion) {
		ConnectionManager conexion = new ConnectionManager();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		conexion.execSQL("INSERT INTO Partida (nombre, tiempo, fechaHora) VALUES ('"+pNombre+"', "+pPuntuacion+", "+timestamp);
		ResultSet id = conexion.execSQL("SELECT id FROM Partida WHERE fechaHora="+timestamp);
		String modo = juego.getModoJuego();
		if (modo == "OrdenadorF"){
			conexion.execSQL("INSERT INTO OrdenadorFacil id VALUES "+id);
		} else if (modo == "OrdenadorD") {
			conexion.execSQL("INSERT INTO OrdenadorDificil id VALUES " + id);
		}
	}

	public void inicializarTablero() {
		// TODO - implement GestorPartidas.inicializarTablero
		throw new UnsupportedOperationException();
	}

}