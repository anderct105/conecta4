package packControlador;

import packModelo.*;

public class ModoFactory {

	private static ModoFactory mModoFactory;

	private ModoFactory() {
	}

	public static ModoFactory getmModoFactory() {
		if (mModoFactory == null) {
			mModoFactory = new ModoFactory();
		}
		return mModoFactory;
	}

	/**
	 * El método genera la modalidad de juego en la que la aplicación se configurará para jugar
	 * especificado por el usuario.
	 * @author Alvaro Dueñas
	 * @param pModo el modo de juego especificado
	 */
	public Modo createModo(String pModo) {
		if("OrdenadorD".equals(pModo)) return new OrdenadorD();
		else if("OrdenadorF".equals(pModo)) return new OrdenadorF();
		return new OvO();
	}

}