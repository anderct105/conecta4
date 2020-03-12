package packModelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class Tablero {

	private boolean[][] matriz;
	private static Tablero mTablero;

	private Tablero() {
		// TODO - implement Tablero.Tablero
		throw new UnsupportedOperationException();
	}

	public static Tablero getmTablero() {
		if (mTablero == null) {
			mTablero = new Tablero();
		}
		return mTablero;
	}

	/**
	 * 
	 * @param pColumna
	 * @param pJugador
	 */
	public JSONObject introducirFicha(int pColumna, boolean pJugador) {
		// TODO - implement Tablero.introducirFicha
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pColumna
	 */
	public boolean ocupada(int pColumna) {
		// TODO - implement Tablero.ocupada
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 */
	public JSONObject haGanado(int pX, int pY) {
		// TODO - implement Tablero.haGanado
		throw new UnsupportedOperationException();
	}

	/**
	 * Comprueba si el color de la posición dada es igual a pColor
	 * @param pX
	 * @param pY
	 * @param pColor
	 */
	public boolean esColor(int pX, int pY, boolean pColor) {
		return matriz[pX][pY] == pColor;
	}

	/**
	 * Comprueba si los valores recibidos  están dentro del tablero
	 * @param pX
	 * @param pY
	 */
	public boolean posValida(int pX, int pY) {
		boolean valida = false;
		if (pX >= 0 && pX < matriz.length && pY >= 0 && pY < matriz.length){
			valida = true;
		}
		return valida;
	}

	public void inicializarTablero() {
		// TODO - implement Tablero.inicializarTablero
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 * @param combinacion
	 * @param pCuantos
	 * @param pIteracion
	 * @param pCol
	 */
	public int colFichasBloquear(int pX, int pY, int[] combinacion, int pCuantos, int pIteracion, int pCol) {
		// TODO - implement Tablero.colFichasBloquear
		throw new UnsupportedOperationException();
	}

	public boolean tableroLleno() {
		// TODO - implement Tablero.tableroLleno
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 * @param pColor
	 */
	public int getColindantes(int pX, int pY, boolean pColor) {
		// TODO - implement Tablero.getColindantes
		throw new UnsupportedOperationException();
	}

	public int[] getPosicionesPosibles() {
		// TODO - implement Tablero.getPosicionesPosibles
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param pCont
	 * @param pC
	 * @param pColor
	 */
	public int numSeguidas(int x, int y, int pCont, int[] pC, boolean pColor) {
		// TODO - implement Tablero.numSeguidas
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pColor
	 */
	public Collection<Integer> getOptimo(boolean pColor) {
		// TODO - implement Tablero.getOptimo
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene las posiciones del tablero que hacen 4 en raya.
	 * @author Ander Cejudo
	 * @param pX valor del eje x de la coordenada
	 * @param pY valor del eje y de la coordenada
	 * @param pColor color de la posición a mirar
	 * @return JSON con las posiciones ganadoras y en caso de que no es hayan encontrado, null
	 */
	public JSONArray getCoordenadasGanadoras(int pX, int pY, boolean pColor) {
		Collection<int[]> combinaciones = getPosiblesCombinaciones();
		JSONArray j = new JSONArray();
		ArrayList<int[]> coordenadas = new ArrayList<int[]>();
		boolean obtenidas = false;
		for (int[] c : combinaciones){
			coordenadas = listaSeguidas(pX,pY,1,new ArrayList<int[]>(),c,pColor);
			if (coordenadas.size() != 4) {
				c[0] *= -1;
				c[1] *= -1;
				coordenadas = listaSeguidas(pX, pY, 1, new ArrayList<int[]>(), c, pColor);
			}
			if (coordenadas.size() == 4){
				obtenidas = true;
				break;
			}
		}
		if (obtenidas){
			for (int[] c : coordenadas) {
				JSONObject o = new JSONObject();
				o.put("x",c[0]);
				o.put("y",c[1]);
				j.add(o);
			}
		}
		return j;
	}

	/**
	 * Método recursivo para obtener las coordenadas de hasta 4 fichas seguidas del mismo color, las coordenadas recibidas
	 * se aumentan con los valores de pCombinación en cada llamada recursiva.
	 * @param pX Coordenada del eje X
	 * @param pY Coordenada del eje Y
	 * @param pCont Contador de las veces que se ha llamado al método de forma recursiva
	 * @param pC Lista de las coordenadas del mismo color seguidas
	 * @param pCombinacion Combinación que hace variar las coordenadas pX y pY
	 * @param pColor Color que tienen que tener las fichas en cada coordenada
	 * @return Lista de n coordenadas (donde 0<|n|<5) seguidas del mismo color
	 */
	public ArrayList<int[]> listaSeguidas(int pX, int pY, int pCont, ArrayList<int[]> pC, int[] pCombinacion, boolean pColor) {
		if (posValida(pX,pY) && esColor(pX,pY,pColor) && pCont < 4) {
			int[] coordenada = {pX,pY};
			pC.add(coordenada);
			listaSeguidas(pX+pCombinacion[0],pY+pCombinacion[1],pCont+1,pC,pCombinacion,pColor);
		}
		return pC;
	}

	/**
	 * Obtiene las posibles combinaciones en las que se puede desplazar en el tablero (arriba, abajo, izquierda, derecha y las diagonales).
	 * Hay 8 direcciones en las que se puede mirar, pero con 4 y multiplicando por -1 se obtienen todas.
	 * @author Ander Cejudo
	 * @return lista que cotiene todas las combinaciones posibles para desplazarse por el tablero
	 */
	public Collection<int[]> getPosiblesCombinaciones() {
		ArrayList<int[]> combinaciones = new ArrayList<int[]>();
		int[] i1 = {1,0};
		int[] i2 = {0,1};
		int[] i3 = {1,1};
		int[] i4 = {1,-1};
		combinaciones.add(i1);
		combinaciones.add(i2);
		combinaciones.add(i3);
		combinaciones.add(i4);
		return combinaciones;
	}

}