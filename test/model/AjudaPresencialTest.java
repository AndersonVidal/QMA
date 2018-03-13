package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Entidade responsável pelos testes unitários da classe AjudaPresencial.
 * 
 * @author Maria Cecília Kemiac
 */
public class AjudaPresencialTest {
	/**
	 * Objeto Ajuda para teste
	 */
	private Ajuda ajuda;
	
	/**
	 * Inicializa um objeto AjudaOnline de teste.
	 */
	@Before
	public void criaAjudaPresencialParaTeste() {
		this.ajuda = new AjudaPresencial(1, "007", "000", "linear", "13:00", "sex", "lá em casa");
	}
	
	// *---------- CONSTRUIR AJUDA PRESENCIAL ----------*
	
	/**
	 * Testa se cria uma ajuda online com os parâmetros corretos. 
	 */
	@Test
	public void testCriaAjudaPresencialSimples() {
		this.ajuda = new AjudaPresencial(1, "007", "000", "linear", "13:00", "sex", "lá em casa");
	}
	
	/**
	 * Testa uma entrada com horario inválido
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriaAjudaHorarioInvalido() {
		this.ajuda = new AjudaPresencial(1, "007", "000", "linear", "13:", "sex", "lá em casa");
	}
	
	/**
	 * Testa uma entrada com horario vazio
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriaAjudaHorarioVazio() {
		this.ajuda = new AjudaPresencial(1, "007", "000", "linear", "", "sex", "lá em casa");
	}
	
	/**
	 * Testa uma entrada com horario em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriaAjudaHorarioEmBranco() {
		this.ajuda = new AjudaPresencial(1, "007", "000", "linear", "   ", "sex", "lá em casa");
	}
	
	/**
	 * Testa uma entrada comdia vazio
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriaAjudaDiaVazio() {
		this.ajuda = new AjudaPresencial(1, "007", "000", "linear", "13:00", "", "lá em casa");
	}
	
	/**
	 * Testa uma entrada com dia em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriaAjudaDiaEmBranco() {
		this.ajuda = new AjudaPresencial(1, "007", "000", "linear", "13:00", "  ", "lá em casa");
	}
	
	/**
	 * Testa uma entrada com local vazio
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriaAjudaLocalVazio() {
		this.ajuda = new AjudaPresencial(1, "007", "000", "linear", "13:00", "sex", "");
	}
	
	/**
	 * Testa uma entrada com local em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriaAjudaLocalEmBranco() {
		this.ajuda = new AjudaPresencial(1, "007", "000", "linear", "13:00", "sex", "   ");
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
		assertEquals(this.ajuda.getInfoAjuda("disciplina"), "linear");
	}
	
	/**
	 * Testa um retorno simples do método getInfoAjuda
	 */
	@Test
	public void testGetInfoAjudaHorario() {
		assertEquals(this.ajuda.getInfoAjuda("horario"), "13:00");
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
		assertEquals(this.ajuda.getInfoTutor(), "Tutor - 000, horario - 13:00, dia - sex, "
				+ "local - lá em casa, disciplina - linear");
	}
	
	// *---------- TOSTRING ----------*
	
	/**
	 * Testa o método toString
	 */
	@Test
	public void testToString() {
		assertEquals(this.ajuda.toString(), "1 - 007 - 000 - linear - 13:00 - sex - lá em casa");
	}
	
	// *---------- EQUALS ----------*
	
	/**
	 * Testa um equals verdadeiro
	 */
	@Test
	public void testEqualsTrue() {
		Ajuda ajuda2 = new AjudaPresencial(1, "007", "000", "linear", "13:00", "sex", "lá em casa");
		assertTrue(this.ajuda.equals(ajuda2));
	}
	
	/**
	 * Testa um equals falso
	 */
	@Test
	public void testEqualsFalse() {
		Ajuda ajuda2 = new AjudaPresencial(2, "007", "000", "linear", "13:00", "sex", "lá em casa");
		assertFalse(this.ajuda.equals(ajuda2));
	}

}

