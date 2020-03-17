package packControlador;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import packDao.ConnectionManager;
import packModelo.Modo;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class GestorPartidasTest {

    private GestorPartidas miGestor;
    private Modo m1, m2, m3;
    private ConnectionManager con;
    int cuantosI, cuantosF;

    @Before
    public void setUp() {
        miGestor = GestorPartidas.getmGestorPartidas();
        m1 = ModoFactory.getmModoFactory().createModo("1vs1");
        m2 = ModoFactory.getmModoFactory().createModo("Ordenador modo Facil");
        m3 = ModoFactory.getmModoFactory().createModo("Ordenador modo Dificil");
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
        cuantosI = con.execSQL("SELECT count(*) as cuantos from Partida").getInt(0);
        miGestor.setModoJuego(m1);
        miGestor.guardarPartida("pruebaGP",0);
        cuantosF = con.execSQL("SELECT count(*) as cuantos from Partida").getInt(0);
        assertNotEquals(cuantosI + 1, cuantosF);

        cuantosI = con.execSQL("SELECT count(*) as cuantos from OrdenadorFacil").getInt(0);
        miGestor.setModoJuego(m2);
        miGestor.guardarPartida("pruebaGP",0);
        cuantosF = con.execSQL("SELECT count(*) as cuantos from OrdenadorFacil").getInt(0);
        assertEquals(cuantosI + 1, cuantosF);

        cuantosI = con.execSQL("SELECT count(*) as cuantos from OrdenadorDificil").getInt(0);
        miGestor.setModoJuego(m3);
        miGestor.guardarPartida("pruebaGP",0);
        cuantosF = con.execSQL("SELECT count(*) as cuantos from OrdenadorFacil").getInt(0);
        assertEquals(cuantosI + 1, cuantosF);

        con.execSQL("DELETE FROM Partida where nombre='pruebaGP'");
    }
}