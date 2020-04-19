package packModelo;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        t.introducirFicha(0,true);
        o.jugar(1);
        t.imprimirTablero();
    }
}