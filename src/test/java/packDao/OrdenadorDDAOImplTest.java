package packDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrdenadorDDAOImplTest {
    OrdenadorDDAOImpl ordenadorDificil;
    private String nombre;
    private int tiempo;
    private ConnectionManager conexionM;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        ConnectionManager conexion=new ConnectionManager();
        ordenadorDificil=new OrdenadorDDAOImpl();
        nombre = "Pedro";
        tiempo = 3000;
        conexionM = new ConnectionManager();
        con = conexionM.getConnection();
    }

    @After
    public void tearDown() throws Exception {
        ordenadorDificil=null;
        nombre = null;
        tiempo = 0;
        conexionM = null;
        con = null;
    }

    @Test
    public void cargarRanking() throws SQLException {

        System.out.println(ordenadorDificil.cargarRanking());
    }

    @Test
    public void create(){
        OrdenadorDDAOImpl ordenadorDificil = this.ordenadorDificil;
        assertNotNull(con);

        int cuantosInicio = -1;
        int cuantosFin = -1;
        try {
            Statement s = con.createStatement();
            ResultSet resultado = s.executeQuery("SELECT count(*) FROM Partida"); //Con count vemos las tuplas que tiene la bd
            if(resultado.next()) {
                cuantosInicio = resultado.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ordenadorDificil.create(nombre,tiempo);
        try {
            Statement s = con.createStatement();
            ResultSet resultado = s.executeQuery("SELECT count(*) FROM Partida"); //Con count vemos las tuplas que tiene la bd
            if(resultado.next()) {
                cuantosFin = resultado.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertSame(cuantosInicio+1,cuantosFin);

        try {
            Statement s = con.createStatement();
            ResultSet resultado = s.executeQuery("SELECT id FROM Partida WHERE nombre ='"+nombre+"' AND tiempo ="+tiempo);
            if(resultado.next()) {
                int id = resultado.getInt("id");
                s.executeUpdate("DELETE FROM Partida WHERE id ="+id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
