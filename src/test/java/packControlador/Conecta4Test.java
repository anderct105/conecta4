package packControlador;

import junit.framework.TestCase;

public class Conecta4Test extends TestCase {

    public void setUp() throws Exception {
        Conecta4.getmConecta4().setModoJuego("OrdenadorF");
    }

    public void tearDown() throws Exception {
    }

    public void testGetmConecta4() {
    }

    public void testJugarPartida() {
    }

    public void testGetModoJuego() {
        assertEquals(Conecta4.getmConecta4().getModoJuego(), "OrdenadorF");
    }

    public void testSetModoJuego() {
        Conecta4.getmConecta4().setModoJuego("OrdenadorD");
        assertTrue(Conecta4.getmConecta4().getModoJuego().equals("OrdenadorD"));

        Conecta4.getmConecta4().setModoJuego("OrdenadorF");
        assertTrue(Conecta4.getmConecta4().getModoJuego().equals("OrdenadorF"));

        Conecta4.getmConecta4().setModoJuego("Algo");
        assertTrue(Conecta4.getmConecta4().getModoJuego().equals("OvO"));
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