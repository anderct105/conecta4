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

	/**Pre: recibe como parámetro la columna introducida por el jugador
	 * Post:devuelve un json que contiene la información de lo sucedido durante la partida
	 * @author Nuria Lebeña
	 * @param pColumna la columna en la que se va a introducir la ficha en la jugada
	 * @return Json de la forma {x:int, y:int, lleno: boolean, posicionesGanadoras {x1:,y1:,x2:,y2:...}} que contiene la
	 * información de lo sucedido durante la partida y nulo en caso de que no se haya podido introducir la ficha
	 */
	public JSONObject jugarPartida(int pColumna) {
		return modo.jugar(pColumna);
	}
	/**
	 * Post:El método se encarga de inicializar todas las posiciones del tablero a null
	 * @author Nuria Lebeña
	 */
	public void inicializarTablero() {
		Tablero.getmTablero().inicializarTablero();
	}

}