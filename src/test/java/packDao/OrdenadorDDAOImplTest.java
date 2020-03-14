package packDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import packDao.ConnectionManager;
import packDao.OrdenadorDDAOImpl;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class OrdenadorDDAOImplTest {
    OrdenadorDDAOImpl ordenadorDificil;
    @Before
    public void setUp() throws Exception {
        ConnectionManager conexion=new ConnectionManager();
        ordenadorDificil=new OrdenadorDDAOImpl();

    }

    @After
    public void tearDown() throws Exception {
        ordenadorDificil=null;
    }

    @Test
    public void cargarRanking() throws SQLException {

        System.out.println(ordenadorDificil.cargarRanking());

    }

    @Test
    public void create() {
    }
}