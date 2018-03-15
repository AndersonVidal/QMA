package Controller;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.*;

import controller.ControllerAlunos;
import controller.ControllerTutores;
import model.Aluno;
import utils.ComparaNome;

class ControllerTutoresTest {
	
	private ControllerAlunos ctrlAlunos;
	private ControllerTutores ctrlTutores;
	
	@Before
	void inicializacao() {
		this.ctrlAlunos = new ControllerAlunos();
		this.ctrlTutores = new ControllerTutores();
		this.ctrlAlunos.cadastrarAluno("teste1", "12345", 1, "83988888888", "teste1@ufcg.edu.br");
		this.ctrlAlunos.cadastrarAluno("teste2", "12341", 1, "83988888881", "teste1@ufcg.edu.br");
		this.ctrlAlunos.cadastrarAluno("teste3", "12342", 2, "83988888882", "teste1@ufcg.edu.br");
		this.ctrlAlunos.cadastrarAluno("teste4", "12343", 3, "83988888883", "teste1@ufcg.edu.br");
	}
	
	//---tornarTutor
	@Test()
	public void tornarTutorTest() {
		this.ctrlTutores.tornarTutor("12345", "P2", 3, this.ctrlAlunos.getAlunos());
		if(!this.ctrlAlunos.getAlunos().get("12345").isTutor()) fail(); 
	}

	@Test(expected=NullPointerException.class)
	public void tornarTutorMatriculaNull() {
		this.ctrlTutores.tornarTutor(null, "P2", 3, this.ctrlAlunos.getAlunos());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tornarTutorMatriculaVazia() {
		this.ctrlTutores.tornarTutor("  ", "P2", 3, this.ctrlAlunos.getAlunos());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tornarTutorMatriculaInexistente() {
		this.ctrlTutores.recuperaTutor("54321", this.ctrlAlunos.getAlunos());
	}
	
	@Test(expected=NullPointerException.class)
	public void tornarTutorDisciplinaNull() {
		this.ctrlTutores.tornarTutor("12345", null, 3, this.ctrlAlunos.getAlunos());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tornarTutorDisciplinaVazia() {
		this.ctrlTutores.tornarTutor("12345", "  ", 3, this.ctrlAlunos.getAlunos());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tornarTutorProfissienciaLimiteInferior() {
		this.ctrlTutores.tornarTutor("12345", "P2", 0, this.ctrlAlunos.getAlunos());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tornarTutorProfissienciaLimiteSuperior() {
		this.ctrlTutores.tornarTutor("12345", "P2", 6, this.ctrlAlunos.getAlunos());
	}
	
	@Test(expected=NullPointerException.class)
	public void tornarTutorAlunosNull() {
		this.ctrlTutores.tornarTutor("12345", "P2", 3, null);
	}
	
	//---recuperarTutor
	@Test()
	public void recuperaTutorTest() {
		this.ctrlTutores.tornarTutor("12345", "P2", 3, this.ctrlAlunos.getAlunos());
		String r = this.ctrlTutores.recuperaTutor("12345", this.ctrlAlunos.getAlunos());
		assertEquals("tutor não recuperado", "12345 - teste1 - 1 - 83988888888 - teste1@ufcg.edu.br", r);
	}
	
	@Test(expected=NullPointerException.class)
	public void recuperaTutorMatriculaNull() {
		this.ctrlTutores.recuperaTutor(null, this.ctrlAlunos.getAlunos());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void recuperaTutorMatriculaVazia() {
		this.ctrlTutores.recuperaTutor("  ", this.ctrlAlunos.getAlunos());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void recuperaTutorMatriculaInexistente() {
		this.ctrlTutores.recuperaTutor("54321", this.ctrlAlunos.getAlunos());
	}
	
	@Test(expected=NullPointerException.class)
	public void recuperaTutorAlunosNull() {
		this.ctrlTutores.recuperaTutor("12345", null);
	}
	
	//---listarAlunosTutores
	@Test()
	public void listarAlunosTutoresTest() {
		String l = "12345 - teste1 - 1 - 83988888888 - teste1@ufcg.edu.br";
		Comparator<Aluno> c = new ComparaNome();
		assertEquals("tutores listados incorretamente", l , this.ctrlTutores.listarAlunosTutores(this.ctrlAlunos.getAlunos(), c));
	}
	
	
}
