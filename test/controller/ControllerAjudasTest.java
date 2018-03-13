package controller;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.Aluno;

/**
 * Entidade responsável pelos testes unitários da classe ControllerAjudas.
 * 
 * @author Maria Cecília Kemiac
 */
public class ControllerAjudasTest {
	private ControllerAjudas caj = new ControllerAjudas();
	private List<Aluno> tutores;
	private Set<String> mtrAlunos;

	/**
	 * Cadastra alguns alunos e tutores para testes.
	 */
	@Before
	public void cadastraParaTeste() {
		mtrAlunos = new HashSet<>();
		tutores = new ArrayList<>();
		
		Aluno aluno1 = new Aluno("100", "a", 1, "8888", "a@ccc.com.br");
		Aluno aluno2 = new Aluno("101", "b", 1, "1010", "b@ccc.com.br");
		Aluno aluno3 = new Aluno("102", "c", 1, "2020", "c@ccc.com.br");
		
		mtrAlunos.add(aluno1.getMatricula());
		mtrAlunos.add(aluno2.getMatricula());
		mtrAlunos.add(aluno3.getMatricula());
		
		aluno1.tornaTutor(1, "mat", 3);
		aluno1.getTutor().addHorario("12:00", "sex");
		aluno1.getTutor().addLocal("praça");
		
		aluno2.tornaTutor(2, "mat", 4);
		aluno2.getTutor().addHorario("12:00", "sex");
		aluno2.getTutor().addLocal("praça");
		
		aluno3.tornaTutor(3, "fis", 3);
		
		tutores.add(aluno1);
		tutores.add(aluno2);
		tutores.add(aluno3);
		
	}
	
	/**
	 * Testa a inicialização do método pedirAjudaPresencial
	 */
	@Test
	public void testPedirAjudaPresencial() {
		this.caj.pedirAjudaPresencial("102", "mat", "12:00", "sex", "praça", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com matricula vazia
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialMatrAlunoVazia() {
		this.caj.pedirAjudaPresencial("", "a", "10:00", "seg", "aqui", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com matricula em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialMatrAlunoEmBranco() {
		this.caj.pedirAjudaPresencial(" ", "a", "10:00", "seg", "aqui", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com disciplina vazia
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialDisciplinaVazia() {
		this.caj.pedirAjudaPresencial("111", "", "10:00", "seg", "aqui", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com disciplina em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialDisciplinaEmBranco() {
		this.caj.pedirAjudaPresencial("111", " ", "10:00", "seg", "aqui", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com horario vazio
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialHorarioVazio() {
		this.caj.pedirAjudaPresencial("111", "a", "", "seg", "aqui", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com horario em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialHorarioEmBranco() {
		this.caj.pedirAjudaPresencial("111", "a", " ", "seg", "aqui", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com horario invalido
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialHorarioInvalido() {
		this.caj.pedirAjudaPresencial("111", "a", "122", "seg", "aqui", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com dia vazio
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialDiaVazio() {
		this.caj.pedirAjudaPresencial("111", "a", "12:00", "", "aqui", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com dia em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialDiaEmBranco() {
		this.caj.pedirAjudaPresencial("111", "a", "12:00", " ", "aqui", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com local vazio
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialLocalVazio() {
		this.caj.pedirAjudaPresencial("111", "a", "12:00", "seg", "", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com local em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialLocalEmBranco() {
		this.caj.pedirAjudaPresencial("111", "a", "12:00", "seg", " ", tutores, mtrAlunos);
	}
	
	/**
	 * Testa a entrada do método pedirAjudaPresencial com matricula não cadastrada
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialMtrAlunoNaoCadastrada() {
		this.caj.pedirAjudaPresencial("111", "a", "12:00", "seg", "aqui", tutores, mtrAlunos);
	}
	
	/**
	 * Testa o retorno do método pedirAjudaPresencial quando não há tutor para a ajuda
	 */
	@Test(expected = NullPointerException.class)
	public void testPedirAjudaPresencialSemTutor() {
		this.caj.pedirAjudaPresencial("102", "mat", "00:00", "seg", "lá", tutores, mtrAlunos);
	}
	
	/**
	 * Testa pedir uma ajuda presencial simples
	 */
	@Test
	public void testPedirAjudaPresencialSimples() {
		assertEquals(1, this.caj.pedirAjudaPresencial("102", "mat", "12:00", "sex", "praça", tutores, mtrAlunos));
	}
	
	/**
	 * Testa pedir uma segunda ajuda presencial
	 */
	@Test
	public void testPedirOutraAjudaPresencial() {
		this.caj.pedirAjudaPresencial("102", "mat", "12:00", "sex", "praça", tutores, mtrAlunos);
		assertEquals(2, this.caj.pedirAjudaPresencial("102", "mat", "12:00", "sex", "praça", tutores, mtrAlunos));
	}
	
	/**
	 * Testa a inicialização de uma ajuda online
	 */
	@Test
	public void testPedirAjudaOnline() {
		this.caj.pedirAjudaOnline("100", "fis", tutores, mtrAlunos);
	}
	
	/**
	 * Testa pedir uma ajuda online com matrícula vazia
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaOnlineMatriculaAlunoVazia() {
		this.caj.pedirAjudaOnline("", "fis", tutores, mtrAlunos);
	}
	
	/**
	 * Testa pedir uma ajuda online com matrícula em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaOnlineMatriculaAlunoEmBranco() {
		this.caj.pedirAjudaOnline(" ", "fis", tutores, mtrAlunos);
	}
	
	/**
	 * Testa pedir uma ajuda online com disciplina vazia
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaOnlineDisciplinaVazia() {
		this.caj.pedirAjudaOnline("102", "", tutores, mtrAlunos);
	}
	
	/**
	 * Testa pedir uma ajuda online com disciplina em branco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaOnlineDisciplinaEmBranco() {
		this.caj.pedirAjudaOnline("102", " ", tutores, mtrAlunos);
	}
	
	/**
	 * Testa pedir uma ajuda online com matrícula não cadastrada
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaOnlineMatriculaAlunoNaoCadastrado() {
		this.caj.pedirAjudaOnline("332", "geo", tutores, mtrAlunos);
	}
	
	/**
	 * Testa pedir uma ajuda online simples
	 */
	@Test
	public void testPedirAjudaOnlineSimples() {
		assertEquals(1, this.caj.pedirAjudaOnline("102", "mat", tutores, mtrAlunos));
	}
	
	/**
	 * Testa pedir mais de uma ajuda online
	 */
	@Test
	public void testPedirMaisDeUmaAjuda() {
		this.caj.pedirAjudaOnline("102", "mat", tutores, mtrAlunos);
		assertEquals(2, this.caj.pedirAjudaOnline("102", "mat", tutores, mtrAlunos));
	}
	
	/**
	 * Testa Id inválido para tutor
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPegarTutorIdInvalido() {
		this.caj.pegarTutor(-2);
	}
	
	/**
	 * Testa Id inexistente para tutor
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testPegarTutorIdInexistente() {
		this.caj.pedirAjudaOnline("100", "mat", tutores, mtrAlunos);
		this.caj.pegarTutor(2);
	}
	
	/**
	 * Testa se há o retorno correto de tutor 
	 */
	@Test
	public void testPegarTutor() {
		this.caj.pedirAjudaOnline("100", "fis", tutores, mtrAlunos);
		assertEquals(this.caj.pegarTutor(1), "Tutor - 102, disciplina - fis");
	}
	
	/**
	 * Testa se há o retorno correto de tutor quando há empate na nota
	 */
	@Test
	public void testPegarTutorEmpate() {
		this.caj.pedirAjudaPresencial("102", "mat", "12:00", "sex", "praça", tutores, mtrAlunos);
		assertEquals(this.caj.pegarTutor(1), "Tutor - 100, horario - 12:00, dia - sex, local - praça, disciplina - mat");
	}
	
	/**
	 * Testa uma entrada com Id inválido
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAjudaIdInvalido() {
		this.caj.getInfoAjuda(-2, "disciplina");
	}
	
	/**
	 * Testa uma entrada com Id inexistente
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetInfoAjudaIdInexistente() {
		this.caj.getInfoAjuda(1, "disciplina");
	}
	
	/**
	 * Testa pegar uma informação de uma ajuda simples
	 */
	@Test
	public void testGetInfoAjuda() {
		this.caj.pedirAjudaOnline("100", "mat", tutores, mtrAlunos);
		assertEquals(this.caj.getInfoAjuda(1, "disciplina"), "mat");
	}
	
}
