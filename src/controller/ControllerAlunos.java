/**
 * @author Arthur da Silva Rodrigues e Anderson Vasconcelos
 */

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


public class ControllerAlunos {

	private Set<Aluno> alunos;
	/**
	 * Constrói um Controller com um hashset para armazenar objetos do tipo Aluno.
	 */
	public ControllerAlunos() {
		this.alunos = new HashSet<>();
	}
	/**
	 * Este metodo é responsavel pelo cadastro e validação de alunos no sistema
	 * @param nome Representação do nome do aluno
	 * @param matricula Representação da amtricula do aluno
	 * @param codigoCurso Representação do codigo referente ao curso do aluno
	 * @param telefone Representação do telefone do aluno (opcional)
	 * @param email Representação do email do aluno
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {	
		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone, email);
		Validador.validadorSetCoteins(aluno, this.alunos, "Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
		
		this.alunos.add(aluno);
	}
	/**
	 * Este metodo é um metodo auxiliar responsavel por buscar o aluno atraves de sua matricula
	 * @param matricula Representação da matricula do aluno
	 * @return retorna um objeto do tipo Aluno
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
	 * Este metodo é responsavel por consultar o aluno atrves da matricula 
	 * @param matricula Representação da matricula
	 * @return Uma string com todos os atributos do aluno referenciado pela matricula solicitada
	 */
	public String recuperaAluno(String matricula) {
		Validador.validadorString(matricula, "matricula invalida");
		Aluno aluno;
		aluno = buscaAluno(matricula);
		Validador.validadorObjeto(aluno, "Erro na busca por aluno: Aluno nao encontrado");
		return aluno.toString();
	}
	/**
	 * Este metodo é responsavel por ceder a lista de todos os alunos cadastrados em ordem alfabetica
	 * @return Uma string com todas as representações dos alunos concatenadas
	 */
	public String listarAlunos() {
		List<Aluno> arrayAlunos = new ArrayList<>();
		for(Aluno aluno: alunos) {
			arrayAlunos.add(aluno);
		}
		
		Comparador alfabetico = new Alfabetica();
		Collections.sort(arrayAlunos, alfabetico);
		
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
	 * Este metodo é responsavel por buscar um certo atributo de um certo aluno e retorná-lo
	 * @param matricula Representação da matricula 
	 * @param atributo Representação do atributo
	 * @return Retorna uma string com o atributo solicitado de um determinado aluno
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
