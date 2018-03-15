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
		tutor = new Tutor(1, "AA", 3);
		tutor.addDisciplina("P2", 3);
		assertEquals("Disciplina não cadastrada", Integer.valueOf(3), tutor.getDisciplinas().get("P2"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void addDisciplinaDisciplinaVazia() {
		tutor = new Tutor(1, "AA", 3);
		tutor.addDisciplina("  ", 3);
	}

	@Test(expected=NullPointerException.class)
	public void addDisciplinaDisciplinaNull() {
		tutor = new Tutor(1, "AA", 3);
		tutor.addDisciplina(null, 3);
	}

	@Test(expected=IllegalArgumentException.class)
	public void addDisciplinaProfissienciaSuperior() {
		tutor = new Tutor(1, "AA", 3);
		tutor.addDisciplina("P2", 10);
	}

	@Test(expected=IllegalArgumentException.class)
	public void addDisciplinaProfissienciaInferior() {
		tutor = new Tutor(1, "AA", 3);
		tutor.addDisciplina("P2", 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void addDisciplinaDisciplinaInexistente() {
		tutor = new Tutor(1, "AA", 3);
		tutor.addDisciplina("2P", 10);
	}
	
	//--addDinheiro
	@Test()
	public void addDinheiroTest() {
		tutor = new Tutor(1, "AA", 3);
		tutor.addDinheiro(200);
		if(tutor.getDinheiro() != 200) fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addDinheiroNegativoTest() {
		tutor = new Tutor(1, "AA", 3);
		tutor.addDinheiro(-200);
	}
	
	//---addHorario
	@Test()
	public void addHorarioTest() {
		tutor = new Tutor(1, "AA", 3);
		tutor.addHorario("10:00", "seg");
		assertEquals("Dinheiro não adicionado", true, tutor.consultaHorario("10:00", "seg"));
	}
	
	@Test(expected=NullPointerException.class)
	public void addHorarioHorarioNull(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addHorario(null, "seg");
	}
	
	@Test(expected=NullPointerException.class)
	public void addHorarioDiaNull(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addHorario("10:00", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addHorarioDiaVazio(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addHorario("10:00", " ");
	}
	
	@Test(expected=NullPointerException.class)
	public void addHorarioHorarioVazio(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addHorario("  ", "seg");
	}
	
	@Test(expected=NullPointerException.class)
	public void addHorarioHorarioInvalido(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addHorario("123", "seg");
	}
	
	@Test(expected=NullPointerException.class)
	public void addHorarioDiaInvalido(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addHorario("10:00", "asff");
	}
	
	//---addLocal
	@Test()
	public void addLocal(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addLocal("CAA201");
		assertEquals("Local não cadastrado", true, tutor.consultaLocal("CAA201"));
	}
	
	@Test(expected=NullPointerException.class)
	public void addLocalLocalNull(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addLocal(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addLocalLocalVazio(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addLocal("  ");
	}
	
	//---consultaHorario
	@Test()
	public void cosultaHorario(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addHorario("10:00", "seg");
		assertEquals("Dinheiro não adicionado", false, tutor.consultaHorario("10:00", "ter"));
	}
	
	//---consultaLocal
	@Test()
	public void consultaLocal(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addLocal("CAA201");
		assertEquals("Dinheiro não adicionado", false, tutor.consultaLocal("RE-10"));
	}
	
	//---avaliar
	@Test()
	public void avaliartest(){
		tutor = new Tutor(1, "AA", 3);
		tutor.avaliar(6);
		if(tutor.getNota() != 1) fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void avaliarNotaNegativa(){
		tutor = new Tutor(1, "AA", 3);
		tutor.avaliar(-1);
	}
	
	//---getNivel
	@Test()
	public void getNiveltest(){
		tutor = new Tutor(1, "AA", 3);
		tutor.avaliar(6);
		if(!tutor.getNivel().equals("Aprendiz")) fail();
	}
	
	//---getTaxa
	@Test()
	public void getTaxatest(){
		tutor = new Tutor(1, "AA", 3);
		tutor.avaliar(6);
		if(tutor.getTaxa() != 20) fail();
	}
	
	//--toString
	@Test()
	public void toStringtest(){
		tutor = new Tutor(1, "AA", 3);
		tutor.addHorario("10:00", "seg");
		tutor.addLocal("CAA201");
		String disciplinas = "AA - 3, ";
		String horarios = "seg - (10:00), ";
		String string = "0 - 0 - (" + disciplinas + ") - (CAA201) - (" + horarios + ")";
		if(!string.equals(tutor.toString())) fail();
	}
}