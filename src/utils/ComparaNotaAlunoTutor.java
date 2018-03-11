package utils;

import java.util.Comparator;

import model.Aluno;

/**
 * Classe que extende da interface Comparator e ordena de acordo com a nota
 * e a ordem de cadastro.
 * 
 * @author Daniel Coura
 */
public class ComparaNotaAlunoTutor implements Comparator<Aluno> {

	@Override
	public int compare(Aluno tutor1, Aluno tutor2) {
		if (tutor1.getTutor().getNota() == tutor2.getTutor().getNota()) {
			return tutor2.getTutor().getId() - tutor1.getTutor().getId(); 
		} else {
			if(tutor1.getTutor().getNota() > tutor2.getTutor().getNota()) {
				return 1;
			}			
			return -1;
		}
	}
}
