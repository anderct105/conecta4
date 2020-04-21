package packVista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    private Label LabelTemporizador;

    @FXML
    private AnchorPane PaneTurno;
    @FXML
    private Label LabelTurno;

    private Thread thrd;
    @FXML
    public void initialize() {
       String modoJuego =obtenerModoJuego();
       if (modoJuego.equals("1vs1")){
           PaneTiempo.setVisible(false);

        }
       else {
           PaneTurno.setVisible(false);
           //temporizador();
          


       }
    }

    private String obtenerModoJuego() {

       // return Conecta4.getmConecta4().getModoJuego();
        return "modo Vs ordenador";
    }
    private void temporizador() {

       thrd=new Thread(new Runnable() {
           @Override
           public void run() {
               int secs=0;
               try {
                   while (true){
                       LabelTemporizador.setText(String.valueOf(secs));
                       thrd.sleep(1000);

                       secs++;
                   }
               }
               catch (Exception e){

               }
           }

       });

        thrd.run();
    }


}