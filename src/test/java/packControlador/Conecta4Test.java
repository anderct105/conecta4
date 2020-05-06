package packControlador;

import junit.framework.TestCase;

public class Conecta4Test extends TestCase {

	public void setUp() {
		Conecta4.getmConecta4().setModoJuego("Ordenador modo Facil");
	}

	public void tearDown() {
	}

	public void testGetModoJuego() {
		assertEquals(Conecta4.getmConecta4().getModoJuego(), "Ordenador modo Facil");
	}

	public void testSetModoJuego() {
		Conecta4.getmConecta4().setModoJuego("Ordenador modo Dificil");
		assertEquals("Ordenador modo Dificil", Conecta4.getmConecta4().getModoJuego());
		Conecta4.getmConecta4().setModoJuego("Ordenador modo Facil");
		assertEquals("Ordenador modo Facil", Conecta4.getmConecta4().getModoJuego());
		Conecta4.getmConecta4().setModoJuego("Algo");
		assertEquals("1vs1", Conecta4.getmConecta4().getModoJuego());
	}
}