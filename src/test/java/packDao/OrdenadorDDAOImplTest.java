package packDao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class OrdenadorDDAOImplTest {

	OrdenadorDDAOImpl ordenadorDificil;
	private String nombre;
	private int tiempo;
	private ConnectionManager conexionM;

	@Before
	public void setUp() {
		ordenadorDificil = new OrdenadorDDAOImpl();
		nombre = "Pedro";
		tiempo = 3000;
		conexionM = new ConnectionManager();
	}

	@After
	public void tearDown() {
		ordenadorDificil = null;
		nombre = null;
		tiempo = 0;
		conexionM = null;
	}

	@Test
	public void cargarRanking() throws SQLException {
		String sql = "DELETE FROM Partida";
		conexionM.execSQL(sql);
		//Prueba1: No hay elementos en el ranking
		JSONArray jsonVacio = new JSONArray();
		assertEquals(jsonVacio, ordenadorDificil.cargarRanking());
		System.out.println();
		//Prueba2: Hay pocos elementos en el ranking
		ordenadorDificil.create("Pedro", 3000);
		ordenadorDificil.create("Lola", 34);
		ordenadorDificil.create("Pepe", 20);
		JSONObject pedro = new JSONObject();
		pedro.put("puntuacion", 3000);
		pedro.put("nombre", "Pedro");
		JSONObject lola = new JSONObject();
		lola.put("puntuacion", 34);
		lola.put("nombre", "Lola");
		JSONObject pepe = new JSONObject();
		pepe.put("puntuacion", 20);
		pepe.put("nombre", "Pepe");
		jsonVacio.add(pepe);
		jsonVacio.add(lola);
		jsonVacio.add(pedro);
		assertEquals(jsonVacio, ordenadorDificil.cargarRanking());
		//Prueba3: Hay diez elementos en el ranking
		JSONArray json2 = new JSONArray();
		ordenadorDificil.create("Pedro", 3000);
		ordenadorDificil.create("Lola", 34);
		ordenadorDificil.create("Pepe", 20);
		ordenadorDificil.create("Pedro", 3000);
		ordenadorDificil.create("Lola", 34);
		ordenadorDificil.create("Pepe", 20);
		ordenadorDificil.create("Pepe", 20);
		json2.add(pepe);
		json2.add(pepe);
		json2.add(pepe);
		json2.add(pepe);
		json2.add(lola);
		json2.add(lola);
		json2.add(lola);
		json2.add(pedro);
		json2.add(pedro);
		json2.add(pedro);
		assertEquals(json2, ordenadorDificil.cargarRanking());
		//Prueba4: Hay más de diez elementos en el ranking
		assertEquals(json2, ordenadorDificil.cargarRanking());
		ordenadorDificil.create("Pedro", 3000);
		conexionM.execSQL("truncate table OrdenadorDificil");
		conexionM.execSQL("delete from Partida where nombre='Pedro'");
		conexionM.execSQL("delete from Partida where nombre='Pepe'");
		conexionM.execSQL("delete from Partida where nombre='Lola'");
	}

	@Test
	public void create() {
		OrdenadorDDAOImpl ordenadorDificil = this.ordenadorDificil;
		assertNotNull(conexionM);
		int cuantosInicio = -1;
		int cuantosFin = -1;
		try {
			ResultSet resultado = conexionM.execSQL("SELECT count(*) FROM Partida"); //Con count vemos las tuplas que tiene la bd
			if (resultado.next()) {
				cuantosInicio = resultado.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ordenadorDificil.create(nombre, tiempo);
		try {
			ResultSet resultado = conexionM.execSQL("SELECT count(*) FROM Partida"); //Con count vemos las tuplas que tiene la bd
			if (resultado.next()) {
				cuantosFin = resultado.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertSame(cuantosInicio + 1, cuantosFin);
		try {
			ResultSet resultado = conexionM.execSQL("SELECT id FROM Partida WHERE nombre ='" + nombre + "' AND tiempo =" + tiempo);
			if (resultado.next()) {
				int id = resultado.getInt("id");
				conexionM.execSQL("DELETE FROM OrdenadorDificil WHERE id =" + id);
				conexionM.execSQL("DELETE FROM Partida WHERE id =" + id);
				conexionM.closeConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
