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
    }
}