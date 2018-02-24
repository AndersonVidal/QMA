package utils;

import java.util.Comparator;

import model.Aluno;


public abstract class Comparador implements Comparator<Aluno> {

	public abstract int compare(Aluno a1, Aluno a2);
}
