package controller;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import controller.ControllerTutores;
import controller.ControllerAlunos;

/**
 * Entidade responsável pelos testes unitários da classe ControllerTutores.
 * 
 * @author Daniel Coura
 */
public class ControllerTutoresTest {
	
	/**
	 * Objeto ControllerAlunos para testes.
	 */
	private ControllerAlunos ca = new ControllerAlunos();
	/**
	 * Objeto ControllerTutores para testes.
	 */
	private ControllerTutores ct = new ControllerTutores(ca);
	
	/**
	 * Cadastra alguns alunos e tutores para testes.
	 */
	@Before
	public void cadastraAlunosParaTeste() {
		this.ca.cadastrarAluno("daniel", "1234", 123, "daniel@email.com");
		this.ca.cadastrarAluno("ana", "5678", 10, "ana@email.com");
		this.ca.cadastrarAluno("simba", "1324", 40, "simba@email.com");
		this.ca.cadastrarAluno("bright", "2222", 40, "bright@email.com");
		
		this.ct.tornarTutor("2222", "aa", 2);
		this.ct.tornarTutor("2222", "bb", 3);
		this.ct.tornarTutor("5678", "mat", 4);
		this.ct.tornarTutor("1324", "port", 5);
	}
	
	// *---------- CADASTRAR TURORES ----------*
	
	/**
	 * Testa se cadastra um tutor com os parâmetros corretos.
	 */
	@Test
	public void testCadastraTutorSimples() {
		this.ct.tornarTutor("1234", "cc", 2);
	}
	
	/**
	 * Testa se cadastra um tutor com proficiência valor de borda 1.
	 */
	@Test
	public void testCadastraTutorProficienciaBorda1() {
		this.ct.tornarTutor("1234", "cc", 1);
	}
	
	/**
	 * Testa se cadastra um tutor com proficiência valor de borda 5.
	 */
	@Test
	public void testCadastraTutorProficienciaBorda5() {
		this.ct.tornarTutor("1234", "cc", 5);
	}
	
	/**
	 * Testa se cadastra um tutor com várias disciplinas.
	 */
	@Test
	public void testCadastraTutorVariasDisciplinas() {
		this.ct.tornarTutor("1234", "cc", 3);
		this.ct.tornarTutor("1234", "dd", 3);
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um tutor com matrícula null.
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastraTutorMatriculaNull() {
		this.ct.tornarTutor(null, "cc", 2);
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um tutor com matrícula vazia.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraTutorMatriculaVazia() {
		this.ct.tornarTutor("   ", "cc", 2);
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um tutor com diciplina null.
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastraTutorDisciplinaNull() {
		this.ct.tornarTutor("1234", null, 2);
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um tutor com diciplina vazia.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraTutorDisciplinaVazia() {
		this.ct.tornarTutor("1234", "  ", 2);
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um tutor com proficiencia menor que 1.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraTutorProficienciaMenorQue1() {
		this.ct.tornarTutor("1234", "cc", 0);
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um tutor com proficiencia maior que 5.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraTutorProficienciaMaiorQue5() {
		this.ct.tornarTutor("1234", "cc", 6);
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar tutores com mesma matrícula e disciplina.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraTutorMesmaMatriculaEDisciplina() {
		this.ct.tornarTutor("1234", "cc", 3);
		this.ct.tornarTutor("1234", "cc", 3);
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar tutor que não é aluno.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraTutorQueNaoEhAluno() {
		this.ct.tornarTutor("0000", "cc", 3);
	}
	
	// *----------- RECUPERAR TUTOR ----------*
	
	/**
	 * Testa se recuperar tutor passando matrícula correta.
	 */
	@Test
	public void testRecuperaTutor() {
		String alunoString = "1234 - daniel - 123 - daniel@email.com";
		assertEquals(alunoString, this.ct.recuperaTutor("2222"));
	}
	
	/**
	 * Testa se uma exceção é lançada recuperar tutor passando matrícula nula.
	 */
	@Test(expected=NullPointerException.class)
	public void testRecuperaTutorMatriculaNull() {
		this.ct.recuperaTutor(null);
	}
	
	/**
	 * Testa se uma exceção é lançada recuperar tutor passando matrícula vazia.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testRecuperaTutorMatriculaVazia() {
		this.ct.recuperaTutor("  ");
	}
	
	/**
	 * Testa se uma exceção é lançada recuperar tutor passando matrícula inexistente.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testRecuperaTutorMatriculaNaoCadastrada() {
		this.ct.recuperaTutor("0000");
	}
	
	// *----------- LISTAR TUTORES -----------*

	/**
	 * Testa se lista tutores em ordem alfabética.
	 */
	@Test
	public void testListaTutores() {
		String listaDeTutores = "5678 - ana - 10 - ana@email.com, " + 
								"2222 - bright - 40 - bright@email.com, " + 
								"1324 - simba - 40 - simba@email.com";
		assertEquals(listaDeTutores, this.ct.listarTutores());
	}
	
	/**
	 * Testa se lista tutores sem tutores cadastrados.
	 */
	@Test
	public void testListaTutoresSemTutoresCadastrados() {
		this.ct = new ControllerTutores(this.ca);
		assertEquals("", this.ct.listarTutores());
	}
}
