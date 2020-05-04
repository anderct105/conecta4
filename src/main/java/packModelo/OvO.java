package packModelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class OvO extends Modo {

    private boolean turno=true;

    public OvO() {
        super("1vs1");

    }

    /**
     * Pre: recibe como parámetro la columna introducida por el jugador
     * Post:devuelve un json que contiene la información de lo sucedido durante la partida
     *
     * @param pColumna la columna en la que se va a introducir la ficha en la jugada
     * @return Json de la forma {haGanadoA : boolean, haGanadoB : boolean, lleno: boolean, posicionesGanadoras {x1:,y1:,x2:,y2:...}} que contiene la
     * información de lo sucedido durante la partida y nulo en caso de que no se haya podido introducir la ficha
     * @author Nuria Lebeña
     */
    public JSONObject jugar(int pColumna) {
        JSONObject json = Tablero.getmTablero().introducirFicha(pColumna, turno);
        if (json != null) {
            Integer fila = (Integer) json.get("fila");
            Integer columna = (Integer) json.get("columna");
            JSONArray ganado = Tablero.getmTablero().haGanado(fila, columna, turno);
            json.put("haGanadoA",false);
            json.put("haGanadoB",false);
            if (ganado == null) {
                cambiarTurno();
            }
            else {

                if (turno) {
                    json.put("haGanadoB", true);
                }
                else {
                    json.put("haGanadoA", true);
                }
                json.put("posicionesGanadoras", ganado);
            }
            return json;
        } else {
            return null;
        }
    }

    /**Cambia el turno al contrario
     * @author Nuria Lebeña
     */
    public void cambiarTurno() {
        turno = !turno;
    }
}