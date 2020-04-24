package packVista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import packControlador.Conecta4;
import java.io.IOException;

public class IU_RegistrarPartida {

    @FXML
    private TextField nombreU;
    @FXML
    private TextField puntuacionU;

    @FXML
    void pulsarGuardar(ActionEvent event){
        registrarPartida();
        cerrarVentana();
        abrirVentana();
    }

    @FXML
    void volverInicio(ActionEvent event){
        cerrarVentana();
        abrirVentana();
    }

    public void registrarPartida(){
        String nombre = nombreU.getText();
        int puntuacion = Integer.parseInt(puntuacionU.getText());
        Conecta4.getmConecta4().guardarPartida(nombre, puntuacion);
    }

    //Cerrar ventana JavaFX
    public void cerrarVentana(){
        Stage stage = (Stage) nombreU.getScene().getWindow();
        stage.close();
    }

    //Abrir IU_Menu JavaFX
    public void abrirVentana() {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Conecta 4");
        primaryStage.setScene(new Scene(root, 1100, 600));
        primaryStage.show();
    }

    public void setPuntuacionU(int punt){
        this.puntuacionU.setText(""+punt+"");
    }

}