package packVista;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packControlador.Conecta4;
import packMain.Main;

import java.io.IOException;

public class IU_Menu extends Stage {

    @FXML
    public AnchorPane entryAnimation;
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
    private Label modo;
    @FXML
    private ProgressBar pb;

    @FXML
    public void initialize() {
        if (!Main.animacionInicio){
            entryAnimation.setVisible(false);
        }else {
            Task<Void> t = new Task<Void>() {
                @Override
                protected Void call() {
                    return null;
                }
            };
            pb.progressProperty().unbind();
            pb.progressProperty().bind(t.progressProperty());
            Thread th = new Thread(t);
            th.setDaemon(true);
            th.run();
        }
        shakeStage();
        modo.setText("Modo: " + Conecta4.getmConecta4().getModoJuego());
        table_facil.setSelectionModel(null);
        table_dificil.setSelectionModel(null);
        table_facil.setItems(obtenerModelo(true));
        table_dificil.setItems(obtenerModelo(false));
        table_facil.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table_dificil.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        if (Main.animacionInicio) {
            animacionInicial();
            Main.animacionInicio = false;
        }
    }


    public void animacionInicial() {
        Thread t2 = new Thread() {
            public void run() {
                try {
                    while (!entryAnimation.getScene().getWindow().isShowing()) {
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException | NullPointerException e) {
                }
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Timeline timeline = new Timeline();
                KeyFrame key = new KeyFrame(Duration.millis(2000),
                        new KeyValue(entryAnimation.translateYProperty(), -2000,Interpolator.EASE_IN));
                timeline.getKeyFrames().add(key);
                timeline.setOnFinished(event -> {entryAnimation.setVisible(false);});
                timeline.play();
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //entryAnimation.setVisible(false);
            }
        };

        t2.start();
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
        primero.setCellValueFactory(new PropertyValueFactory<Partida, String>("nombre"));
        segundo.setCellValueFactory(new PropertyValueFactory<Partida, String>("puntuacion"));
        ObservableList data = FXCollections.observableArrayList();
        for (Object o : ja) {
            JSONObject jo = (JSONObject) o;
            Partida p = new Partida((String) jo.get("nombre"), "" + jo.get("puntuacion"));
            data.add(p);
        }
        return data;
    }

    @FXML
    public void config() {
        RotateTransition rt = new RotateTransition(Duration.millis(1000), config);
        rt.setByAngle(180);
        rt.setCycleCount(1);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                oscurecerFondo();
            }
        });
        rt.play();
    }

    public void oscurecerFondo() {
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(-0.5);
        pane.getScene().getRoot().setEffect(ca);
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Configurar.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Conecta 4");
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                ColorAdjust ca = new ColorAdjust();
                ca.setBrightness(0);
                pane.getScene().getRoot().setEffect(ca);
            }
        });

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

    }

    @FXML
    public void jugar(){
        FadeTransition ft = new FadeTransition(Duration.millis(2500), pane);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cambiarATablero();
            }
        });
        ft.play();

    }

    public void cambiarATablero() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Tablero.fxml"));
            pane.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}