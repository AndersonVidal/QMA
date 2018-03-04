package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.Ajuda;
import model.AjudaOnline;
import model.AjudaPresencial;
import model.Aluno;
import utils.ComparaNotaAlunoTutor;
import utils.Validador;

/**
 * Controle que contem a lógica de negócio do armazenamento de ajudas do sistema.
 * 
 * @author Daniel Coura
 */
public class ControllerAjudas {
	
	/**
	 * Representação de um Controller de alunos.
	 */
	private ControllerAlunos ctAlunos;
	/**
	 * Representação de uma lista de ajudas.
	 */
	private List<Ajuda> ajudas;
	
	/**
	 * Constrói o controle de ajudas a partir do controle de alunos.
	 * 
	 * @param ctAlunos o controle de alunos
	 */
	public ControllerAjudas(ControllerAlunos ctAlunos) {
		this.ctAlunos = ctAlunos;
		this.ajudas = new ArrayList<Ajuda>();
	}
	
	/**
	 * Seleciona um tutor que possui melhor proficiencia na disciplina 
	 * e retorna sua matrícula de acordo com os critérios:
	 * <ul><li>disciplina</li>
	 * <li>horario</li>
	 * <li>dia</li></ul>
	 * Caso exista empate entre tutores, é retorna o tutor mais antigo no sistema.
	 * Caso nenhum tutor seja selecionado, é retornado null.
	 * 
	 * @param matrAluno Representação da matrícula do aluno, no formato "123456"
	 * @param disciplina Represetação da disciplina, no formato "inglês"
	 * @param horario Representação do horário, no formato "12:12"
	 * @param dia Representação do dia, no formato "seg"
	 * @param local Representação do local, no formato "ufcg"
	 * @return Representação textual da matrícula do tutor. Null caso não seja selecionado tutor.
	 */
	private String selecionaTutorAjudaPresencial(String matrAluno, String disciplina, String horario, String dia, String local) {
		List<Aluno> alunosTutores = new ArrayList<Aluno>();
		for (Aluno aluno : this.ctAlunos.getAlunosTutores()) {
			boolean contemDisciplina = aluno.getTutor().getDisciplinas().containsKey(disciplina);
			boolean diaDisponivel = aluno.getTutor().getDisponibilidade().containsKey(dia);
			boolean horarioDisponivel = aluno.getTutor().getDisponibilidade().get(dia).contains(horario);
			boolean localDisponivel = aluno.getTutor().getLocalAtendimento().contains(local);
			
			if(contemDisciplina && diaDisponivel && horarioDisponivel && localDisponivel) {
				alunosTutores.add(aluno);
			}
		}		
		
		String tutorSelecionado = this.selecionaMelhorTutor(alunosTutores, matrAluno);
		
		return tutorSelecionado;
	}

	private String selecionaMelhorTutor(List<Aluno> l, String matrAluno){
		if(l.isEmpty()) return null;
		Comparator<Aluno> comparatorTutor = new ComparaNotaAlunoTutor();
		Aluno alnMax = Collections.max(l, comparatorTutor);
		if(alnMax.getMatricula().equals(matrAluno)){
			l.remove(alnMax);
			this.selecionaMelhorTutor(l, matrAluno);
		}
		return alnMax.getMatricula();
	}
	
	/**
	 * Retorna a matrícula do tutor que possui melhor proficiencia na disciplina.
	 * Caso exista empate entre tutores, é retorna o tutor mais antigo no sistema.
	 * Caso nenhum tutor seja selecionado, é retornado null. 
	 * 
	 * @param disciplina Represetação da disciplina, no formato "inglês"
	 * @return Representação textual da matrícula do tutor. Null caso nenhum tutor seja selecionado.
	 */
	private String selecionaTutorAjudaOnline(String matrAluno, String disciplina) {
		List<Aluno> alunosTutores = new ArrayList<Aluno>();
		for (Aluno aluno : this.ctAlunos.getAlunosTutores()) {
			if(aluno.getTutor().getDisciplinas().containsKey(disciplina)) {
				alunosTutores.add(aluno);
			}
		}
		
		String tutorSelecionado = this.selecionaMelhorTutor(alunosTutores, matrAluno);
		
		return tutorSelecionado;
	}
	
	/**
	 * Registra uma ajuda presencial no sistema.
	 * 
	 * @param matrAluno Represetanção da matrícula do aluno, no formato String "123456"
	 * @param disciplina Representação da disciplina, no formato String "inglês"
	 * @param horario Representação do horário, no formato "12:12"
	 * @param dia Representação do dia, no formato "seg"
	 * @param local Representação do local, no formato "ufcg"
	 * @return O id da ajuda cadastrada
	 */
	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia, String local) {
		Validador.validadorString(matrAluno, "Erro no pedido de ajuda presencial: matricula de aluno nao pode ser vazio ou em branco");
		Validador.validadorString(disciplina, "Erro no pedido de ajuda presencial: disciplina nao pode ser vazio ou em branco");
		Validador.validadorString(horario, "Erro no pedido de ajuda presencial: horario nao pode ser vazio ou em branco");
		Validador.validadorHorario(horario, "Erro no pedido ajuda presencial: horario invalido");
		Validador.validadorString(dia, "Erro no pedido de ajuda presencial: dia nao pode ser vazio ou em branco");
		Validador.validadorString(local, "Erro no pedido de ajuda presencial: local de interesse nao pode ser vazio ou em branco");	
		Validador.validadorSetNotContains(matrAluno, this.ctAlunos.getMatriculaAlunos(), "Erro no pedido de ajuda presencial: matricula de aluno nao cadastrada");

		String tutor = this.selecionaTutorAjudaPresencial(matrAluno, disciplina, horario, dia, local);
		Validador.validadorString(tutor, "Erro no pedido de ajuda presencial: tutor nao encontrado");
		int id = this.ajudas.size() + 1;
		Ajuda ajuda = new AjudaPresencial(id, matrAluno, tutor, disciplina, horario, dia, local);
		this.ajudas.add(ajuda);
		return id;
	}
	
	public int pedirAjudaOnline(String matriculaAluno, String disciplina){
		Validador.validadorString(matriculaAluno, "Erro no pedido de ajuda online: matricula de aluno nao pode ser vazio ou em branco");
		Validador.validadorString(disciplina, "Erro no pedido de ajuda online: disciplina nao pode ser vazio ou em branco");
		Validador.validadorSetNotContains(matriculaAluno, this.ctAlunos.getMatriculaAlunos(), "Erro no pedido de ajuda online: matricula de aluno nao cadastrada");
		
		String tutor = this.selecionaTutorAjudaOnline(matriculaAluno, disciplina);
		Validador.validadorString(tutor, "Erro no pedido de ajuda online: tutor nao encontrado");
		int id = this.ajudas.size() + 1;
		Ajuda ajuda = new AjudaOnline(id, matriculaAluno, tutor, disciplina);
		this.ajudas.add(ajuda);
		return id;
	}
}
