package packVista;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packControlador.Conecta4;
import packModelo.Tablero;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    private boolean finJugador;

    // Para marcar/desmarcar la columna llena
    private boolean llena;

    //para que no queden animaciones de columnas pendiente
    private boolean colAnimTerminado;

    private int secs = 0;
    private int mins = 0;
    private int hours = 0;
    Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            // int secs=Integer.parseInt(TimeSeconds.getText());
            secs = secs + 1;
            if (secs < 10) {
                TimeSeconds.setText("0" + (secs));
            } else if (secs == 60) {
                TimeSeconds.setText("00");
                secs = 0;
                mins = mins + 1;
                if (mins < 10) {
                    TimeMinutes.setText("0" + (mins));
                } else if (mins == 60) {
                    TimeMinutes.setText("00");
                    mins = 0;
                    hours = hours + 1;
                    if (hours < 10) {
                        TimeHours.setText("0" + (hours));
                    } else {
                        TimeHours.setText(String.valueOf(hours));
                    }
                } else {
                    TimeMinutes.setText(String.valueOf(mins));
                }
            } else {
                TimeSeconds.setText(String.valueOf(secs));
            }
        }
    }));
    private boolean turno = true;
    private boolean bloqueo = false;
    private int filaSav;
    private int columnaSav;
    private boolean turnoSav;
    private boolean contGestion = true;
    private MediaPlayer musicaFondo;
    //esto sirve para dejar marcada la columna que usó el jugador tras la animación de la ficha
    private int columnaJugador;

    @FXML
    public void initialize() throws InterruptedException {
        //dependiendo del modo de juego se coloca el reloj o el panel del turno
        setModoJuego();
        listenerTerminarPartida();
        listenerTablero();
        Conecta4.getmConecta4().inicializarTablero();
        Tablero.getmTablero().addObserver(this);
        colAnimTerminado = true;

        musicaFondoOn();
    }

    private void musicaFondoOn() {
        Media sound = new Media(new File("background.mp3").toURI().toString());
        musicaFondo = new MediaPlayer(sound);
        musicaFondo.play();
        musicaFondo.setCycleCount(MediaPlayer.INDEFINITE);
    }

    private void musicaFondoOff() {
        musicaFondo.stop();
    }

    private void setModoJuego() {
        String modoJuego = obtenerModoJuego();
        if (modoJuego.equals("1vs1")) {
            PaneTiempo.setVisible(false);

            setTurno();
        } else {
            PaneTurno.setVisible(false);
            fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            fiveSecondsWonder.play();
        }
    }

    //devuelve la ficha correspondiente al turno
    private Circle getFicha(boolean color) {
        if (color) {
            return getFichaRoja();
        } else {
            return getFichaAzul();
        }
    }

    private Circle getFichaRoja() {
        Circle ficha = new Circle();
        Stop[] stops = new Stop[]{new Stop(0, Color.rgb(255, 105, 153)), new Stop(1, Color.RED)};
        ficha.setRadius(31);

        ficha.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops));
        ficha.setOpacity(1);
        return ficha;
    }

    private Circle getFichaAzul() {
        Circle ficha = new Circle();
        Stop[] stops = new Stop[]{new Stop(0, Color.rgb(108, 215, 255)), new Stop(1, Color.rgb(61, 73, 224))};
        ficha.setRadius(31);

        ficha.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops));
        ficha.setOpacity(1);
        return ficha;
    }

    public void oscurecerFondo(int columna) {
        //EFECTO PARA OSCURECER
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(-0.5);
        pane.getScene().getRoot().setEffect(ca);
        //CREAR LA VENTANA DE COLUMNA LLENA
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/ColumnaLlena.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(root, 291, 100);
        //FONDO TRANSPARENTE
        primaryStage.initStyle(StageStyle.UNDECORATED);
        s.setFill(Color.TRANSPARENT);
        primaryStage.setScene(s);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //CENTRAR EN TABLERO
        Stage sAct = (Stage) panelTablero.getScene().getWindow();
        //OCULTAR VENTANA CUANDO APAREZCA, PARA ASI PODE OBTENER SU POSICIÓN Y REAJUSTARLA
        primaryStage.setOnShowing(ev -> primaryStage.hide());
        //EN CUANTO SE ENSEÑE SE AJUSTA Y SE PONE VISIBLE
        primaryStage.setOnShown(ev -> {
            double centerXPosition = sAct.getX() + sAct.getWidth() / 2;
            double centerYPosition = sAct.getY() + sAct.getHeight() / 2;
            primaryStage.setX(centerXPosition - primaryStage.getWidth() / 2);
            primaryStage.setY(centerYPosition - primaryStage.getHeight() / 2);
            fiveSecondsWonder.stop();
            primaryStage.show();
        });
        panelTablero.getScene().getRoot().setDisable(true);
        primaryStage.show();
        //CUANDO SE OCULTE SE QUITARÁ EL EFECTO OSCURO
        primaryStage.setOnHiding(event -> {
            ColorAdjust ca1 = new ColorAdjust();
            ca1.setBrightness(0);
            pane.getScene().getRoot().setEffect(ca1);
            panelTablero.getScene().getRoot().setDisable(false);
            marcarDesmarcarColumnaLlena(columna);
            fiveSecondsWonder.play();
        });
    }

    private void listenerTablero() {
        //se rellenan todas las posiciones con fichas transparentes para detectar el clic
        tablero = new Circle[6][9];
        fin = false;
        llena = false;
        bloqueo = false;
        finJugador = false;
        int filas = panelTablero.getRowConstraints().size();
        int columnas = panelTablero.getColumnConstraints().size();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Circle ficha = new Circle();
                ficha.setRadius(31);
                ficha.setFill(Color.TRANSPARENT);
                ficha.setOpacity(1);
                this.tablero[i][j] = ficha;
                panelTablero.add(ficha, j, i);
            }
        }
        panelTablero.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 1 && !bloqueo) {
                        //int columna = GridPane.getColumnIndex(item);
                        //LO COMENTO PARA QUE NO DE EXCEPCIÓN EN LOS BORDES
                        JSONObject json = jugar(columnaJugador, turno);
                        JSONArray ja = null;
                        if (json != null) {
                            ja = (JSONArray) json.get("posicionesGanadoras");
                        }
                        if (ja != null) {
                            fin = true;
                            marcarGanadoras(json);
                            fiveSecondsWonder.stop();
                        }
                    }
                }
            });
            listenerSeleccionCol(item);
        });
    }

    public int columna(Node c) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == c) {
                    return j;
                }
            }
        }
        return -1;
    }

    public void clickJuego() {

    }

    public void listenerSeleccionCol(Node item) {
        item.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int columna = columna(item);
                if (columna != -1 && !fin) {
                    ponerSeleccionColumna(columna);
                    columnaJugador = columna;
                }
            }
        });

    }

    @FXML
    public void salirTableroSel() {
        quitarSeleccionColumna();
        this.columnaJugador = -1;
    }


    public void quitarSeleccionColumna() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                Circle c = tablero[i][j];
                if (!fin) {
                    c.setStrokeWidth(0);
                }
                ColorAdjust ca = new ColorAdjust();
                ca.setBrightness(0);
                c.setEffect(ca);
            }
        }
    }

    private void ponerSeleccionColumna(int columna) {
        Timeline[] tm = new Timeline[tablero.length];
        if (columna != -1 && colAnimTerminado) {
            if (!bloqueo) {
                quitarSeleccionColumna();
                ColorAdjust ca = new ColorAdjust();
                ca.setBrightness(0.3);
                for (int i = 0; i < tablero.length; i++) {
                    Timeline tl = new Timeline();
                    Circle c = tablero[i][columna];
                    KeyFrame kf = new KeyFrame(Duration.millis(20), new KeyValue(c.strokeWidthProperty(), 2, Interpolator.EASE_IN), new KeyValue(c.strokeProperty(), Color.BLACK, Interpolator.EASE_IN), new KeyValue(c.effectProperty(), ca, Interpolator.EASE_IN));
                    tl.getKeyFrames().addAll(kf);
                    tm[i] = tl;
                }
                int actual = columna;
                for (int i = tm.length - 1; i > 0; i--) {
                    Timeline t = tm[i - 1];
                    tm[i].setOnFinished(event -> t.play());
                }
                tm[0].setOnFinished(event -> {
                    colAnimTerminado = true;
                    if (columnaJugador == -1) {
                        quitarSeleccionColumna();
                    } else if (actual != columnaJugador) {
                        ponerSeleccionColumna(columnaJugador);
                    }
                });
                colAnimTerminado = false;
                tm[tm.length - 1].play();
            }
        }
    }

    private JSONObject jugar(int pColumna, boolean turno) {
        JSONObject json = Conecta4.getmConecta4().jugarPartida(pColumna);
        if (json == null) {
            oscurecerFondo(pColumna);
            marcarDesmarcarColumnaLlena(pColumna);
        } else {
            //fila = (int)json.get("fila");
            //panelTablero.add(ficha,pColumna,5-fila);

        }
        return json;
    }

    private void listenerTerminarPartida() {
        BTerminarPartida.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Parent root = null;
                try {
                    Node source = (Node) e.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                    root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Conecta 4");
                    primaryStage.setScene(new Scene(root, 1100, 600));
                    primaryStage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void setTurno() {
        if (turno) {
            NombreTurno.setText("Jugador rojo");
        } else {
            NombreTurno.setText("Jugador azul");
        }
    }

    private String obtenerModoJuego() {
        return Conecta4.getmConecta4().getModoJuego();
        // return "modo Vs ordenador";
    }

    private void marcarGanadoras(JSONObject jo) {
        JSONArray ja = (JSONArray) jo.get("posicionesGanadoras");
        boolean ganadoA = (boolean) jo.get("haGanadoA");
        boolean ganadoB = (boolean) jo.get("haGanadoB");
        for (int i = 0; i < ja.size(); i++) {
            JSONObject objeto = (JSONObject) ja.get(i);
            Integer x = (Integer) objeto.get("x");
            Integer y = (Integer) objeto.get("y");
            Stop[] stops = null;
            Stop[] borde;
            LinearGradient lg1 = null;
            Circle ficha = tablero[5 - x][y];
            if (ganadoA && !obtenerModoJuego().equals("1vs1") || !ganadoA && obtenerModoJuego().equals("1vs1")) {
                stops = new Stop[]{new Stop(0, Color.rgb(255, 0, 77)), new Stop(1, Color.RED)};
                borde = new Stop[]{new Stop(0, Color.rgb(252, 234, 187)), new Stop(1, Color.rgb(248, 181, 0))};
                lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, borde);
            } else if (ganadoB && !obtenerModoJuego().equals("1vs1") || !ganadoB && obtenerModoJuego().equals("1vs1")) {
                stops = new Stop[]{new Stop(0, Color.rgb(96, 192, 228)), new Stop(1, Color.rgb(53, 63, 196))};
                borde = new Stop[]{new Stop(0, Color.rgb(255, 255, 255)), new Stop(1, Color.rgb(192, 192, 192))};
                lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, borde);
            }
            ficha.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops));
            ficha.setStrokeWidth(2.5);
            ficha.setStroke(lg1);
            if (ganadoA && !obtenerModoJuego().equals("1vs1")) {
                finJugador = true;
            }
        }

        //Ordenamos las fichas
        HashMap<Integer, JSONObject> fichas = new HashMap<>();
        boolean igual = false;
        JSONObject objeto = (JSONObject) ja.get(0);
        JSONObject objetoB = (JSONObject) ja.get(1);
        Integer x = (Integer) objeto.get("x");
        Integer xB = (Integer) objetoB.get("x");
        Integer y = (Integer) objeto.get("y");
        Integer yB = (Integer) objetoB.get("y");
        if (x + y == xB + yB) {
            igual = true;
        }

        for (int i = 0; i < ja.size(); i++) {
            objeto = (JSONObject) ja.get(i);
            x = (Integer) objeto.get("x");
            y = (Integer) objeto.get("y");
            if (!igual) {
                fichas.put(x + y, objeto);
            } else {
                fichas.put(y, objeto);
            }
        }
        List<Integer> empleados = new ArrayList<>(fichas.keySet());
        Collections.sort(empleados);

        //FICHA 1
        JSONObject objeto1 = fichas.get(empleados.get(0));
        Integer x1 = (Integer) objeto1.get("x");
        Integer y1 = (Integer) objeto1.get("y");
        Circle ficha1 = tablero[5 - x1][y1];

        Timeline timeline11 = new Timeline();
        KeyFrame key11 = new KeyFrame(Duration.millis(500),
                new KeyValue(ficha1.scaleXProperty(), 0.7), new KeyValue(ficha1.scaleYProperty(), 0.7));
        timeline11.getKeyFrames().add(key11);
        //timeline11.play();

        Timeline timeline1 = new Timeline();
        KeyFrame key1 = new KeyFrame(Duration.millis(1000),
                new KeyValue(ficha1.scaleXProperty(), 1.1), new KeyValue(ficha1.scaleYProperty(), 1.1));
        timeline1.getKeyFrames().add(key1);

        //FICHA 2
        JSONObject objeto2 = fichas.get(empleados.get(1));
        Integer x2 = (Integer) objeto2.get("x");
        Integer y2 = (Integer) objeto2.get("y");
        Circle ficha2 = tablero[5 - x2][y2];

        Timeline timeline21 = new Timeline();
        KeyFrame key21 = new KeyFrame(Duration.millis(500),
                new KeyValue(ficha2.scaleXProperty(), 0.7), new KeyValue(ficha2.scaleYProperty(), 0.7));
        timeline21.getKeyFrames().add(key21);

        Timeline timeline2 = new Timeline();
        KeyFrame key2 = new KeyFrame(Duration.millis(1000),
                new KeyValue(ficha2.scaleXProperty(), 1.1), new KeyValue(ficha2.scaleYProperty(), 1.1));
        timeline2.getKeyFrames().add(key2);

        //FICHA 3
        JSONObject objeto3 = fichas.get(empleados.get(2));
        Integer x3 = (Integer) objeto3.get("x");
        Integer y3 = (Integer) objeto3.get("y");
        Circle ficha3 = tablero[5 - x3][y3];

        Timeline timeline31 = new Timeline();
        KeyFrame key31 = new KeyFrame(Duration.millis(500),
                new KeyValue(ficha3.scaleXProperty(), 0.7), new KeyValue(ficha3.scaleYProperty(), 0.7));
        timeline31.getKeyFrames().add(key31);

        Timeline timeline3 = new Timeline();
        KeyFrame key3 = new KeyFrame(Duration.millis(1000),
                new KeyValue(ficha3.scaleXProperty(), 1.1), new KeyValue(ficha3.scaleYProperty(), 1.1));
        timeline3.getKeyFrames().add(key3);

        //FICHA 4
        JSONObject objeto4 = fichas.get(empleados.get(3));
        Integer x4 = (Integer) objeto4.get("x");
        Integer y4 = (Integer) objeto4.get("y");
        Circle ficha4 = tablero[5 - x4][y4];

        Timeline timeline41 = new Timeline();
        KeyFrame key41 = new KeyFrame(Duration.millis(500),
                new KeyValue(ficha4.scaleXProperty(), 0.7), new KeyValue(ficha4.scaleYProperty(), 0.7));
        timeline41.getKeyFrames().add(key41);

        Timeline timeline4 = new Timeline();
        KeyFrame key4 = new KeyFrame(Duration.millis(1000),
                new KeyValue(ficha4.scaleXProperty(), 1.1), new KeyValue(ficha4.scaleYProperty(), 1.1));
        timeline4.getKeyFrames().add(key4);

        timeline11.setOnFinished(event -> {
            timeline1.play();
        });
        timeline11.play();
        timeline1.setOnFinished(event -> {
            timeline21.play();
        });
        timeline21.setOnFinished(event -> {
            timeline2.play();
        });
        timeline2.setOnFinished(event -> {
            timeline31.play();
        });
        timeline31.setOnFinished(event -> {
            timeline3.play();
        });
        timeline3.setOnFinished(event -> {
            timeline41.play();
        });
        timeline41.setOnFinished(event -> {
            timeline4.play();
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!fin) {
            quitarSeleccionColumna();
            JSONObject json = (JSONObject) arg;
            int fila = 5 - (int) json.get("fila");
            int columna = (int) json.get("columna");
            Circle ficha = getFicha(turno);
            if (!bloqueo) {
                bloqueo = true;
                animacionCaer(fila, columna, ficha, false);
            } else {
                filaSav = fila;
                columnaSav = columna;
                turnoSav = turno;
            }
            turno = !turno;
            setTurno();
        }
    }

    private void animacionCaer(int pFila, int pColumna, Circle ficha, boolean bloq) {
        if (!finJugador) {
            //SE AÑADE A LA LÓGICA Y AL TABLERO
            panelTablero.add(ficha, pColumna, pFila);
            tablero[pFila][pColumna] = ficha;
            //SE LE PONE LOS LISTENERS A LA FICHA
            listenerSeleccionCol(ficha);
            ficha.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 1 && !bloqueo) {
                        //int columna = GridPane.getColumnIndex(item);
                        //LO COMENTO PARA QUE NO DE EXCEPCIÓN EN LOS BORDES
                        JSONObject json = jugar(columnaJugador, turno);
                        JSONArray ja = null;
                        if (json != null) {
                            ja = (JSONArray) json.get("posicionesGanadoras");
                        }
                        if (ja != null) {
                            fin = true;
                            marcarGanadoras(json);
                            fiveSecondsWonder.stop();
                        }
                    }
                }
            });
            //SE HACE INVISIBLE EN LA POSICIÓN Y SE CREA A, QUE VA A SER LA QUE CAIGA
            ficha.setOpacity(0);
            Circle a;
            if (bloq) {
                a = getFicha(turnoSav);
            } else {
                a = getFicha(turno);
            }
            //SE COLOCA A EN EL TABLERO ARRIBA
            a.setCenterX(getCoordenadaColumna(pColumna));
            a.setCenterY(getCoordenadaFila(-1));
            pane.getChildren().add(a);
            //SE CREA LA ANIMACIÓN
            Timeline timelineA = new Timeline();
            KeyFrame keyA = new KeyFrame(Duration.millis(100 + 150 * (pFila)),
                    new KeyValue(a.centerYProperty(), getCoordenadaFila(5 - pFila)));
            timelineA.getKeyFrames().add(keyA);


            Timeline timelineB = new Timeline();
            KeyFrame keyB = new KeyFrame(Duration.millis(1),
                    new KeyValue(ficha.opacityProperty(), 100));
            timelineB.getKeyFrames().add(keyB);

            Timeline timelineC = new Timeline();
            KeyFrame keyC = new KeyFrame(Duration.millis(250),
                    new KeyValue(a.opacityProperty(), 0), new KeyValue(a.radiusProperty(), 75));
            timelineC.getKeyFrames().add(keyC);

            timelineA.setOnFinished(event -> sonido(timelineB));
            timelineB.setOnFinished(event -> timelineC.play());
            timelineC.setOnFinished(event -> {
                gestionarAnimacion(a, pColumna);
                ponerSeleccionColumna(this.columnaJugador);
            });
            timelineA.play();
        }
    }

    private void sonido(Timeline timelineB) {
        Media sound = new Media(new File("clack.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        timelineB.play();
    }

    private void gestionarAnimacion(Circle a, int columna) {
        pane.getChildren().remove(a);
        if (!obtenerModoJuego().equals("1vs1")) {
            if (!contGestion) {
                bloqueo = false;
                contGestion = true;
            } else {
                animacionCaer(filaSav, columnaSav, getFicha(turnoSav), true);
                contGestion = false;
            }
        } else {
            bloqueo = false;
        }
    }


    private double getCoordenadaFila(int pFila) {
        //FILA 0: Y=540
        //FILA 1: Y=471
        //FILA 2: Y=401
        //FILA 3: Y=331
        //FILA 4: Y=261
        //FILA 5: Y=191
        //ENCIMA DEL TABLERO: Y=121

        switch (pFila) {
            case 0:
                return 540;
            case 1:
                return 471;
            case 2:
                return 401;
            case 3:
                return 331;
            case 4:
                return 261;
            case 5:
                return 191;
            case -1:
                return 121;
        }
        return -1;
    }

    private double getCoordenadaColumna(int pColumna) {
        //COLUMNA 0: X=245
        //COLUMNA 1: X=320
        //COLUMNA 2: X=395
        //COLUMNA 3: X=470
        //COLUMNA 4: X=545
        //COLUMNA 5: X=621
        //COLUMNA 6: X=697
        //COLUMNA 7: X=773
        //COLUMNA 8: X=849

        switch (pColumna) {
            case 0:
                return 245;
            case 1:
                return 320;
            case 2:
                return 395;
            case 3:
                return 470;
            case 4:
                return 545;
            case 5:
                return 621;
            case 6:
                return 697;
            case 7:
                return 773;
            case 8:
                return 849;
        }
        return -1;
    }

    private void marcarDesmarcarColumnaLlena(int col) {
        quitarSeleccionColumna();
        ColorAdjust ca = new ColorAdjust();
        if (!llena) {
            ca.setBrightness(5);
            llena = true;
        } else {
            ca.setBrightness(0);
            llena = false;
        }
        for (int i = 0; i < 6; i++) {
            Circle ficha = tablero[i][col];
            ficha.setEffect(ca);
        }
    }
}


