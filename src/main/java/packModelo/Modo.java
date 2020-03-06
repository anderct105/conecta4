package packModelo;

import org.json.simple.JSONObject;

public interface Modo {

	/**
	 * 
	 * @param pColumna
	 */
	JSONObject jugar(int pColumna);

	String getNombre();

}