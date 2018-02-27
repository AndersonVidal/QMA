package model;

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
	
	/**
	 * Constroi um tutor.
	 * 
	 * @param matricula Representação da matricula do tutor.
	 * @param disciplina Representação da disciplina.
	 * @param proeficiencia Representação da proeficiencia do tutor na disciplina.
	 */
	public Tutor(String matricula, String disciplina, int proeficiencia) {
		Validador.validadorString(matricula, "Erro no cadastro de tutor: Nome nao pode ser vazio ou nulo");
		Validador.validadorString(disciplina, "Erro no cadastro de tutor: Disciplina nao pode ser vazio ou nulo");
		Validador.validadorPositivo(proeficiencia, "Erro na definicao de papel: Proficiencia invalida");
		this.matricula = matricula;
		this.disciplina = disciplina;
		this.proeficiencia = proeficiencia;
		this.nota = 4;
		this.dinheiro = 0;
	}
	
	public String getMatricula() {
		return this.matricula;
	}
	
	public String getDisciplina() {
		return this.disciplina;
	}
	
	public int getProeficiencia() {
		return this.proeficiencia;
	}
	
	public int getNota() {
		return this.nota;
	}
	
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	public double getDinheiro() {
		return this.dinheiro;
	}
	
	public void adicionaDinheiro(double dinheiro) {
		this.dinheiro += dinheiro;
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