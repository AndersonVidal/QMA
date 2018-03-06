package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import model.Aluno;
import java.util.Optional;

/**
 * Entidade responsável pelos testes unitários da classe Aluno.
 * 
 * @author Daniel Coura
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
	
	// *---------- CONSTRUIR ALUNO ----------*
	
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
	
	// *---------- TUTOR ----------*
	
	/**
	 * Testa se atribui ao aluno o ofício de tutor passando os parâmetros corretos.
	 */
	@Test
	public void testTornaTutorSimples() {
		this.aluno.tornaTutor(1, "aa", 4);
	}
	
	/**
	 * Testa uma exceção é lançada tornar o aluno um tutor com id negativo.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTornaTutorIdNegativo() {
		this.aluno.tornaTutor(-1, "aa", 4);
	}
	
	/**
	 * Testa uma exceção é lançada tornar o aluno um tutor com id igual a 0.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTornaTutorIdZero() {
		this.aluno.tornaTutor(0, "aa", 4);
	}
	
	/**
	 * Testa uma exceção é lançada tornar o aluno um tutor com disciplina nula.
	 */
	@Test(expected=NullPointerException.class)
	public void testTornaTutorDisciplinaNull() {
		this.aluno.tornaTutor(1, null, 4);
	}
	
	/**
	 * Testa uma exceção é lançada tornar o aluno um tutor com disciplina vazia.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTornaTutorDisciplinaVazia() {
		this.aluno.tornaTutor(1, "   ", 4);
	}
	
	/**
	 * Testa uma exceção é lançada tornar o aluno um tutor com proficiencia menor que 1.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTornaTutorProficienciaMenorQueUm() {
		this.aluno.tornaTutor(1, "aa", 0);
	}
	
	/**
	 * Testa uma exceção é lançada tornar o aluno um tutor com proficiencia maior que 5.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTornaTutorProficienciaMaiorQueCinco() {
		this.aluno.tornaTutor(1, "aa", 6);
	}
	
	/**
	 * Testa se o aluno não é um tutor.
	 */
	@Test
	public void testIsTutorFalse() {
		assertFalse(this.aluno.isTutor());
	}
	
	/**
	 * Testa se o aluno é um tutor.
	 */
	@Test
	public void testIsTutorTrue() {
		this.aluno.tornaTutor(1, "aa", 4);
		assertTrue(this.aluno.isTutor());
	}
	
	/**
	 * Testa se retorna um objeto Optional<Tutor>.
	 */
	@Test
	public void testGetTutor() {
		this.aluno.tornaTutor(1, "aa", 4);
		Tutor tutor = this.aluno.getTutor();
		assertTrue(tutor.equals(new Tutor(1, "aa", 4)));
	}
	
	/**
	 * Testa se uma exceção é lançada ao tentar recuperar um tutor não cadastrado.
	 */
	@Test(expected=NullPointerException.class)
	public void testGetTutorNaoCadastrado() {
		this.aluno.getTutor();
	}

	// *---------- EQUALS ----------*
	
	/**
	 * Verifica se dois objetos Aluno são iguais.
	 */
	@Test
	public void testEqualsTrue() {
		Aluno aluno2 = new Aluno("123456", "atal", 12, "11111-111", "chai@ccc.ufcg.edu.br");
		assertTrue(this.aluno.equals(aluno2));
	}
	
	/**
	 * Verifica se dois objetos Aluno são diferentes (matriculas diferentes).
	 */
	@Test
	public void testEqualsMatriculaDiferente() {
		Aluno aluno2 = new Aluno("123123", "aa", 123, "99123-123", "daniel@ccc.ufcg.edu.br");
		assertFalse(this.aluno.equals(aluno2));
	}
	
	// *---------- TOSTRING ----------*
	
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
		this.aluno = new Aluno("123456", "daniel", 123, "", "daniel@ccc.ufcg.edu.br");
		String stringAluno = "123456 - daniel - 123 - daniel@ccc.ufcg.edu.br";
		assertEquals(stringAluno, this.aluno.toString());
	}
	
}
