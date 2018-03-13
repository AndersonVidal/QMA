package utils;

import java.util.Comparator;

import model.Aluno;

public class ComparaTelefone implements Comparator<Aluno> {

	@Override
	public int compare(Aluno aluno1, Aluno aluno2) {
		return aluno2.getTelefone().compareTo(aluno1.getTelefone());
	}

}
