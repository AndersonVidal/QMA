package controller;

import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import model.Aluno;
import utils.Alfabetica;
import utils.ComparaMatricula;
import utils.ComparaNome;
import utils.ComparaTelefone;
import utils.Constants;
import utils.Validador;

public class ControllerSistema {
	
	private ControllerAlunos controllerAlunos;
	private ControllerTutores controllerTutores;
	private ControllerAjudas controllerAjudas;
	private CaixaSistema caixaSistema;
	private Comparator<Aluno> criterioDeListagem;
	
	public ControllerSistema(){
		this.controllerAlunos = new ControllerAlunos();
		this.controllerTutores = new ControllerTutores();
		this.controllerAjudas = new ControllerAjudas();
		this.caixaSistema = new CaixaSistema();
		this.criterioDeListagem = new Alfabetica();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email){
		this.controllerAlunos.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
	}
	
	public String recuperaAluno(String matricula){
		return this.controllerAlunos.recuperaAluno(matricula);
	}
	
	public String listarAlunos(){
		return this.controllerAlunos.listarAlunos(this.criterioDeListagem);
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
		return this.controllerTutores.listarAlunosTutores(alunos, this.criterioDeListagem);
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
		Validador.validadorLimiteSuperior(nota, 5, "Erro na avaliacao de tutor: nota nao pode ser maior que 5");
		Validador.validadorLimiteInferior(nota, 0, "Erro na avaliacao de tutor: nota nao pode ser menor que 0");
		String matrTutor = this.controllerAjudas.avaliarTutor(idAjuda);
		Validador.validadorString(matrTutor, "Erro na avaliacao de tutor: Ajuda ja avaliada");
		Map<String, Aluno> atualizacao = this.controllerTutores.avaliaTutor(matrTutor, nota, alunos);
		this.controllerAlunos.setAlunos(atualizacao);
	}
	
	public String pegarNota(String matriculaTutor){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		String nota = this.controllerTutores.pegarNota(matriculaTutor, alunos);
		Validador.validadorString(nota, "Aluno não é tutor");
		return nota;
	}
	
	public String pegarNivel(String matriculaTutor){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		String nivel = this.controllerTutores.pegarNivel(matriculaTutor, alunos);
		Validador.validadorString(nivel, "Aluno não é tutor");
		return nivel;
	}
	
	public void doar(String matriculaTutor, int totalCentavos) {
		Validador.validadorPositivo(totalCentavos, "dinhero não pode ser negativo");
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		
		double taxaTutor = this.controllerTutores.getTaxaTutor(matriculaTutor, alunos);
		Validador.validadorPositivo(taxaTutor, "matricula não é de tutor");
		System.out.println("taxaTutor = " + taxaTutor);
		
		double totalSistema = Math.floor(totalCentavos * (1 - taxaTutor));
		System.out.println("totalSistema = " + totalSistema);
		
		double totalTutor = (totalCentavos - totalSistema);
		System.out.println("totalTutor = " + totalTutor);
		
		this.controllerAlunos.setAlunos(this.controllerTutores.receberDinheiroTutor(matriculaTutor, totalTutor, alunos));
		this.caixaSistema.addDinheiroSistema(totalSistema);
	}

	public int totalDinheiroTutor(String emailTutor) {
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		return (int) this.controllerTutores.getDinheiroTutor(emailTutor, alunos);
	}
	
	public int totalDinheiroSistema(){
		return (int) this.caixaSistema.getDinheiroSistema();
	}

	public void configurarOrdem(String atributo) {
		Validador.validadorString(atributo, "Erro ao configurar ordem: atributo nao pode ser vazio ou nulo");
		
		switch (atributo) {
		case "Nome":
			this.criterioDeListagem = new ComparaNome();
		case "Matricula":
			this.criterioDeListagem = new ComparaMatricula();
		case "Telefone":
			this.criterioDeListagem = new ComparaTelefone();
		default:
			throw new IllegalArgumentException("Erro ao configurar ordem: atributo invalido");
		}
		
	}

	public void salvar() {
		this.controllerAjudas.salvar(Constants.FILE_PATH + "ajudas.txt");
		this.controllerAlunos.salvar(Constants.FILE_PATH + "alunos.txt");
		this.caixaSistema.salvar(Constants.FILE_PATH + "caixa.txt");
	}

	public void carregar() {
		this.controllerAjudas.carregar(Constants.FILE_PATH + "ajudas.txt");
		this.controllerAlunos.carregar(Constants.FILE_PATH + "alunos.txt");
		this.caixaSistema.carregar(Constants.FILE_PATH + "caixa.txt");
	}

	public void limpar() {
		this.controllerAjudas.limpar(Constants.FILE_PATH + "ajudas.txt");
		this.controllerAlunos.limpar(Constants.FILE_PATH + "alunos.txt");
		this.caixaSistema.limpar(Constants.FILE_PATH + "caixa.txt");
	}
}
