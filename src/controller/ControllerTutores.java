package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.Aluno;
import utils.Alfabetica;
import utils.Comparador;
import utils.Validador;

/**
 * Controle que contém a lógica de negócio dos tutores do sistema.
 * 
 * @author Maria Cecília Kemiac e Anderson Fellipe de Vasconcelos Vidal
 */
public class ControllerTutores {
	
	/**
	 * Adiciona a propriedade tutor a um aluno cadastrado no sistema. Caso o aluno já seja tutor, é
	 * adicionado apenas a disciplina com sua profissiencia.
	 * 
	 * @param matricula Matricula do aluno, no formato String "111222333"
	 * @param disciplina Disciplina para tutor, no formato String "Calculo 2"
	 * @param proficiencia Profissiencia do tutor na disciplina, no formato inteiro "1"
	 * @param alunos Alunos cadastrados no sistema, no formato Map
	 * @return Cadastro de aluno atualizados, no formato Map
	 */
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
	
	/**
	 * Recupera a descrição de um tutor.
	 * 
	 * @param matricula Matricula do tutor no sistema.
	 * @param alunos Alunos cadastrados no sistema, no formato Map
	 * @return Desrição de um tutor.
	 */
	public String recuperaTutor(String matricula, Map<String, Aluno> alunos){
		Validador.validadorString(matricula, "Erro na busca por tutor: Matricula não pode ser vazio ou nulo");
		Validador.validadorMapKeyNotConteins(matricula, alunos, "Erro na busca por tutor: Tutor nao encontrado");
		
		Aluno aluno = alunos.get(matricula);
		if (!aluno.isTutor()) {
			throw new IllegalArgumentException("Erro na busca por tutor: Matricula nao eh de um aluno tutor");
		}
		return aluno.toString();
	}
	
	/**
	 * Cria e retorna uma lista de descrições de cada aluno que é tutor
	 * 
	 * @param alunos Mapa de alunos cadastrados, no formato Map
	 * @return Lista de descrições de alunos tutores
	 */
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
	
	/**
	 * Cadastra um horário de disponibilidade de atendimeno para um tutor.
	 * 
	 * @param email Email do aluno tutor, no formato String "tutor@gmail.com"
	 * @param horario Horario de disponibilidade, no formato String "HH:mm"
	 * @param dia Dia de atendimento, no formato String "ter"
	 * @param alunos Cdastro de alunos do sistema, no formato Map
	 * @return Cadastro atualizado dos alunos no sistema, no formato Map
	 */
	public Map<String, Aluno> cadastrarHorario(String email, String horario, String dia, Map<String, Aluno> alunos) {
		Validador.validadorString(email, "Erro no cadastrar horario: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		Validador.validadorString(matriculaTutor, "Erro no cadastrar horario: tutor nao cadastrado");
		
		alunos.get(matriculaTutor).getTutor().addHorario(horario, dia);
		return alunos;
	}
	
	/**
	 * Registra um local de disponibilidade para atendimentos de um tutor cadastrado
	 * 
	 * @param email Email do aluno tutor, no formato String "tutor@gmail.com"
	 * @param local Local de atendimento, no formato String "CAA303"
	 * @param alunos Cdastro de alunos do sistema, no formato Map
	 * @return Cadastro atualizado dos alunos no sistema, no formato Map
	 */
	public Map<String, Aluno> cadastrarLocalDeAtendimento(String email, String local, Map<String, Aluno> alunos) {		
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		Validador.validadorString(matriculaTutor, "Erro no cadastrar local de atendimento: tutor nao cadastrado");
		
		alunos.get(matriculaTutor).getTutor().addLocal(local);
		return alunos;
	}
	
	/**
	 * Retorna a possibilidade de um atendimento em determinado horario e dia
	 * 
	 * @param email Email do aluno tutor, no formato String "tutor@gmail.com"
	 * @param horario Horario de disponibilidade, no formato String "HH:mm"
	 * @param dia Dia de atendimento, no formato String "ter"
	 * @param alunos Cdastro de alunos do sistema, no formato Map
	 * @return Booleano que representa a proposição de horário de atendimento existir.
	 */
	public boolean consultaHorario(String email, String horario, String dia, Map<String, Aluno> alunos){
		Validador.validadorString(email, "Erro na consulta de horario: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		if(matriculaTutor == null) return false;
		
		return alunos.get(matriculaTutor).getTutor().consultaHorario(horario, dia); 
	}
	
	/**
	 * Retorna a possibilidade de um atendimento em determinado local.
	 * 
	 * @param email Email do aluno tutor, no formato String "tutor@gmail.com"
	 * @param local Local de atendimento, no formato String "CAA303"
	 * @param alunos Cdastro de alunos do sistema, no formato Map
	 * @return Booleano que representa a proposição de local de atendimento existir.
	 */
	public boolean consultaLocal(String email, String local, Map<String, Aluno> alunos){
		Validador.validadorString(email, "Erro na consulta de local: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		if(matriculaTutor == null) return false;
		
		return alunos.get(matriculaTutor).getTutor().consultaLocal(local);
	}
	
	/**
	 * Retorna uma lista de alunos que são tutores.
	 * 
	 * @param alunos Cdastro de alunos do sistema, no formato Map
	 * @return Alunos tutores, no formato List
	 */
	public List<Aluno> getAlunosTutores(Map<String, Aluno> alunos){
		List<Aluno> tutores = new ArrayList<Aluno>();
		for(Aluno aln : alunos.values()) {
			if (aln.isTutor()) tutores.add(aln);
		}
		
		return tutores;
	}
	
	/**
	 * Registra uma avaliação de uma ajuda prestara por um tutor identificado pela sua matricula.
	 * 
	 * @param matrTutor Matricula do tutor responsável pela ajuda, no formato String "123456"
	 * @param nota Avaliação para a ajuda prestada, no formato inteiro "7"
	 * @param alunos Mapa de alunos cadastrados, no formato Map
	 * @return Mapa de alunos cadastrados atualizado.
	 */
	public Map<String, Aluno> avaliaTutor(String matrTutor, int nota, Map<String, Aluno> alunos){
		Validador.validadorMapKeyNotConteins(matrTutor, alunos, "Tutor não encontrado");
		alunos.get(matrTutor).getTutor().avaliar(nota);
		return alunos;
	}
	
	/**
	 * Recupera a nota de um tutor de acordo com a matricula do mesmo. <br>
	 * Caso a matricula referencie um aluno que não é tutor, é retornado -1;
	 * 
	 * @param matriculaTutor Matricula do aluno tutor, no formato String "123456"
	 * @param alunos Mapa de alunos cadastrados, no formato Map
	 * @return Nota do tutor. -1, caso matricula não referencie tutor.
	 */
	public int pegarNota(String matriculaTutor, Map<String, Aluno> alunos) {
		Validador.validadorString(matriculaTutor, "tutor não pode ser nulo ou vazio");
		Validador.validadorMapKeyNotConteins(matriculaTutor, alunos, "tutor não cadastrado");
		Aluno aluno = alunos.get(matriculaTutor);
		if(aluno.isTutor()) {
			return aluno.getNota();
		}
		return -1;
	} 
	
	private int getQuatidadeTutores(Map<String, Aluno> alunos){
		return this.getAlunosTutores(alunos).size();
	}
	
	private List<Aluno> ordenaAlfabeticaTutores(List<Aluno> tutores) {
		Comparador alfabetico = new Alfabetica();
		Collections.sort(tutores, alfabetico);
		return tutores;
	}
	
	private String buscaTutor(Map<String, Aluno> alunos, String email) {
		String matriculaTutor = null;
		for(Aluno aluno : alunos.values()) {
			if(aluno.isTutor()) {
				if(aluno.getEmail().equals(email)) matriculaTutor = aluno.getMatricula();
			}
		}
		return matriculaTutor;
	}
	
	
}