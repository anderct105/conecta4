package packDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import packDao.ConnectionManager;
import packDao.OrdenadorDDAOImpl;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class OrdenadorDDAOImplTest {

    @Before
    public void setUp() throws Exception {
        ConnectionManager conexion=new ConnectionManager();


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void cargarRanking() throws SQLException {
         OrdenadorDDAOImpl ordenadorDificil=new OrdenadorDDAOImpl();
        System.out.println(ordenadorDificil.cargarRanking());

    }

    @Test
    public void create() {
    }
}