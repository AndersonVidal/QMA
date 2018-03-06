package controller;

public class Controller {
	
	private ControllerAlunos controleAlunos = new ControllerAlunos();
	private ControllerTutores controleTutores = new ControllerTutores(controleAlunos);
	private ControllerAjudas controleAjudas = new ControllerAjudas(controleAlunos);
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		this.controleAlunos.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
	}

	public String recuperaAluno(String matricula) {
		return this.controleAlunos.recuperaAluno(matricula);
	}

	public String listarAlunos() {
		return controleAlunos.listarAlunos();
	}

	public String getInfoAluno(String matricula, String atributo) {
		return this.controleAlunos.getInfoAluno(matricula, atributo);
	}

	public void tornarTutor(String matricula, String disciplina, int proeficiencia) {
		this.controleAlunos.tornarTutor(matricula, disciplina, proeficiencia);
	}

	public String recuperaAlunoTutor(String matricula) {
		return this.controleAlunos.recuperaAlunoTutor(matricula);
	}

	public String listarAlunosTutores() {
		return this.controleAlunos.listarAlunosTutores();
	}

	public void cadastrarHorario(String email, String horario, String dia) {
		this.controleTutores.cadastrarHorario(email, horario, dia);
	}

	public void cadastrarLocalDeAtendimento(String email, String local) {
		this.controleTutores.cadastrarLocalDeAtendimento(email, local);
	}

	public boolean consultaHorario(String email, String horario, String dia) {
		return this.controleTutores.consultaHorario(email, horario, dia);
	}

	public boolean consultaLocal(String email, String local) {
		return this.controleTutores.consultaLocal(email, local);
	}

	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia,
			String localInteresse) {
		return this.controleAjudas.pedirAjudaPresencial(matrAluno, disciplina, horario, dia, localInteresse);
	}

	public int pedirAjudaOnline(String matriculaAluno, String disciplina) {
		return this.controleAjudas.pedirAjudaOnline(matriculaAluno, disciplina);
	}

	public String pegarTutor(int idAjuda) {
		return this.controleAjudas.pegarTutor(idAjuda);
	}

	public String getInfoAjuda(int idAjuda, String atributo) {
		return this.controleAjudas.getInfoAjuda(idAjuda, atributo);
	}
	
}
