package packVista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import packControlador.Conecta4;
import packControlador.GestorIdiomas;

import java.awt.geom.GeneralPath;
import java.io.IOException;

public class IU_RegistrarPartida {

    @FXML
    private TextField nombreU;
    @FXML
    private TextField puntuacionU;
    @FXML
    private Text textoRegistrar;
    @FXML
    private TextArea nombre;
    @FXML
    private TextArea puntuacion;
    @FXML
    private Button guardar;
    @FXML
    private Button volverInicio;


    private IU_TerminarPartida iu_terminarPartida;


    public void idioma(){
        JSONObject frases = GestorIdiomas.getmGestorIdiomas().getIdiomaActual();
        textoRegistrar.setText((String)frases.get("texto_registrar"));
        nombre.setText((String)frases.get("nombre_registrar"));
        puntuacion.setText((String)frases.get("puntuacion_registrar"));
        guardar.setText((String)frases.get("guardar"));
        volverInicio.setText((String)frases.get("volver_inicio"));
    }
    @FXML
    public void initialize() {
        idioma();
    }

    @FXML
    void pulsarGuardar(ActionEvent event){
        registrarPartida();
        abrirVentana();
        cerrarVentana();
    }

    @FXML
    void volverInicio(ActionEvent event){
        cerrarVentana();
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
        iu_terminarPartida.setCerrarTodo(true);
        /*
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
        */

    }

    public void setPuntuacionU(int punt){
        this.puntuacionU.setText(""+punt+"");
    }

    public void setIu_terminarPartida(IU_TerminarPartida iu_terminarPartida) {
        this.iu_terminarPartida = iu_terminarPartida;
    }
}