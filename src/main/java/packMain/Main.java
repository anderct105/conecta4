package packMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import packVista.GestorIdiomas;

public class Main extends Application {

	public static boolean animacionInicio = true;

	public static double volumen = 75;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GestorIdiomas.getmGestorIdiomas().setIdioma(0);
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
		primaryStage.setTitle("Conecta 4");
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, 1100, 600));
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX((screenBounds.getWidth() - 1100) / 2);
		primaryStage.setY((screenBounds.getHeight() - 600) / 2);
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/imagenes/Conecta4_Icono4.png")));
		primaryStage.setOnHiding(event -> System.exit(0));
		primaryStage.show();
	}
}
