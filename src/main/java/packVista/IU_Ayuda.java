package packVista;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class IU_Ayuda {
    @FXML
    private Button volverInicio;

    @FXML
    private void volverInicio(){
        Stage stage = (Stage) volverInicio.getScene().getWindow();
        stage.close();
    }
}
