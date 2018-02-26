package model;

import utils.Validador;

/**
 * Representação de um aluno, que terá matricula, identificador de curso, nome, telefone(opcional), email e uma nota de 0 a 5. 
 * @author Arthur da Silva Rodrigues e Anderson Vasconcelos
 */
public class Aluno {
	
	/**
	 * Representação de matricula do aluno, no formato String "123456"
	 */
	private String matricula;
	/**
	 * Representação do nome do aluno, no formato String "james"
	 */
	private String nome;
	/**
	 * Representação do codigo do curso, no formato inteiro "1"
	 */
	private int codigoCurso;
	/**
	 * Representação do telefone do aluno, no formato String "99999999"
	 */
	private String telefone;
	/**
	 * Representação do email do aluno, no formato String "james@ccc.ufcg.edu.br"
	 */
	private String email;
	/**
	 * Representação da nota do aluno, no formato Inteiro "3"
	 */
	private int nota;

	/**
	 * Constroi um aluno sem telefone
	 * 
	 * @param matricula Representação da matricula do aluno
	 * @param nome Representação do nome do aluno
	 * @param codigoCurso Representação do codigo referente ao curso do aluno
	 * @param email Representação do email do aluno
	 */
	public Aluno(String matricula, String nome, int codigoCurso, String email) {
		Validador.validadorString(matricula, "Erro no cadastro de aluno: Nome nao pode ser vazio ou nulo");
		Validador.validadorString(nome, "Erro no cadastro de aluno: Nome nao pode ser vazio ou nulo");
		Validador.validadorEmail(email, "Erro no cadastro de aluno: Email invalido");
		
		this.matricula = matricula;
		this.nome = nome;
		this.codigoCurso = codigoCurso;
		this.telefone = "";
		this.email = email;
		this.nota = 5;
	}
	/**
	 * Constroi um aluno com telefone
	 * 
	 * @param matricula Representação da matricula do aluno
	 * @param nome Representação do nome do aluno
	 * @param codigoCurso Representação do codigo referente ao curso do aluno
	 * @param telefone Representação do telefone do aluno
	 * @param email Representação do email do aluno
	 */
	public Aluno(String matricula, String nome, int codigoCurso, String telefone, String email) {
		this(matricula, nome, codigoCurso, email);
		Validador.validadorString(telefone, "Telefone invalido");
		this.telefone = telefone;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public String getNome() {
		return nome;
	}
	public int getCodigoCurso() {
		return codigoCurso;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getEmail() {
		return email;
	}
	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	/**
	 * Retorna a representação textual de um aluno:<br>
	 * <ul><li>Sem telefone:</li>
	 * <tt>“123456 - James - 1 - james@ccc.ufcg.edu.br”</tt>
	 * <li>Com telefone:</li>
	 * <tt>“123456 - James - 1 - 99999999 - james@ccc.ufcg.edu.br”</tt>
	 */
	@Override
	public String toString() {
		if(this.telefone.isEmpty()) {
			return this.matricula + " - " + this.nome + " - " + this.codigoCurso + " - " + this.email;
		}else {
			return this.matricula + " - " + this.nome + " - " + this.codigoCurso + " - " + this.telefone + " - " + this.email;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}
	
	/**
	 * Retorna um boleano que representa a proposição de o objeto aluno ter a mesma <b>matricula</b> 
	 * que outro objeto aluno.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
}
