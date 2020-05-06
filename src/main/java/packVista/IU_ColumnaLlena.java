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

	@FXML
	public void initialize() {
		mensaje.setText("Esta columna está llena. Elige otra.");
	}

	@FXML
	public void cerrar() {
		Stage stage = (Stage) aceptar.getScene().getWindow();
		stage.close();
	}
}