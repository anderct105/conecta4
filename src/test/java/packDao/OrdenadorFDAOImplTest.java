package packDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class OrdenadorFDAOImplTest {
    OrdenadorFDAOImpl ordenadorFacil;
    @Before
    public void setUp() throws Exception {
        ConnectionManager conexion=new ConnectionManager();
        ordenadorFacil=new OrdenadorFDAOImpl();
    }

    @After
    public void tearDown() throws Exception {
        ordenadorFacil=null;
    }

    @Test
    public void cargarRanking() throws SQLException {
        
        System.out.println(ordenadorFacil.cargarRanking());

    }

    @Test
    public void create() {
    }
}