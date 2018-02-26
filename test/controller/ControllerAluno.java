package controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import controller.ControllerAlunos;

/**
 * Entidade responsável pelos testes unitários da classe ControllerAlunos.
 * 
 * @author Daniel Coura
 */
public class ControllerAluno {

	private ControllerAlunos ca = new ControllerAlunos();
	
	@Before
	public void cadastraAlunosParaTeste() {
		this.ca.cadastrarAluno("aba", "123412", 123, "123-123", "aluno1@ccc.br");
		this.ca.cadastrarAluno("aaa", "654321", 123, "123-123", "aluno2@ccc.br");
		this.ca.cadastrarAluno("cab", "111111", 123, "aluno3@ccc.br");
	}
	
	// *----- CADASTRAR ALUNO ------*
	
	/**
	 * Testa se cadastra um aluno com os parâmetros corretos. 
	 */
	@Test
	public void testCadastraAlunoSimples() {
		this.ca.cadastrarAluno("daniel", "123456", 123, "99123-123", "daniel@ccc.ufcg.edu.br");
	}

	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com o nome nulo. 
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastraAlunoNomeNull() {
		this.ca.cadastrarAluno(null, "123456", 123, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com o nome vazio. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoNomeVazio() {
		this.ca.cadastrarAluno("   ", "123456", 123, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com a matricula nula. 
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastraAlunoMatriculaNull() {
		this.ca.cadastrarAluno("daniel", null, 123, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com a matricula vazia. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoMatriculaVazia() {
		this.ca.cadastrarAluno("daniel", "  ", 123, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com o código de curso negativo.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoCodigoCursoNegativo() {
		this.ca.cadastrarAluno("daniel", "123456", -1, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com o código de curso igual a 0.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoCodigoCursoZero() {
		this.ca.cadastrarAluno("daniel", "123456", 0, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com o telefone nulo. 
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastraAlunoTelefoneNull() {
		this.ca.cadastrarAluno("daniel", "123456", 123, null, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com o telefone vazio. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoTelefoneVazio() {
		this.ca.cadastrarAluno("daniel", "123456", 123, "   ", "daniel@ccc.ufcg.edu.br");
	}

	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com o email nulo. 
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastraAlunoEmailNull() {
		this.ca.cadastrarAluno("daniel", "123456", 123, "99123-123", null);
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com o email vazio.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoEmailVazio() {
		this.ca.cadastrarAluno("daniel", "123456", 123, "99123-123", "   ");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com o email sem arroba. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoEmailSemArroba() {
		this.ca.cadastrarAluno("daniel", "123456", 123, "99123-123", "emailsemarroba");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com o email sem algo antes
	 * do arroba. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoEmailVazioAntesDoArroba() {
		this.ca.cadastrarAluno("daniel", "123456", 123, "99123-123", "@semarroba");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno com o email sem algo depois
	 * do arroba. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoEmailVazioDepoisDoArroba() {
		this.ca.cadastrarAluno("daniel", "123456", 123, "99123-123", "semarroba@");
	}
	
	/**
	 * Testa se cria um aluno sem telefone. 
	 */
	@Test
	public void testCadastraAlunoSemTelefone() {
		this.ca.cadastrarAluno("daniel", "123456", 1, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno sem telefone 
	 * com o nome nulo.
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastraAlunoSemTelefoneNomeNull() {
		this.ca.cadastrarAluno(null, "123456", 1, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno sem telefone 
	 * com o nome vazio.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoSemTelefoneNomeVazio() {
		this.ca.cadastrarAluno("  ", "123456", 1, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno sem telefone 
	 * com a matricula nula.
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastraAlunoSemTelefoneMatriculaNull() {
		this.ca.cadastrarAluno("daniel", null, 1, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno sem telefone 
	 * com a matricula vazia.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoSemTelefoneMatriculaVazia() {
		this.ca.cadastrarAluno("daniel", "  ", 1, "daniel@ccc.ufcg.edu.br");
	}

	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno sem telefone 
	 * com o código de curso negativo.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoSemTelefoneCodigoCursoNegativo() {
		this.ca.cadastrarAluno("daniel", "123456", -1, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno sem telefone 
	 * com o código de curso igual a 0.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoSemTelefoneCodigoCursoZero() {
		this.ca.cadastrarAluno("daniel", "123456", 0, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno sem telefone
	 * com o email nulo.
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastraAlunoSemTelefoneEmailNull() {
		this.ca.cadastrarAluno("daniel", "123456", 123, null);
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno sem telefone 
	 * com o email vazio.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoSemTelefoneEmailVazio() {
		this.ca.cadastrarAluno("daniel", "123456", 123, "   ");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno sem telefone
	 * com o email sem o arroba.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoSemTelefoneEmailSemArroba() {
		this.ca.cadastrarAluno("daniel", "123456", 123, "emailsemarroba");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno sem telefone 
	 * com o email sem algo antes do arroba.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoSemTelefoneEmailVazioAntesDoArroba() {
		this.ca.cadastrarAluno("daniel", "123456", 123, "@semarroba");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar um aluno sem telefone
	 * com o email sem algo depois do arroba.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraAlunoSemTelefoneEmailVazioDepoisDoArroba() {
		this.ca.cadastrarAluno("daniel", "123456", 123, "semarroba@");
	}
	
	/**
	 * Testa se uma exceção é lançada ao cadastrar alunos com mesma matrícula.
	 */
	@Test(expected=StringIndexOutOfBoundsException.class)
	public void testCadastraAlunoComMesmaMatricula() {
		this.ca.cadastrarAluno("daniel", "123412", 123, "aluno@ccc.br");
	}
	
	// *------ RECUPERAR ALUNO ------*
	
	@Test
	public void testRecuperaAluno() {
		String stringAluno = "123412 - aba - 123 - 123-123 - aluno1@ccc.br";
		assertEquals(stringAluno, this.ca.recuperaAluno("123412"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testRecuperaAlunoMatriculaNull() {
		this.ca.recuperaAluno(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRecuperaAlunoMatriculaVazia() {
		this.ca.recuperaAluno("  ");
	}
	
	@Test(expected=NullPointerException.class)
	public void testRecuperaAlunoMatriculaInexistente() {
		this.ca.recuperaAluno("0000");
	}
	
	// *------ LISTAR ALUNOS ------*
	
	@Test
	public void testListaAlunos() {
		String listaDeAlunos = "654321 - aaa - 123 - 123-123 - aluno2@ccc.br, " +
							   "123412 - aba - 123 - 123-123 - aluno1@ccc.br, " +
							   "111111 - cab - 123 - aluno3@ccc.br";
		assertEquals(listaDeAlunos, this.ca.listarAlunos());
	}
	
	@Test
	public void testListaAlunosSemAlunosCadastrados() {
		ControllerAlunos ca = new ControllerAlunos();
		assertEquals("", ca.listarAlunos());
	}
	
	// *------ INFORMAÇÕES DE ALUNO ------*
	
	@Test
	public void testGetInfoAlunoNome() {
		assertEquals("aaa", this.ca.getInfoAluno("654321", "Nome"));
	}
	
	@Test
	public void testGetInfoAlunoTelefone() {
		assertEquals("123-123", this.ca.getInfoAluno("654321", "Telefone"));
	}
	
	@Test
	public void testGetInfoAlunoEmail() {
		assertEquals("aluno2@ccc.br", this.ca.getInfoAluno("654321", "Email"));
	}
	
	@Test
	public void testGetInfoAlunoCodigoCurso() {
		assertEquals("123", this.ca.getInfoAluno("654321", "CodigoCurso"));
	}
	
	@Test
	public void testGetInfoAlunoSemTelefone() {
		assertEquals("", this.ca.getInfoAluno("111111", "Telefone"));
	}
	
	@Test(expected=StringIndexOutOfBoundsException.class)
	public void testGetInfoAlunoAtributoInvalido() {
		this.ca.getInfoAluno("111111", "Teste");
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetInfoAlunoMatriculaInexistente() {
		this.ca.getInfoAluno("0000", "Teste");
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetInfoMatriculaNull() {
		this.ca.getInfoAluno(null, "Nome");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetInfoMatriculaVazia() {
		this.ca.getInfoAluno("  ", "Nome");
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetInfoAtributoNull() {
		this.ca.getInfoAluno("654321", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetInfoAtributoVazio() {
		this.ca.getInfoAluno("654321", "  ");
	}
}
