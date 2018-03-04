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
	private String local;

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
		this.local = localInteresse;
	}
	
	public String getHorario() { return this.horario; }
	
	public String getDia() { return this.dia; }
	
	public String getLocal() { return this.local; }
	
	/**
	 * Recupera informação de ajuda através de um atributo
	 * 
	 * @param atributo Representação de um atributo de ajuda, no formato "tutor"
	 * @return Informação da ajuda
	 */
	@Override
	public String getInfoAjuda(String atributo) {
		Validador.validadorString(atributo, "Erro ao tentar recuperar info da ajuda : atributo nao pode ser vazio ou em branco");
		
		switch(atributo){
		case "disciplina":
			return this.getDisciplina();
		case "tutor":
			return this.getMatriclaTutor();
		case "aluno":
			return this.getMatriculaAluno();
		case "horario":
			return this.horario;
		case "dia":
			return this.dia;
		case "localInteresse":
			return this.local;
		default:
			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : atributo nao encontrado");
		}
	}
	
	/**
	 * Retorna a especificação do tutor, no formato:
	 * "Tutor - 12345, horario - 14:00, dia - seg, disciplina - ATAL"
	 * 
	 * @return Representação textual da descrição do tutor da ajuda.
	 */
	@Override
	public String getInfoTutor() {
		return "Tutor - " + getMatriclaTutor() + ", horario - " + this.horario + 
				", dia - " + this.getDia() + ", local - " + this.local + ", disciplina - " + getDisciplina();
	}
	
	/**
	 * Retorna a representação textual da ajuda, no seguinte formato:<br><br>
	 * <tt style="font-size: 14px;">"1 - 123456 - 332211 - inglês - 12:30 - seg - ufcg"</tt>
	 * 
	 * @return a representação textual da ajuda
	 */
	@Override
	public String toString() {
		return this.getId() + " - " + this.getMatriculaAluno() + " - " + this.getMatriclaTutor() + " - " + this.getDisciplina() +
				" - " + this.horario + " - " + this.dia + " - " + this.local;
	}

}
