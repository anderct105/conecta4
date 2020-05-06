package packModelo;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OrdenadorFTest {

	private Tablero t;
	private OrdenadorF o;

	@Before
	public void setUp() {
		t = Tablero.getmTablero();
		t.inicializarTablero();
		o = new OrdenadorF();
	}

	@After
	public void tearDown() {
		t.inicializarTablero();
		o = null;
	}

	@Test
	public void jugar() {
		//INTRODUCIENDO SOLO UNA FICHA
		JSONObject ja = o.jugar(0);
		assertNull(ja.get("posicionesGanadoras"));
		//System.out.println(ja.toJSONString());
		assertEquals(false, ja.get("haGanadoA"));
		assertEquals(false, ja.get("haGanadoB"));
		assertEquals(false, ja.get("lleno"));
		//LLENANDO LA PRIMERA COLUMNAintroducirFicha
		for (int i = 0; i < 4; i++) {
			t.introducirFicha(0, true);
		}
		ja = o.jugar(0);
		System.out.println(ja.toJSONString());
		assertEquals(false, ja.get("haGanadoB"));
		assertEquals(false, ja.get("lleno"));
		//INTRODUCIENDO CON LA PRIMERA COLUMNA LLENA
		ja = o.jugar(0);
		assertNull(ja);
		//LLENANDO TODO EL TABLERO
		for (int i = 1; i <= 6; i++) {
			for (int j = 0; j < 9; j++) {
				t.introducirFicha(j, true);
				if (i == 5 && j == 7) {
					//SE DEJA UNA FICHA SIN METER
					break;
				}
			}
		}
		ja = o.jugar(8);
		assertEquals(true, ja.get("lleno"));
	}
}