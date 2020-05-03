package packVista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import packControlador.Conecta4;


public class IU_Configuracion {

    @FXML
    private Button guardar;
    @FXML
    private Button cerrar;
    @FXML
    private ComboBox modoJuego;
    @FXML
    private Text modoLabel;

    public void idioma(){
        JSONObject frases = GestorIdiomas.getmGestorIdiomas().getIdiomaActual();
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "1vs1",
                        (String)frases.get("ordenador_facil"),
                        (String)frases.get("ordenador_dificil")
                );
        modoJuego.setValue(Conecta4.getmConecta4().getModoJuego());
        modoJuego.setItems(options);

        guardar.setText((String)frases.get("guardar"));
        cerrar.setText((String)frases.get("cerrar"));
        modoLabel.setText((String)frases.get("modo_juego"));
    }


    @FXML
    public void initialize(){
        idioma();
        guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                guardar();
                cerrar();
            }
        });
    }

    @FXML
    public void guardar(){
        String modoElegido = (String) modoJuego.getValue();
        for (int i=0;i<2;i++){
            JSONObject idioma = GestorIdiomas.getmGestorIdiomas().getIdioma(i);
            if(idioma.get("ordenador_facil").equals(modoElegido)){
                modoElegido = (String)GestorIdiomas.getmGestorIdiomas().getIdioma(0).get("ordenador_facil");
            }
            if(idioma.get("ordenador_dificil").equals(modoElegido)){
                modoElegido = (String)GestorIdiomas.getmGestorIdiomas().getIdioma(0).get("ordenador_dificil");
            }
        }
        Conecta4.getmConecta4().setModoJuego(modoElegido);
    }

    @FXML
    public void cerrar(){
        Stage stage = (Stage) cerrar.getScene().getWindow();
        stage.close();
    }


}