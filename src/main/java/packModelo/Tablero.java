package packModelo;

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
	 * 
	 * @param pX
	 * @param pY
	 * @param pColor
	 */
	public boolean esColor(int pX, int pY, boolean pColor) {
		// TODO - implement Tablero.esColor
		throw new UnsupportedOperationException();
	}

	/**
	 * 
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
	 * 
	 * @param pX
	 * @param pY
	 * @param pColor
	 */
	public JSONObject getCoordenadasGanadoras(int pX, int pY, boolean pColor) {
		Collection<int[]> combinaciones = getPosiblesCombinaciones();
		for (int[] c : combinaciones){
			listaSeguidas(pX,pY,1,combinaciones,c,pColor);
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 * @param pCont
	 * @param pC
	 * @param pCombinacion
	 * @param pColor
	 */
	public Collection<int[]> listaSeguidas(int pX, int pY, int pCont, Collection<int[]> pC, int[] pCombinacion, boolean pColor) {
		// TODO - implement Tablero.listaSeguidas
		throw new UnsupportedOperationException();
	}

	/**
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