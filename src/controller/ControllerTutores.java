package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.Aluno;
import model.Ocupacao;
import utils.Alfabetica;
import utils.Comparador;
import utils.Validador;

/**
 * Controle que contém a lógica de negócio dos tutores do sistema.
 * 
 * @author Maria Cecília Kemiac
 */
public class ControllerTutores {
	
	/**
	 * Representação de um Controller de alunos.
	 */
	private ControllerAlunos ctAlunos;
	
	/**
	 * Consotrói um controller de tutores a partir de um controller
	 * de alunos.
	 * 
	 * @param ctrlAlunos Controller de alunos
	 */
	public ControllerTutores(ControllerAlunos ctrlAlunos) {
		this.ctAlunos = ctrlAlunos;
	}


	/**
	 * Adiciona um horário ao dia especificado para um tutor específico
	 * 
	 * @param email Email do tutor, no formato String "teste@email.com"
	 * @param horario Horário a ser adicionado, no formato String "17:00"
	 * @param dia Dia da semana, no formato String "seg"
	 */
	public void cadastrarHorario(String email, String horario, String dia) {
		Validador.validadorString(email, "Erro no cadastrar horario: email nao pode ser vazio ou em branco");
		Validador.validadorMapKeyNotConteins(email, this.ctAlunos.getAlunosTutoresKeyEmail(), "Erro no cadastrar horario: tutor nao cadastrado");
		
		Ocupacao tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
		tutor.addHorario(horario, dia);
	}

	/**
	 * Adiciona um local de atendimento do tutor especificado.
	 * 
	 * @param email Email do tutor, no formato String "teste@email.com"
	 * @param local Local de atendimento a ser adicionado, no formato String "ufcg"
	 */
	public void cadastrarLocalDeAtendimento(String email, String local) {
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		Validador.validadorMapKeyNotConteins(email, this.ctAlunos.getAlunosTutoresKeyEmail(), "Erro no cadastrar local de atendimento: tutor nao cadastrado");
		
		Ocupacao tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
		tutor.addLocal(local);
	}
	
	/**
	 *
	 * Verifica se o horario especificado é de disponibilidade do tutor especificado.
	 *
	 * @param email Email do tutor, no formato String "teste@email.com"
	 * @param horario Horário a ser consultado, no formato String "17:00"
	 * @param dia Dia da semana, no formato String "seg"
	 * @return Representacao booleana da verificacao de horário do tutor
	 */
	public boolean consultaHorario(String email, String horario, String dia) {
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		if (!this.ctAlunos.getAlunosTutoresKeyEmail().containsKey(email)) return false; 
		Ocupacao tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
		
		return tutor.consultaHorario(horario, dia);
	}
	
	/**
	 * Verifica se o local especificado é de disponibilidade do tutor especificado
	 * 
	 * @param email Email do tutor, no formato String "teste@email.com"
	 * @param local Local a ser consultado, no formato String "ufcg"
	 * @return Representacao booleana da verificacao de local do tutor
	 */
	public boolean consultaLocal(String email, String local) {
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		if (!this.ctAlunos.getAlunosTutoresKeyEmail().containsKey(email)) return false;
		Ocupacao tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
		
		return tutor.consultaLocal(local);
	}
	
	
	//--------------------------------------------------------------------------
	
	private List<Aluno> getAlunosTutores(Map<String, Aluno> alunos){
		List<Aluno> tutores = new ArrayList<Aluno>();
		for(Aluno aln : alunos.values()) {
			if (aln.isTutor()) {
				tutores.add(aln);
			}
		}
		
		return tutores;
	}
	
	private int getQuatidadeTutores(Map<String, Aluno> alunos){
		return this.getAlunosTutores(alunos).size();
	}
	
	private List<Aluno> ordenaAlfabeticaTutores(List<Aluno> tutores) {
		Comparador alfabetico = new Alfabetica();
		Collections.sort(tutores, alfabetico);
		return tutores;
	}
	
	public Map<String, Aluno> tornarTutor(String matricula, String disciplina, int proficiencia, Map<String, Aluno> alunos){
		Validador.validadorString(matricula, "Erro na definicao de papel: matricula invalida");
		Validador.validadorMapKeyNotConteins(matricula, alunos, "Erro na definicao de papel: Tutor nao encontrado");
		
		Aluno aluno = alunos.get(matricula);
		if (aluno.isTutor()) {
			aluno.getTutor().addDisciplina(disciplina, proficiencia);
		} else  {
			int id = this.getQuatidadeTutores(alunos) + 1;
			aluno.tornaTutor(id, disciplina, proficiencia);
		}
		
		return alunos;
	}
	
	public String recuperaTutor(String matricula, Map<String, Aluno> alunos){
		Validador.validadorString(matricula, "Erro na busca por tutor: Matricula não pode ser vazio ou nulo");
		Validador.validadorMapKeyNotConteins(matricula, alunos, "Erro na busca por tutor: Tutor nao encontrado");
		
		Aluno aluno = alunos.get(matricula);
		if (!aluno.isTutor()) {
			throw new IllegalArgumentException("Erro na busca por tutor: Matricula nao eh de um aluno tutor");
		}
		return aluno.toString();
	}
	
	public String listarAlunosTutores(Map<String, Aluno> alunos){
		List<Aluno> arrayTutores = this.ordenaAlfabeticaTutores(this.getAlunosTutores(alunos));
	
		int quantidadeTutores = this.getQuatidadeTutores(alunos);
		String lista = "";
		int i = 0;
		for (Aluno tutor : arrayTutores) {
			i++;
			if (i < quantidadeTutores) {
				lista += tutor.toString() + ", ";
			} else if(tutor.isTutor()){
				lista += tutor.toString();
			}
		}
		
		return lista;
	}
	
	
}

