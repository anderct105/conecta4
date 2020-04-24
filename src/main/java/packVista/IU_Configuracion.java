package packVista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import packControlador.Conecta4;


public class IU_Configuracion {

    @FXML
    private Button guardar;
    @FXML
    private Button cerrar;
    @FXML
    private ComboBox modoJuego;


    @FXML
    public void initialize(){
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "1vs1",
                        "Ordenador modo Facil",
                        "Ordenador modo Dificil"
                );
        modoJuego = new ComboBox(options);
    }

    @FXML
    public void guardar(){
        String modoElegido = (String) modoJuego.getValue();
        Conecta4.getmConecta4().setModoJuego(modoElegido);
    }

    @FXML
    public void cerrar(){
        Stage stage = (Stage) cerrar.getScene().getWindow();
        stage.close();
    }


}