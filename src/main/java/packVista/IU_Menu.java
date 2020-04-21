package packVista;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packControlador.Conecta4;

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
        table_facil.setSelectionModel(null);
        table_dificil.setSelectionModel(null);
        table_facil.setItems(obtenerModelo(true));
        table_dificil.setItems(obtenerModelo(false));
        table_facil.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table_dificil.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public ObservableList obtenerModelo(boolean facil) {
        TableColumn primero;
        TableColumn segundo;
        JSONArray ja;
        if (facil) {
            primero = (TableColumn) table_facil.getColumns().get(0);
            segundo = (TableColumn) table_facil.getColumns().get(1);
            ja = Conecta4.getmConecta4().cargarRankingFacil();
        } else {
            primero = (TableColumn) table_dificil.getColumns().get(0);
            segundo = (TableColumn) table_dificil.getColumns().get(1);
            ja = Conecta4.getmConecta4().cargarRankingDificil();
        }
        primero.setCellValueFactory(new PropertyValueFactory<Partida,String>("nombre"));
        segundo.setCellValueFactory(new PropertyValueFactory<Partida,String>("puntuacion"));
        ObservableList data = FXCollections.observableArrayList();
        for (Object o : ja) {
            JSONObject jo = (JSONObject) o;
            Partida p = new Partida((String)jo.get("nombre"),"" + jo.get("puntuacion"));
            data.add(p);
        }
        return data;
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