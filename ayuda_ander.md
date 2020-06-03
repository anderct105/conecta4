Conecta4

#Cerrar ventana JavaFX - Añadir el import {javafx.scene.control."El tipo del elemento pane"}
        (pane es cualquie elemento de la ventana)
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
        
#Abrir cualquier ventana JavaFX

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