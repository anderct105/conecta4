package packControlador;

import junit.framework.TestCase;

public class Conecta4Test extends TestCase {

    public void setUp() throws Exception {
        Conecta4.getmConecta4().setModoJuego("Ordenador modo Facil");
    }

    public void tearDown() throws Exception {
    }

    public void testGetmConecta4() {
    }

    public void testJugarPartida() {
    }

    public void testGetModoJuego() {
        assertEquals(Conecta4.getmConecta4().getModoJuego(), "Ordenador modo Facil");
    }

    public void testSetModoJuego() {
        Conecta4.getmConecta4().setModoJuego("Ordenador modo Dificil");
        assertTrue(Conecta4.getmConecta4().getModoJuego().equals("Ordenador modo Dificil"));

        Conecta4.getmConecta4().setModoJuego("Ordenador modo Facil");
        assertTrue(Conecta4.getmConecta4().getModoJuego().equals("Ordenador modo Facil"));

        Conecta4.getmConecta4().setModoJuego("Algo");
        assertTrue(Conecta4.getmConecta4().getModoJuego().equals("1vs1"));
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