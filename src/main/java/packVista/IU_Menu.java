package packVista;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packControlador.Conecta4;

import java.util.HashMap;
import java.util.Map;

public class IU_Menu {

    @FXML
    private TableView table_facil;
    @FXML
    private TableView table_dificil;
    @FXML
    private ImageView config;

    @FXML
    public void initialize() {
        cargarRankingFacil();
    }

    public void cargarRankingFacil() {
        JSONArray ja = Conecta4.getmConecta4().cargarRankingFacil();
        /*ObservableList<String> nombres = FXCollections.observableArrayList();
        ObservableList<String> puntuaciones = FXCollections.observableArrayList();
        for (Object o : ja) {
            JSONObject j = (JSONObject) o;
            Partida p = new Partida((String)j.get("nombre"),(int)j.get("puntuacion"));
            table_facil.getItems().add(p);
        }*/
        ObservableList<String> nombres = FXCollections.observableArrayList("ander");
        table_facil.getItems().add("ander");
    }

    @FXML
    public void config() {
        RotateTransition rt = new RotateTransition(Duration.millis(1000),config);
        rt.setByAngle(180);
        rt.setCycleCount(1);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }
}