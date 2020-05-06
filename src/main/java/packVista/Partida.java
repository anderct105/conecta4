package packVista;

import javafx.beans.property.SimpleStringProperty;

public class Partida {
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty puntuacion;

    public Partida(String pN, String pP) {
        this.nombre = new SimpleStringProperty(pN);
        this.puntuacion = new SimpleStringProperty(pP);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String pN) {
        this.nombre.set(pN);
    }

    public String getPuntuacion() {
        return puntuacion.get();
    }

    public void setPuntuacion(String pP) {
        this.puntuacion.set(pP);
    }
}