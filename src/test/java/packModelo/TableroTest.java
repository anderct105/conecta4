package packModelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
    public void getmTablero() {
    }

    @Test
    public void introducirFicha() {

        Tablero.getmTablero().introducirFicha(4,true);
        Tablero.getmTablero().introducirFicha(4,true);
        Tablero.getmTablero().introducirFicha(4,false);
        Tablero.getmTablero().introducirFicha(4,false);
        Tablero.getmTablero().introducirFicha(4,true);
        Tablero.getmTablero().introducirFicha(4,true);
        Tablero.getmTablero().introducirFicha(4,true);
        Tablero.getmTablero().imprimirTablero();
    }

    @Test
    public void ocupada() {
    }

    @Test
    public void haGanado() {
    }

    @Test
    public void esColor() {
    }

    @Test
    public void posValida() {
    }

    @Test
    public void inicializarTablero() {
    }

    @Test
    public void colFichasBloquear() {
    }

    @Test
    public void tableroLleno() {
    }

    @Test
    public void getColindantes() {

            //Columna 0
            Tablero.getmTablero().introducirFicha(0,true);
            Tablero.getmTablero().introducirFicha(0,false);
            Tablero.getmTablero().introducirFicha(0,true);
            Tablero.getmTablero().introducirFicha(0,true);
            Tablero.getmTablero().introducirFicha(0,false);
            Tablero.getmTablero().introducirFicha(0,true);

            //Columna 1
            Tablero.getmTablero().introducirFicha(1,false);
            Tablero.getmTablero().introducirFicha(1,true);
            Tablero.getmTablero().introducirFicha(1,false);
            Tablero.getmTablero().introducirFicha(1,false);
            Tablero.getmTablero().introducirFicha(1,false);
            Tablero.getmTablero().introducirFicha(1,true);

            //Columna 2
            Tablero.getmTablero().introducirFicha(2,false);
            Tablero.getmTablero().introducirFicha(2,false);
            Tablero.getmTablero().introducirFicha(2,true);
            Tablero.getmTablero().introducirFicha(2,false);
            Tablero.getmTablero().introducirFicha(2,false);
            Tablero.getmTablero().introducirFicha(2,false);

            //Columna 3
            Tablero.getmTablero().introducirFicha(3,false);
            Tablero.getmTablero().introducirFicha(3,true);
            Tablero.getmTablero().introducirFicha(3,false);
            Tablero.getmTablero().introducirFicha(3,true);
            Tablero.getmTablero().introducirFicha(3,false);
            Tablero.getmTablero().introducirFicha(3,false);

            //Columna 4
            Tablero.getmTablero().introducirFicha(4,true);
            Tablero.getmTablero().introducirFicha(4,false);
            Tablero.getmTablero().introducirFicha(4,false);
            Tablero.getmTablero().introducirFicha(4,true);
            Tablero.getmTablero().introducirFicha(4,false);
            Tablero.getmTablero().introducirFicha(4,true);
            //Columna 5
            Tablero.getmTablero().introducirFicha(5,false);
            Tablero.getmTablero().introducirFicha(5,true);
            Tablero.getmTablero().introducirFicha(5,true);
            Tablero.getmTablero().introducirFicha(5,false);
            Tablero.getmTablero().introducirFicha(5,false);
            Tablero.getmTablero().introducirFicha(5,true);
            //Columna 6
            Tablero.getmTablero().introducirFicha(6,false);
            Tablero.getmTablero().introducirFicha(6,true);
            Tablero.getmTablero().introducirFicha(6,false);
            Tablero.getmTablero().introducirFicha(6,false);
            Tablero.getmTablero().introducirFicha(6,false);
            Tablero.getmTablero().introducirFicha(6,true);
            //Columna 7
            Tablero.getmTablero().introducirFicha(7,true);
            Tablero.getmTablero().introducirFicha(7,true);
            Tablero.getmTablero().introducirFicha(7,false);
            Tablero.getmTablero().introducirFicha(7,false);
            Tablero.getmTablero().introducirFicha(7,false);
            Tablero.getmTablero().introducirFicha(7,true);
            //Columna 8
            Tablero.getmTablero().introducirFicha(8,false);
            Tablero.getmTablero().introducirFicha(8,true);
            Tablero.getmTablero().introducirFicha(8,true);
            Tablero.getmTablero().introducirFicha(8,true);
            Tablero.getmTablero().introducirFicha(8,true);
            Tablero.getmTablero().introducirFicha(8,false);

            Tablero.getmTablero().imprimirTablero();
            Tablero tablero=Tablero.getmTablero();
            //Prueba1: una colindante en fila
            //hacia la derecha
            assertEquals(tablero.getColindantes(5,0,true),1);
            //hacia la izquierda
            assertEquals(tablero.getColindantes(5,1,true),1);
            //Prueba2: una colindante en columna
            //hacia arriba
            assertEquals(tablero.getColindantes(2,0,true),1);
            //hacia abajo
            assertEquals(tablero.getColindantes(3,0,true),1);

            //Prueba3: una colindante en diagonal
            assertEquals(tablero.getColindantes(0,4,true),1);
            //Prueba4: una colindante en diagonal,fila y columna
            assertEquals(tablero.getColindantes(0,6,true),1);
            //Prueba 5: Dos colindantes en diagonal, fila y columna
            assertEquals(tablero.getColindantes(3,6,false),2);
            //Prueba 6: Dos colindantes en diagonal y columna y 1 en fila
            assertEquals(tablero.getColindantes(3,1,false),2);
            //Prueba 7: tres colindantes en ambas diagonales
            // hacia la derecha
            assertEquals(tablero.getColindantes(1,6,true),3);
            //hacia la izquierda
            assertEquals(tablero.getColindantes(2,3,true),3);

            //Prueba 8: tres colindantes en fila
            assertEquals(tablero.getColindantes(3,8,true),3);

            //Prueba 9: tres colindantes en columna
            assertEquals(tablero.getColindantes(5,5,true),3);

            //Prueba 10: ninguna colindante
            assertEquals(tablero.getColindantes(0,8,true),0);

            //Prueba 11: más de 3 colindantes
            assertEquals(tablero.getColindantes(4,0,true),3);

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
    }

    @Test
    public void getPosiblesCombinaciones() {
    }
}