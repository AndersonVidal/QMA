package controller;

import java.util.Map;

import model.Aluno;

public class ControllerSistema {
	
	private ControllerAlunos controllerAlunos;
	private ControllerTutores controllerTutores;
	private ControllerAjudas controllerAjudas;
	
	public ControllerSistema(){
		this.controllerAlunos = new ControllerAlunos();
		this.controllerTutores = new ControllerTutores(this.controllerAlunos);
		this.controllerAjudas = new ControllerAjudas(this.controllerAlunos);
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email){
		this.controllerAlunos.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
	}
	
	public String recuperaAluno(String matricula){
		return this.controllerAlunos.recuperaAluno(matricula);
	}
	
	public String listarAlunos(){
		return this.controllerAlunos.listarAlunos();
	}
	
	public String getInfoAluno(String matricula, String atributo){
		return this.controllerAlunos.getInfoAluno(matricula, atributo);
	}
	
	public void tornarTutor(String matricula, String disciplina, int proficiencia){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		this.controllerAlunos.setAlunos(this.controllerTutores.tornarTutor(matricula, disciplina, proficiencia, alunos));
	}
	
	public String recuperaTutor(String matricula){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		return this.controllerTutores.recuperaTutor(matricula, alunos);
	}
	
	public String listarTutores() {
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		return this.controllerTutores.listarAlunosTutores(alunos);
	}
	
}
