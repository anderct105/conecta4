package packVista;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import packControlador.Conecta4;

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

    public IU_TerminarPartida() {
    }


    @FXML
    public void initialize(){

    }

    @FXML
    public void guardar(){
        // llamada a guardar partida
    }
    @FXML
    public void cerrar(){
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
    }

    public void inicializar(int tiempo, int resultado){
        this.tiempo = tiempo;
        this.resultado = resultado;
        ponerPuntuacion();
        ponerImagen();
    }

    public void ponerPuntuacion() {
        puntuacion.setText("La puntuacion obtenida es: "+tiempo);
    }

    public void ponerImagen() {
        Image image;
        switch (resultado) {
            case 0: if(Conecta4.getmConecta4().getModoJuego().equals("1vs1"))
                image = new Image("/imagenes/ganadoAzul.gif",
                    415,275,false,false);
            else  image = new Image("/imagenes/derrota.jpg",
                        415,275,false,false);
                break;
            case 1: image = new Image("/imagenes/ganadoRojo.gif",
                    415,275,false,false); break;
            default: image = new Image("/imagenes/empate.jpeg",
                    415,275,false,false); break;
        }
        imagen.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        guardar.setDisable(Conecta4.getmConecta4().getModoJuego().equals("1vs1"));
    }
}