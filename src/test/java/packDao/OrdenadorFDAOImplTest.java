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
    private OrdenadorFDAOImpl ordenadorFacil;

    @Before
    public void setUp() throws Exception {
        nombre = "Pedro";
        tiempo = 3000;
        conexionM = new ConnectionManager();
        con = conexionM.getConnection();
        ordenadorFacil=new OrdenadorFDAOImpl();
    }

    @After
    public void tearDown() {
        nombre = null;
        tiempo = 0;
        conexionM = null;
        con = null;
        ordenadorFacil=null;
    }

    @Test
    public void cargarRanking() throws SQLException {
        
        System.out.println(ordenadorFacil.cargarRanking());

    }

    @Test
    public void create() {
        OrdenadorFDAOImpl ordenadorFacil = this.ordenadorFacil;
        assertNotNull(con);

        int cuantosInicio = -1;
        int cuantosFin = -1;
        try {
            ResultSet resultado = conexionM.execSQL("SELECT count(*) FROM Partida"); //Con count vemos las tuplas que tiene la bd
            if(resultado.next()) {
                cuantosInicio = resultado.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ordenadorFacil.create(nombre,tiempo);
        try {
            ResultSet resultado = conexionM.execSQL("SELECT count(*) FROM Partida"); //Con count vemos las tuplas que tiene la bd
            if(resultado.next()) {
                cuantosFin = resultado.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertSame(cuantosInicio+1,cuantosFin);

        try {
            ResultSet resultado = conexionM.execSQL("SELECT id FROM Partida WHERE nombre ='"+nombre+"' AND tiempo ="+tiempo);
            if(resultado.next()) {
                int id = resultado.getInt("id");
                conexionM.execSQL("DELETE FROM Partida WHERE id ="+id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}