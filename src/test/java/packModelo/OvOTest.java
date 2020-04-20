package packModelo;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OvOTest {
    private OvO ovo;

    @Before
    public void setUp() throws Exception {
        ovo = new OvO();
        Tablero.getmTablero().inicializarTablero();
        Tablero.getmTablero().imprimirTablero();
    }

    @After
    public void tearDown() throws Exception {
        ovo = null;
        Tablero.getmTablero().inicializarTablero();
    }

    @Test
    public void jugar() {
        //Prueba con columna llena
        Tablero.getmTablero().introducirFicha(0,true);
        Tablero.getmTablero().introducirFicha(0,false);
        Tablero.getmTablero().introducirFicha(0,true);
        Tablero.getmTablero().introducirFicha(0,true);
        Tablero.getmTablero().introducirFicha(0,false);
        Tablero.getmTablero().introducirFicha(0,true);

        assertNull(ovo.jugar(0));

        //Prueba columna con espacio pero no partida ganada
        Tablero.getmTablero().introducirFicha(1,true);
        Tablero.getmTablero().introducirFicha(1,false);
        Tablero.getmTablero().introducirFicha(1,true);

        JSONObject json = ovo.jugar(1);
        ArrayList pos = (ArrayList) json.get("posicionesGanadoras");
        assertEquals(pos.size(), 0);

        //Prueba columna con espacio y partida ganada
        Tablero.getmTablero().introducirFicha(2,true);
        Tablero.getmTablero().introducirFicha(3,false);
        Tablero.getmTablero().introducirFicha(2,false);

        JSONObject json1 = ovo.jugar(3);
        ArrayList pos1 = (ArrayList) json1.get("posicionesGanadoras");
        assertEquals(pos1.size(), 4);

        Tablero.getmTablero().imprimirTablero();
    }

    @Test
    public void getNombre() {
        assertNotNull(ovo.getNombre());
    }


}