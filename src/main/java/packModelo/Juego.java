package packModelo;

import org.json.simple.JSONObject;

public class Juego {

	private Modo modo;

	public Juego() {}

	/**
	 * El método obtiene el modo en el que el usuario está jugando
	 * @author Naiara Maneiro
	 * @return El modo del juego en modo String
	 */

	public String getModoJuego() {
		String modoS = "";
		if (modo instanceof OrdenadorD) {
			modoS = "OrdenadorD";
		} else if (modo instanceof OrdenadorF){
			modoS = "OrdenadorF";
		} else {
			modoS = "OvO";
		}
		return modoS;
	}

	/**
	 * El método añade el modo que el usuario quiere jugar
	 * @author Naiara Maneiro
	 * @param pModo El modo que se le quiere asignar al juego
	 */
	public void setModoJuego(Modo pModo) { this.modo = pModo; }

	public void notificar() {
		// TODO - implement Juego.notificar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pColumna
	 */
	public JSONObject jugarPartida(int pColumna) {
		// TODO - implement Juego.jugarPartida
		throw new UnsupportedOperationException();
	}

	public void inicializarTablero() {
		// TODO - implement Juego.inicializarTablero
		throw new UnsupportedOperationException();
	}

}