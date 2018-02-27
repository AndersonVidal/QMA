/**
 * @author Arthur da Silva Rodrigues e Anderson Vasconcelos
 */
package view;

import controller.ControllerAlunos;
import controller.ControllerTutores;

import easyaccept.EasyAccept;

public class Facade {
	
	public static void main(String[] args) { 
		args = new String[] { "view.Facade",
				"acceptance_tests/us1_test.txt", 
				"acceptance_tests/us2_test.txt",
				"acceptance_tests/us3_test.txt"};
		EasyAccept.main(args);
	}
	
	private ControllerAlunos controleAlunos = new ControllerAlunos();
	private ControllerTutores controleTutores = new ControllerTutores(controleAlunos);
	
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
	 * Cadastra um aluno no sistema.
	 * 
	 * @param nome Representação do nome do aluno, no formato String "james"
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * @param codigoCurso Representação do codigo do curso, no formato inteiro "1"
	 * @param email Representação do email do aluno, no formato String "james@ccc.ufcg.edu.br"
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String email){
		this.controleAlunos.cadastrarAluno(nome, matricula, codigoCurso, email);
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
		this.controleTutores.tornarTutor(matricula, disciplina, proeficiencia);
	}
	
	/**
	 * Encontra um tutor segundo a sua matricula no sistema.
	 * 
	 * @param matricula Representação do tutor, no formato String "123456"
	 * 
	 * @return Representaçao textual do tutor buscado.
	 */
	public String recuperaTutor(String matricula) {
		return this.controleTutores.recuperaTutor(matricula);
	}
	
	/**
	 * Lista todos os tutores do sistema exibindo suas representações textuais em sequencia alfabética.
	 * 
	 * @return Representação textual de uma lista de tutores.
	 */
	public String listarTutores() {
		return this.controleTutores.listarTutores();
	}
}
