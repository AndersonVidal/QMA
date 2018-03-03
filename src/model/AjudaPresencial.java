package model;

import utils.Validador;

/**
 * Classe que represeta uma ajuda presencial no sistema.
 * 
 * @author Daniel Coura
 */
public class AjudaPresencial extends Ajuda {

	/**
	 * Representação do horário, no formato String "12:30"
	 */
	private String horario;
	/**
	 * Representação do dia, no formato String "seh"
	 */
	private String dia;
	/**
	 * Representação do local, no formato String "ufcg"
	 */
	private String localInteresse;

	/**
	 * Constrói a ajuda presencial a partir do id, das matrículas do aluno e do tutor,
	 * da disciplina, do horário, do dia e do local.
	 * 
	 * @param id Representação do id da ajuda, no formato "1"
	 * @param matrAluno Representação da matrícula do aluno, no formato "123456"
	 * @param matrTutor Representação da matrícula do tutor, no formato "123456"
	 * @param disciplina Representação da disciplina, no formato "inglês"
	 * @param horario Representação da horário, no formato "12:30"
	 * @param dia Representação do dia, no formato "seg"
	 * @param localInteresse Representação do local, no formato "ufcg"
	 */
	public AjudaPresencial(int id, String matrAluno, String matrTutor, String disciplina, String horario, String dia, String localInteresse) {
		super(id, matrAluno, matrTutor, disciplina);
		Validador.validadorString(horario, "Erro no pedido de ajuda presencial: horario nao pode ser vazio ou em branco");
		Validador.validadorHorario(horario, "Erro no pedido ajuda presencial: horario invalido");
		Validador.validadorString(dia, "Erro no pedido de ajuda presencial: dia nao pode ser vazio ou em branco");
		Validador.validadorString(localInteresse, "Erro no pedido de ajuda presencial: local nao pode ser vazio ou em branco");
		
		this.horario = horario;
		this.dia = dia;
		this.localInteresse = localInteresse;
	}
	
	public String getHorario() { return this.horario; }
	
	public String getDia() { return this.dia; }
	
	public String getLocalInteresse() { return this.localInteresse; }
	
	/**
	 * Retorna a representação textual da ajuda. segue o
	 * formato "1 - 123456 - 123456 - inglês - 12:30 - seg - ufcg"
	 * 
	 * @return a representação textual da ajuda
	 */
	public String toString() {
		return this.getId() + " - " + this.getMatrAluno() + " - " + this.getMatrTutor() + " - " + this.getDisciplina() +
				" - " + this.horario + " - " + this.dia + " - " + this.localInteresse;
	}

}
