package packVista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class IU_Configuracion extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../animations.fxml"));
        primaryStage.setTitle("Conecta 4");
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}