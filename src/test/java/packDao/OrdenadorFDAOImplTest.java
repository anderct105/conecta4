package packDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class OrdenadorFDAOImplTest {

    @Before
    public void setUp() throws Exception {
        ConnectionManager conexion=new ConnectionManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void cargarRanking() throws SQLException {
        OrdenadorFDAOImpl ordenadorFacil=new OrdenadorFDAOImpl();
        System.out.println(ordenadorFacil.cargarRanking());

    }

    @Test
    public void create() {
    }
}