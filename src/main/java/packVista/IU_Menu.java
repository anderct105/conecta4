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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packControlador.Conecta4;
import packControlador.GestorPartidas;
import packMain.Main;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class IU_Menu extends Stage implements Observer {

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
    private ImageView title;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label modo;
    @FXML
    private ProgressBar pb;
    @FXML
    private Button ayuda;
    @FXML
    private Tab rankingFacil;
    @FXML
    private Tab rankingDificil;
    @FXML
    private ImageView idi_es;
    @FXML
    private ImageView idi_eu;

    public void idioma(){
        JSONObject frases = GestorIdiomas.getmGestorIdiomas().getIdiomaActual();
        ayuda.setText((String)frases.get("ayuda"));
        TableColumn a = (TableColumn)table_facil.getColumns().get(0);
        a.setText((String)frases.get("nombre"));
        a = (TableColumn)table_facil.getColumns().get(1);
        a.setText((String)frases.get("tiempo"));
        a = (TableColumn)table_dificil.getColumns().get(0);
        a.setText((String)frases.get("nombre"));
        a = (TableColumn)table_dificil.getColumns().get(1);
        a.setText((String)frases.get("tiempo"));
        table_facil.setPlaceholder(new Label((String)frases.get("tabla_vacia")));
        table_dificil.setPlaceholder(new Label((String)frases.get("tabla_vacia")));
        rankingDificil.setText((String)frases.get("dificil"));
        rankingFacil.setText((String)frases.get("facil"));
        setModoJuego();
    }


    @FXML
    public void initialize() {
        if (!Main.animacionInicio) {
            entryAnimation.setVisible(false);
        } else {
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
        listenerIdiomas();
        setModoJuego();
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
        GestorPartidas.getmGestorPartidas().addObserver(this);
        idioma();
    }

    public void listenerIdiomas(){
        idi_es.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                GestorIdiomas.getmGestorIdiomas().setIdioma(0);
                idioma();
            }
        });
        idi_eu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                GestorIdiomas.getmGestorIdiomas().setIdioma(1);
                idioma();
            }
        });
    }

    public void animacionTitulo() {
        ScaleTransition sc = new ScaleTransition();
        sc.setFromX(title.getScaleX());
        sc.setToX(title.getScaleX() + 0.3);
        sc.setFromY(title.getScaleY());
        sc.setToY(title.getScaleY() + 0.3);
        sc.setFromZ(title.getScaleZ());
        sc.setToZ(title.getScaleZ() + 0.2);
        sc.setNode(title);
        sc.setDuration(new Duration(1200));
        sc.setAutoReverse(true);
        sc.setCycleCount(Animation.INDEFINITE);
        sc.play();


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
                        new KeyValue(entryAnimation.translateYProperty(), -2000, Interpolator.EASE_IN));
                timeline.getKeyFrames().add(key);
                timeline.setOnFinished(event -> {
                    entryAnimation.setVisible(false);
                    animacionTitulo();
                });
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
                cambiarAConfiguracion();
            }
        });
        rt.play();


    }

    private void cambiarAConfiguracion() {
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(-0.5);
        pane.getScene().getRoot().setEffect(ca);
        pane.getScene().getRoot().setDisable(true);
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Configuracion.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 360, 120));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getScene().setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Stage sAct = (Stage) pane.getScene().getWindow();
        double centerXPosition = sAct.getX() + sAct.getWidth() / 2;
        double centerYPosition = sAct.getY() + sAct.getHeight() / 2;
        primaryStage.setX(centerXPosition - 360 / 2);
        primaryStage.setY(centerYPosition - 120 / 2);
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                ColorAdjust ca = new ColorAdjust();
                ca.setBrightness(0);
                pane.getScene().getRoot().setEffect(ca);
                pane.getScene().getRoot().setDisable(false);
            }
        });
        primaryStage.show();
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

    @FXML
    public void jugar() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
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
            ColorAdjust c = new ColorAdjust();
            c.setBrightness(1);
            //root.setEffect(c);
            FadeTransition ft = new FadeTransition(Duration.millis(2500), root);
            ft.setFromValue(0);
            ft.setToValue(1.0);
            //ft.play();
            root.setOpacity(0);
            pane.getScene().setRoot(root);
            Timeline t = new Timeline();
            KeyFrame kf = new KeyFrame(Duration.millis(1000), new KeyValue(root.opacityProperty(), 1,Interpolator.EASE_IN));
            t.getKeyFrames().add(kf);
            t.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        setModoJuego();
    }

    public void setModoJuego() {
        JSONObject frases = GestorIdiomas.getmGestorIdiomas().getIdiomaActual();
        String modoJuego = Conecta4.getmConecta4().getModoJuego();
        if(GestorIdiomas.getmGestorIdiomas().getIdioma(0).get("ordenador_facil").equals(modoJuego)){
            modo.setText(frases.get("modo") + (String)GestorIdiomas.getmGestorIdiomas().getIdiomaActual().get("ordenador_facil"));
        }
        else if(GestorIdiomas.getmGestorIdiomas().getIdioma(0).get("ordenador_dificil").equals(modoJuego)){
            modo.setText(frases.get("modo") + (String)GestorIdiomas.getmGestorIdiomas().getIdiomaActual().get("ordenador_dificil"));
        }
        else{
            modo.setText(frases.get("modo") + "1vs1");
        }
    }

    @FXML
    private void ayuda(){
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(-0.5);
        pane.getScene().getRoot().setEffect(ca);
        pane.getScene().getRoot().setDisable(true);
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Ayuda.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(root, 540, 290);
        primaryStage.setScene(s);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        s.setFill(Color.TRANSPARENT);
        primaryStage.setScene(s);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Stage sAct = (Stage) pane.getScene().getWindow();
        double centerXPosition = sAct.getX() + sAct.getWidth() / 2;
        double centerYPosition = sAct.getY() + sAct.getHeight() / 2;
        primaryStage.setX(centerXPosition - 560 / 2);
        primaryStage.setY(centerYPosition - 310 / 2);
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                ColorAdjust ca = new ColorAdjust();
                ca.setBrightness(0);
                pane.getScene().getRoot().setEffect(ca);
                pane.getScene().getRoot().setDisable(false);
            }
        });
        primaryStage.show();
    }
}