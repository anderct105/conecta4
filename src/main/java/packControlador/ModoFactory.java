package packControlador;

import packModelo.*;

public class ModoFactory {

	private static ModoFactory mModoFactory;

	private ModoFactory() {
		// TODO - implement ModoFactory.ModoFactory
		throw new UnsupportedOperationException();
	}

	public static ModoFactory getmModoFactory() {
		return mModoFactory;
	}

	/**
	 * 
	 * @param pModo
	 */
	public Modo createModo(String pModo) {
		// TODO - implement ModoFactory.createModo
		throw new UnsupportedOperationException();
	}

}