package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import utils.Validador;

/**
 * Representação de um tutor, que terá matrícula, disciplina, proeficiencia na discipliana,
 * nota de 0 a 5 e dinheiro recebido pela tutoria.
 * @author Maria Cecília Kemiac
 */
public class Tutor {
	
	/**
	 * Representação da matrícula do tutor, no formato String "123456".
	 */
	private String matricula;
	/**
	 * Representação do contato do tutor, no formato String "teste@email.com"
	 */
	private String contato;
	/**
	 * Representação da disciplina, no formato String "disciplina".
	 */
	private String disciplina;
	/**
	 * Representação da proeficiencia, no formato inteiro "1".
	 */
	private int proeficiencia;
	/**
	 * Representação da nota, no formato inteiro "2".
	 */
	private int nota;
	/**
	 * Representação do dinheiro recebido, no formato double "0.0".
	 */
	private double dinheiro;
	
	private Map<String, String> disponibilidade;
	
	private Set<String> locaisAtendimento;
	
	/**
	 * Constroi um tutor.
	 * 
	 * @param matricula Representação da matricula do tutor.
	 * @param disciplina Representação da disciplina.
	 * @param proeficiencia Representação da proeficiencia do tutor na disciplina.
	 */
	public Tutor(String matricula, String disciplina, int proeficiencia, String contato) {
		Validador.validadorString(matricula, "Erro no cadastro de tutor: Nome nao pode ser vazio ou nulo");
		Validador.validadorString(disciplina, "Erro no cadastro de tutor: Disciplina nao pode ser vazio ou nulo");
		Validador.validadorPositivo(proeficiencia, "Erro na definicao de papel: Proficiencia invalida");
		this.matricula = matricula;
		this.contato = contato;
		this.disciplina = disciplina;
		this.proeficiencia = proeficiencia;
		this.nota = 4;
		this.dinheiro = 0;
		this.constroiHorarios();
		this.locaisAtendimento = new HashSet<>();
	}
	
	private void constroiHorarios(){
		this.disponibilidade = new HashMap<>();
		this.disponibilidade.put("seg", "");
		this.disponibilidade.put("ter", "");
		this.disponibilidade.put("qua", "");
		this.disponibilidade.put("qui", "");
		this.disponibilidade.put("sex", "");
	}
	
	public String getMatricula() { return this.matricula; }
	
	public String getDisciplina() { return this.disciplina; }
	
	public int getProeficiencia() { return this.proeficiencia; }
	
	public int getNota() { return this.nota; }
	
	public void setNota(int nota) { this.nota = nota; }
	
	public double getDinheiro() { return this.dinheiro; }
	
	public String getContato() { return this.contato; }
	
	public Set<String> getLocalAtendimento() { return this.locaisAtendimento; }
	
	public void adicionaDinheiro(double dinheiro) { this.dinheiro += dinheiro; }
	
	/**
	 * Adiciona um horário ao dia especificado
	 * 
	 * @param horario Representação de um horario, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 */
	public void setHorario(String horario, String dia) {
		Validador.validadorString(dia, "Erro no cadastrar horario: dia nao pode ser vazio ou em branco");
		Validador.validadorMapKeyNotConteins(dia, this.disponibilidade , "dia invalido");
		Validador.validadorString(horario, "Erro no cadastrar horario: horario nao pode ser vazio ou em branco");
		this.disponibilidade.put(dia, horario);
	}
	
	/**
	 * Adiciona um novo local ao conjunto de locais de atendimento
	 * 
	 * @param local Representação de um local no formato String "ufcg"
	 */
	public void setLocal(String local) {
		Validador.validadorString(local, "Erro no cadastrar local de atendimento: local nao pode ser vazio ou em branco");
		Validador.validadorSetContains(local, this.locaisAtendimento, "local ja existe");
		this.locaisAtendimento.add(local);
	}
	
	/**
	 * Verifica se o horario especificado é de disponibilidade do tutor.
	 * 
	 * @param horario Representação de um horario, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 * @return Representação booleana da verificação de horario do tutor
	 */
	public boolean consultaHorario(String horario, String dia) {
		Validador.validadorString(dia, "dia null ou vazio");
		Validador.validadorMapKeyNotConteins(dia, this.disponibilidade, "dia invalido");
		Validador.validadorString(horario, "Erro no cadastrar horario: horario nao pode ser vazio ou em branco");
		return this.disponibilidade.get(dia).equals(horario);
	}
	
	/**
	 * Verifica se o local especificado é de disponibilidade do tutor
	 * 
	 * @param local Representação de um local no formato String "ufcg"
	 * @return Representação booleana da verificação de local do tutor
	 */
	public boolean consultaLocal(String local){
		Validador.validadorString(local, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		return this.locaisAtendimento.contains(local);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	/**
	 * Retorna um booleano que representa a proposição de o objeto tutor ter a mesma matrícula
	 * e disciplina que outro objeto tutor.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tutor other = (Tutor) obj;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	
}