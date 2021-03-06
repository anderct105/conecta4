package packModelo;

import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class TableroTest {

	@Before
	public void setUp() {
		Tablero.getmTablero().inicializarTablero();
	}

	@After
	public void tearDown() {
		Tablero.getmTablero().inicializarTablero();
	}


	@Test
	public void introducirFicha() {
		Tablero tablero = Tablero.getmTablero();
		tablero.inicializarTablero();
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(2, true);
		tablero.introducirFicha(4, true);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		//Prueba 1:introducir ficha enn una posición ocupada
		assertNull(tablero.introducirFicha(5, false));
		//Prueba 2: introducir ficha en una posición válida
		JSONObject json = tablero.introducirFicha(4, false);
		int fila = (int) json.get("fila");
		int columna = (int) json.get("columna");
		boolean lleno = (boolean) json.get("lleno");
		assertTrue(fila == 1 && columna == 4 && !lleno);
		//Prueba 3: tablero lleno
		//Columna 0
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, false);
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, false);
		Tablero.getmTablero().introducirFicha(0, true);
		//Columna 1
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, true);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, true);
		//Columna 2
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, true);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		//Columna 3
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, false);
		//Columna 4
		Tablero.getmTablero().introducirFicha(4, true);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, true);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, true);
		//Columna 5
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, true);
		Tablero.getmTablero().introducirFicha(5, true);
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, true);
		//Columna 6
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, true);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, true);
		//Columna 7
		Tablero.getmTablero().introducirFicha(7, true);
		Tablero.getmTablero().introducirFicha(7, true);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, true);
		//Columna 8
		Tablero.getmTablero().introducirFicha(8, false);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		json = Tablero.getmTablero().introducirFicha(8, false);
		fila = (int) json.get("fila");
		columna = (int) json.get("columna");
		lleno = (boolean) json.get("lleno");
		assertTrue(fila == 5 && columna == 8 && lleno);
	}

	@Test
	public void ocupada() {
		Tablero tablero = Tablero.getmTablero();
		tablero.inicializarTablero();
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(2, true);
		tablero.introducirFicha(4, true);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		//Prueba 1:posición ocupada
		tablero.introducirFicha(5, false);
		assertTrue(tablero.ocupada(5));
		//Prueba 2: posición libre
		assertFalse(tablero.ocupada(4));
	}

	@Test
	public void haGanado() {
		//Columna 0
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, false);
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, false);
		Tablero.getmTablero().introducirFicha(0, true);
		//Columna 1
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, true);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, true);
		//Columna 2
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, true);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		//Columna 3
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, false);
		//Columna 4
		Tablero.getmTablero().introducirFicha(4, true);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, true);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, true);
		//Columna 5
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, true);
		Tablero.getmTablero().introducirFicha(5, true);
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, true);
		//Columna 6
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, true);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, true);
		//Columna 7
		Tablero.getmTablero().introducirFicha(7, true);
		Tablero.getmTablero().introducirFicha(7, true);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, true);
		//Columna 8
		Tablero.getmTablero().introducirFicha(8, false);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, false);
		Tablero tablero = Tablero.getmTablero();
		//Prueba1: no ha ganado
		assertNull(tablero.haGanado(5, 0, true));
		//Prueba2: ha ganado
		assertNotNull(tablero.haGanado(3, 8, true));
	}

	@Test
	public void esColor() {
		Tablero.getmTablero().introducirFicha(0, true);
		assertTrue(Tablero.getmTablero().esColor(0, 0, true));
		assertFalse(Tablero.getmTablero().esColor(1, 0, true));
		Tablero.getmTablero().introducirFicha(0, false);
		assertFalse(Tablero.getmTablero().esColor(1, 0, true));
		assertFalse(Tablero.getmTablero().esColor(2, 0, true));
	}

	@Test
	public void posValida() {
		assertTrue(Tablero.getmTablero().posValida(0, 0));
		assertTrue(Tablero.getmTablero().posValida(5, 8));
		assertFalse(Tablero.getmTablero().posValida(5, 9));
		assertFalse(Tablero.getmTablero().posValida(-1, 1));
		assertFalse(Tablero.getmTablero().posValida(6, 1));
		assertFalse(Tablero.getmTablero().posValida(5, 11));
		assertFalse(Tablero.getmTablero().posValida(5, -1));
	}


	@Test
	public void tableroLleno() {
		//Columna 0
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, false);
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, false);
		Tablero.getmTablero().introducirFicha(0, true);
		assertFalse(Tablero.getmTablero().tableroLleno());
		//Columna 1
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, true);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, true);
		//Columna 2
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, true);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		//Columna 3
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, false);
		//Columna 4
		Tablero.getmTablero().introducirFicha(4, true);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, true);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, true);
		//Columna 5
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, true);
		Tablero.getmTablero().introducirFicha(5, true);
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, true);
		//Columna 6
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, true);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, true);
		//Columna 7
		Tablero.getmTablero().introducirFicha(7, true);
		Tablero.getmTablero().introducirFicha(7, true);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, true);
		//Columna 8
		Tablero.getmTablero().introducirFicha(8, false);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, false);
		assertTrue(Tablero.getmTablero().tableroLleno());
	}

	@Test
	public void getColindantes() {
		//Columna 0
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, false);
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, false);
		Tablero.getmTablero().introducirFicha(0, true);
		//Columna 1
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, true);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, true);
		//Columna 2
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, true);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		//Columna 3
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, false);
		//Columna 4
		Tablero.getmTablero().introducirFicha(4, true);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, true);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, true);
		//Columna 5
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, true);
		Tablero.getmTablero().introducirFicha(5, true);
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, true);
		//Columna 6
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, true);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, true);
		//Columna 7
		Tablero.getmTablero().introducirFicha(7, true);
		Tablero.getmTablero().introducirFicha(7, true);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, true);
		//Columna 8
		Tablero.getmTablero().introducirFicha(8, false);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, false);
		Tablero tablero = Tablero.getmTablero();
		//Prueba1: una colindante en fila
		//hacia la derecha
		assertEquals(tablero.getColindantes(5, 0, true), 1);
		//hacia la izquierda
		assertEquals(tablero.getColindantes(5, 1, true), 1);
		//Prueba2: una colindante en columna
		//hacia arriba
		assertEquals(tablero.getColindantes(2, 0, true), 1);
		//hacia abajo
		assertEquals(tablero.getColindantes(3, 0, true), 1);
		//Prueba3: dos colindante en diagonal
		assertEquals(tablero.getColindantes(0, 4, true), 2);
		//Prueba4: una colindante en fila
		assertEquals(tablero.getColindantes(0, 6, false), 1);
		//Prueba 5: Dos colindantes en diagonal, fila y columna
		assertEquals(tablero.getColindantes(3, 6, false), 2);
		//Prueba 6: Dos colindantes en diagonal y columna y 1 en fila
		assertEquals(tablero.getColindantes(3, 1, false), 2);
		//Prueba 7: tres colindantes en ambas diagonales
		// hacia la derecha
		assertEquals(tablero.getColindantes(1, 5, true), 3);
		//hacia la izquierda
		assertEquals(tablero.getColindantes(1, 8, true), 3);
		//Prueba 8: tres colindantes en fila
		assertEquals(tablero.getColindantes(3, 8, true), 3);
		//Prueba 9: tres colindantes en columna
		assertEquals(tablero.getColindantes(5, 5, true), 3);
		//Prueba 10: ninguna colindante
		assertEquals(tablero.getColindantes(0, 8, false), 0);
		//Prueba 11: más de 3 colindantes
		assertEquals(tablero.getColindantes(4, 0, true), 3);
	}

	@Test
	public void getPosicionesPosibles() {
		Tablero tablero = Tablero.getmTablero();
		tablero.inicializarTablero();
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(2, true);
		tablero.introducirFicha(4, true);
		tablero.introducirFicha(4, true);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		//Prueba
		int[] listaPosiciones = tablero.getPosicionesPosibles();
		assertTrue(listaPosiciones[0] == 0 && listaPosiciones[1] == 1 && listaPosiciones[2] == 1 && listaPosiciones[3] == 0 &&
				listaPosiciones[4] == 2 && listaPosiciones[5] == -1 && listaPosiciones[6] == 0 && listaPosiciones[7] == 0 &&
				listaPosiciones[8] == 0);
	}

	@Test
	public void numSeguidas() {
		Tablero.getmTablero().inicializarTablero();
		//Columna 0
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, false);
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, false);
		Tablero.getmTablero().introducirFicha(0, true);
		//Columna 1
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, true);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, false);
		Tablero.getmTablero().introducirFicha(1, true);
		//Columna 2
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, true);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		Tablero.getmTablero().introducirFicha(2, false);
		//Columna 3
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, false);
		Tablero.getmTablero().introducirFicha(3, false);
		//Columna 4
		Tablero.getmTablero().introducirFicha(4, true);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, true);
		Tablero.getmTablero().introducirFicha(4, false);
		Tablero.getmTablero().introducirFicha(4, true);
		//Columna 5
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, true);
		Tablero.getmTablero().introducirFicha(5, true);
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, false);
		Tablero.getmTablero().introducirFicha(5, true);
		//Columna 6
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, true);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, false);
		Tablero.getmTablero().introducirFicha(6, true);
		//Columna 7
		Tablero.getmTablero().introducirFicha(7, true);
		Tablero.getmTablero().introducirFicha(7, true);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, false);
		Tablero.getmTablero().introducirFicha(7, true);
		//Columna 8
		Tablero.getmTablero().introducirFicha(8, false);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, true);
		Tablero.getmTablero().introducirFicha(8, false);
		Tablero tablero = Tablero.getmTablero();
		//Prueba1: colindantes hacia todas las direcciones
		//hacia arriba
		int[] combinacion = new int[2];
		combinacion[0] = 1;
		combinacion[1] = 0;
		assertEquals(tablero.numSeguidas(3, 6, combinacion, false), 1);
		//hacia abajo
		combinacion = new int[2];
		combinacion[0] = -1;
		combinacion[1] = 0;
		assertEquals(tablero.numSeguidas(3, 6, combinacion, false), 1);
		//hacia la derecha
		combinacion = new int[2];
		combinacion[0] = 0;
		combinacion[1] = 1;
		assertEquals(tablero.numSeguidas(3, 6, combinacion, false), 1);
		//diagonal hacia arriba a la derecha
		combinacion = new int[2];
		combinacion[0] = 1;
		combinacion[1] = 1;
		assertEquals(tablero.numSeguidas(3, 6, combinacion, false), 2);
		//diagonal hacia abajo a la izquierda
		combinacion = new int[2];
		combinacion[0] = -1;
		combinacion[1] = -1;
		assertEquals(tablero.numSeguidas(3, 6, combinacion, false), 0);
		//diagonal hacia arriba a la izquierda
		combinacion = new int[2];
		combinacion[0] = 1;
		combinacion[1] = -1;
		assertEquals(tablero.numSeguidas(3, 6, combinacion, false), 1);
		//diagonal hacia abajo a la derecha
		combinacion = new int[2];
		combinacion[0] = -1;
		combinacion[1] = 1;
		assertEquals(tablero.numSeguidas(3, 6, combinacion, false), 1);
		//Prueba 2:al mirar se sale del tablero
		//hacia la derecha
		combinacion = new int[2];
		combinacion[0] = 0;
		combinacion[1] = 1;
		assertEquals(tablero.numSeguidas(0, 8, combinacion, false), 0);
		//hacia abajo
		combinacion = new int[2];
		combinacion[0] = -1;
		combinacion[1] = 0;
		assertEquals(tablero.numSeguidas(0, 8, combinacion, false), 0);
		//diagonal hacia arriba a la derecha
		combinacion = new int[2];
		combinacion[0] = 1;
		combinacion[1] = 1;
		assertEquals(tablero.numSeguidas(0, 8, combinacion, false), 0);
		//diagonal hacia abajo a la izquierda
		combinacion = new int[2];
		combinacion[0] = -1;
		combinacion[1] = -1;
		assertEquals(tablero.numSeguidas(0, 8, combinacion, false), 0);
		//diagonal hacia abajo a la derecha
		combinacion = new int[2];
		combinacion[0] = -1;
		combinacion[1] = 1;
		assertEquals(tablero.numSeguidas(0, 8, combinacion, false), 0);
		//Prueba 3: más de tres fichas en filas
		combinacion = new int[2];
		combinacion[0] = 0;
		combinacion[1] = 1;
		assertEquals(tablero.numSeguidas(4, 0, combinacion, false), 7);
	}

	@Test
	public void getOptimo() {
		Tablero tablero = Tablero.getmTablero();
		tablero.inicializarTablero();
		//Prueba 1: tablero vacío
		assertNotNull(tablero.getOptimo(true));
		//Prueba 2: una ficha de color true en medio del tablero
		tablero.introducirFicha(4, true);
		Pair<Integer, Integer> par = tablero.getOptimo(true);
		assertTrue(par.getKey().equals(4) || par.getKey().equals(5) || par.getKey().equals(3));
		//Prueba 3: óptimo en diagonal
		tablero.inicializarTablero();
		tablero.introducirFicha(0, true);
		tablero.introducirFicha(1, false);
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(2, true);
		tablero.introducirFicha(2, false);
		par = tablero.getOptimo(true);
		assertEquals(2, (int) par.getValue());
		//Prueba 4: 3 en raya vertical
		tablero.inicializarTablero();
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(2, true);
		tablero.introducirFicha(4, true);
		tablero.introducirFicha(5, false);
		tablero.introducirFicha(5, false);
		par = tablero.getOptimo(false);
		assertEquals(5, (int) par.getKey());
		//Prueba 5: ganar frente a hacer 3 en raya
		tablero.inicializarTablero();
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(2, true);
		tablero.introducirFicha(4, true);
		tablero.introducirFicha(8, true);
		tablero.introducirFicha(8, true);
		par = tablero.getOptimo(true);
		assertEquals(3, (int) par.getValue());
		//Prueba 6: hacer 2 en raya en vertical
		tablero.inicializarTablero();
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(2, true);
		tablero.introducirFicha(5, false);
		par = tablero.getOptimo(false);
		assertTrue(par.getKey().equals(5) || par.getKey().equals(4) || par.getKey().equals(6));
		//Prueba 7: hacer 3 en raya de dos formas
		tablero.inicializarTablero();
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(2, true);
		tablero.introducirFicha(5, false);
		par = tablero.getOptimo(true);
		assertTrue(par.getKey().equals(0) || par.getKey().equals(3));
		//Prueba 8: hacer 2 en raya de 3 formas
		tablero.inicializarTablero();
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(2, false);
		par = tablero.getOptimo(true);
		assertTrue(par.getKey().equals(1) || par.getKey().equals(2) || par.getKey().equals(0));
		//Prueba 9: hacer 3 en raya
		tablero.inicializarTablero();
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(2, false);
		tablero.introducirFicha(2, false);
		par = tablero.getOptimo(true);
		assertTrue(par.getKey().equals(1) || par.getKey().equals(2));
		//Prueba 10: ganar en diagonal
		tablero.inicializarTablero();
		tablero.introducirFicha(0, true);
		tablero.introducirFicha(1, false);
		tablero.introducirFicha(1, true);
		tablero.introducirFicha(2, false);
		tablero.introducirFicha(2, false);
		tablero.introducirFicha(2, true);
		tablero.introducirFicha(3, true);
		tablero.introducirFicha(3, false);
		tablero.introducirFicha(3, false);
		par = tablero.getOptimo(true);
		assertEquals(3, (int) par.getKey());
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
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(0, true);
		assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0, 0, true).size(), 0);
		Tablero.getmTablero().introducirFicha(0, true);
		assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0, 0, true).size(), 4);
		assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(2, 0, true).size(), 4);
		assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(3, 0, true).size(), 4);
		//PRUEBA2
		Tablero.getmTablero().inicializarTablero();
		Tablero.getmTablero().introducirFicha(0, true);
		Tablero.getmTablero().introducirFicha(1, true);
		Tablero.getmTablero().introducirFicha(1, true);
		Tablero.getmTablero().introducirFicha(2, true);
		Tablero.getmTablero().introducirFicha(2, true);
		Tablero.getmTablero().introducirFicha(2, true);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, true);
		Tablero.getmTablero().introducirFicha(3, true);
		assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0, 0, true).size(), 4);
		Tablero.getmTablero().introducirFicha(0, true);
		assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0, 0, true).size(), 4);
		assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(2, 3, true).size(), 4);
		assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0, 3, true).size(), 4);
		assertEquals(Tablero.getmTablero().getCoordenadasGanadoras(0, 2, true).size(), 4);
	}

	private void checkCoordenadasGanadoras(int cambioColumnas, int inicio, boolean diagonal) {
		Tablero tablero = Tablero.getmTablero();
		tablero.inicializarTablero();
		ArrayList<JSONObject> reales = new ArrayList<>();
		// añadir las fichas correspondientes
		for (int i = 0; i <= 3; i++) {
            if (diagonal) {
                for (int n = 0; n < i; n++) {
                    tablero.introducirFicha(
                            cambioColumnas * i + inicio, false);
                }
            }
			reales.add(tablero.introducirFicha(
					cambioColumnas * i + inicio, true));
		}
		// comprobar que fuera cual fuese la ultima ficha obtendria la misma cadena ganadora
		// TODO: el caso de la columna depende como este implementado puede dar fallo
		// (si no mira las superiores)
		for (JSONObject lastPiece : reales) {
			JSONArray predicted = tablero.getCoordenadasGanadoras(
					Integer.parseInt(lastPiece.get("fila").toString()),
					Integer.parseInt(lastPiece.get("columna").toString()), true);
			for (JSONObject piece : reales) {
				for (int i = 0; i < predicted.size(); i++) {
					JSONObject checkIfExists = (JSONObject) predicted.get(i);
					if (piece.get("fila") == checkIfExists.get("fila") & piece.get("columna") == checkIfExists.get("columna")) {
						predicted.remove(i);
						break;
					}
				}
			}
			assertEquals(predicted.size(), 4);
		}
	}

	@Test
	public void listaSeguidas() {
		Tablero tablero = Tablero.getmTablero();
		tablero.inicializarTablero();
		//esquina y no-esquina vacias
		for (int[] combinacion : tablero.getPosiblesCombinaciones()) {
			assertEquals(tablero.listaSeguidas(0, 0, 0,
					new ArrayList<>(), combinacion, true).size(), 0);
			assertEquals(tablero.listaSeguidas(1, 0, 0,
					new ArrayList<>(), combinacion, true).size(), 0);
		}
		int[] horizontal = {0, 1};
		int[] vertical = {1, 0};
		int[] diagonalUp = {1, 1};
		int[] diagonalDown = {1, -1};
		tablero.introducirFicha(0, false);
		tablero.introducirFicha(1, false);
		tablero.introducirFicha(1, false);
		tablero.introducirFicha(1, false);
		tablero.introducirFicha(2, true);
		tablero.introducirFicha(2, false);
		tablero.introducirFicha(3, false);
		tablero.introducirFicha(3, true);
		tablero.introducirFicha(3, false);
		tablero.introducirFicha(4, true);
		tablero.introducirFicha(4, false);
		tablero.introducirFicha(4, true);
		tablero.introducirFicha(5, true);
		tablero.introducirFicha(5, true);
		tablero.introducirFicha(6, true);
		// left or right only
		assertEquals(
				tablero.listaSeguidas(0, 0, 0,
						new ArrayList<>(), horizontal, false).size(),
				2);
		assertEquals(
				tablero.listaSeguidas(0, 4, 0,
						new ArrayList<>(), horizontal, true).size(),
				3);
		// horizontal
		assertEquals(
				tablero.listaSeguidas(0, 5, 0,
						new ArrayList<>(), horizontal, true).size(),
				2);
		// down
		assertEquals(
				tablero.listaSeguidas(0, 1, 0,
						new ArrayList<>(), vertical, false).size(),
				3);
		// diagonal one side
		assertEquals(
				tablero.listaSeguidas(1, 4, 0,
						new ArrayList<>(), diagonalDown, false).size(),
				2);
		assertEquals(
				tablero.listaSeguidas(0, 2, 0,
						new ArrayList<>(), diagonalUp, true).size(),
				3);
		// diagonal both sides
		assertEquals(
				tablero.listaSeguidas(0, 1, 0,
						new ArrayList<>(), diagonalUp, false).size(),
				3);
		assertEquals(
				tablero.listaSeguidas(0, 4, 0,
						new ArrayList<>(), diagonalUp, true).size(),
				2);
	}
}