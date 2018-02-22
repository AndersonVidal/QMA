package controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Aluno;
import utils.Utils;

public class ControllerAlunos {
	
	private ArrayList<Aluno> alunos;
	
	public ControllerAlunos() {
		this.alunos = new HashMap<>();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone, email);
		this.alunos.put(matricula, aluno);
	}
	
	public String recuperaAluno(String matricula) {
		return this.alunos.get(matricula).toString();
	}
	
	public String listarAlunos() {
		Collections.sort(( Utils.toList(alunos));
	}
}
