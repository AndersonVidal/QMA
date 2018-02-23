package controller;

import java.util.ArrayList;
import java.util.Collections;

import model.Aluno;
import utils.NomeComparator;


public class ControllerAlunos {

	private ArrayList<Aluno> alunos;
	private NomeComparator Comparator;
	
	public ControllerAlunos() {
		this.alunos = new ArrayList<>();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone, email);
		this.alunos.add(aluno);
	}
	
	public String recuperaAluno(String matricula) {
		String str = "";
		for(Aluno aluno: alunos) {
			if(aluno.getMatricula().equals(matricula)) {
				str += aluno.toString();
			}
		}
		return str;
	}
	
	public String listarAlunos() {
		String str = "";
		Collections.sort(alunos,Comparator);
		for(Aluno aluno: alunos) {
			str += aluno.toString() + "/n";
		}
		
		return str.trim();
	}
}

