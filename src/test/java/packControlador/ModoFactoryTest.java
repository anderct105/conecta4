package packControlador;

import junit.framework.TestCase;
import packModelo.Modo;

import static packControlador.ModoFactory.getmModoFactory;

public class ModoFactoryTest extends TestCase {

	public void testCreateModo() {
		Modo m = getmModoFactory().createModo("Ordenador modo Dificil");
		assertNotNull(m);
		assertEquals(m.getNombre(), "Ordenador modo Dificil");
		Modo m2 = getmModoFactory().createModo("Nada");
		assertNotNull(m2);
		assertEquals(m2.getNombre(), "1vs1");
		Modo m3 = getmModoFactory().createModo("Ordenador modo Facil");
		assertNotNull(m3);
		assertEquals(m3.getNombre(), "Ordenador modo Facil");
	}
}