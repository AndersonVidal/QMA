
package utils;

import java.util.Comparator;

import model.Aluno;

/**
 * Abstracao que implementa um comparador de aluno 
 * 
 * @author Anderson Vasconcelos
 */
public abstract class Comparador implements Comparator<Aluno> {

	public abstract int compare(Aluno a1, Aluno a2);
}
