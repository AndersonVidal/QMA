package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Entidade responsável pelos testes unitários da classe AjudaOnline.
 * 
 * @author Maria Cecília Kemiac
 */
public class AjudaOnlineTest {
	/**
	 * Objeto Ajuda para teste
	 */
	private Ajuda ajuda;
	
	/**
	 * Inicializa um objeto AjudaOnline de teste.
	 */
	@Before
	public void criaAjudaOnlineParaTeste() {
		this.ajuda = new AjudaOnline(1, "116000000", "117000999", "calculo");
	}
	
	// *---------- CONSTRUIR AJUDA ONLINE ----------*

	/**
	 * Testa se cria uma ajuda online com os parâmetros corretos. 
	 */
	@Test
	public void testCriaAjudaOnlineSimples() {
		this.ajuda = new AjudaOnline(1, "116000000", "117000999", "calculo");
	}
	
	/**
	 * Testa uma entrada com Id negativo
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriaAjudaIdNegativo() {
		this.ajuda = new AjudaOnline(-1, "116000000", "117000999", "calculo");
	}
	
	/**
	 * Testa uma entrada com a matricula vazia
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriaAjudaMatriculaAlunoVazia() {
		this.ajuda = new AjudaOnline(1, "", "117000999", "calculo");
	}
	
	/**
	 * Testa uma entrada com a matricula em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriaAjudaMatriculaAlunoEmBranco() {
		this.ajuda = new AjudaOnline(1, "    ", "117000999", "calculo");
	}
	
	/**
	 * Testa uma entrada com a matricula de tutor vazia
	 */
	@Test(expected = IllegalArgumentException.class) 
	public void testCriaAjudaMatriculaTutorVazia() {
		this.ajuda = new AjudaOnline(1, "116000000", "", "calculo");
	}
	
	/**
	 * Testa uma entrada com a matricula de tutor em branco
	 */
	@Test(expected = IllegalArgumentException.class) 
	public void testCriaAjudaMatriculaTutorEmBranco() {
		this.ajuda = new AjudaOnline(1, "116000000", "   ", "calculo");
	}
	
	/**
	 * Testa uma entrada com a disciplina vazia
	 */
	@Test(expected = IllegalArgumentException.class) 
	public void testCriaAjudaDisciplinaVazia() {
		this.ajuda = new AjudaOnline(1, "116000000", "117000999", "");
	}
	
	/**
	 * Testa uma entrada com a disciplina em branco
	 */
	@Test(expected = IllegalArgumentException.class) 
	public void testCriaAjudaDisciplinaEmBranco() {
		this.ajuda = new AjudaOnline(1, "116000000", "117000999", "    ");
	}
	
	// *---------- AJUDA AVALIADA ----------*
	
	/**
	 * Testa se é falsa uma entrada com a ajuda ainda não avaliada
	 */
	@Test
	public void testAjudaAindaNaoAvaliada() {
		assertFalse(this.ajuda.getAvaliacao());
	}
	
	/**
	 * Testa se é verdadeira uma entrada com a ajuda ainda já avaliada
	 */
	@Test
	public void testAjudaAvaliada() {
		this.ajuda.checkAvaliacao();;
		assertTrue(this.ajuda.getAvaliacao());
	}
	
	// *---------- GET INFO ----------*
	
	/**
	 * Testa uma entrada com atributo vazio
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAjudaAtributoVazio() {
		this.ajuda.getInfoAjuda("");
	}
	
	/**
	 * Testa uma entrada com atributo em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAjudaAtributoEmBranco() {
		this.ajuda.getInfoAjuda("   ");
	}
	
	/**
	 * Testa um retorno simples do método getInfoAjuda
	 */
	@Test
	public void testGetInfoAjudaDisciplina() {
		assertEquals(this.ajuda.getInfoAjuda("disciplina"), "calculo");
	}
	
	/**
	 * Testa uma entrada de atributo inexistente
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAjudaAtributoInexistente() {
		this.ajuda.getInfoAjuda("qualquer coisa");
	}
	
	/**
	 * Testa uma entrada simples do método getInfoTutor
	 */
	@Test
	public void testGetInfoTutor() {
		assertEquals(this.ajuda.getInfoTutor(), "Tutor - 117000999, disciplina - calculo");
	}
	
	// *---------- TOSTRING ----------*
	
	/**
	 * Testa o retorno do toString
	 */
	@Test
	public void testToString() {
		assertEquals(this.ajuda.toString(), "1 - 116000000 - 117000999 - calculo");
	}
	
	// *---------- EQUALS ----------*
	
	/**
	 * Testa um equals verdadeiro
	 */
	@Test
	public void testEqualsTrue() {
		Ajuda ajuda2 = new AjudaOnline(1, "116000000", "117000999", "calculo");
		assertTrue(this.ajuda.equals(ajuda2));
	}
	
	/**
	 * Testa um equals falso
	 */
	@Test
	public void testEqualsFalse() {
		Ajuda ajuda2 = new AjudaOnline(2, "116000000", "117000999", "calculo");
		assertFalse(this.ajuda.equals(ajuda2));
	}

}
