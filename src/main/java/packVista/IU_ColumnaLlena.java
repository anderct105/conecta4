package packVista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class IU_ColumnaLlena {

    @FXML
    private Button aceptar;
    @FXML
    private Label mensaje;

    /*  El siguiente código es necesario para la transparencia
        primaryStage.initStyle(StageStyle.UNDECORATED);
        s.setFill(Color.TRANSPARENT);
        primaryStage.setScene(s);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Tamaño ventana: 291, 100
     */

    @FXML
    public void initialize() {
        mensaje.setText("Esta columna está llena. Elige otra.");
    }

    @FXML
    public void cerrar(){
        Stage stage = (Stage) aceptar.getScene().getWindow();
        stage.close();
    }

}