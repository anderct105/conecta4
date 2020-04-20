package packVista;

public class Partida {

    private String nombre;
    private int puntuacion;

    public Partida(String pN, int pP) {
        this.nombre = pN;
        this.puntuacion = pP;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getNombre() {
        return nombre;
    }
}
