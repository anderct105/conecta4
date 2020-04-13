package packModelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packControlador.GestorPartidas;

public class OvO implements Modo {

	private boolean turno;

	public OvO() {}

	/**Pre: recibe como parámetro la columna introducida por el jugador
	 * Post:devuelve un json que contiene la información de lo sucedido durante la partida
	 * @author Nuria Lebeña
	 * @param pColumna la columna en la que se va a introducir la ficha en la jugada
	 * @return Json de la forma {x:int, y:int, lleno: boolean, posicionesGanadoras {x1:,y1:,x2:,y2:...}} que contiene la
	 * información de lo sucedido durante la partida y nulo en caso de que no se haya podido introducir la ficha
	 */
	public JSONObject jugar(int pColumna) {

		JSONObject json=Tablero.getmTablero().introducirFicha(pColumna,turno);

		if(json!=null){
			Integer fila= (Integer) json.get("x");
			Integer columna= (Integer) json.get("y");
			JSONArray ganado=Tablero.getmTablero().haGanado(fila,columna,turno);
			if(ganado==null){
				turno=!turno;
			}
			else{
				json.put("posicionesGanadoras",ganado);
			}
			return json;
		}
		else{
			return null;
		}
	}

	public String getNombre() {
		return "OvO";
	}

	public void cambiarTurno() {
		turno=!turno;
	}

}