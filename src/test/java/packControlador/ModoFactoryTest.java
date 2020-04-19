package packControlador;

import junit.framework.TestCase;

import packModelo.Modo;

import static packControlador.ModoFactory.getmModoFactory;

public class ModoFactoryTest extends TestCase {

    public void testCreateModo() {
        Modo m = getmModoFactory().createModo("OrdenadorD");
        assertNotNull(m);
        assertEquals(m.getNombre(),"OrdenadorD");

        Modo m2 = getmModoFactory().createModo("Nada");
        assertNotNull(m2);
        assertEquals(m2.getNombre(),"OvO");

        Modo m3 = getmModoFactory().createModo("OrdenadorF");
        assertNotNull(m3);
        assertEquals(m3.getNombre(),"OrdenadorF");
    }
}