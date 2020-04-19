package packControlador;

import junit.framework.TestCase;

public class Conecta4Test extends TestCase {

    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {
    }

    public void testGetmConecta4() {
    }

    public void testJugarPartida() {
    }

    public void testGetModoJuego() {
    }

    public void testSetModoJuego() {
        Conecta4.getmConecta4().setModoJuego("OrdenadorD");
        assertTrue(Conecta4.getmConecta4().getModoJuego().equals("OrdenadorD"));
    }

    public void testCargarRankingFacil() {
    }

    public void testCargarRankingDificil() {
    }

    public void testGuardarPartida() {
    }

    public void testInicializarTablero() {
    }
}