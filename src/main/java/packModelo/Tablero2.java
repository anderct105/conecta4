package packModelo;

public class Tablero2 {

	private boolean[][] matriz;
	private static Tablero mTablero;

	private void Tablero() {
		// TODO - implement Tablero2.Tablero
		throw new UnsupportedOperationException();
	}

	public static Tablero getmTablero() {
		return this.mTablero;
	}

	/**
	 * 
	 * @param pColumna
	 * @param pJugador
	 */
	public JSON introducirFichar(int pColumna, boolean pJugador) {
		// TODO - implement Tablero2.introducirFichar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pColumna
	 */
	public boolean ocupada(int pColumna) {
		// TODO - implement Tablero2.ocupada
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 */
	public boolean haGanado(int pX, int pY) {
		// TODO - implement Tablero2.haGanado
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pX
	 * @param pColor
	 */
	public boolean esColor(int pX, boolean pColor) {
		// TODO - implement Tablero2.esColor
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 * @param pColor
	 * @param pIteracion
	 */
	public boolean esColor(int pX, int pY, boolean pColor, int pIteracion) {
		// TODO - implement Tablero2.esColor
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 */
	public boolean posValida(int pX, int pY) {
		// TODO - implement Tablero2.posValida
		throw new UnsupportedOperationException();
	}

	public int getColumnaBloquear() {
		// TODO - implement Tablero2.getColumnaBloquear
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 * @param pN
	 */
	public int getColumnaNEnRaya(int pX, int pY, int pN) {
		// TODO - implement Tablero2.getColumnaNEnRaya
		throw new UnsupportedOperationException();
	}

	public void inicializarTablero() {
		// TODO - implement Tablero2.inicializarTablero
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
		// TODO - implement Tablero2.colFichasBloquear
		throw new UnsupportedOperationException();
	}

	public boolean tableroLleno() {
		// TODO - implement Tablero2.tableroLleno
		throw new UnsupportedOperationException();
	}

	public Collection<int[]> getPosiblesCombinaciones() {
		// TODO - implement Tablero2.getPosiblesCombinaciones
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 * @param pColor
	 */
	public void getColindantes(int pX, int pY, int pColor) {
		// TODO - implement Tablero2.getColindantes
		throw new UnsupportedOperationException();
	}

}