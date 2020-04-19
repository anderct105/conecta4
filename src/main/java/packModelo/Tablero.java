package packModelo;

import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Se encarga de toda la lógica del juego, almacena una matriz con todas las posiciones del tablero y en cada posición una ficha.
 */

public class Tablero {

    /**
     * Atributo estático para el patrón singleton.
     */
    private static Tablero mTablero;
    /**
     * La matriz representa el tablero, es de tipo booleano de modo que true será rojo, false azul y null que la posición está vacía
     */
    private Boolean[][] matriz;

    private Tablero() {

    }

    public static Tablero getmTablero() {
        if (mTablero == null) {
            mTablero = new Tablero();
        }
        return mTablero;
    }

    /**
     * Pre: recibe como parámetro una columna y un jugador
     * Post: introduce la ficha en la columna indicada en caso de que sea posible. Devuelve un json con la siguiente
     * forma: JSON{x:int,y:int, lleno:boolean} en caso de que la ficha se pueda introducir. Un JSON nulo
     * en caso de que la ficha no se pueda introducir.
     *
     * @param pColumna la columna en la que se va a introducir la ficha
     * @param pJugador el color de la ficha
     * @author Nuria Lebeña
     */
    public JSONObject introducirFicha(int pColumna, boolean pJugador) {
        boolean ocupada = true;
        JSONObject json = new JSONObject();
        if (!ocupada(pColumna)) {
            json = anadirFicha(pColumna, pJugador);
            ocupada = false;
        }
        boolean lleno = tableroLleno();

        if (ocupada) {
            return null;
        } else {
            json.put("lleno", lleno);

            return json;
        }
    }

    /**
     * Pre: recibe como parámetro una columna y el indicador de un jugador. Es posible introducir la ficha
     * Post: introduce la ficha en la columna indicada en caso de que sea posible. Devuelve un json con la siguiente
     * forma: JSON{x:int,y:int}
     *
     * @param pColumna la columna en la que se va a introducir la ficha
     * @param pJugador el color de la ficha
     * @author Nuria Lebeña
     */
    private JSONObject anadirFicha(int pColumna, boolean pJugador) {
        int i = 0;
        Boolean pos = matriz[i][pColumna];
        while (pos != null) {
            i++;
            pos = matriz[i][pColumna];
        }
        matriz[i][pColumna] = pJugador;
        JSONObject json = new JSONObject();
        json.put("x", i);
        json.put("y", pColumna);
        return json;
    }

    /** Precondición: recibe como parámetro una columna
     *Postcondición: devuelve un booleano indicando si la columna está llena o no
     * @author Nuria Lebeña
     * @param pColumna entero que hace referencia a la columna
     */
    public boolean ocupada(int pColumna) {
        int i = 0;
        boolean ocupada = true;
        while (i < matriz.length && ocupada) {
            Boolean pos = matriz[i][pColumna];
            if (pos == null) {
                ocupada = false;
            }
            i = i + 1;

        }
        return ocupada;
    }

    /**
     * Precondición:recibe como parámetro las coordenadas de la última ficha introducida y su color
     * PostCondicion: si el jugador ha ganado devuelve las posiciones en las que están las fichas que forman 4 en raya
     *
     * @param pFila:    posición x de la última ficha introducida
     * @param pColumna: posición y de la última ficha introducida
     * @param pColor:   color de la ficha
     * @return JSONObject de la forma ..... si el jugador ha ganado, null si no ha ganado
     */
    public JSONArray haGanado(int pFila, int pColumna, boolean pColor) {
        int colindantes = getColindantes(pFila, pColumna, pColor);
        JSONArray json = new JSONArray();
        if (colindantes == 3) {
            json = getCoordenadasGanadoras(pFila, pColumna, pColor);
        } else {
            json = null;
        }
        return json;
    }

    /**
     * Comprueba si el color de la posición dada es igual a pColor.
     *
     * @param pFila    Fila del tablero.
     * @param pColumna Columna del tablero.
     * @param pColor   Color de la ficha.
     * @return Si pColor es igual al color de la ficha de la posición dada.
     * @author Ander Cejudo
     */
    public boolean esColor(int pFila, int pColumna, boolean pColor) {
        if (posValida(pFila, pColumna) && matriz[pFila][pColumna] != null) {
            return matriz[pFila][pColumna] == pColor;
        }
        return false;
    }

    /**
     * Comprueba si los valores recibidos  están dentro del tablero.
     *
     * @param pFila    Fila del tablero.
     * @param pColumna columna del tablero.
     * @author Ander Cejudo
     */
    public boolean posValida(int pFila, int pColumna) {
        boolean valida = false;
        if (pFila >= 0 && pFila < matriz.length && pColumna >= 0 && pColumna < matriz[0].length) {
            valida = true;
        }
        return valida;
    }

    /**
     * Post:El método se encarga de inicializar todas las posiciones del tablero a null
     *
     * @author Nuria Lebeña
     */
    public void inicializarTablero() {
        matriz = new Boolean[6][9];

        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[0].length; y++) {
                matriz[x][y] = null;
            }
        }
    }

    public void imprimirTablero() {
        System.out.println("\n");
        for (int x = matriz.length - 1; x >= 0; x--) {
            for (int y = 0; y < matriz[0].length; y++) {
                Boolean contenido = matriz[x][y];
                if (contenido == null || contenido) {
                    System.out.print(contenido + "   ");
                } else {
                    System.out.print(contenido + "  ");
                }


            }
            System.out.println("\n");
        }
    }

    /**
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
        boolean lleno = true;
        for (int i = 0; i < matriz[1].length; i++) {
            Boolean pos = matriz[matriz.length - 1][i];
            if (pos == null) {
                lleno = false;
                return lleno;
            }
        }
        return lleno;
    }

    /**
     * @param pFila
     * @param pColumna
     * @param pColor
     */
    public int getColindantes(int pFila, int pColumna, boolean pColor) {
        int max = 0;
        Collection<int[]> combinaciones = getPosiblesCombinaciones();
        for (int[] comb : combinaciones) {
            int[] complementario = new int[2];
            complementario[0] = comb[0] * -1;
            complementario[1] = comb[1] * -1;
            int pos = numSeguidas(pFila, pColumna, comb, pColor);
            int neg = numSeguidas(pFila, pColumna, complementario, pColor);
            int colindantes = pos + neg;
            if (colindantes > max) {
                max = colindantes;
            }
        }
        if(max>3){
            max = 3;
        }
        return max;
    }

    public int[] getPosicionesPosibles() {
        int[] posiciones = new int[matriz[0].length];
        for(int col = 0;col<matriz[0].length;col++){
            posiciones[col] = -1;
            for(int fila = 0; fila<matriz.length;fila++){
                if (matriz[fila][col] == null){
                    posiciones[col] = fila;
                    break;
                }
            }
        }
        return posiciones;
    }

    /**
     * Indica el numero de fichas seguidas de un color que hay desde una determinada casilla en la dirección indicada
     *
     * @param pFila La fila de la casilla que se quiere comprobar
     * @param pColumna La columna de la casilla que se quiere comprobar
     * @param pC La dirección en la que se mirará la cantidad de fichas seguidas
     * @param pColor El color de la ficha a mirar
     * @author Igor garcía
     */
    public int numSeguidas(int pFila, int pColumna, int[] pC, boolean pColor) {
        int seguidas = 0;
        bucleSeguidas:
        while(true){
            pFila = pFila + pC[0];
            pColumna = pColumna + pC[1];
            if(posValida(pFila,pColumna)){
                if(esColor(pFila,pColumna,pColor)){
                    seguidas++;
                }
                else{
                    break bucleSeguidas;
                }
            }
            else{
                break bucleSeguidas;
            }
        }
        return seguidas;
    }

    /**
     * Indica la columna en la que se debe introducir la ficha de un determinado color para obtener el merjor resultado
     *
     * @param pColor True = rojo, False = azul
     * @return Array de dos posiciones: 1-La columna en la que se debe meter 2-La cantidad de fichas colindantes de ese color que hay
     * @author Igor García
     */
    public Pair<Integer,Integer> getOptimo(boolean pColor) {
        int[] posiciones = getPosicionesPosibles();
        int colindantesMax = 0;
        Pair<Integer,Integer> optimo = null;
        for(int i = 0;i<posiciones.length;i++){
            if(posiciones[i] != -1){
                int colindantes = getColindantes(posiciones[i], i, pColor);
                //SI LA ACTUAL ES MEJOR QUE LA MAXIMA, SE PONE LA ACTUAL COMO MAXIMA
                if(colindantes > colindantesMax){
                    colindantesMax = colindantes;
                    optimo = new Pair<>(i,colindantes);
                }
                //SI LA ACTUAL ES IGUAL QUE LA MAXIMA, HAY UN 33% DE POSIBILIDADES DE QUE SE PONGA COMO MAXIMA LA ACTUAL Y UN 66% DE QUE SE MANTENGA LA QUE YA ESTABA
                if(colindantes == colindantesMax){
                    if(optimo == null){
                        optimo = new Pair<>(i,colindantes);
                    }
                    int random = ThreadLocalRandom.current().nextInt(0, 101);
                    if(random < 33){
                        optimo = new Pair<>(i,colindantes);
                    }
                }
            }

        }
        return optimo;
    }

    /**
     * Obtiene las posiciones del tablero que hacen 4 en raya.
     *
     * @param pFila    valor del eje x de la coordenada.
     * @param pColumna valor del eje y de la coordenada.
     * @param pColor   color de la posición a mirar.
     * @return JSON con las posiciones ganadoras y en caso de que no es hayan encontrado, null.
     * @author Ander Cejudo
     */
    public JSONArray getCoordenadasGanadoras(int pFila, int pColumna, boolean pColor) {
        Collection<int[]> combinaciones = getPosiblesCombinaciones();
        JSONArray j = new JSONArray();
        ArrayList<int[]> coordenadas = new ArrayList<int[]>(), coordenadas_inversas = new ArrayList<int[]>();
        boolean obtenidas = false;
        for (int[] c : combinaciones) {
            coordenadas = listaSeguidas(pFila, pColumna, 0, new ArrayList<int[]>(), c, pColor);
            if (coordenadas.size() != 4) {
                c[0] *= -1;
                c[1] *= -1;
                coordenadas_inversas = listaSeguidas(pFila, pColumna, 0, new ArrayList<int[]>(), c, pColor);
                if (coordenadas_inversas.size() > 0) {
                    coordenadas_inversas.remove(0);
                }
            }
            coordenadas.addAll(coordenadas_inversas);
            if (coordenadas.size() == 4) {
                obtenidas = true;
                break;
            }
        }
        if (obtenidas) {
            for (int[] c : coordenadas) {
                JSONObject o = new JSONObject();
                o.put("x", c[0]);
                o.put("y", c[1]);
                j.add(o);
            }
        }
        return j;
    }

    /**
     * Método recursivo para obtener las coordenadas de hasta 4 fichas seguidas del mismo color, las coordenadas recibidas
     * se aumentan con los valores de pCombinación en cada llamada recursiva.
     *
     * @param pFila        Coordenada del eje X.
     * @param pColumna     Coordenada del eje Y.
     * @param pCont        Contador de las veces que se ha llamado al método de forma recursiva.
     * @param pC           Lista de las coordenadas del mismo color seguidas.
     * @param pCombinacion Combinación que hace variar las coordenadas pFila y pColumna.
     * @param pColor       Color que tienen que tener las fichas en cada coordenada.
     * @return Lista de n coordenadas (donde 0<|n|<5) seguidas del mismo color.
     * @author Ander Cejudo
     */
    public ArrayList<int[]> listaSeguidas(int pFila, int pColumna, int pCont, ArrayList<int[]> pC, int[] pCombinacion, boolean pColor) {
        if (posValida(pFila, pColumna) && esColor(pFila, pColumna, pColor) && pCont < 4) {
            int[] coordenada = {pFila, pColumna};
            pC.add(coordenada);
            listaSeguidas(pFila + pCombinacion[0], pColumna + pCombinacion[1], pCont + 1, pC, pCombinacion, pColor);
        }
        return pC;
    }

    /**
     * Obtiene las posibles combinaciones en las que se puede desplazar en el tablero (arriba, abajo, izquierda, derecha y las diagonales).
     * Hay 8 direcciones en las que se puede mirar, pero con 4 y multiplicando por -1 se obtienen todas.
     *
     * @return lista que cotiene todas las combinaciones posibles para desplazarse por el tablero.
     * @author Ander Cejudo
     */
    public Collection<int[]> getPosiblesCombinaciones() {
        ArrayList<int[]> combinaciones = new ArrayList<int[]>();
        int[] i1 = {1, 0};
        int[] i2 = {0, 1};
        int[] i3 = {1, 1};
        int[] i4 = {1, -1};
        combinaciones.add(i1);
        combinaciones.add(i2);
        combinaciones.add(i3);
        combinaciones.add(i4);
        return combinaciones;
    }

}