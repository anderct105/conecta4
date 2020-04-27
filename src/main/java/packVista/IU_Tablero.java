package packVista;

import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.collections.ObservableList;
import javafx.stage.StageStyle;
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
import packModelo.Tablero;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class IU_Tablero implements Observer {

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
    private Circle[][] tablero;

    //Para fin de juego
    private boolean fin;

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
        Tablero.getmTablero().addObserver(this);
        Tablero.getmTablero().setInterfaz(this);

        tablero = new Circle[6][9];
        fin = false;
    }

    private void setModoJuego() {
        String modoJuego = obtenerModoJuego();
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
                       int columna = GridPane.getColumnIndex(item);
                        JSONObject json = jugar(columna,turno);
                        JSONArray ja = (JSONArray) json.get("posicionesGanadoras");
                        if (ja != null) {
                            fin = true;
                            marcarGanadoras(json);
                        }
                    }
                }
            });
        });
    }

    private JSONObject jugar(int pColumna, boolean turno) {
        JSONObject json = Conecta4.getmConecta4().jugarPartida(pColumna);
        if (json == null){
            Stage primaryStage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene s = new Scene(root,291,100);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            s.setFill(Color.TRANSPARENT);
            primaryStage.setScene(s);
            primaryStage.show();
        } else{
            //fila = (int)json.get("fila");
            //panelTablero.add(ficha,pColumna,5-fila);
        }
        return json;
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
        if (turno){
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
            Stop[] stops = null; Stop[] borde;
            LinearGradient lg1;
            Circle ficha = tablero[5-x][y];
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
            ficha.setStrokeWidth(2.5);
            ficha.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!fin){
            JSONObject json = (JSONObject)arg;
            int fila = 5-(int)json.get("fila");
            int columna = (int)json.get("columna");
            Circle ficha = getFicha(turno);
            panelTablero.add(ficha,columna,fila);
            tablero[fila][columna] = ficha;
            turno = !turno;
        }
    }
}


