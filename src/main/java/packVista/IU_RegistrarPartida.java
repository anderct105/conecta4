package packVista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import packControlador.Conecta4;

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


	public void idioma() {
		JSONObject frases = GestorIdiomas.getmGestorIdiomas().getIdiomaActual();
		textoRegistrar.setText((String) frases.get("texto_registrar"));
		nombre.setText((String) frases.get("nombre_registrar"));
		puntuacion.setText((String) frases.get("puntuacion_registrar"));
		guardar.setText((String) frases.get("guardar"));
		volverInicio.setText((String) frases.get("volver"));
	}

	@FXML
	public void initialize() {
		idioma();
		nombreU.setPromptText("");
	}

	@FXML
	void pulsarGuardar(ActionEvent event) {
		JSONObject frases = GestorIdiomas.getmGestorIdiomas().getIdiomaActual();
		boolean valido = validarNombre();
		if (valido) {
			registrarPartida();
			abrirVentana();
			cerrarVentana();
		} else {
			nombreU.setText("");
			nombreU.setStyle("-fx-border-color: #c70005;");
			nombreU.setPromptText((String) frases.get("largura"));
		}
	}

	@FXML
	void volverInicio(ActionEvent event) {
		cerrarVentana();
	}

	public void registrarPartida() {
		String nombre = nombreU.getText();
		String[] punt = puntuacionU.getText().split(" ");
		int puntuacion = Integer.parseInt(punt[0]);
		Conecta4.getmConecta4().guardarPartida(nombre, puntuacion);
	}

	//Cerrar ventana JavaFX
	public void cerrarVentana() {
		Stage stage = (Stage) nombreU.getScene().getWindow();
		stage.close();
	}

	//Abrir IU_Menu JavaFX
	public void abrirVentana() {
		iu_terminarPartida.setCerrarTodo(true);
	}

	public void setPuntuacionU(int punt) {
		JSONObject frases = GestorIdiomas.getmGestorIdiomas().getIdiomaActual();
		this.puntuacionU.setText("" + punt + " " + frases.get("segundos"));
	}

	public void setIu_terminarPartida(IU_TerminarPartida iu_terminarPartida) {
		this.iu_terminarPartida = iu_terminarPartida;
	}

	private boolean validarNombre() {
		boolean valido = true;
		String nombre = nombreU.getText();
		if (1 > nombre.length() || nombre.length() > 20) {
			valido = false;
		}
		return valido;
	}
}