package packModelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TableroTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getmTablero() {
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


    }

    private void checkCoordenadasGanadoras(int cambioColumnas, int inicio, boolean diagonal) {
        Tablero tablero = Tablero.getmTablero();
        tablero.inicializarTablero();
        ArrayList<JSONObject> reales = new ArrayList<JSONObject>();
        // añadir las fichas correspondientes
        for (int i = 0; i <= 3; i++) {
            reales.add(tablero.introducirFicha(
                    cambioColumnas * i + inicio, true));
            if (diagonal)
                for (int n = 0; n < i; n++)
                    tablero.introducirFicha(
                            cambioColumnas * i + inicio, false);
        }
        // assertTrue(predicted.containsAll(reales) TODO: Probarlo
        // comprobar que fuera cual fuese la ultima ficha obtendria la misma cadena ganadora
            // TODO: el caso de la columna depende como este implementado puede dar fallo
                // (si no mira las superiores)
        for (JSONObject lastPiece : reales) {
            JSONArray predicted = tablero.getCoordenadasGanadoras(
                    Integer.parseInt((String) lastPiece.get("x")),
                    Integer.parseInt((String) lastPiece.get("y")), true);
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