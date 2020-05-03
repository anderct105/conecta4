package packVista;

import org.json.simple.JSONObject;

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
        frases.put("volver_inicio", "Volver al inicio");
        frases.put("instrucciones", "INSTRUCCIONES");
        frases.put("texto_instrucciones", "1. Cada jugador, a su turno, juega una ficha de su color en la \n" +
                "columna de su elección.\n" +
                "2. El primer jugador que consigue alinear 4 fichas de su color, \n" +
                "horizontalmente, verticalmente o en diagonal gana la partida. \n" +
                "3. Si el tablero se llena completamente sin que ningún jugador \n" +
                "logre alinear 4 fichas de su color, la partida es declarada empate.");
        frases.put("musica", "MUSICA:");
        frases.put("sonido", "SONIDO:");
        frases.put("terminar", "Terminar partida");
        frases.put("siguiente_cancion", "Siguiente cancion");
        frases.put("turno", "Turno");
        frases.put("jugador_rojo", "Jugador rojo");
        frases.put("jugador_azul", "Jugador azul");
        frases.put("guardar_partida", "Guardar partida");
        frases.put("puntuacion", "La puntuacion obtenida es: ");
        frases.put("texto_registrar", "Escriba el nombre que quiere que se vea en el ranking:");
        frases.put("nombre_registrar", "Nombre:");
        frases.put("puntuacion_registrar", "Puntuación:");
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
        frases.put("volver_inicio", "Itzuli hasierara");
        frases.put("instrucciones", "ARGIBIDEAK");
        frases.put("texto_instrucciones", "1. Jokalari bakoitzak, bere txandan, bere koloreko fitxa\n" +
                "bat jartzen du nahi duen zutabean.\n" +
                "2. Bere koloreko 4 fitxa horizontalean, bertikalean edo\n" +
                "diagonalean lerrokatzen dituen jokalariak partida irabazten du.\n" +
                "3. Taula guztiz betetzen bada inork bere koloreko 4\n" +
                "fitxa lerrokatu gabe, partida berdinketan bukkatzen du.");
        frases.put("musica", "MUSIKA:");
        frases.put("sonido", "SOINUA:");
        frases.put("terminar", "Bukatu partida");
        frases.put("siguiente_cancion", "Hurrengo abestia");
        frases.put("turno", "Txanda");
        frases.put("jugador_rojo", "Jokalari gorria");
        frases.put("jugador_azul", "Jokalari urdina");
        frases.put("guardar_partida", "Gorde partida");
        frases.put("puntuacion", "Lortutako puntuazioa da: ");
        frases.put("texto_registrar", "Idatzi ranking-ean agertzea nahi duzun izena");
        frases.put("nombre_registrar", "Izena:");
        frases.put("puntuacion_registrar", "Puntuazioa:");
        return frases;
    }


}

