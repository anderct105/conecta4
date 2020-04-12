package packModelo;

import javafx.scene.control.Tab;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class TableroTest {

    @Before
    public void setUp() throws Exception {
        Tablero.getmTablero().inicializarTablero();
        Tablero.getmTablero().imprimirTablero();
    }

    @After
    public void tearDown() throws Exception {
        Tablero.getmTablero().inicializarTablero();
    }


    @Test
    public void introducirFicha() {

    }

    @Test
    public void ocupada() {
    }

    @Test
    public void haGanado() {
    }

    @Test
    public void esColor() {
        Tablero.getmTablero().introducirFicha(0,true);
        assertTrue(Tablero.getmTablero().esColor(0,0,true));
        assertFalse(Tablero.getmTablero().esColor(1,0,true));
        Tablero.getmTablero().introducirFicha(0,false);
        assertFalse(Tablero.getmTablero().esColor(1,0,true));
        assertFalse(Tablero.getmTablero().esColor(2,0,true));
    }

    @Test
    public void posValida() {
        assertTrue(Tablero.getmTablero().posValida(0,0));
        assertTrue(Tablero.getmTablero().posValida(5,8));
        assertFalse(Tablero.getmTablero().posValida(5,9));
        assertFalse(Tablero.getmTablero().posValida(-1,1));
        assertFalse(Tablero.getmTablero().posValida(6,1));
        assertFalse(Tablero.getmTablero().posValida(5,11));
        assertFalse(Tablero.getmTablero().posValida(5,-1));
    }

    @Test
    public void colFichasBloquear() {
    }

    @Test
    public void tableroLleno() {
    }

    @Test
    public void getColindantes() {

    }

    @Test
    public void getPosicionesPosibles() {
    }

    @Test
    public void numSeguidas() {
    }

    @Test
    public void getOptimo() {
    }

    @Test
    public void getCoordenadasGanadoras() {
        // columna
        checkCoordenadasGanadoras(0, 1, false);
        // fila
        checkCoordenadasGanadoras(1, 1, false);
        // diagonal creciente
        checkCoordenadasGanadoras(1, 1, true);
        // diagonal decreciente
        checkCoordenadasGanadoras(-1, 4, true);
        //PRUEBA 1
        Tablero.getmTablero().inicializarTablero();
        Tablero.getmTablero().introducirFicha(0,true);
        Tablero.getmTablero().introducirFicha(0,true);
        Tablero.getmTablero().introducirFicha(0,true);
        assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0,0,true).size(),0);
        Tablero.getmTablero().introducirFicha(0,true);
        Tablero.getmTablero().imprimirTablero();
        assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0,0,true).size(),4);
        assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(2,0,true).size(),4);
        assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(3,0,true).size(),4);
        //PRUEBA2
        Tablero.getmTablero().inicializarTablero();
        Tablero.getmTablero().introducirFicha(0,true);
        Tablero.getmTablero().introducirFicha(1,true);
        Tablero.getmTablero().introducirFicha(1,true);
        Tablero.getmTablero().introducirFicha(2,true);
        Tablero.getmTablero().introducirFicha(2,true);
        Tablero.getmTablero().introducirFicha(2,true);
        Tablero.getmTablero().introducirFicha(3,true);
        Tablero.getmTablero().introducirFicha(3,true);
        Tablero.getmTablero().introducirFicha(3,true);
        Tablero.getmTablero().introducirFicha(3,true);
        Tablero.getmTablero().imprimirTablero();
        assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0,0,true).size(),4);
        Tablero.getmTablero().introducirFicha(0,true);
        Tablero.getmTablero().imprimirTablero();
        assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0,0,true).size(),4);
        assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(2,3,true).size(),4);
        assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0,3,true).size(),4);
        assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0,2,true).size(),4);

    }

    private void checkCoordenadasGanadoras(int cambioColumnas, int inicio, boolean diagonal) {
        Tablero tablero = Tablero.getmTablero();
        tablero.inicializarTablero();
        ArrayList<JSONObject> reales = new ArrayList<JSONObject>();
        // añadir las fichas correspondientes
        for (int i = 0; i <= 3; i++) {
            if (diagonal)
                for (int n = 0; n < i; n++)
                    tablero.introducirFicha(
                            cambioColumnas * i + inicio, false);
            reales.add(tablero.introducirFicha(
                    cambioColumnas * i + inicio, true));

        }
        tablero.imprimirTablero();
        // comprobar que fuera cual fuese la ultima ficha obtendria la misma cadena ganadora
        // TODO: el caso de la columna depende como este implementado puede dar fallo
        // (si no mira las superiores)
        for (JSONObject lastPiece : reales) {
            JSONArray predicted = tablero.getCoordenadasGanadoras(
                    Integer.parseInt(lastPiece.get("x").toString()),
                    Integer.parseInt(lastPiece.get("y").toString()), true);
            for (JSONObject piece : reales) {
                for (int i = 0; i < predicted.size(); i++) {
                    JSONObject checkIfExists = (JSONObject) predicted.get(i);
                    if (piece.get("x") == checkIfExists.get("x") & piece.get("y") == checkIfExists.get("y")) {
                        predicted.remove(i);
                        break;
                    }
                }
            }
            assertEquals(predicted.size(), 0);
        }
    }

    @Test
    public void listaSeguidas() {
        Tablero tablero = Tablero.getmTablero();
        tablero.inicializarTablero();
        //esquina y no-esquina vacias
        for(int[] combinacion : tablero.getPosiblesCombinaciones()){
            assertEquals(tablero.listaSeguidas(0,0,0,
                    new ArrayList<int[]>(),combinacion,true).size(),0);
            assertEquals(tablero.listaSeguidas(1,0,0,
                    new ArrayList<int[]>(),combinacion,true).size(),0);
        }
        int[] horizontal = {0,1};
        int[] vertical = {1,0};
        int[] diagonalUp = {1,1};
        int[] diagonalDown = {1,-1};
        tablero.introducirFicha(0,false);
        tablero.introducirFicha(1,false);
        tablero.introducirFicha(1,false);
        tablero.introducirFicha(1,false);
        tablero.introducirFicha(2,true);
        tablero.introducirFicha(2,false);
        tablero.introducirFicha(3,false);
        tablero.introducirFicha(3,true);
        tablero.introducirFicha(3,false);
        tablero.introducirFicha(4,true);
        tablero.introducirFicha(4,false);
        tablero.introducirFicha(4,true);
        tablero.introducirFicha(5,true);
        tablero.introducirFicha(5,true);
        tablero.introducirFicha(6,true);
        tablero.imprimirTablero();
        // left or right only
        assertEquals(
                tablero.listaSeguidas(0,0,0,
                        new ArrayList<int[]>(),horizontal,false).size(),
                2);
        assertEquals(
                tablero.listaSeguidas(0,4,0,
                        new ArrayList<int[]>(),horizontal,true).size(),
                3);
        // horizontal
        assertEquals(
                tablero.listaSeguidas(0,5,0,
                        new ArrayList<int[]>(),horizontal,true).size(),
                2);
        // down
        assertEquals(
                tablero.listaSeguidas(0,1,0,
                        new ArrayList<int[]>(),vertical,false).size(),
                3);
        // diagonal one side
        assertEquals(
                tablero.listaSeguidas(1,4,0,
                        new ArrayList<int[]>(),diagonalDown,false).size(),
                2);
        assertEquals(
                tablero.listaSeguidas(0,2,0,
                        new ArrayList<int[]>(),diagonalUp,true).size(),
                3);
        // diagonal both sides
        assertEquals(
                tablero.listaSeguidas(0,1,0,
                        new ArrayList<int[]>(),diagonalUp,false).size(),
                3);
        assertEquals(
                tablero.listaSeguidas(0,4,0,
                        new ArrayList<int[]>(),diagonalUp,true).size(),
                2);


    }

}