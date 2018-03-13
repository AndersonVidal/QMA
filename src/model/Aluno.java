package model;

import java.io.Serializable;

import utils.Validador;
import utils.SerializableOptional;

/**
 * Representação de um aluno, que terá matricula, identificador de curso, 
 * nome, telefone(opcional), email, uma nota de 0 a 5, e uma ocupação. 
 * 
 * @author Arthur da Silva Rodrigues, Anderson Vasconcelos e Daniel Coura
 */
public class Aluno implements Serializable {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Representação da matricula do aluno, no formato String "123456"
	 */
	private String matricula;
	/**
	 * Representação do nome do aluno, no formato String "james"
	 */
	private String nome;
	/**
	 * Representação do código do curso, no formato inteiro "1"
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
	 * Representação a função de Tutor do aluno
	 */
	private SerializableOptional<Tutor> tutor;
	
	/**
	 * Constrói um aluno a partir da matricula, do nome, do código do curso,
	 * do telefone e do email.
	 * 
	 * @param matricula Representacao da matricula do aluno, no formato String "123456"
	 * @param nome Representacao do nome do aluno, no formato String "James"
	 * @param codigoCurso Representacao do codigo referente ao curso do aluno, no formato inteiro "123"
	 * @param telefone Representacao do telefone do aluno, no formato String "988888888"
	 * @param email Representacao do email do aluno, no formato String "james.fulano@ccc.ufcg.edu.br"
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
		this.tutor = SerializableOptional.empty();
	}
	
	public String getMatricula() { return matricula; }
	
	public String getNome() { return nome; }
	
	public int getCodigoCurso() { return codigoCurso; }
	
	public String getTelefone() { return telefone; }
	
	public String getEmail() { return email; }
	
	public int getNota() { return nota; }

	/**
	 * Retorna um true caso aluno seja tutor.
	 * 
	 * @return Booleano que representa se o aluno é um tutor
	 */
	public boolean isTutor() {
		return this.tutor.asOptional().isPresent();
	}
	
	/**
	 * Retorna o objeto tutor de aluno, se existir.
	 * 
	 * @return Objeto tutor de aluno.
	 */
	public Tutor getTutor() {
		if (!this.isTutor()) {
			throw new NullPointerException("Erro ao recuperar tutor: Tutor nao existe");
		}
		
		return this.tutor.asOptional().get();
	}
	
	/**
	 * Torna o aluno um tutor atraves da disciplina e da proficiencia.
	 * 
	 * @param disciplina Disciplina que o aluno será tutor
	 * @param proficiencia Proficiência do tutor na disciplina
	 */
	public void tornaTutor(int id, String disciplina, int proficiencia) {
		this.tutor = SerializableOptional.of(new Tutor(id, disciplina, proficiencia));
	}
	
	/**
	 * Retorna a representacao textual de um aluno:<br>
	 * Segue o formato: <ul><li>“123456 - James - 123 - james@ccc.ufcg.edu.br” (sem telefone)</li>
	 *                  <li>“123456 - James - 123 - 988888888 - james@ccc.ufcg.edu.br” (com telefone)</li></ul>
	 *                  
	 * @return Representação textual do aluno
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
	 * Representacao hash do aluno em relaçao a matricula.
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

