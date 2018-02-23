package utils;

import java.util.Comparator;

import model.Aluno;


public abstract class NomeComparator implements Comparator<Aluno> {

	public int compare(Aluno a, Aluno a2) {
		return a.getNome().compareTo(a2.getNome());
		
	}

}
