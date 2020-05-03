package packControlador;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packDao.OrdenadorDDAOImpl;
import packDao.OrdenadorFDAOImpl;
import packModelo.Juego;
import packModelo.Modo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Observable;

public class GestorIdiomas extends Observable {

    private static GestorIdiomas mGestorIdiomas;

    private int idioma;


    private GestorIdiomas() {
    }

    public static GestorIdiomas getmGestorIdiomas() {
        if (mGestorIdiomas == null) {
            mGestorIdiomas = new GestorIdiomas();
        }
        return mGestorIdiomas;
    }

    public void setIdioma(int idioma){
        this.idioma = idioma;
    }

    public JSONObject getIdioma(int idi){
        if(idi == 0){
            return castellano();
        }
        else{
            return euskera();
        }
    }

    public JSONObject getIdiomaActual(){
        if(idioma == 0){
            return castellano();
        }
        else{
            return euskera();
        }
    }

    public JSONObject castellano(){
        JSONObject frases = new JSONObject();
        frases.put("nombre", "Nombre");
        frases.put("tiempo", "Tiempo");
        frases.put("facil", "Facil");
        frases.put("dificil", "Dificil");
        frases.put("ayuda", "Ayuda");
        frases.put("tabla_vacia", "No hay partidas");
        frases.put("ordenador_facil", "Ordenador modo Facil");
        frases.put("ordenador_dificil", "Ordenador modo Dificil");
        frases.put("guardar", "Guardar");
        frases.put("cerrar", "Cerrar");
        frases.put("modo_juego", "Modo de juego:");
        frases.put("modo", "Modo: ");
        return frases;
    }

    public JSONObject euskera(){
        JSONObject frases = new JSONObject();
        frases.put("nombre", "Izena");
        frases.put("tiempo", "Denbora");
        frases.put("facil", "Erraza");
        frases.put("dificil", "Zaila");
        frases.put("ayuda", "Laguntza");
        frases.put("tabla_vacia", "Ez dago partidarik");
        frases.put("ordenador_facil", "Ordenailu modu Erraza");
        frases.put("ordenador_dificil", "Ordenagailu modu Zaila");
        frases.put("guardar", "Gorde");
        frases.put("cerrar", "Itzi");
        frases.put("modo_juego", "Jolas modua:");
        frases.put("modo", "Modua: ");
        return frases;
    }


}

