package model;

import static org.junit.Assert.*;

import org.junit.Test;
import model.Tutor;

/**
 * Entidade responsável pelos testes unitários da classe Tutor.
 * 
 * @author Daniel Coura
 */
public class TutorTest {

	/**
	 * Objeto Tutor para testes.
	 */
	private Tutor tutor;
	
	/**
	 * Testa se cria um tutor com os parâmetros corretos.
	 */
	@Test
	public void testCadastraTutorSimples() {
		this.tutor = new Tutor("1234", "aa", 2);
	}
	
	/**
	 * Testa se cria um tutor com valor de borda 1.
	 */
	@Test
	public void testCadastraTutorProficienciaBorda1() {
		this.tutor = new Tutor("1234", "aa", 1);
	}
	
	/**
	 * Testa se cria um tutor com valor de borda 5.
	 */
	@Test
	public void testCadastraTutorProficienciaBorda5() {
		this.tutor = new Tutor("1234", "aa", 5);
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um tutor com matrícula null.
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastraTutorMatriculaNull() {
		this.tutor = new Tutor(null, "aa", 2);
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um tutor com matrícula vazia.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraTutorMatriculaVazia() {
		this.tutor = new Tutor("   ", "aa", 2);
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um tutor com diciplina null.
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastraTutorDisciplinaNull() {
		this.tutor = new Tutor("1234", null, 2);
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um tutor com diciplina vazia.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraTutorDisciplinaVazia() {
		this.tutor = new Tutor("1234", "  ", 2);
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um tutor com proficiencia menor que 1.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraTutorProficienciaMenorQue1() {
		this.tutor = new Tutor("1234", "aa", 0);
	}
	
	/**
	 * Testa se uma exceção é lançada ao criar um tutor com proficiencia maior que 5.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraTutorProficienciaMaiorQue5() {
		this.tutor = new Tutor("1234", "aa", 6);
	}
	
	/**
	 * Testa se a nota inicial do tutor é 4.
	 */
	@Test
	public void testNotaInicial4() {
		this.tutor = new Tutor("1234", "aa", 6);
		assertEquals(4, this.tutor.getNota());
	}
}
