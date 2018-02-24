/**
 *  A classe é a representação de um aluno, que receberá atributos referentes a matricula, identificador de curso, nome, telefone(opcional), email e uma nota de 0 a 5 
 * @author Arthur da Silva Rodrigues e Anderson Vasconcelos
 */

package model;

import utils.Validador;

public class Aluno {
	
	private String matricula;
	private String nome;
	private int codigoCurso;
	private String telefone;
	private String email;
	private int nota;

	/**
	 * Constrói um aluno com telefone vazio;
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
	 * Contrói um aluno desta vez com o telefone não-nulo
	 * @param matricula Representação da matricula do aluno
	 * @param nome Representação do nome do aluno
	 * @param codigoCurso Representação do codigo referente ao curso do aluno
	 * @param telefone Representação do telefone do aluno
	 * @param email Representação do email do aluno
	 */
	public Aluno(String matricula, String nome, int codigoCurso, String telefone, String email) {
		this(matricula, nome, codigoCurso, email);
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
