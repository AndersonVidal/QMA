package model;

import utils.Validador;

/**
 * Representacao de um aluno, que tera matricula, identificador de curso, 
 * nome, telefone(opcional), email, uma nota de 0 a 5 e uma ocupacao. 
 * 
 * @author Arthur da Silva Rodrigues, Anderson Vasconcelos e Daniel Coura
 */
public class Aluno {
	
	/**
	 * Representacao da matricula do aluno, no formato String "123456"
	 */
	private String matricula;
	/**
	 * Representacao do nome do aluno, no formato String "james"
	 */
	private String nome;
	/**
	 * Representacao do codigo do curso, no formato inteiro "1"
	 */
	private int codigoCurso;
	/**
	 * Representacao do telefone do aluno, no formato String "99999999"
	 */
	private String telefone;
	/**
	 * Representacao do email do aluno, no formato String "james@ccc.ufcg.edu.br"
	 */
	private String email;
	/**
	 * Representacao da nota do aluno, no formato Inteiro "3"
	 */
	private int nota;
	/**
	 * Representacao a funcao de Tutor do aluno
	 */
	private Tutor tutor;
	
	
	/**
	 * Constroi um aluno a partir da matricula, do nome, do codigo do curso,
	 * do telefone e do email.
	 * 
	 * @param matricula Representacao da matricula do aluno
	 * @param nome Representacao do nome do aluno
	 * @param codigoCurso Representacao do codigo referente ao curso do aluno
	 * @param telefone Representacao do telefone do aluno
	 * @param email Representacao do email do aluno
	 */
	public Aluno(String matricula, String nome, int codigoCurso, String telefone, String email) {
		Validador.validadorString(matricula, "Erro no cadastro de aluno: Nome nao pode ser vazio ou nulo");
		Validador.validadorString(nome, "Erro no cadastro de aluno: Nome nao pode ser vazio ou nulo");
		Validador.validadorEmail(email, "Erro no cadastro de aluno: Email invalido");
		if(!telefone.isEmpty()) {
			Validador.validadorString(matricula, "Erro no cadastro de aluno: Telefone nao pode ser vazio ou nulo");
		}
		
		this.matricula = matricula;
		this.nome = nome;
		this.codigoCurso = codigoCurso;
		this.telefone = telefone;
		this.email = email;
		this.nota = 5;
	}
	
	public String getMatricula() { return matricula; }
	
	public String getNome() { return nome; }
	
	public int getCodigoCurso() { return codigoCurso; }
	
	public String getTelefone() { return telefone; }
	
	public String getEmail() { return email; }
	
	public int getNota() { return nota; }

	/**
	 * Retorna se o aluno eh ou nao um tutor.
	 * 
	 * @return um booleano informando se o aluno eh um tutor
	 */
	public boolean isTutor() {
		return this.tutor != null;
	}
	
	/**
	 * Retorna o objeto tutor de aluno, se existir.
	 * 
	 * @return o objeto tutor de aluno.
	 */
	public Tutor getTutor() {
		if (!this.isTutor()) {
			throw new NullPointerException("Erro ao recuperar tutor: Tutor nao existe");
		}
		
		return this.tutor;
	}
	
	/**
	 * Torna o aluno um tutor atraves da disciplina e da proficiencia.
	 * 
	 * @param disciplina a disciplina que o aluno ira tutelar
	 * @param proficiencia a proficiencia do tutor na disciplina
	 */
	public void tornaTutor(String disciplina, int proficiencia) {
		this.tutor = new Tutor(disciplina, proficiencia);
	}
	
	/**
	 * Retorna a representacao textual de um aluno:
	 * Segue o formato: “MATRICULA - NOME - CODIGOCURSO - EMAIL” (sem telefone)
	 *                  “MATRICULA - NOME - CODIGOCURSO - TELEFONE - EMAIL” (com telefone)
	 *                  
	 * @return a representacao em String do aluno
	 */
	@Override
	public String toString() {
		if(this.telefone.isEmpty()) {
			return this.matricula + " - " + this.nome + " - " + this.codigoCurso + " - " + this.email;
		}else {
			return this.matricula + " - " + this.nome + " - " + this.codigoCurso + " - " + this.telefone + " - " + this.email;
		}
	}

	/**
	 * Representacao numerica de um aluno.
	 */
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
