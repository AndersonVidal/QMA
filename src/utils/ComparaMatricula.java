package utils;

import java.util.Comparator;

import model.Aluno;

public class ComparaMatricula implements Comparator<Aluno> {

	@Override
	public int compare(Aluno aluno1, Aluno aluno2) {
		return aluno2.getMatricula().compareTo(aluno1.getMatricula());
	}
	
}
