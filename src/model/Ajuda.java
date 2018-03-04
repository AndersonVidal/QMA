package model;

import utils.Validador;

/**
 * Classe que abstrai uma ajuda no sistema.
 * 
 * @author Daniel Coura
 */
public abstract class Ajuda {
	
	/**
	 * Representação do id da ajuda, no formato int "1"
	 */
	private int id;
	/**
	 * Representação da matrícula do aluno, no formato String "123456"
	 */
	private String matrAluno;
	/**
	 * Representação da matrícula do tutor, no formato String "123456"
	 */
	private String matrTutor;
	/**
	 * Representação da disciplina, no formato String "inglês"
	 */
	private String disciplina;
	
	/**
	 * Constrói ajuda a partir do id, das matrículas do aluno e do tutor,
	 * e da disciplina.
	 * 
	 * @param id Represetação do id da ajuda, no formato "1"
	 * @param matrAluno Representação da matrícula do aluno, no formato "123456"
	 * @param matrTutor Representação da matrícula do tutor, no formato "123456"
	 * @param disciplina Representação da disciplina , no formato "inglês"
	 */
	public Ajuda(int id, String matrAluno, String matrTutor, String disciplina) {
		Validador.validadorPositivo(id, "Erro no pedido ajuda presencial: id nao pode ser negativo ou nulo");
		Validador.validadorString(matrAluno, "Erro no pedido de ajuda presencial: matricula de aluno nao pode ser vazio ou em branco");
		Validador.validadorString(matrTutor, "Erro no pedido de ajuda presencial: matricula do tutor nao pode ser vazio ou em branco");
		Validador.validadorString(disciplina, "Erro no pedido de ajuda presencial: disciplina nao pode ser vazio ou em branco");
			
		this.id = id;
		this.matrTutor = matrTutor;
		this.matrAluno = matrAluno;
		this.disciplina = disciplina;
	}
	
	public int getId() { return this.id; }

	public String getMatrAluno() { return this.matrAluno; }
	
	public String getMatrTutor() { return this.matrTutor; }
	
	public String getDisciplina() { return this.disciplina; }
	
	/**
	 * Retorna a representação textual da ajuda, no seguinte formato: <br><br>
	 * <tt>"123456 - 123456 - inglês"</tt>
	 * 
	 * @return Representação textual da ajuda
	 */
	public String toString() {
		return this.id + " - " + this.matrAluno + " - " + this.matrTutor + " - " + this.disciplina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ajuda other = (Ajuda) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
