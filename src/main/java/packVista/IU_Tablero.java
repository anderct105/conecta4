package packVista;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import packControlador.Conecta4;

import java.awt.event.ActionListener;


import java.util.Timer;
import java.util.TimerTask;

public class IU_Tablero {

    @FXML
    private AnchorPane pane;
    @FXML
    private GridPane gridPane;

    @FXML
    private Button BTerminarPartida;
    @FXML
    private AnchorPane PaneTiempo;
    @FXML
    private Label LabelTiempo;
    @FXML
    private GridPane PaneTemporizador;
    @FXML
    private TextField TimeHours;
    @FXML
    private TextField TimeMinutes;
    @FXML
    private TextField TimeSeconds;


    @FXML
    private AnchorPane PaneTurno;
    @FXML
    private Label LabelTurno;

    private Thread thrd=new Thread();

    private int secs=0;
    private int mins=0;
    private int hours=0;

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
                   TimeHours.setText(String.valueOf(mins));
               }
           }
           else {
               TimeSeconds.setText(String.valueOf(secs));
           }

        }
    }));

    @FXML
    public void initialize() throws InterruptedException {
       String modoJuego =obtenerModoJuego();
       if (modoJuego.equals("1vs1")){
           PaneTiempo.setVisible(false);
           LabelTiempo.setVisible(false);

        }
       else {
         PaneTurno.setVisible(false);
           fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
           fiveSecondsWonder.play();
              }
    }


    private String obtenerModoJuego() {

       // return Conecta4.getmConecta4().getModoJuego();
        return "modo Vs ordenador";
    }


}


