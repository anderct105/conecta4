package packControlador;

import packModelo.Modo;
import packModelo.OrdenadorD;
import packModelo.OrdenadorF;
import packModelo.OvO;

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
	 *
	 * @param pModo el modo de juego especificado
	 * @author Alvaro Dueñas
	 */
	public Modo createModo(String pModo) {
        if ("Ordenador modo Dificil".equals(pModo)) {
            return new OrdenadorD();
        } else if ("Ordenador modo Facil".equals(pModo)) {
            return new OrdenadorF();
        }
		return new OvO();
	}
}