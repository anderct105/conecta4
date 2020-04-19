package packModelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packControlador.GestorPartidas;

public class OvO implements Modo {

	private boolean turno;

	public OvO() {
	}

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
			Integer x= (Integer) json.get("x");
			Integer y= (Integer) json.get("y");
			JSONArray ganado=Tablero.getmTablero().haGanado(x,y,turno);
			if(ganado==null){
				cambiarTurno();
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
	/*Devuelve un String que contiene el nombre del modo de juego
	 * @author Nuria Lebeña
	 * @return String que contiene el nombre del modo de juego
	 */

	public String getNombre() {
		return "OvO";
	}

	/*Cambia el turno al contrario
	 * @author Nuria Lebeña
	*/
	public void cambiarTurno() {
		turno=!turno;
	}

}