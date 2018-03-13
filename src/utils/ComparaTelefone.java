package utils;

import model.Aluno;

public class ComparaTelefone extends Comparador{

	@Override
	public int compare(Aluno aluno1, Aluno aluno2) {
		return aluno2.getTelefone().compareTo(aluno1.getTelefone());
	}

}
