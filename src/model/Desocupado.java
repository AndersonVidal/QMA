package model;

/**
 * Representação de uma ocupação vazia.
 * 
 * @author anderson vidal
 *
 */
public class Desocupado extends Ocupacao {
	
	/**
	 * Uma ocupação vazia é contruida com disciplina vazia e proficiência minima
	 */
	public Desocupado() {
		super("", 1);
	}

}
