package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Aluno;
import utils.Alfabetica;
import utils.Comparador;
import utils.Validador;

/**
 * Controle que contem a lógica de negocio do armazenamento de alunos do sistema
 * 
 * @author Arthur da Silva Rodrigues e Anderson Vasconcelos
 */
public class ControllerAlunos {

	/**
	 * Representação de um conjunto de alunos diferenciados pela matricula.
	 */
	private Set<Aluno> alunos;

	public ControllerAlunos() {
		this.alunos = new HashSet<>();
	}
	
	public Set<Aluno> getAlunos() {
		return this.alunos;
	}
	
	/**
	 * Cadastra e valida um aluno com telefone no sistema
	 * 
	 * @param nome Representação do nome do aluno, no formato String "james"
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * @param codigoCurso Representação do codigo do curso, no formato inteiro "1"
	 * @param telefone Representação do telefone do aluno, no formato String "99999999"
	 * @param email Representação do email do aluno, no formato String "james@ccc.ufcg.edu.br"
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {	
		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone, email);
		Validador.validadorSetCoteins(aluno, this.alunos, "Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
		
		this.alunos.add(aluno);
	}
	
	/**
	 * Cadastra um aluno no sistema.
	 * 
	 * @param nome Representação do nome do aluno, no formato String "james"
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * @param codigoCurso Representação do codigo do curso, no formato inteiro "1"
	 * @param email Representação do email do aluno, no formato String "james@ccc.ufcg.edu.br"
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String email) {	
		Aluno aluno = new Aluno(matricula, nome, codigoCurso, email);
		Validador.validadorSetCoteins(aluno, this.alunos, "Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
		
		this.alunos.add(aluno);
	}
	/**
	 * Retorna o aluno especificado pela matricula.<br>
	 * Caso aluno não exista, é retornado null.
	 * 
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * 
	 * @return Aluno. Caso não exista, null.
	 */
	private Aluno buscaAluno(String matricula) {
		Aluno busca = null;
		for(Aluno aluno: alunos) {
			if(aluno.getMatricula().equals(matricula)) {
				busca = aluno;
				break;
			}
		}
		
		return busca;
	}
	
	/**
	 * Encontra um aluno segundo a sua matricula no sistema.
	 * 
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * 
	 * @return Representaçao textual do aluno buscado.
	 */
	public String recuperaAluno(String matricula) {
		Validador.validadorString(matricula, "matricula invalida");
		Aluno aluno;
		aluno = buscaAluno(matricula);
		Validador.validadorObjeto(aluno, "Erro na busca por aluno: Aluno nao encontrado");
		return aluno.toString();
	}
	
	/**
	 * Cria uma lista ordenada de alunos, em ordem alfabética.
	 * 
	 * @return List de Alunos.
	 */
	private List<Aluno> ordenaAlfabeticaAlunos() {
		List<Aluno> arrayAlunos = new ArrayList<>();
		for(Aluno aluno: alunos) {
			arrayAlunos.add(aluno);
		}
		
		Comparador alfabetico = new Alfabetica();
		Collections.sort(arrayAlunos, alfabetico);
		return arrayAlunos;
	}
	
	/**
	 * Lista todos os alunos do sistema exibindo suas representações textuais em sequencia alfabética.
	 * 
	 * @return Representação textual de uma lista de alunos.
	 */
	public String listarAlunos() {
		List<Aluno> arrayAlunos = ordenaAlfabeticaAlunos();
		
		String str = "";
		
		for(int i=0; i < arrayAlunos.size(); i++) {
			if(i == arrayAlunos.size() - 1) {
				str += arrayAlunos.get(i).toString();
				break;
			}
			str += arrayAlunos.get(i).toString() + ", ";
		}
		
		return str.trim();
	}
	
	/**
	 * Encontra um aluno segundo sua matricula no sistema e imprime a caracteristica desejada.
	 * 
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * @param atributo Representação de uma caractéristica do aluno
	 * @return Representação textual de uma caractéristica do aluno
	 */
	public String getInfoAluno(String matricula, String atributo) {
		
		Aluno aluno = this.buscaAluno(matricula);
		Validador.validadorObjeto(aluno, "Erro na obtencao de informacao de aluno: Aluno nao encontrado");
		
		switch (atributo) {
		case "Nome":
			return aluno.getNome();
		case "Telefone":
			return aluno.getTelefone();
		case "Email":
			return aluno.getEmail();
		case "CodigoCurso":
			return aluno.getCodigoCurso() + "";
		default:
			throw new StringIndexOutOfBoundsException();
		}
	}

}
