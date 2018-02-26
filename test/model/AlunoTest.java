package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import model.Aluno;

/**
 * Entidade responsável pelos testes unitários da classe Aluno.
 * 
 * @author daniel
 *
 */
public class AlunoTest {
	
	/**
	 * Objeto Aluno para teste.
	 */
	private Aluno aluno;
	
	/**
	 * Inicializa um objeto Aluno de teste.
	 */
	@Before
	public void criaAlunoParaTeste() {
		this.aluno = new Aluno("123456", "daniel", 123, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se cria um aluno com os parâmetros corretos. 
	 */
	@Test
	public void testCriaAlunoSimples() {
		this.aluno = new Aluno("123456", "daniel", 123, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com a matrícula nula. 
	 */
	@Test(expected=NullPointerException.class)
	public void testCriaAlunoMatriculaNull() {
		this.aluno = new Aluno(null, "daniel", 123, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com a matrícula vazia. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoMatriculaVazia() {
		this.aluno = new Aluno("   ", "daniel", 123, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com o nome nulo. 
	 */
	@Test(expected=NullPointerException.class)
	public void testCriaAlunoNomeNull() {
		this.aluno = new Aluno("123456", null, 123, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com o nome vazio. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoNomeVazio() {
		this.aluno = new Aluno("123456", "   ", 123, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com o telefone nulo. 
	 */
	@Test(expected=NullPointerException.class)
	public void testCriaAlunoTelefoneNull() {
		this.aluno = new Aluno("123456", "daniel", 123, null, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com o telefone vazio. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoTelefoneVazio() {
		this.aluno = new Aluno("123456", "daniel", 123, "   ", "daniel@ccc.ufcg.edu.br");
	}

	/**
	 * Testa se uma exceção é lançada ao criar um aluno com o email nulo. 
	 */
	@Test(expected=NullPointerException.class)
	public void testCriaAlunoEmailNull() {
		this.aluno = new Aluno("123456", "daniel", 123, "99123-123", null);
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com o email vazio. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoEmailVazio() {
		this.aluno = new Aluno("123456", "daniel", 123, "99123-123", "   ");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com o email sem arroba. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoEmailSemArroba() {
		this.aluno = new Aluno("123456", "daniel", 123, "99123-123", "emailsemarroba");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com o email sem algo antes
	 * do arroba. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoEmailVazioAntesDoArroba() {
		this.aluno = new Aluno("123456", "daniel", 123, "99123-123", "@semarroba");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com o email sem algo depois
	 * do arroba. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoEmailVazioDepoisDoArroba() {
		this.aluno = new Aluno("123456", "daniel", 123, "99123-123", "semarroba@");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com o código de curso negativo.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoCodigoCursoNegativo() {
		this.aluno = new Aluno("123456", "daniel", -1, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno com o código de curso igual a 0.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoCodigoCursoZero() {
		this.aluno = new Aluno("123456", "daniel", 0, "99123-123", "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se cria um aluno sem telefone. 
	 */
	@Test
	public void testCriaAlunoSemTelefone() {
		this.aluno = new Aluno("123456", "daniel", 1, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno sem telefone 
	 * com a matrícula nula.
	 */
	@Test(expected=NullPointerException.class)
	public void testCriaAlunoSemTelefoneMatriculaNull() {
		this.aluno = new Aluno(null, "daniel", 1, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno sem telefone 
	 * com a matrícula vazia.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoSemTelefoneMatriculaVazia() {
		this.aluno = new Aluno("  ", "daniel", 1, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno sem telefone 
	 * com o nome nulo.
	 */
	@Test(expected=NullPointerException.class)
	public void testCriaAlunoSemTelefoneNomeNull() {
		this.aluno = new Aluno("123456", null, 1, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno sem telefone 
	 * com o nome vazio.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoSemTelefoneNomeVazio() {
		this.aluno = new Aluno("123456", "  ", 1, "daniel@ccc.ufcg.edu.br");
	}

	/**
	 * Testa se uma exceção é lançada ao criar um aluno sem telefone
	 * com o email nulo.
	 */
	@Test(expected=NullPointerException.class)
	public void testCriaAlunoSemTelefoneEmailNull() {
		this.aluno = new Aluno("123456", "daniel", 123, null);
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno sem telefone 
	 * com o email vazio.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoSemTelefoneEmailVazio() {
		this.aluno = new Aluno("123456", "daniel", 123, "   ");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno sem telefone
	 * com o email sem o arroba.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoSemTelefoneEmailSemArroba() {
		this.aluno = new Aluno("123456", "daniel", 123, "emailsemarroba");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno sem telefone 
	 * com o email sem algo antes do arroba.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoSemTelefoneEmailVazioAntesDoArroba() {
		this.aluno = new Aluno("123456", "daniel", 123, "@semarroba");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno sem telefone
	 * com o email sem algo depois do arroba.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoSemTelefoneEmailVazioDepoisDoArroba() {
		this.aluno = new Aluno("123456", "daniel", 123, "semarroba@");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno sem telefone 
	 * com o código de curso negativo.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoSemTelefoneCodigoCursoNegativo() {
		this.aluno = new Aluno("123456", "daniel", -1, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um aluno sem telefone 
	 * com o código de curso igual a 0.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoSemTelefoneCodigoCursoZero() {
		this.aluno = new Aluno("123456", "daniel", 0, "daniel@ccc.ufcg.edu.br");
	}
	
	/**
	 * Verifica o toString do aluno que possui telefone.
	 */
	@Test
	public void testToStringAlunoComTelefone() {
		String stringAluno = "123456 - daniel - 123 - 99123-123 - daniel@ccc.ufcg.edu.br";
		assertEquals(stringAluno, this.aluno.toString());
	}
	
	/**
	 * Verifica o toString do aluno que não possui telefone.
	 */
	@Test
	public void testToStringAlunoSemTelefone() {
		this.aluno = new Aluno("123456", "daniel", 123, "daniel@ccc.ufcg.edu.br");
		String stringAluno = "123456 - daniel - 123 - daniel@ccc.ufcg.edu.br";
		assertEquals(stringAluno, this.aluno.toString());
	}
}
