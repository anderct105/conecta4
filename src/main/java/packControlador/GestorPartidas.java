package packControlador;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packDao.OrdenadorDDAOImpl;
import packDao.OrdenadorFDAOImpl;
import packModelo.Juego;
import packModelo.Modo;

import java.sql.SQLException;
import java.util.Observable;

public class GestorPartidas extends Observable {

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

    /**
     * Pre: recibe como parámetro la columna introducida por el jugador
     * Post:devuelve un json que contiene la información de lo sucedido durante la partida
     *
     * @param pColumna
     * @return Json de la forma {x:int, y:int, lleno: boolean, posicionesGanadoras {x1:,y1:,x2:,y2:...}} que contiene la
     * información de lo sucedido durante la partida y nulo en caso de que no se haya podido introducir la ficha
     * @author Nuria Lebeña
     */
    public JSONObject jugarPartida(int pColumna) {
        return juego.jugarPartida(pColumna);
    }

    public JSONArray cargarRankingFacil() {
        OrdenadorFDAOImpl ordenador = new OrdenadorFDAOImpl();
        try {
            return ordenador.cargarRanking();
        } catch (NullPointerException | SQLException e) {
            System.out.println("No se ha podido conectar a la base de datos");
        }
        return new JSONArray();
    }

    public JSONArray cargarRankingDificil() {
        try {
            OrdenadorDDAOImpl ordenador = new OrdenadorDDAOImpl();
            return ordenador.cargarRanking();
        } catch (NullPointerException | SQLException e) {
            System.out.println("No se ha podido conectar a la base de datos");
        }
        return new JSONArray();
    }

    /**
     * El método se encarga de guardar las partidas de los usuarios, tanto del modo fácil como el difícil
     *
     * @param pNombre     el nombre del usuario que ha jugado la partida
     * @param pPuntuacion el tiempo que ha durado la partida
     * @author Naiara Maneiro
     */

    public void guardarPartida(String pNombre, int pPuntuacion) {
        String modo = juego.getModoJuego();
        if (modo.equals("Ordenador modo Facil")) {
            OrdenadorFDAOImpl of = new OrdenadorFDAOImpl();
            of.create(pNombre, pPuntuacion);
        } else if (modo.equals("Ordenador modo Dificil")) {
            OrdenadorDDAOImpl od = new OrdenadorDDAOImpl();
            od.create(pNombre, pPuntuacion);
        }
    }

    /**
     * Post:El método se encarga de inicializar todas las posiciones del tablero a null
     *
     * @author Nuria Lebeña
     */
    public void inicializarTablero() {
        juego.inicializarTablero();
    }

    /**
     * El método obtiene el modo en el que la aplicación esta configurada para jugar.
     *
     * @return el modo de juego
     * @author Alvaro Dueñas
     */
    public String getModoJuego() {
        return juego.getModoJuego();
    }

    /**
     * El método establece el modo en el que la aplicación estará configurada para jugar.
     *
     * @param pModo el nombre del modo especificado
     * @author Alvaro Dueñas
     */
    public void setModoJuego(Modo pModo) {
        juego.setModoJuego(pModo);
        setChanged();
        notifyObservers();
    }
}