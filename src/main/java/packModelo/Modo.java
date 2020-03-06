package packModelo;

import org.json.simple.JSONObject;

public interface Modo {

	/**
	 *
	 * @param pColumna
	 * @return
	 */
	JSONObject jugar(int pColumna);

	String getNombre();

}