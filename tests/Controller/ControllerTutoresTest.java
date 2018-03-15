package Controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
		this.ctrlAlunos.cadastrarAluno("teste1", "12345", 1, "83988888888", "teste1@ccc.ufcg.edu.br");
		this.ctrlAlunos.cadastrarAluno("teste2", "12341", 1, "83988888881", "teste1@ccc.ufcg.edu.br");
		this.ctrlAlunos.cadastrarAluno("teste3", "12342", 2, "83988888882", "teste1@ccc.ufcg.edu.br");
		this.ctrlAlunos.cadastrarAluno("teste4", "12343", 3, "83988888883", "teste1@ccc.ufcg.edu.br");
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
		assertEquals("tutor não recuperado", "12345 - teste1 - 1 - 83988888888 - teste1@ccc.ufcg.edu.br", r);
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
		String l = "12345 - teste1 - 1 - 83988888888 - teste1@ccc.ufcg.edu.br";
		Comparator<Aluno> c = new ComparaNome();
		assertEquals("tutores listados incorretamente", l , this.ctrlTutores.listarAlunosTutores(this.ctrlAlunos.getAlunos(), c));
	}
	//---cadastrarHorario
	@Test()
	public void cadastrarHorarioTest() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		alunos.get("12345").tornaTutor(1, "AA", 3);
		alunos.get("12345").getTutor().addHorario("10:00", "seg");
		assertEquals("cadastro de horario não realizado", alunos, this.ctrlTutores.cadastrarHorario("teste1@ccc.ufcg.edu.br", "10:00", "seg", alunosAntigo));
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastrarHorarioEmailNull() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarHorario(null, "10:00", "seg", alunosAntigo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastrarHorarioEmailVazio() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarHorario("  ", "10:00", "seg", alunosAntigo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastrarHorarioEmailInvalido() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarHorario("teste", "10:00", "seg", alunosAntigo);
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastrarHorarioHoraNull() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarHorario("teste1@ccc.ufcg.edu.br", null, "seg", alunosAntigo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastrarHorarioHoraVazia() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarHorario("teste1@ccc.ufcg.edu.br", "  ", "seg", alunosAntigo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraHorarioHoraInvalida() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarHorario("teste1@ccc.ufcg.edu.br", "etc", "seg", alunosAntigo);
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastrarHorarioDiaNull() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarHorario("teste1@ccc.ufcg.edu.br", "10:00", null, alunosAntigo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraHorarioDiaVazio() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarHorario("teste1@ccc.ufcg.edu.br", "10:00", "  ", alunosAntigo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraHorarioDiaInvalido() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarHorario("teste1@ccc.ufcg.edu.br", "10:00", "ztx", alunosAntigo);
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastraHorarioAlunosNull() {
		this.ctrlTutores.cadastrarHorario("teste1@ccc.ufcg.edu.br", "10:00", "seg", null);
	}
	
	//---cadastrarLocalDeAtendimento
	@Test()
	public void cadastrarLocalDeAtendimentoTest() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		alunos.get("12345").tornaTutor(1, "AA", 3);
		alunos.get("12345").getTutor().addLocal("CAA101");
		assertEquals("cadastro de horario não realizado", alunos, this.ctrlTutores.cadastrarLocalDeAtendimento("teste1@ccc.ufcg.edu.br", "CAA101", alunosAntigo));
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastrarLocalDeAtendimentoEmailNull() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarLocalDeAtendimento(null, "CAA101", alunosAntigo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastrarLocalDeAtendimentoEmailVazio() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarLocalDeAtendimento("  ", "CAA101", alunosAntigo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastrarLocalDeAtendimentoEmailInvalido() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarLocalDeAtendimento("213", "CAA101", alunosAntigo);
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastrarLocalDeAtendimentoLocalNull() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarLocalDeAtendimento("teste1@ccc.ufcg.edu.br", null, alunosAntigo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastrarLocalDeAtendimentoLocalVazio() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.cadastrarLocalDeAtendimento("teste1@ccc.ufcg.edu.br", "  ", alunosAntigo);
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastrarLocalDeAtendimentoAlunosNull() {
		this.ctrlTutores.cadastrarLocalDeAtendimento("teste1@ccc.ufcg.edu.br", "CAA101", null);
	}
	
	//---consultaHorario
	@Test()
	public void consultaHorarioTest() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		alunos.get("12345").tornaTutor(1, "AA", 3);
		alunos.get("12345").getTutor().addHorario("10:00", "seg");
		assertEquals("Consulta não realizada corretamente", true, this.ctrlTutores.consultaHorario("teste1@ccc.ufcg.edu.br", "10:00", "seg", alunos));
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaHorarioEmailNull() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaHorario(null, "10:00", "seg", alunos);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaHorarioEmailVazio() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaHorario("   ", "10:00", "seg", alunos);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaHorarioEmailInvalido() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaHorario("asfas", "10:00", "seg", alunos);
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaHorarioHoraNull() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaHorario("teste1@ccc.ufcg.edu.br", null, "seg", alunos);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaHorarioHoraVazia() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaHorario("teste1@ccc.ufcg.edu.br", "  ", "seg", alunos);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaHorarioHoraInvalida() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaHorario("teste1@ccc.ufcg.edu.br", "aaa", "seg", alunos);
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaHorarioDiaNull() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaHorario("teste1@ccc.ufcg.edu.br", "10:00", null, alunos);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaHorarioDiaVazio() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaHorario("teste1@ccc.ufcg.edu.br", "10:00", "   ", alunos);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaHorarioDiaInvalido() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaHorario("teste1@ccc.ufcg.edu.br", "10:00", "ges", alunos);
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaHorarioAlunosNull() {
		this.ctrlTutores.consultaHorario("teste1@ccc.ufcg.edu.br", "10:00", "seg", null);	
	}
	
	//---consultaLocal
	@Test()
	public void consultaLocalDeAtendimentoTest() {
		Map<String, Aluno> alunosAntigo = this.ctrlAlunos.getAlunos();
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		alunos.get("12345").tornaTutor(1, "AA", 3);
		alunos.get("12345").getTutor().addLocal("CAA101");
		assertEquals("cadastro de horario não realizado", true, this.ctrlTutores.consultaLocal("teste1@ccc.ufcg.edu.br", "CAA101", alunosAntigo));
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaLocalDeAtendimentoEmailNull() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaLocal("teste1@ccc.ufcg.edu.br", "CAA101", alunos);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaLocalDeAtendimentoEmailVazio() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaLocal("  ", "CAA101", alunos);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaLocalDeAtendimentoEmailInvalido() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaLocal("213", "CAA101", alunos);
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaLocalDeAtendimentoLocalNull() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaLocal("teste1@ccc.ufcg.edu.br", null, alunos);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaLocalDeAtendimentoLocalVazio() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		this.ctrlTutores.consultaLocal("teste1@ccc.ufcg.edu.br", "  ", alunos);
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaLocalDeAtendimentoAlunosNull() {
		this.ctrlTutores.consultaLocal("teste1@ccc.ufcg.edu.br", "CAA101", null);
	}
	
	//---getAlunosTutores
	@Test()
	public void getAlunosTutoresTest() {
		Map<String, Aluno> alunos = this.ctrlAlunos.getAlunos();
		alunos.get("12345").tornaTutor(1, "AA", 3);
		List<Aluno> l = new ArrayList<>();
		l.add(alunos.get("12345"));
		assertEquals("Recuperação de alunos tutores incorreta", l, this.ctrlTutores.getAlunosTutores(alunos));
	}
	
	@Test(expected=NullPointerException.class)
	public void getAlunosTutoresAlunosNull() {
		this.ctrlTutores.getAlunosTutores(null);
	}
}
