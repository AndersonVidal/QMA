package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Aluno;
import model.Tutor;
import utils.Alfabetica;
import utils.Comparador;
import utils.Validador;

/**
 * Controle que contem a logica de negocio do armazenamento de alunos do sistema
 * 
 * @author Arthur da Silva Rodrigues, Anderson Vasconcelos e Daniel Coura
 */
public class ControllerAlunos {

	/**
	 * Representacao de um conjunto de alunos diferenciados pela matricula.
	 */
	private Map<String, Aluno> alunos;

	public ControllerAlunos() {
		this.alunos = new HashMap<String, Aluno>();
	}
	
	/**
	 * Cadastra e valida um aluno com telefone no sistema
	 * 
	 * @param nome o nome do aluno, no formato String "james"
	 * @param matricula a matricula do aluno, no formato String "123456"
	 * @param codigoCurso o codigo do curso, no formato inteiro "1"
	 * @param telefone o telefone do aluno, no formato String "99999999"
	 * @param email o email do aluno, no formato String "james@ccc.ufcg.edu.br"
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {	
		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone, email);
		Validador.validadorMapKeyConteins(matricula, this.alunos, "Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
		
		this.alunos.put(matricula, aluno);
	}
	
	/**
	 * Encontra um aluno segundo a sua matricula no sistema.
	 * 
	 * @param matricula o nome do aluno, no formato String "123456"
	 * @return a representacao textual do aluno buscado.
	 */
	public String recuperaAluno(String matricula) {
		Validador.validadorString(matricula, "Erro ao recuperar aluno: matricula invalida");
		Validador.validadorMapKeyNotConteins(matricula, this.alunos, "Erro na busca por aluno: Aluno nao encontrado");
		Aluno aluno = this.alunos.get(matricula);
		return aluno.toString();
	}
	
	/**
	 * Cria uma lista ordenada de alunos, em ordem alfabetica.
	 * 
	 * @return um List de Alunos.
	 */
	private List<Aluno> ordenaAlfabeticaAlunos() {
		List<Aluno> arrayAlunos = new ArrayList<Aluno>(this.alunos.values());
		
		Comparador alfabetico = new Alfabetica();
		Collections.sort(arrayAlunos, alfabetico);
		return arrayAlunos;
	}
	
	/**
	 * Lista todos os alunos do sistema exibindo suas representacoes textuais
	 * em sequencia alfabetica.
	 * 
	 * @return a representacao textual de uma lista de alunos.
	 */
	public String listarAlunos() {
		List<Aluno> arrayAlunos = ordenaAlfabeticaAlunos();
		
		String str = "";
		for(int i = 0; i < arrayAlunos.size(); i++) {
			if (i == arrayAlunos.size() - 1) {
				str += arrayAlunos.get(i).toString();
				continue;
			}
			str += arrayAlunos.get(i).toString() + ", ";
		}
		
		return str;
	}
	
	/**
	 * Encontra um aluno segundo sua matricula no sistema e imprime a caracteristica desejada.
	 * 
	 * @param matricula o nome do aluno, no formato String "123456"
	 * @param atributo uma caractéristica do aluno
	 * @return a representacao textual de uma caracteristica do aluno
	 */
	public String getInfoAluno(String matricula, String atributo) {
		Validador.validadorString(matricula, "Erro na obtencao de informacao de aluno: Matricula nula ou vazia");
		Validador.validadorString(atributo, "Erro na obtencao de informacao de aluno: Atributo nulo ou vazio");
		Validador.validadorMapKeyNotConteins(matricula, this.alunos, "Erro na obtencao de informacao de aluno: Aluno nao encontrado");
		
		Aluno aluno = this.alunos.get(matricula);
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
			throw new IllegalArgumentException("Erro na obtencao de informacao de aluno: Atributo invalido");
		}
	}
	
	/**
	 * Torna um aluno tutor de alguma disciplina atraves de sua matricula.
	 * 
	 * @param matricula a matrícula do aluno, no formato String "123456"
	 * @param disciplina a disciplina, no formato String "matemática"
	 * @param proeficiencia a proeficiencia do tutor, no formato inteiro "0"
	 */
	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		Validador.validadorString(matricula, "Erro na definicao de papel: matricula invalida");
		Validador.validadorMapKeyNotConteins(matricula, this.alunos, "Erro na definicao de papel: Tutor nao encontrado");
		Validador.validadorMapKeyNotConteins(matricula, this.alunos, "Erro na definicao de papel: Ja eh tutor dessa disciplina");
		
		Aluno aluno = this.alunos.get(matricula);
		if (aluno.isTutor()) {
			aluno.getTutor().addDisciplina(disciplina, proficiencia);
		} else  {
			aluno.tornaTutor(disciplina, proficiencia);
		}
	}
	
	/**
	 * Encontra um tutor segundo a sua matricula no sistema.
	 * 
	 * @param matricula o nome do tutor, no formato String "123456"
	 * @return a representacao textual do tutor buscado.
	 */
	public String recuperaAlunoTutor(String matricula) {
		Validador.validadorString(matricula, "Erro na busca por tutor: Matricula não pode ser vazio ou nulo");
		Validador.validadorMapKeyNotConteins(matricula, this.alunos, "Erro na busca por tutor: Tutor nao encontrado");
		
		Aluno aluno = this.alunos.get(matricula);
		if (!aluno.isTutor()) {
			throw new IllegalArgumentException("Erro na busca por tutor: Matricula nao eh de um aluno tutor");
		}
		return aluno.toString();
	}
	
	/**
	 * Cria uma lista ordenada de alunos que sao tutores, em ordem alfabética.
	 * 
	 * @return um List de Tutores.
	 */
	public String listarAlunosTutores() {
		List<Aluno> arrayAlunos = ordenaAlfabeticaAlunos();
		
		String str = "";
		int cont = 0, i = 0;
		while (cont < this.getAlunosTutores().size()) {
			if (arrayAlunos.get(i).isTutor()) {
				if (i == this.getAlunosTutores().size()) {
					str += arrayAlunos.get(i).toString();
					break;
				}
				str += arrayAlunos.get(i).toString() + ", ";
			}
			i++;
		}
		
		return str;
	}
	
	/**
	 * Retorna um Map onde a chave eh o email do tutor e o valor eh o Tutor.
	 * 
	 * @return um Map de tutores
	 */
	public Map<String, Tutor> getAlunosTutores(){
		Map<String, Tutor> tutores = new HashMap<String, Tutor>();
		for(Aluno aln : this.alunos.values()) {
			if (aln.isTutor()) {
				tutores.put(aln.getEmail(), aln.getTutor());
			}
		}
		
		return tutores;
	}
}
