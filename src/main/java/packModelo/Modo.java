package packModelo;

import org.json.simple.JSONObject;

public abstract class Modo {

	private final String nombre;

	public Modo(String pNombre) {
		this.nombre = pNombre;
	}
	/**
	 *
	 * @param pColumna
	 * @return
	 */
	public abstract JSONObject jugar(int pColumna);

	public String getNombre() {
		return this.nombre;
	}

}