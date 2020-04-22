package packVista;

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

    private Thread thrd;


    @FXML
    public void initialize() {
       String modoJuego =obtenerModoJuego();
       if (modoJuego.equals("1vs1")){
           PaneTiempo.setVisible(false);
           LabelTiempo.setVisible(false);

        }
       else {
         PaneTurno.setVisible(false);



       }
    }


    private String obtenerModoJuego() {

       // return Conecta4.getmConecta4().getModoJuego();
        return "modo Vs ordenador";
    }


    public void iniciarTemporizador(ActionEvent mouseEvent) {
        thrd=new Thread(new Runnable() {
            @Override
            public void run() {
                int secs=0;
                try {
                    while (true){
                        TimeSeconds.setText(String.valueOf(secs));
                        BTerminarPartida.setText("T: "+secs);
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


