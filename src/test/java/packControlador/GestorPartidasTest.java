package packControlador;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import packDao.ConnectionManager;
import packModelo.Modo;
import packModelo.OrdenadorD;
import packModelo.OrdenadorF;
import packModelo.OvO;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GestorPartidasTest {

	int cuantosI, cuantosF;
	private GestorPartidas miGestor;
	private Modo m1, m2, m3;
	private ConnectionManager con;

	@Before
	public void setUp() {
		miGestor = GestorPartidas.getmGestorPartidas();
		m1 = new OvO();
		m2 = new OrdenadorF();
		m3 = new OrdenadorD();
		con = new ConnectionManager();
		cuantosI = 0;
		cuantosF = 0;
	}

	@After
	public void tearDown() {
		miGestor = null;
		m1 = null;
		m2 = null;
		m3 = null;
		con = null;
	}

	@Test
	public void guardarPartida() throws SQLException {
		ResultSet rs = con.execSQL("SELECT count(*) as cuantos from Partida");
		rs.next();
		cuantosI = rs.getInt(1);
		miGestor.setModoJuego(m1);
		miGestor.guardarPartida("pruebaGP", 0);
		rs = con.execSQL("SELECT count(*) as cuantos from Partida");
		rs.next();
		cuantosF = rs.getInt(1);
		assertNotEquals(cuantosI + 1, cuantosF);
		rs = con.execSQL("SELECT count(*) as cuantos from OrdenadorFacil");
		rs.next();
		cuantosI = rs.getInt(1);
		miGestor.setModoJuego(m2);
		miGestor.guardarPartida("pruebaGP", 0);
		rs = con.execSQL("SELECT count(*) as cuantos from OrdenadorFacil");
		rs.next();
		cuantosF = rs.getInt(1);
		assertEquals(cuantosI + 1, cuantosF);
		rs = con.execSQL("SELECT count(*) as cuantos from OrdenadorDificil");
		rs.next();
		cuantosI = rs.getInt(1);
		miGestor.setModoJuego(m3);
		miGestor.guardarPartida("pruebaGP", 0);
		rs = con.execSQL("SELECT count(*) as cuantos from OrdenadorDificil");
		rs.next();
		cuantosF = rs.getInt(1);
		assertEquals(cuantosI + 1, cuantosF);
		con.execSQL("DELETE FROM Partida where nombre='pruebaGP'");
	}
}