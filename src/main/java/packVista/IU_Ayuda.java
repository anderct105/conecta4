package packVista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.json.simple.JSONObject;
import packControlador.GestorIdiomas;

public class IU_Ayuda {
    @FXML
    private Button volverInicio;
    @FXML
    private Label ins;
    @FXML
    private TextArea instrucciones;

    public void idioma(){
        JSONObject frases = GestorIdiomas.getmGestorIdiomas().getIdiomaActual();
        volverInicio.setText((String)frases.get("volver_inicio"));
        ins.setText((String)frases.get("instrucciones"));
        instrucciones.setText((String)frases.get("texto_instrucciones"));
    }

    @FXML
    public void initialize(){
        idioma();
    }

    @FXML
    private void volverInicio(){
        Stage stage = (Stage) volverInicio.getScene().getWindow();
        stage.close();
    }
}
