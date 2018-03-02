package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import utils.Validador;
import java.time.*;

/**
 * Representação de um tutor, que terá um conjunto de  disciplinas, proficiência 
 * em cada disciplina, uma nota de 0 a 5 e o dinheiro recebido pela tutoria.
 * 
 * @author Maria Cecília Kemiac e Daniel Coura
 */
public class Tutor {
	
	/**
	 * Representação de um mapa das disciplinas onde a chave é a disciplina e o valor
	 * é a proficiência do tutor na disciplina
	 */
	private Map<String, Integer> disciplinas;
	/**
	 * Representação da avaliação do tutor, no formato inteiro "1"
	 */
	private int nota;
	/**
	 * Representacao do dinheiro recebido, no formato double "0.0"
	 */
	private double dinheiro;
	/**
	 * Representacao dos horários de disponibilidade do tutor
	 */
	private Map<String, Set<LocalTime>> disponibilidade;
	/**
	 * Representacao dos locais de atendimento do tutor
	 */
	private Set<String> locaisAtendimento;

	/**
	 * Constroói um tutor a partir da disciplina e da proficiencia.
	 * 
	 * @param disciplina o nome da disciplina.
	 * @param proeficiencia a proficiencia do tutor na disciplina.
	 */
	public Tutor(String disciplina, int proficiencia) {
		Validador.validadorString(disciplina, "Erro no cadastro de disciplina: Disciplina nao pode ser vazia ou nula");
		Validador.validadorIntervalo(proficiencia, 1, 5, "Erro na definicao de papel: Proficiencia invalida");
		
		this.disciplinas = new HashMap<String, Integer>();
		this.disciplinas.put(disciplina, proficiencia);
		this.nota = 4;
		this.dinheiro = 0;
		this.constroiHorarios();
		this.locaisAtendimento = new HashSet<>();
	}
	
	/**
	 * Constrói o mapa de horarios em que a chave sao os dias da
	 * semana e o valor, um conjunto de horarios.
	 */
	private void constroiHorarios(){
		this.disponibilidade = new HashMap<String, Set<LocalTime>>();
		this.disponibilidade.put("seg", new HashSet<LocalTime>());
		this.disponibilidade.put("ter", new HashSet<LocalTime>());
		this.disponibilidade.put("qua", new HashSet<LocalTime>());
		this.disponibilidade.put("qui", new HashSet<LocalTime>());
		this.disponibilidade.put("sex", new HashSet<LocalTime>());
	}
	
	/**
	 * Retorna um mapa onde a chave é uma String disciplina e o valor
	 * é um inteiro proficiência.
	 * 
	 * @return Mapa que representa uma disciplina
	 */
	public Map<String, Integer> getDisciplinas() { return this.disciplinas; }

	/**
	 * Adiciona uma disciplina no tutor. A proficiência dever 
	 * ser de 1 a 5.
	 * 
	 * @param disciplina Nome da disciplina
	 * @param proficiencia Proficiência do tutor na disciplina
	 */
	public void addDisciplina(String disciplina, int proficiencia) {
		Validador.validadorString(disciplina, "Erro no cadastro de disciplina: Disciplina nao pode ser vazia ou nula");
		Validador.validadorMapKeyConteins(disciplina, disciplinas, "Erro na definicao de papel: Ja eh tutor dessa disciplina");
		Validador.validadorIntervalo(proficiencia, 1, 5, "Erro na definicao de papel: Proficiencia invalida");
		
		this.disciplinas.put(disciplina, proficiencia);
	}
	
	public int getNota() { return this.nota; }
	
	public double getDinheiro() { return this.dinheiro; }
	
	public Set<String> getLocalAtendimento() { return this.locaisAtendimento; }
	
	/**
	 * Adiciona dinheiro ao caixa do tutor.
	 * 
	 * @param dinheiro Valor a ser adicionado no caixa
	 */
	public void addDinheiro(double dinheiro) {
		Validador.validadorPositivo(dinheiro, "Erro ao adicionar dinheiro: valor nao pode ser menor ou igual a 0");
		this.dinheiro += dinheiro; 
	}
	
	/**
	 * Adiciona um horario ao dia especificado.
	 * 
	 * @param horario Representacao de um horário, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 */
	public void addHorario(String horario, String dia) {
		Validador.validadorString(dia, "Erro no cadastrar horario: dia nao pode ser vazio ou em branco");
		Validador.validadorMapKeyNotConteins(dia, this.disponibilidade , "Erro no cadastrar horario: dia invalido");
		Validador.validadorString(horario, "Erro no cadastrar horario: horario nao pode ser vazio ou em branco");
		Validador.validadorHorario(horario, "Erro no cadastrar horario: formato de horario invalido");
		
		this.disponibilidade.get(dia).add(LocalTime.parse(horario));
	}
	
	/**
	 * Adiciona um novo local ao conjunto de locais de atendimento.
	 * 
	 * @param local Representacao de um local no formato String "ufcg"
	 */
	public void addLocal(String local) {
		Validador.validadorString(local, "Erro no cadastrar local de atendimento: local nao pode ser vazio ou em branco");
		Validador.validadorSetContains(local, this.locaisAtendimento, "Erro ao cadastrar local de atendimento: local ja existe");
		this.locaisAtendimento.add(local);
	}
	
	/**
	 * Verifica se o horario especificado é de disponibilidade do tutor.
	 * 
	 * @param horario Representação de um horario, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 * @return Representação booleana da verificação de horário do tutor
	 */
	public boolean consultaHorario(String horario, String dia) {
		Validador.validadorString(dia, "Erro no consultar horario: dia null ou vazio");
		Validador.validadorMapKeyNotConteins(dia, this.disponibilidade, "Erro no consultar horario: dia invalido");
		Validador.validadorString(horario, "Erro no consultar horario: horario nao pode ser vazio ou em branco");
		Validador.validadorHorario(horario, "Erro no cadastrar horario: formato de horario invalido");
		
		return this.disponibilidade.get(dia).contains(LocalTime.parse(horario));
	}
	
	/**
	 * Verifica se o local especificado é de disponibilidade do tutor.
	 * 
	 * @param local Representação de um local no formato String "ufcg"
	 * @return Representação booleana da verificação de local do tutor
	 */
	public boolean consultaLocal(String local){
		Validador.validadorString(local, "Erro no consultar local de atendimento: local nao pode ser vazio ou em branco");
		
		return this.locaisAtendimento.contains(local);
	}
	
	/**
	 * Retorna a representacao em String de um tutor. Segue o 
	 * formato:<br> "NOTA - DINHEIRO - (DISCIPLINAS - PROFICIENCIA) - (LOCAIS DE ATENDIMENTO)
	 *  - (HORARIOS DE ATENDIMENTO)"
	 */
	public String toString() {
		String disciplinas = "";
		for (String dsc : this.disciplinas.keySet()) {
			disciplinas += dsc + " - " + this.disciplinas.get(dsc) + ", "; 
		}

		String horarios = "";
		for (String dia : this.disponibilidade.keySet()) {
			horarios += dia + " - (" + this.disponibilidade.get(dia).toString() + "), "; 
		}
		
		return this.nota + " - " + this.dinheiro + " - (" + disciplinas + ") - (" +
			   this.locaisAtendimento.toString() + ") - (" + horarios;
	}
}
