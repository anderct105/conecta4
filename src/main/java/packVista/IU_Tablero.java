package packVista;

import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import packControlador.Conecta4;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.JSONObject;
import java.io.IOException;

public class IU_Tablero{

    @FXML
    private AnchorPane pane;
    @FXML
    private GridPane panelTablero;

    @FXML
    private Button BTerminarPartida;
    @FXML
    private AnchorPane PaneTiempo;
    @FXML
    private Label LabelTiempo;
    @FXML
    private Label NombreTurno;
    @FXML
    private GridPane PaneTemporizador;
    @FXML
    private TextField TimeHours;
    @FXML
    private TextField TimeMinutes;
    @FXML
    private TextField TimeSeconds;

    @FXML
    private Circle fichaRoja;
    @FXML
    private Circle fichaAzul;

    @FXML
    private AnchorPane PaneTurno;
    @FXML
    private Label LabelTurno;

    //Para marcar las fichas ganadoras
    private Circle fichaG;
    private Stop[] stopsG;
    private int xG;
    private int yG;

    private int secs=0;
    private int mins=0;
    private int hours=0;

    private boolean turno=true;

    Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
          // int secs=Integer.parseInt(TimeSeconds.getText());
           secs=secs+1;
           if(secs<10){
               TimeSeconds.setText("0"+(secs));
           }
           else if(secs==60){
               TimeSeconds.setText("00");
               secs=0;
               mins=mins+1;
               if(mins<10){
                   TimeMinutes.setText("0"+(mins));
               }
               else if(mins==60){
                   TimeMinutes.setText("00");
                   mins=0;
                   hours=hours+1;
                   if(hours<10){
                       TimeHours.setText("0"+(hours));
                   }
                   else {
                       TimeHours.setText(String.valueOf(hours));
                   }
               }
               else{
                   TimeMinutes.setText(String.valueOf(mins));
               }
           }
           else {
               TimeSeconds.setText(String.valueOf(secs));
           }

        }
    }));

    @FXML
    public void initialize() throws InterruptedException {
        //dependiendo del modo de juego se coloca el reloj o el panel del turno
        setModoJuego();
        listenerTerminarPartida();
        listenerTablero();

       Conecta4.getmConecta4().inicializarTablero();
       Tablero.getmTablero().registrarObservador(this);
        /*panelTablero.add(getFichaRoja(),1,1);
        panelTablero.add(getFichaAzul(),2,2);*/
       //Prueba marcarCasillas ganadoras
        panelTablero.add(getFichaRoja(),0,0);
        panelTablero.add(getFichaRoja(),0,1);
        panelTablero.add(getFichaRoja(),0,2);
        panelTablero.add(getFichaRoja(),0,3);

        JSONObject jo = new JSONObject();
        jo.put("lleno", false);
        jo.put("haGanadoA", true);
        jo.put("haGanadoB", false);
        JSONArray ja = new JSONArray();
        JSONObject o1 = new JSONObject();
        o1.put("x",0);
        o1.put("y",0);
        JSONObject o2 = new JSONObject();
        o2.put("x",0);
        o2.put("y",1);
        JSONObject o3 = new JSONObject();
        o3.put("x",0);
        o3.put("y",2);
        JSONObject o4 = new JSONObject();
        o4.put("x",0);
        o4.put("y",3);
        ja.add(o1); ja.add(o2); ja.add(o3); ja.add(o4);
        jo.put("posicionesGanadoras", ja);

        marcarGanadoras(jo);

        panelTablero.add(getFichaRoja(),1,1);
        panelTablero.add(getFichaAzul(),2,2);
    }

    private void setModoJuego() {
        String modoJuego =obtenerModoJuego();
        if (modoJuego.equals("1vs1")){
            PaneTiempo.setVisible(false);
            LabelTiempo.setVisible(false);
            setTurno();
        }
        else {
            PaneTurno.setVisible(false);
            fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            fiveSecondsWonder.play();
        }
    }

    //devuelve la ficha correspondiente al turno
    private Circle getFicha(boolean color){
        if(color){
            return getFichaRoja();
        }
        else {
            return getFichaAzul();
        }
    }

    private Circle getFichaRoja(){
        Circle ficha=new Circle();
        Stop[] stops = new Stop[] { new Stop(0, Color.rgb(255,105,153)), new Stop(1, Color.RED)};
        ficha.setRadius(31);

        ficha.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops));
        ficha.setOpacity(1);
        return ficha;
    }

    private Circle getFichaAzul(){
        Circle ficha=new Circle();
        Stop[] stops = new Stop[] { new Stop(0, Color.rgb(108,215,255)), new Stop(1, Color.rgb(61,73,224))};
        ficha.setRadius(31);

        ficha.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops));
        ficha.setOpacity(1);
        return ficha;
    }

    private void listenerTablero() {
        //se rellenan todas las posiciones con fichas transparentes para detectar el clic
      int filas=panelTablero.getRowConstraints().size();
      int columnas=panelTablero.getColumnConstraints().size();
      for(int i=0;i<filas;i++){
          for(int j=0;j<columnas;j++){
              Circle ficha = new Circle();
              ficha.setRadius(31);
              ficha.setFill(javafx.scene.paint.Color.RED);
              ficha.setOpacity(0);
              panelTablero.add(ficha,j,i);

          }
      }
        panelTablero.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                       int columna=GridPane.getColumnIndex(item);

                       //llamada al método jugar
                        /*int fila=*/jugar(columna,turno);
                        //colocarFicha(fila,columna);
                       // panelTablero.add(getFicha(turno),columna,5-0);
                    }
                   /* if (event.isPrimaryButtonDown()) {
                        System.out.println("PrimaryKey event");
                    }*/

                }
            });

        });
    }
    private void colocarFicha(int fila,int columna){
        fila=5-fila;
        panelTablero.add(getFicha(turno),columna,fila);
    }

    private int jugar(int pColumna, boolean turno) {
        int fila=0;
        Circle ficha = getFicha(turno);
        JSONObject json = Conecta4.getmConecta4().jugarPartida(pColumna);
        if (json == null){
            //IU_COLUMNA LLENA
        }
        else{
            //fila = (int)json.get("fila");
            //panelTablero.add(ficha,pColumna,5-fila);
        }



        int numFilas = panelTablero.getRowConstraints().size();
        int numColumnas = panelTablero.getColumnConstraints().size();


        return fila;
    }



    private void listenerTerminarPartida() {
        BTerminarPartida.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Parent root = null;
                try {
                    Node source = (Node) e.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                    root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
                    Stage primaryStage=new Stage();
                    primaryStage.setTitle("Conecta 4");
                    primaryStage.setScene(new Scene(root, 1100, 730));
                    primaryStage.show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    private void setTurno() {
        if (turno==true){
            NombreTurno.setText("Jugador rojo");
        }
        else{
            NombreTurno.setText("Jugador azul");
        }
    }


    private String obtenerModoJuego() {

        return Conecta4.getmConecta4().getModoJuego();
        // return "modo Vs ordenador";
    }

    private void marcarGanadoras(JSONObject jo){
        JSONArray ja = (JSONArray) jo.get("posicionesGanadoras");
        boolean ganadoA = (boolean) jo.get("haGanadoA");
        boolean ganadoB = (boolean) jo.get("haGanadoB");
        for (int i = 0; i < ja.size(); i++){
            JSONObject objeto = (JSONObject) ja.get(i);
            Integer x = (Integer) objeto.get("x");
            Integer y = (Integer) objeto.get("y");
            Circle ficha = (Circle) getNodeByRowColumnIndex(5 - x, y, panelTablero);
            ficha.setStrokeWidth(2.5);
            Stop[] stops = null; Stop[] borde;
            LinearGradient lg1;
            if (ganadoA){
                stops = new Stop[] { new Stop(0, Color.rgb(255,0,77)), new Stop(1, Color.RED)};
                borde = new Stop[] { new Stop(0, Color.rgb(252,234,187)), new Stop(1, Color.rgb(248,181,0))};
                lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, borde);
                ficha.setStroke(lg1);
            } else if (ganadoB) {
                stops = new Stop[] { new Stop(0, Color.rgb(96,192,228)), new Stop(1, Color.rgb(53,63,196))};
                borde = new Stop[] { new Stop(0, Color.rgb(255,255,255)), new Stop(1, Color.rgb(192,192,192))};
                lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, borde);
                ficha.setStroke(lg1);
            }
    public void update(int pFila, int pColumna, boolean pColor){
        Circle ficha = getFicha(pColor);
        //System.out.println(ficha.getCenterX());
        panelTablero.add(ficha,pColumna,pFila);
        /*System.out.println(ficha.getCenterX());
        ficha.setOpacity(0);
        Circle fichaAnim = getFicha(pColor);
        fichaAnim.setCenterY(100);
        this.pane.getChildren().add(fichaAnim);
        fichaAnim.setCenterY(100);
        Bounds boundsInScene = ficha.localToScene(ficha.getBoundsInLocal());
        System.out.println(boundsInScene.getMinX());*/
    }

            //ficha.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops));
            //panelTablero.add(ficha, x, y);

            fichaG = ficha; stopsG = stops; xG = x; yG = y;

            Timeline timeline0 = new Timeline();
            KeyFrame key = new KeyFrame(Duration.seconds(2));
            timeline0.getKeyFrames().add(key);
            timeline0.setOnFinished(event -> {
                fichaG.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stopsG));
                //panelTablero.add(fichaG, xG, yG);
            });
            timeline0.play();

            /*
            if (i == 0){
                Timeline timeline0 = new Timeline();
                KeyFrame key0 = new KeyFrame(Duration.seconds(2));
                timeline0.getKeyFrames().add(key0);
                timeline0.setOnFinished(event -> {
                    fichaG.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stopsG));
                    panelTablero.add(fichaG, xG, yG);
                });
                timeline0.play();
            } else if (i == 1){
                Timeline timeline1 = new Timeline();
                KeyFrame key1 = new KeyFrame(Duration.seconds(2));
                timeline1.getKeyFrames().add(key1);
                timeline1.setOnFinished(event -> {
                    fichaG.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stopsG));
                    panelTablero.add(fichaG, xG, yG);
                });
                timeline1.play();
            } else if (i == 2){
                Timeline timeline2 = new Timeline();
                KeyFrame key2 = new KeyFrame(Duration.seconds(2));
                timeline2.getKeyFrames().add(key2);
                timeline2.setOnFinished(event -> {
                    fichaG.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stopsG));
                    panelTablero.add(fichaG, xG, yG);
                });
                timeline2.play();
            } else {
                Timeline timeline3 = new Timeline();
                KeyFrame key3 = new KeyFrame(Duration.seconds(2));
                timeline3.getKeyFrames().add(key3);
                timeline3.setOnFinished(event -> {
                    fichaG.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stopsG));
                    panelTablero.add(fichaG, xG, yG);
                });
                timeline3.play();
            }*/
        }
    }

    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRo.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }
}


