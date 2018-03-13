package utils;

import model.Aluno;

public class ComparaNome extends Comparador{

	@Override
	public int compare(Aluno aluno1, Aluno aluno2) {
		if (aluno2.getNome().compareTo(aluno1.getNome()) == 0) {
			return aluno2.getMatricula().compareTo(aluno1.getMatricula());
		}
		
		return aluno2.getNome().compareTo(aluno1.getNome());
	}
	
}
