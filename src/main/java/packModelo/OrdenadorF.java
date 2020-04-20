package packModelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Random;

public class OrdenadorF extends Modo {

    public OrdenadorF() {
        super("Ordenador modo Facil");
    }

    /**
     * Pre: recibe como parámetro la columna introducida por el jugador
     * Post: devuelve un json que contiene la información de lo sucedido durante la partida
     *
     * @param pColumna la columna en la que se va a introducir la ficha en la jugada
     * @return Json de la forma {x:int, y:int, lleno: boolean, posicionesGanadoras {x1:,y1:,x2:,y2:...}} que contiene la
     * información de lo sucedido durante la partida y nulo en caso de que no se haya podido introducir la ficha
     * @author Naiara Maneiro
     */
    public JSONObject jugar(int pColumna) {
        boolean turno = false;
        JSONObject json = Tablero.getmTablero().introducirFicha(pColumna, turno);
        if (json != null) {
            Integer filaA = (Integer) json.get("fila");
            Integer columnaA = (Integer) json.get("columna");
            JSONArray ganadoA = Tablero.getmTablero().haGanado(filaA, columnaA, turno);
            boolean lleno = (boolean) json.get("lleno");
            if (ganadoA == null && !lleno) {
                turno = !turno;
                boolean enc = false;
                Random r = new Random();
                while (!enc) {
                    int col = r.nextInt(9);
                    boolean ocupada = Tablero.getmTablero().ocupada(col);
                    if (!ocupada) {
                        json = Tablero.getmTablero().introducirFicha(col, turno);
                        enc = true;
                        Integer filaB = (Integer) json.get("fila");
                        Integer columnaB = (Integer) json.get("columna");
                        JSONArray ganadoB = Tablero.getmTablero().haGanado(filaB, columnaB, turno);
                        if (ganadoB != null) {
                            json.put("posicionesGanadoras", ganadoB);
                            json.put("haGanadoA", false);
                            json.put("haGanadoB", true);
                        } else {
                            json.put("haGanadoA", false);
                            json.put("haGanadoB", false);
                        }
                    }
                }
            } else {
                json.put("posicionesGanadoras", ganadoA);
                json.put("haGanadoA", true);
                json.put("haGanadoB", false);
            }
            json.remove("fila");
            json.remove("columna");
            return json;
        } else {
            return null;
        }
    }
}