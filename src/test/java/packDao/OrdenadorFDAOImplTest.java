package packDao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class OrdenadorFDAOImplTest {

    private String nombre;
    private int tiempo;
    private ConnectionManager conexionM;
    private OrdenadorFDAOImpl ordenadorFacil;

    @Before
    public void setUp() {
        nombre = "Pedro";
        tiempo = 3000;
        conexionM = new ConnectionManager();
        ordenadorFacil=new OrdenadorFDAOImpl();
    }

    @After
    public void tearDown() {
        nombre = null;
        tiempo = 0;
        conexionM.execSQL("truncate table OrdenadorFacil");
        conexionM.execSQL("truncate table OrdenadorDificil");
        conexionM.execSQL("truncate table Partida");
        conexionM = null;
        ordenadorFacil=null;
    }

    @Test
    public void cargarRanking() throws SQLException {
        //Prueba1: No hay elementos en el ranking
        JSONArray jsonVacio=new JSONArray();
        assertTrue(jsonVacio.equals(ordenadorFacil.cargarRanking()));
        System.out.println();

        //Prueba2: Hay pocos elementos en el ranking
        ordenadorFacil.create("Pedro",3000);
        ordenadorFacil.create("Lola",34);
        ordenadorFacil.create("Pepe",20);
        JSONObject pedro=new JSONObject();
        pedro.put("puntuacion",3000);
        pedro.put("nombre","Pedro");

        JSONObject lola=new JSONObject();
        lola.put("puntuacion",34);
        lola.put("nombre","Lola");

        JSONObject pepe=new JSONObject();
        pepe.put("puntuacion",20);
        pepe.put("nombre","Pepe");

        jsonVacio.add( pepe);
        jsonVacio.add( lola);
        jsonVacio.add(pedro);


        assertTrue(jsonVacio.equals(ordenadorFacil.cargarRanking()));

        //Prueba3: Hay diez elementos en el ranking
        JSONArray json2=new JSONArray();
        ordenadorFacil.create("Pedro",3000);
        ordenadorFacil.create("Lola",34);
        ordenadorFacil.create("Pepe",20);

        ordenadorFacil.create("Pedro",3000);
        ordenadorFacil.create("Lola",34);
        ordenadorFacil.create("Pepe",20);
        ordenadorFacil.create("Pepe",20);

        json2.add( pepe);json2.add( pepe);json2.add( pepe);json2.add( pepe);
        json2.add( lola);json2.add( lola);json2.add( lola);
        json2.add(pedro); json2.add(pedro); json2.add(pedro);

        assertTrue(json2.equals(ordenadorFacil.cargarRanking()));

        //Prueba4: Hay más de diez elementos en el ranking

        ordenadorFacil.create("Pedro",3000);


        assertTrue(json2.equals(ordenadorFacil.cargarRanking()));

    }

    @Test
    public void create() throws SQLException {
        OrdenadorFDAOImpl ordenadorFacil = this.ordenadorFacil;
        assertNotNull(conexionM);

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

        conexionM.closeConnection();
    }
}