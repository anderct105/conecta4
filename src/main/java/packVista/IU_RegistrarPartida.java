package packVista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import packControlador.Conecta4;

import java.awt.*;

public class IU_RegistrarPartida {

    @FXML
    private TextField nombreU;
    @FXML
    private TextField puntuacionU;

    @FXML
    void pulsarGuardar(ActionEvent event){
        registrarPartida();
        //Cerrar la ventana actual y abrir el IU_Menu
    }

    @FXML
    void volverInicio(ActionEvent event){
        //Cerrar la ventana actual y abrir el IU_Menu
    }

    public void registrarPartida(){
        String nombre = nombreU.getText();
        int puntuacion = Integer.parseInt(puntuacionU.getText());
        Conecta4.getmConecta4().guardarPartida(nombre, puntuacion);
    }
}