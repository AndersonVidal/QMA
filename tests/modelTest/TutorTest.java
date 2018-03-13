package modelTest;

import static org.junit.Assert.*;

import org.junit.*;

import model.Tutor;

/**
 * Entidade responsável pelos testes unitários da classe Tutor.
 * 
 * @author Daniel Coura
 */
public class TutorTest {

	private Tutor tutor;
	
	//---construtor
	@Test
	public void criaTutorTest() {
		tutor = new Tutor(1, "AA", 4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void criaTutorIdNegativo() {
		tutor = new Tutor(-1, "AA", 4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void criaTutorDisciplinaVazia() {
		tutor = new Tutor(1, "  ", 4);
	}
	
	@Test(expected=NullPointerException.class)
	public void criaTutorDisciplinaNull() {
		tutor = new Tutor(1, null, 4);
	}
	
	@Test(expected=NullPointerException.class)
	public void criaTutorProfissienciaInferior() {
		tutor = new Tutor(1, "AA", 0);
	}
	
	@Test(expected=NullPointerException.class)
	public void criaTutorProfissienciaSuperior() {
		tutor = new Tutor(1, "AA", 6);
	}
	
	//---addDisciplina
	@Test()
	public void addDisciplinaTest() {
		tutor = new Tutor(1, "AA", 0);
		tutor.addDisciplina("P2", 3);
		assertEquals("Disciplina não cadastrada", Integer.valueOf(3), tutor.getDisciplinas().get("P2"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void addDisciplinaDisciplinaVazia() {
		tutor = new Tutor(1, "AA", 0);
		tutor.addDisciplina("  ", 3);
	}

	@Test(expected=NullPointerException.class)
	public void addDisciplinaDisciplinaNull() {
		tutor = new Tutor(1, "AA", 0);
		tutor.addDisciplina(null, 3);
	}

	@Test(expected=IllegalArgumentException.class)
	public void addDisciplinaProfissienciaSuperior() {
		tutor = new Tutor(1, "AA", 0);
		tutor.addDisciplina("P2", 10);
	}

	@Test(expected=IllegalArgumentException.class)
	public void addDisciplinaProfissienciaInferior() {
		tutor = new Tutor(1, "AA", 0);
		tutor.addDisciplina("P2", 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void addDisciplinaDisciplinaInexistente() {
		tutor = new Tutor(1, "AA", 0);
		tutor.addDisciplina("2P", 10);
	}
	
	//--addDinheiro
	@Test()
	public void addDinheiroTest() {
		tutor = new Tutor(1, "AA", 0);
		tutor.addDinheiro(200);
		assertEquals("Dinheiro não adicionado", 200.0, tutor.getDinheiro());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addDinheiroNegativoTest() {
		tutor = new Tutor(1, "AA", 0);
		tutor.addDinheiro(-200);
	}
	
	//---addHorario
	@Test()
	public void addHorarioTest() {
		tutor = new Tutor(1, "AA", 0);
		tutor.addHorario("10:00", "seg");
		assertEquals("Dinheiro não adicionado", true, tutor.consultaHorario("10:00", "seg"));
	}
	
	
}