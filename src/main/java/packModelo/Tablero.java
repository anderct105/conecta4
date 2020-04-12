package packModelo;

import org.json.simple.JSONObject;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Collection;

public class Tablero {

	private Boolean[][] matriz;
	private static Tablero mTablero;

	private Tablero() {

	}

	public static Tablero getmTablero() {
		if (mTablero == null) {
			mTablero = new Tablero();
		}
		return mTablero;
	}

	/**Pre: recibe como parámetro una columna y un jugador
	 * Post: introduce la ficha en la columna indicada en caso de que sea posible. Devuelve un json con la siguiente
	 * forma: JSON{x:int,y:int, lleno:boolean} en caso de que la ficha se pueda introducir. Un JSON nulo
	 *  en caso de que la ficha no se pueda introducir.
	 * @author Nuria Lebeña
	 * @param pColumna la columna en la que se va a introducir la ficha
	 * @param pJugador el color de la ficha
	 */
	public JSONObject introducirFicha(int pColumna, boolean pJugador) {
		boolean ocupada=true;
		JSONObject json=new JSONObject();
		if(!ocupada(pColumna)){
			json=anadirFicha(pColumna,pJugador);
			ocupada=false;
		}
		boolean lleno=tableroLleno();


		if(ocupada){
			return null;
		}
		else{
			json.put("lleno",lleno);

			return json;
		}



	}
	/**Pre: recibe como parámetro una columna y el indicador de un jugador. Es posible introducir la ficha
	 * Post: introduce la ficha en la columna indicada en caso de que sea posible. Devuelve un json con la siguiente
	 * forma: JSON{x:int,y:int}
	 * @author Nuria Lebeña
	 * @param pColumna la columna en la que se va a introducir la ficha
	 * @param pJugador el color de la ficha
	 */
	private JSONObject anadirFicha(int pColumna, boolean pJugador) {
		int i=0;
		Boolean pos=matriz[i][pColumna];
		while(pos!=null){
			i++;
			pos=matriz[i][pColumna];

		}
		matriz[i][pColumna]=pJugador;
		JSONObject json=new JSONObject();
		json.put("fila",i);
		json.put("columna",pColumna);
		return json;
	}

	/** Precondición: recibe como parámetro una columna
	 *Postcondición: devuelve un booleano indicando si la columna está llena o no
	 * @author Nuria Lebeña
	 * @param pColumna entero que hace referencia a la columna
	 */
	public boolean ocupada(int pColumna) {
		int i=0;
		boolean ocupada=true;
		while(i<matriz.length && ocupada){
			Boolean pos=matriz[i][pColumna];
			if(pos==null){
				ocupada=false;
			}
			i=i+1;

		}
		return ocupada;
	}

	/**Precondición:recibe como parámetro las coordenadas de la última ficha introducida y su color
	 * PostCondicion: si el jugador ha ganado devuelve las posiciones en las que están las fichas que forman 4 en raya
	 * @param pFila: posición x de la última ficha introducida
	 * @param pColumna: posición y de la última ficha introducida
	 * @param pColor: color de la ficha
	 * @return JSONObject de la forma ..... si el jugador ha ganado, null si no ha ganado
	 */
	public JSONObject haGanado(int pFila, int pColumna,boolean pColor) {
		int colindantes=getColindantes(pFila,pColumna,pColor);
		boolean ganado=false;
		JSONObject json=new JSONObject();
		if (colindantes==3){
			ganado=true;
			json=getCoordenadasGanadoras(pFila,pColumna,pColor);
		}
		return json;
	}

	/**
	 * 
	 * @param pFila
	 *@param pColumna
	 * @param pColor
	 */
	public boolean esColor(int pFila, int pColumna, boolean pColor) {
		// TODO - implement Tablero.esColor
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pFila
	 *@param pColumna
	 */
	public boolean posValida(int pFila, int pColumna) {
		// TODO - implement Tablero.posValida
		throw new UnsupportedOperationException();
	}
	/**
	 * Post:El método se encarga de inicializar todas las posiciones del tablero a null
	 * @author Nuria Lebeña
	 */
	public void inicializarTablero() {
		matriz =new Boolean[6][9];

		for (int x=0; x<matriz.length;x++){
			for(int y=0;y<matriz[0].length;y++){
				matriz[x][y]= null;
			}
		}
	}

	public void imprimirTablero(){
		System.out.println("\n");
		for (int x=matriz.length-1; x>=0;x--){
			for(int y=0;y<matriz[0].length;y++){
				Boolean contenido=matriz[x][y];
				if (contenido==null|| contenido){
					System.out.print(contenido+"   ");
				}
				else{
					System.out.print(contenido+"  ");
				}


			}
			System.out.println("\n");
		}
	}
	/**
	 * 
	 * @param pFila
	 * @param pColumna
	 * @param combinacion
	 * @param pCuantos
	 * @param pIteracion
	 * @param pCol
	 */
	public int colFichasBloquear(int pFila, int pColumna, int[] combinacion, int pCuantos, int pIteracion, int pCol) {
		// TODO - implement Tablero.colFichasBloquear
		throw new UnsupportedOperationException();
	}

	public boolean tableroLleno() {
		boolean lleno=true;
		for(int i=0;i<matriz[1].length;i++){
			Boolean pos=matriz[matriz.length-1][i];
			if(pos==null){
				lleno=false;
				return lleno;
			}
		}
		return lleno;
	}

	/**
	 * 
	 * @param pFila
	 * @param pColumna
	 * @param pColor
	 */
	public int getColindantes(int pFila, int pColumna, boolean pColor) {
		// TODO - implement Tablero.getColindantes
		throw new UnsupportedOperationException();
	}

	public int[] getPosicionesPosibles() {
		// TODO - implement Tablero.getPosicionesPosibles
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pFila
	 * @param pColumna
	 * @param pCont
	 * @param pC
	 * @param pColor
	 */
	public int numSeguidas(int pFila, int pColumna, int pCont, int[] pC, boolean pColor) {
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
	 * @param pFila
	 *@param pColumna
	 * @param pColor
	 */
	public JSONObject getCoordenadasGanadoras(int pFila, int pColumna, boolean pColor) {
		// TODO - implement Tablero.getCoordenadasGanadoras
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pFila
	 * @param pColumna
	 * @param pCont
	 * @param pC
	 * @param pCombinacion
	 * @param pColor
	 */
	public Collection<int[]> listaSeguidas(int pFila, int pColumna, int pCont, Collection<int[]> pC, int[] pCombinacion, boolean pColor) {
		// TODO - implement Tablero.listaSeguidas
		throw new UnsupportedOperationException();
	}

	public Collection<Collection<Integer>> getPosiblesCombinaciones() {
		// TODO - implement Tablero.getPosiblesCombinaciones
		throw new UnsupportedOperationException();
	}

}