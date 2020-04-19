package packModelo;

import org.json.simple.JSONObject;

import java.util.Observable;

public class Juego extends Observable {

    private Modo modo;

    public Juego() {
    }

    /**
     * El método obtiene el modo en el que el usuario está jugando
     *
     * @return El modo del juego en modo String
     * @author Naiara Maneiro
     */

    public String getModoJuego() {
        return modo.getNombre();
    }

    /**
     * El método añade el modo que el usuario quiere jugar
     *
     * @param pModo El modo que se le quiere asignar al juego
     * @author Naiara Maneiro
     */
    public void setModoJuego(Modo pModo) {
        this.modo = pModo;
        notify();
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
        return modo.jugar(pColumna);
    }

    /**
     * Post:El método se encarga de inicializar todas las posiciones del tablero a null
     *
     * @author Nuria Lebeña
     */
    public void inicializarTablero() {
        Tablero.getmTablero().inicializarTablero();
    }

}