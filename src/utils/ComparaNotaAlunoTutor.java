package utils;

import java.util.Comparator;

import model.Aluno;

public class ComparaNotaAlunoTutor implements Comparator<Aluno> {

	@Override
	public int compare(Aluno tutor1, Aluno tutor2) {
		if (tutor1.getTutor().getNota() == tutor2.getTutor().getNota()) {
			return tutor2.getTutor().getId() - tutor1.getTutor().getId(); 
		}
		
		return tutor1.getTutor().getNota() - tutor2.getTutor().getNota();
	}
	
}
