package packDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class OrdenadorFDAOImplTest {

    private String nombre;
    private int tiempo;
    private ConnectionManager conexionM;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        nombre = "Pedro";
        tiempo = 3000;
        conexionM = new ConnectionManager();
        con = conexionM.getConnection();
    }

    @After
    public void tearDown() {
        nombre = null;
        tiempo = 0;
        conexionM = null;
        con = null;
    }

    @Test
    public void cargarRanking() throws SQLException {
        OrdenadorFDAOImpl ordenadorFacil=new OrdenadorFDAOImpl();
        System.out.println(ordenadorFacil.cargarRanking());

    }

    @Test
    public void create() {
        OrdenadorFDAOImpl ordenadorFacil = new OrdenadorFDAOImpl();
        assertNotNull(con);
        //ordenadorFacil.create(nombre,tiempo);
        int cuantos = -1;
        try {
            Statement s = con.createStatement();
            ResultSet resultado = s.executeQuery("SELECT count(*) FROM Partida"); //Con count vemos las tuplas que tiene la bd
            if(resultado.next()) {
                cuantos= resultado.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertSame(0, cuantos); //Para cuando la tabla está vacia
        //assertSame(1, cuantos); //Al hacer create por primera vez
    }
}