package controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Collections;

import model.Ajuda;
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
	 * Retorna a matrícula do tutor que possui os critérios: disciplina, horario e dia.
	 * 
	 * @param matrAluno Representação da matrícula do aluno, no formato "123456"
	 * @param disciplina Represetação da disciplina, no formato "inglês"
	 * @param horario Representação do horário, no formato "12:12"
	 * @param dia Representação do dia, no formato "seg"
	 * @param local Representação do local, no formato "ufcg"
	 * @return Representação textual da matrícula do tutor 
	 */
	private String selecionaTutorAjudaPresencial(String matrAluno, String disciplina, String horario, String dia, String local) {
		List<Aluno> alunosTutores = new ArrayList<Aluno>();
		for (Aluno aln : this.ctAlunos.getAlunosTutores()) {
			boolean contemDisciplina = aln.getTutor().getDisciplinas().containsKey(disciplina);
			boolean diaDisponivel = aln.getTutor().getDisponibilidade().containsKey(dia);
			boolean horarioDisponivel = aln.getTutor().getDisponibilidade().get(dia).contains(horario);
			boolean localDisponivel = aln.getTutor().getLocalAtendimento().contains(local);
			
			if(contemDisciplina && diaDisponivel && horarioDisponivel && localDisponivel) {
				alunosTutores.add(aln);
			}
		}
		
		if (alunosTutores.isEmpty()) new NullPointerException("Erro no pedido de ajuda presencial: tutor nao encontrado");
		Aluno alnMax = Collections.max(alunosTutores, new ComparaNotaAlunoTutor());
		if (alunosTutores.isEmpty()) new NullPointerException("Erro no pedido de ajuda presencial: tutor nao encontrado");
		if (alnMax.equals(matrAluno)) alunosTutores.remove(alnMax);
				
		return Collections.max(alunosTutores, new ComparaNotaAlunoTutor()).getMatricula();
	}
	
	/**
	 * Retorna a matrícula do tutor que possui o critério disciplina.
	 * 
	 * @param disciplina Represetação da disciplina, no formato "inglês"
	 * @return Representação textual da matrícula do tutor 
	 */
	private String selecionaTutorAjudaOnline(String matrAluno, String disciplina) {
		List<Aluno> alunosTutores = new ArrayList<Aluno>();
		for (Aluno aln : this.ctAlunos.getAlunosTutores()) {
			if(aln.getTutor().getDisciplinas().containsKey(disciplina)) {
				alunosTutores.add(aln);
			}
		}
		
		if (alunosTutores.isEmpty()) new NullPointerException("Erro no pedido de ajuda presencial: tutor nao encontrado");
		Aluno alnMax = Collections.max(alunosTutores, new ComparaNotaAlunoTutor());
		if (alunosTutores.isEmpty()) new NullPointerException("Erro no pedido de ajuda presencial: tutor nao encontrado");
		if (alnMax.equals(matrAluno)) alunosTutores.remove(alnMax);
		
		return Collections.max(alunosTutores, new ComparaNotaAlunoTutor()).getMatricula();
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
		// para passar nos testes
		Validador.validadorString(matrAluno, "Erro no pedido de ajuda presencial: matricula de aluno nao pode ser vazio ou em branco");
		Validador.validadorString(disciplina, "Erro no pedido de ajuda presencial: disciplina nao pode ser vazio ou em branco");
		Validador.validadorString(horario, "Erro no pedido de ajuda presencial: horario nao pode ser vazio ou em branco");
		Validador.validadorHorario(horario, "Erro no pedido ajuda presencial: horario invalido");
		Validador.validadorString(dia, "Erro no pedido de ajuda presencial: dia nao pode ser vazio ou em branco");
		Validador.validadorString(local, "Erro no pedido de ajuda presencial: local de interesse nao pode ser vazio ou em branco");
		
		Validador.validadorSetNotContains(matrAluno, this.ctAlunos.getMatriculaAlunos(), "Erro no pedido de ajuda presencial: matricula de aluno nao cadastrada");

		String tutor = this.selecionaTutorAjudaPresencial(matrAluno, disciplina, horario, dia, local);
		int id = this.ajudas.size() + 1;
		this.ajudas.add(new AjudaPresencial(id, matrAluno, tutor, disciplina, horario, dia, local));
		return id;
		
	} 	
}
