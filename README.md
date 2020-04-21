Conecta4

#Cerrar ventana JavaFX
        (pane es cualquie elemento de la ventana)
        Window w = pane.getScene().getWindow();
        ((Stage) w).close();
        
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