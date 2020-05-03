package packVista;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.json.simple.JSONObject;
import packControlador.Conecta4;

import java.io.IOException;

public class IU_TerminarPartida {
    @FXML
    private Button guardar;
    @FXML
    private Button salir;
    @FXML
    private Pane imagen;
    @FXML
    private Label puntuacion;

    private int tiempo;
    private int resultado;
    private boolean cerrarTodo = false;

    public IU_TerminarPartida() {
    }

    public void idioma(){
        JSONObject frases = GestorIdiomas.getmGestorIdiomas().getIdiomaActual();
        guardar.setText((String)frases.get("guardar_partida"));
        salir.setText((String)frases.get("volver_inicio"));
    }


    @FXML
    public void initialize() {
    }

    @FXML
    public void guardar() {
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(-0.5);
        guardar.getScene().getRoot().setEffect(ca);
        guardar.getScene().getRoot().setDisable(false);
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistrarPartida.fxml"));
        try {
            primaryStage.setScene(new Scene((Parent) loader.load(), 520, 300));
        } catch (IOException e) {
            e.printStackTrace();
        }
        IU_RegistrarPartida iu = loader.<IU_RegistrarPartida>getController();
        iu.setPuntuacionU(tiempo);
        iu.setIu_terminarPartida(this);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getScene().setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Stage sAct = (Stage) guardar.getScene().getWindow();
        double centerXPosition = sAct.getX() + sAct.getWidth() / 2;
        double centerYPosition = sAct.getY() + sAct.getHeight() / 2;
        primaryStage.setX(centerXPosition - 520 / 2);
        primaryStage.setY(centerYPosition - 300 / 2);
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                ColorAdjust ca1 = new ColorAdjust();
                ca1.setBrightness(0);
                guardar.getScene().getRoot().setEffect(ca1);
                if (cerrarTodo) {
                    cerrar();
                }
            }
        });
        primaryStage.show();
    }

    @FXML
    public void cerrar() {
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
    }

    public void inicializar(int tiempo, int resultado) {
        idioma();
        if(resultado==1){
            this.tiempo = tiempo;
        }
        else{
            this.tiempo = 0;
           guardar.setVisible(false);
        }

        this.resultado = resultado;
        ponerPuntuacion();
        ponerImagen();
    }

    public void ponerPuntuacion() {
        puntuacion.setText((String)GestorIdiomas.getmGestorIdiomas().getIdiomaActual().get("puntuacion") + tiempo);
    }

    public void ponerImagen() {
        Image image;
        switch (resultado) {

            case 0:
                if (Conecta4.getmConecta4().getModoJuego().equals("1vs1")) {
                    image = new Image("/imagenes/ganadoRojo.gif",
                            600, 400, false, false);
                    puntuacion.setVisible(false);
                    guardar.setVisible(false);
                }
                else {
                    image = new Image("/imagenes/derrota.jpg",
                            600, 400, false, false);
                    guardar.setVisible(false);
                }
                break;
            case 1:
                if (Conecta4.getmConecta4().getModoJuego().equals("1vs1")) {
                    image = new Image("/imagenes/ganadoAzul.gif",
                            600, 400, false, false);
                    puntuacion.setVisible(false);
                    guardar.setVisible(false);
                }
                else{
                    image = new Image("/imagenes/ganadoRojo.gif",
                            600, 400, false, false);
                }

                break;
            default:
                image = new Image("/imagenes/empate.png",
                        600, 400, false, false);
                puntuacion.setVisible(false);
                guardar.setVisible(false);
                break;
        }
        imagen.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        guardar.setDisable(Conecta4.getmConecta4().getModoJuego().equals("1vs1"));
    }

    public void setCerrarTodo(boolean cT) {
        this.cerrarTodo = cT;
    }
}