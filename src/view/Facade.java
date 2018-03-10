/**
 * @author Arthur da Silva Rodrigues e Anderson Vasconcelos
 */
package view;

import controller.CaixaSistema;
import controller.ControllerAjudas;
import controller.ControllerAlunos;
import controller.ControllerTutores;

import easyaccept.EasyAccept;

public class Facade {
	
	public static void main(String[] args) { 
		args = new String[] { "view.Facade",
				"acceptance_tests/us1_test.txt", 
				"acceptance_tests/us2_test.txt",
				"acceptance_tests/us3_test.txt",
				"acceptance_tests/us4_test.txt",
				"acceptance_tests/us5_test.txt",
				"acceptance_tests/us6_test.txt"};
		EasyAccept.main(args);
	}
	
	private CaixaSistema caixa = new CaixaSistema();
	private ControllerAlunos controleAlunos = new ControllerAlunos();
	private ControllerTutores controleTutores = new ControllerTutores(controleAlunos, caixa);
	private ControllerAjudas controleAjudas = new ControllerAjudas(controleAlunos);
	
	/**
	 * Cadastra um aluno com telefone no sistema.
	 * 
	 * @param nome Representação do nome do aluno, no formato String "james"
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * @param codigoCurso Representação do codigo do curso, no formato inteiro "1"
	 * @param telefone Representação do telefone do aluno, no formato String "99999999"
	 * @param email Representação do email do aluno, no formato String "james@ccc.ufcg.edu.br"
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		this.controleAlunos.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
	}

	
	/**
	 * Encontra um aluno segundo a sua matricula no sistema.
	 * 
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * 
	 * @return Representaçao textual do aluno buscado.
	 */
	public String recuperaAluno(String matricula) {
		return this.controleAlunos.recuperaAluno(matricula);
	}
	
	/**
	 * Lista todos os alunos do sistema exibindo suas representações textuais em sequencia alfabética.
	 * 
	 * @return Representação textual de uma lista de alunos.
	 */
	public String listarAlunos() {
		return this.controleAlunos.listarAlunos();
	}

	/**
	 * Encontra um aluno segundo sua matricula no sistema e imprime a caracteristica desejada.
	 * 
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * @param atributo Representação de uma caractéristica do aluno
	 * @return Representação textual de uma caractéristica do aluno
	 */
	public String getInfoAluno(String matricula, String atributo) {
		return this.controleAlunos.getInfoAluno(matricula, atributo);
	}
	
	/**
	 * Torna um aluno tutor de alguma disciplina através de sua matrícula.
	 * 
	 * @param matricula Representação da matrícula do aluno, no formato String "123456".
	 * @param disciplina Representação da disciplina, no formato String "matemática".
	 * @param proeficiencia Representação da proeficiencia do tutor, no formato inteiro "0".
	 */
	public void tornarTutor(String matricula, String disciplina, int proeficiencia) {
		this.controleAlunos.tornarTutor(matricula, disciplina, proeficiencia);
	}
	
	/**
	 * Encontra um tutor segundo a sua matricula no sistema.
	 * 
	 * @param matricula Representação do tutor, no formato String "123456"
	 * 
	 * @return Representaçao textual do tutor buscado.
	 */
	public String recuperaTutor(String matricula) {
		return this.controleAlunos.recuperaAlunoTutor(matricula);
	}
	
	/**
	 * Lista todos os tutores do sistema exibindo suas representações textuais em sequencia alfabética.
	 * 
	 * @return Representação textual de uma lista de tutores.
	 */
	public String listarTutores() {
		return this.controleAlunos.listarAlunosTutores();
	}
	
	/**
	 * Adiciona um horario ao dia especificado para um tutor especifico
	 * 
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param horario Representação de um horario, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 */
	public void cadastrarHorario(String email, String horario, String dia){
		this.controleTutores.cadastrarHorario(email, horario, dia);
	}
	
	/**
	 * Adiciona um local de atendimento do tutor especificado
	 * 
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param local Representação de um local de atendimento, no formato String "ufcg"
	 */
	public void cadastrarLocalDeAtendimento(String email, String local){
		this.controleTutores.cadastrarLocalDeAtendimento(email, local);
	}
	
	/**
	 * Verifica se o horario especificado é de disponibilidade do tutor especificado.
	 *
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param horario Representação de um horario, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 * @return Representação booleana da verificação de horario do tutor
	 */
	public boolean consultaHorario(String email, String horario, String dia){
		return this.controleTutores.consultaHorario(email, horario, dia);
	}
	
	/**
	 * Verifica se o local especificado é de disponibilidade do tutor especificado
	 * 
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param local Representação de um local no formato String "ufcg"
	 * @return Representação booleana da verificação de local do tutor
	 */
	public boolean consultaLocal(String email, String local){
		return this.controleTutores.consultaLocal(email, local);
	}
	
	/**
	 * Solicita ajuda presencial a um tutor em uma disciplina que o tutor tenha
	 * proficiência.
	 * 
	 * @param matrAluno Representação da matrícula do aluno, no formato String "123456"
	 * @param disciplina Representação da disciplina, no formato String "matemática"
	 * @param horario Representação do horario, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 * @param localInteresse Representação de um local de atendimento, no formato String "ufcg"
	 * @return Represenetação inteira do id da ajuda
	 */
	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia, String localInteresse) {
		return this.controleAjudas.pedirAjudaPresencial(matrAluno, disciplina, horario, dia, localInteresse);
	}
	
	/**
	 * Solicita ajuda online para um aluno em uma determinada disciplina
	 * @param matriculaAluno Rpresentação da matrícula do aluno, no formato String "123456"
	 * @param disciplina Representação da disciplina, no formato String "matemática"
	 * @return ID de cadastro da ajuda.
	 */
	public int pedirAjudaOnline(String matriculaAluno, String disciplina){
		return this.controleAjudas.pedirAjudaOnline(matriculaAluno, disciplina);
	}
	
	/**
	 * Retorna a descrição do tutor da ajuda.
	 * 
	 * @param idAjuda ID da ajuda, no formato inteiroidAjuda 
	 * @return Representação textual da descrição do tutor da ajuda.
	 */
	public String pegarTutor(int idAjuda){
		return this.controleAjudas.pegarTutor(idAjuda);
	}
	
	/**
	 * Recupera um atributo de uma ajuda específica.
	 * 
	 * @param idAjuda idAjuda ID da ajuda, no formato inteiro
	 * @param atributo Representação textual de um atributo ajuda.
	 * @return
	 */
	public String getInfoAjuda(int idAjuda, String atributo){
		return this.controleAjudas.getInfoAjuda(idAjuda, atributo);
	}
	
	/**
	 * Avalia um tutor a partir da nota da ajuda concedida.
	 * 
	 * @param idAjuda ID da ajuda, no formato inteiro.
	 * @param nota nota para a ajuda do tutor, no formato inteiro.
	 * @return
	 */
	public String avaliarTutor(int idAjuda, int nota) {
		return this.controleTutores.avaliarTutor(idAjuda, nota);
	}
	
	/**
	 * Retorna a nota do tutor através de sua matrícula.
	 * @param matriculaTutor a matricula do tutor, no formato String.
	 * @return
	 */
	public String pegarNota(String matriculaTutor) {
		return this.controleTutores.pegarNota(matriculaTutor);
	}
	
	/**
	 * Retorna o nível do tutor através de sua matrícula.
	 * @param matriculaTutor a matrícula do tutor, no formato String.
	 * @return
	 */
	public String pegarNivel(String matriculaTutor) {
		return this.controleTutores.pegarNivel(matriculaTutor);
	}
	
	public void doar(String matriculaTutor, int totalCentavos) {
		this.controleTutores.doar(matriculaTutor, totalCentavos);
	}
	
	public int totalDinheiroTutor(String emailTutor) {
		return this.controleTutores.totalDinheiroTutor(emailTutor);
	}
	
	public int totalDinheiroSistema() {
		return this.caixa.getDinheiroSistema();
	}
	
}
