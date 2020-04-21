package packVista;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packControlador.Conecta4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IU_Menu extends Stage {

    @FXML
    private TableView table_facil;
    @FXML
    private TableView table_dificil;
    @FXML
    private ImageView config;
    @FXML
    private AnchorPane botones;
    @FXML
    private ImageView play;
    @FXML
    private Label title;
    @FXML
    private AnchorPane pane;

    @FXML
    public void initialize() {
        shakeStage();
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

    @FXML
    public void rotateBorder() {

    }

    @FXML
    public void hoverPlay() {
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(0.5);
        play.setEffect(ca);
    }

    @FXML
    public void quitHoverPlay() {
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(0);
        play.setEffect(ca);
    }

    @FXML
    public void hoverConfig() {
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(0.5);
        config.setEffect(ca);
    }

    @FXML
    public void quitHoverConfig() {
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(0);
        config.setEffect(ca);
    }

    public void shakeStage() {
       ShakeTransition st = new ShakeTransition(title);
       st.play();
    }
}