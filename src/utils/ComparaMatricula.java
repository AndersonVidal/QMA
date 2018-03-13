package utils;

import model.Aluno;

public class ComparaMatricula extends Comparador {

	@Override
	public int compare(Aluno aluno1, Aluno aluno2) {
		return aluno2.getMatricula().compareTo(aluno1.getMatricula());
	}
	
}
