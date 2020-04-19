package packModelo;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class OrdenadorDTest extends TestCase {

    private Tablero t;
    private OrdenadorD o;

    public void setUp() throws Exception {
        t = Tablero.getmTablero();
        t.inicializarTablero();
        o = new OrdenadorD();
    }

    public void tearDown() throws Exception {
        t.inicializarTablero();
        o = null;
    }

    public void testJugar() {
        //EL JUGADOR VA A HACER CUATRO EN RAYA
        t.introducirFicha(0,true);
        t.introducirFicha(1,true);
        assertFalse(t.esColor(0,3,true));
        assertFalse(t.esColor(0,3,false));
        o.jugar(2);
        assertTrue(t.esColor(0,3,false));

        //EL ORDENADOR VA A HACER CUATRO EN RAYA
        t.inicializarTablero();
        t.introducirFicha(2, false);
        t.introducirFicha(2, false);
        t.introducirFicha(2, false);
        assertFalse(t.esColor(3,2,true));
        assertFalse(t.esColor(3,2,false));
        o.jugar(1);
        assertTrue(t.esColor(3,2,false));

        //EL JUGADOR Y EL ORDENADOR VAN A HACER CUATRO EN RAYA
        t.inicializarTablero();
        t.introducirFicha(1, false);
        t.introducirFicha(1, false);
        t.introducirFicha(1, false);
        t.introducirFicha(8, true);
        t.introducirFicha(7, true);
        assertFalse(t.esColor(3,1,true));
        assertFalse(t.esColor(3,1,false));
        o.jugar(6);
        assertTrue(t.esColor(3,1,false));
        assertFalse(t.esColor(0,5,true));
        assertFalse(t.esColor(0,5,false));

        //PRIMERA JUGADA
        t.inicializarTablero();
        o.jugar(4);
        assertTrue(t.getColindantes(0,4,false) == 1);
        assertTrue(t.esColor(0,3,false) || t.esColor(1,4,false) || t.esColor(0,5,false));

        //JUGADOR TIENE DOS SEGUIDAS Y ORDENADOR SOLO UNA
        t.inicializarTablero();
        t.introducirFicha(3,true);
        t.introducirFicha(5,false);
        o.jugar(3);
        assertTrue(t.esColor(2,3,false));

        //JUGADOR TIENE UNA SEGUIDA Y ORDENADOR TIENE DOS
        t.inicializarTablero();
        t.introducirFicha(0,true);
        t.introducirFicha(3,true);
        t.introducirFicha(8, false);
        t.introducirFicha(8,false);
        o.jugar(6);
        assertTrue(t.esColor(2,8,false));

        //JUGADOR TIENE DOS FICHAS CON UN HUECO EN MEDIO
        t.inicializarTablero();
        t.introducirFicha(0,true);
        t.introducirFicha(7,false);
        o.jugar(2);
        assertTrue(t.esColor(0,1,false));

        //JUGADOR Y ORDENADOR TIENEN DOS SEGUIDAS
        t.inicializarTablero();
        t.introducirFicha(0,true);
        t.introducirFicha(4,true);
        t.introducirFicha(8,false);
        t.introducirFicha(8,false);
        o.jugar(0);
        assertTrue(t.esColor(2,0,false) || t.esColor(2,8,false));

        //HAY MAS DE TRES SEGUIDAS
        t.inicializarTablero();
        t.introducirFicha(0,true);
        t.introducirFicha(1,true);
        t.introducirFicha(2,true);
        t.introducirFicha(4,true);
        o.jugar(5);
        assertTrue(t.esColor(0,3,false));

        //HAY FICHAS DE JUGADOR Y ORDENADOR, PERO NINGUNA SEGUIDA
        t.inicializarTablero();
        t.introducirFicha(1, true);
        t.introducirFicha(7, false);
        o.jugar(4);
        assertTrue(t.getColindantes(0,1,false) == 1 && t.getColindantes(0,4,false) == 0 && t.getColindantes(0,7,false) == 0 ||
                t.getColindantes(0,1,false) == 0 && t.getColindantes(0,4,false) == 1 && t.getColindantes(0,7,false) == 0 ||
                t.getColindantes(0,1,false) == 0 && t.getColindantes(0,4,false) == 0 && t.getColindantes(0,7,false) == 1);
    }
}