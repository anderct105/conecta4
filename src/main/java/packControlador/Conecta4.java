package packControlador;

import org.json.simple.JSONObject;

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

	/**
	 * 
	 * @param pColumna
	 */
	public JSONObject jugarPartida(int pColumna) {
		// TODO - implement Conecta4.jugarPartida
		throw new UnsupportedOperationException();
	}

	public String getModoJuego() {
		// TODO - implement Conecta4.getModoJuego
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pModo
	 */
	public void setModoJuego(String pModo) {
		// TODO - implement Conecta4.setModoJuego
		throw new UnsupportedOperationException();
	}

	public JSONObject cargarRankingFacil() {
		// TODO - implement Conecta4.cargarRankingFacil
		throw new UnsupportedOperationException();
	}

	public JSONObject cargarRankingDificil() {
		// TODO - implement Conecta4.cargarRankingDificil
		throw new UnsupportedOperationException();
	}

	/**
	 * El método se encarga de guardar las partidas de los usuarios, tanto del modo fácil como el difícil
	 * @author Naiara Maneiro
	 * @param pNombre el nombre del usuario que ha jugado la partida
	 * @param pPuntuacion el tiempo que ha durado la partida
	 */

	public void guardarPartida(String pNombre, int pPuntuacion) {
		GestorPartidas.getmGestorPartidas().guardarPartida(pNombre, pPuntuacion);
	}

	public void inicializarTablero() {
		// TODO - implement Conecta4.inicializarTablero
		throw new UnsupportedOperationException();
	}

}