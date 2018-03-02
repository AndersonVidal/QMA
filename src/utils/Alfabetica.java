package utils;

import model.Aluno;

/**
 * Classe que extende da interface Comparator e irá ordenar por orede alfabetica
 * 
 * @author Arthur da Silva Rodrigues
 */
public class Alfabetica extends Comparador {

	@Override
	public int compare(Aluno a1, Aluno a2) {
		return a1.getNome().compareTo(a2.getNome());
	}

}
