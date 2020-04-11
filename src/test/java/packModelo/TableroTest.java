package packModelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Tablero tablero=Tablero.getmTablero();
        tablero.inicializarTablero();
        //Prueba 1: tablero vacío
        assertNotNull(tablero.getOptimo(true));

        //Prueba 2: una ficha de color true en medio del tablero
        tablero.introducirFicha(4,true);
        Collection<Integer> lista=tablero.getOptimo(true);
        assertTrue(lista.contains(4));
        assertTrue(lista.contains(5));
        assertTrue(lista.contains(3));

        //Prueba 3: óptimo en diagonal
        tablero.inicializarTablero();
        tablero.introducirFicha(0,true);
        tablero.introducirFicha(1,false);
        tablero.introducirFicha(1,true);
        tablero.introducirFicha(2,true);
        tablero.introducirFicha(2,false);
        lista=tablero.getOptimo(true);
        assertTrue(lista.contains(2));

        //Prueba 4: 3 en raya o bloquear al oponente de ganar
        tablero.inicializarTablero();
        tablero.introducirFicha(1,true);
        tablero.introducirFicha(2,true);
        tablero.introducirFicha(4,true);
        tablero.introducirFicha(5,false);
        tablero.introducirFicha(5,false);
        lista=tablero.getOptimo(false);
        assertTrue(lista.contains(3));

        //Prueba 5: ganar frente a bloquear al oponente de ganar
        tablero.inicializarTablero();
        tablero.introducirFicha(1,true);
        tablero.introducirFicha(2,true);
        tablero.introducirFicha(4,true);
        tablero.introducirFicha(5,false);
        tablero.introducirFicha(5,false);
        lista=tablero.getOptimo(true);
        assertTrue(lista.contains(3));

        //Prueba 6: bloquear o hacer 2 en raya
        tablero.inicializarTablero();
        tablero.introducirFicha(1,true);
        tablero.introducirFicha(2,true);
        tablero.introducirFicha(5,false);
        lista=tablero.getOptimo(false);
        assertTrue(lista.contains(3));
        assertTrue(lista.contains(0));

        //Prueba 7: hacer 2 en raya o bloquear
        tablero.inicializarTablero();
        tablero.introducirFicha(1,true);
        tablero.introducirFicha(2,true);
        tablero.introducirFicha(5,false);
        lista=tablero.getOptimo(true);
        assertTrue(lista.contains(3));
        assertTrue(lista.contains(0));

        //Prueba 8: hacer 2 en raya o bloquear dos en raya del oponente
        tablero.inicializarTablero();
        tablero.introducirFicha(1,true);
        tablero.introducirFicha(2,false);
        lista=tablero.getOptimo(true);
        assertTrue(lista.contains(1)|| lista.contains(2)||lista.contains(3));

        //Prueba 9: hacer 3 en raya o bloquear 3 en raya del oponente
        tablero.inicializarTablero();
        tablero.introducirFicha(1,true);
        tablero.introducirFicha(1,true);
        tablero.introducirFicha(2,false);
        tablero.introducirFicha(2,false);
        lista=tablero.getOptimo(true);
        assertTrue(lista.contains(1)|| lista.contains(2));

        //Prueba 10: bloquear oponente de ganar en diagonal
        tablero.inicializarTablero();
        tablero.introducirFicha(0,true);
        tablero.introducirFicha(1,false);
        tablero.introducirFicha(1,true);
        tablero.introducirFicha(2,false);
        tablero.introducirFicha(2,false);
        tablero.introducirFicha(2,true);
        tablero.introducirFicha(3,true);
        tablero.introducirFicha(3,false);
        tablero.introducirFicha(3,false);
        lista=tablero.getOptimo(false);
        assertTrue(lista.contains(3));

    }

    @Test
    public void getCoordenadasGanadoras() {
    }

    @Test
    public void listaSeguidas() {
    }

    @Test
    public void getPosiblesCombinaciones() {
    }
}