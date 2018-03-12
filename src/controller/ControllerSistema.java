package controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Aluno;
import utils.Validador;

public class ControllerSistema {
	
	private ControllerAlunos controllerAlunos;
	private ControllerTutores controllerTutores;
	private ControllerAjudas controllerAjudas;
	
	public ControllerSistema(){
		this.controllerAlunos = new ControllerAlunos();
		this.controllerTutores = new ControllerTutores();
		this.controllerAjudas = new ControllerAjudas();
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
	
	public void cadastrarHorario(String email, String horario, String dia) {
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		alunos = this.controllerTutores.cadastrarHorario(email, horario, dia, alunos);
		
		this.controllerAlunos.setAlunos(alunos);
	}
	
	public void cadastrarLocalDeAtendimento(String email, String local) {
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		alunos = this.controllerTutores.cadastrarLocalDeAtendimento(email, local, alunos);
		this.controllerAlunos.setAlunos(alunos);
	}
	
	public boolean consultaHorario(String email, String horario, String dia){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		return this.controllerTutores.consultaHorario(email, horario, dia, alunos);
	}
	
	public boolean consultaLocal(String email, String local){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		return this.controllerTutores.consultaLocal(email, local, alunos);
	}
	
	public int pedirAjudaPresencial (String matrAluno, String disciplina, String horario, String dia, String localInteresse){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		List<Aluno> tutores = this.controllerTutores.getAlunosTutores(alunos);
		Set<String> matrAlunos = this.controllerAlunos.getMatriculaAlunos();
		return this.controllerAjudas.pedirAjudaPresencial(matrAluno, disciplina, horario, dia, localInteresse, tutores, matrAlunos);
	}
	
	public int pedirAjudaOnline (String matrAluno, String disciplina){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		List<Aluno> tutores = this.controllerTutores.getAlunosTutores(alunos);
		Set<String> matrAlunos = this.controllerAlunos.getMatriculaAlunos();
		return this.controllerAjudas.pedirAjudaOnline(matrAluno, disciplina, tutores, matrAlunos);
	}
	
	public String pegarTutor(int idAjuda){
		return this.controllerAjudas.pegarTutor(idAjuda);
	}
	
	public String getInfoAjuda(int idAjuda, String atributo){
		return this.controllerAjudas.getInfoAjuda(idAjuda, atributo);
	}
	
	public void avaliarTutor (int idAjuda, int nota){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		String matrTutor = this.controllerAjudas.avaliarTutor(idAjuda);
		this.controllerAlunos.setAlunos(this.controllerTutores.avaliaTutor(matrTutor, nota, alunos));
	}
	
	public double pegarNota(String matriculaTutor){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		int nota = this.controllerTutores.pegarNota(matriculaTutor, alunos);
		Validador.validadorPositivo(nota, "Aluno não é tutor");
		return nota;
	}
}
