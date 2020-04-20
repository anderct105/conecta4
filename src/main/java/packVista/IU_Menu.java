package packVista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packControlador.Conecta4;

public class IU_Menu {

    @FXML
    private TableView table_facil;
    @FXML
    private TableView table_dificil;

    @FXML
    public void initialize() {
        Conecta4.getmConecta4().setModoJuego("Ordenador modo Facil");
        Conecta4.getmConecta4().guardarPartida("ander",20);
        cargarRankingFacil();
    }

    public void cargarRankingFacil() {
        JSONArray ja = Conecta4.getmConecta4().cargarRankingFacil();
        ObservableList<String> nombres = FXCollections.observableArrayList();
        ObservableList<String> puntuaciones = FXCollections.observableArrayList();
        for (Object o : ja) {
            JSONObject j = (JSONObject) o;
            nombres.add((String)j.get("nombre"));
            puntuaciones.add("" + (int)j.get("puntuacion"));
        }
        table_facil.setItems(nombres);
    }

}