package modelTest;

import static org.junit.Assert.*;
import org.junit.*;

import model.Aluno;

public class AlunoTest {

	private Aluno aluno;
	
	@Before
	public void criaAlunoTeste() {
		this.aluno = new Aluno("123456", "dani", 1, "1234-1234", "daniel@ccc.br");
	}
	
	// *----- CRIA ALUNO -----*
	
	@Test
	public void testCriaAluno() {
		this.aluno = new Aluno("123456", "dani", 1, "1234-1234", "daniel@ccc.br");
	}
	
	@Test
	public void testCriaAlunoSemTelefone() {
		this.aluno = new Aluno("123456", "dani", 1, "", "daniel@ccc.br");
	}
	
	@Test(expected=NullPointerException.class)
	public void testCriaAlunoMatriculaNull() {
		this.aluno = new Aluno(null, "dani", 1, "1234-1234", "daniel@ccc.br");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoMatriculaVazia() {
		this.aluno = new Aluno("  ", "dani", 1, "1234-1234", "daniel@ccc.br");
	}
	
	@Test(expected=NullPointerException.class)
	public void testCriaAlunoNomeNull() {
		this.aluno = new Aluno("123456", null, 1, "1234-1234", "daniel@ccc.br");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public 	void testCriaAlunoNomeVazio() {
		this.aluno = new Aluno("123456", "  ", 1, "1234-1234", "daniel@ccc.br");
	}
	
	@Test(expected=NullPointerException.class)
	public 	void testCriaAlunoTelefoneNull() {
		this.aluno = new Aluno("123456", "daniel", 1, null, "daniel@ccc.br");
	}
	

	@Test(expected=NullPointerException.class)
	public 	void testCriaAlunoEmailNull() {
		this.aluno = new Aluno("123456", "daniel", 1, "1234-1234", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoEmailVazio() {
		this.aluno = new Aluno("123456", "daniel", 1, "1234-1234", "  ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public 	void testCriaAlunoEmailSemArroba() {
		this.aluno = new Aluno("123456", "daniel", 1, "1234-1234", "semarroba");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoEmailVazioAntesDoArroba() {
		this.aluno = new Aluno("123456", "daniel", 1, "1234-1234", "@ccc.br");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCriaAlunoEmailVazioDepoisDoArroba() {
		this.aluno = new Aluno("123456", "daniel", 1, "1234-1234", "dani@");
	}
	
	// *----- TORNAR TUTOR -----*
	
	@Test
	public void testTornaTutor() {
		this.aluno.tornaTutor(1, "atal", 3);
	}
	
	@Test
	public void testTornaTutorProficienciaLimite1() {
		this.aluno.tornaTutor(1, "atal", 1);
	}
	
	@Test
	public void testTornaTutorProficienciaLimite5() {
		this.aluno.tornaTutor(1, "atal", 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTornaTutorIdNegativo() {
		this.aluno.tornaTutor(-1, "atal", 3);
	}
	
	@Test(expected=NullPointerException.class)
	public void testTornaTutorDisciplinaNull() {
		this.aluno.tornaTutor(1, null, 3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTornaTutorDisciplinaVazia() {
		this.aluno.tornaTutor(1, "  ", 3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTornaTutorProficienciaMenorQue1() {
		this.aluno.tornaTutor(1, "atal", 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTornaTutorProficienciaMaiorQue5() {
		this.aluno.tornaTutor(1, "atal", 6);
	}

}
