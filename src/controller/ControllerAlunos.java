package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Aluno;
import model.Ocupacao;
import utils.Alfabetica;
import utils.Comparador;
import utils.Validador;

/**
 * Controle que contem a lógica de negócio do armazenamento de alunos do sistema.
 * 
 * @author Arthur da Silva Rodrigues, Anderson Vidal e Daniel Coura
 */
public class ControllerAlunos {

	/**
	 * Representação de um conjunto de alunos diferenciados pela matricula.
	 */
	private Map<String, Aluno> alunos;

	public ControllerAlunos() {
		this.alunos = new HashMap<String, Aluno>();
	}
	
	/**
	 * Cadastra e valida um aluno com telefone no sistema
	 * 
	 * @param nome Representação do nome do aluno, no formato String "james"
	 * @param matricula Representação da matricula do aluno, no formato String "123456"
	 * @param codigoCurso Representação do código do curso, no formato inteiro "1"
	 * @param telefone Representação do telefone do aluno, no formato String "99999999"
	 * @param email Representação do email do aluno, no formato String "james@ccc.ufcg.edu.br"
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {	
		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone, email);
		Validador.validadorMapKeyConteins(matricula, this.alunos, "Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
		
		this.alunos.put(matricula, aluno);
	}
	
	/**
	 * Encontra um aluno segundo a sua matricula no sistema.
	 * 
	 * @param matricula Representação da matricula do aluno, no formato String "123456"
	 * @return Representacao textual do aluno buscado.
	 */
	public String recuperaAluno(String matricula) {
		Validador.validadorString(matricula, "Erro ao recuperar aluno: matricula invalida");
		Validador.validadorMapKeyNotConteins(matricula, this.alunos, "Erro na busca por aluno: Aluno nao encontrado");
		Aluno aluno = this.alunos.get(matricula);
		return aluno.toString();
	}
	
	/**
	 * Cria uma lista ordenada de alunos, em ordem alfabética.
	 * 
	 * @return List de Alunos.
	 */
	private List<Aluno> ordenaAlfabeticaAlunos() {
		List<Aluno> arrayAlunos = new ArrayList<Aluno>(this.alunos.values());
		
		Comparador alfabetico = new Alfabetica();
		Collections.sort(arrayAlunos, alfabetico);
		return arrayAlunos;
	}
	
	/**
	 * Lista todos os alunos do sistema exibindo suas representações textuais
	 * em sequência alfabética.
	 * 
	 * @return Representação textual de uma lista de alunos.
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
	 * @param matricula Representação da matricula do aluno, no formato String "123456"
	 * @param atributo Representação de um atributo do aluno
	 * @return Representação textual de um atributo do aluno
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
	 * Torna um aluno tutor de alguma disciplina através de sua matricula.
	 * 
	 * @param matricula Representação da matrícula do aluno, no formato String "123456"
	 * @param disciplina Representação da disciplina, no formato String "matemática"
	 * @param proeficiencia Representação da proeficiencia do tutor, no formato inteiro "0"
	 */
	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		Validador.validadorString(matricula, "Erro na definicao de papel: matricula invalida");
		Validador.validadorMapKeyNotConteins(matricula, this.alunos, "Erro na definicao de papel: Tutor nao encontrado");
		Validador.validadorMapKeyNotConteins(matricula, this.alunos, "Erro na definicao de papel: Ja eh tutor dessa disciplina");
		
		Aluno aluno = this.alunos.get(matricula);
		if (aluno.isTutor()) {
			aluno.getTutor().addDisciplina(disciplina, proficiencia);
		} else  {
			int id = this.getQtdAlunosTutores() + 1;
			aluno.tornaTutor(id, disciplina, proficiencia);
		}
	}
	
	/**
	 * Encontra um tutor segundo a sua matrícula no sistema.
	 * 
	 * @param matricula Representação da matricula do aluno tutor, no formato String "123456"
	 * @return Representação textual do tutor buscado.
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
	 * @return List de Tutores.
	 */
	public String listarAlunosTutores() {
		List<Aluno> arrayAlunos = ordenaAlfabeticaAlunos();
		
		String str = "";
		int cont = 0, i = 0;
		while (cont < this.getQtdAlunosTutores()) {
			if (arrayAlunos.get(i).isTutor()) {
				if (i == this.getQtdAlunosTutores()) {
					return str + arrayAlunos.get(i).toString();
				}
				str += arrayAlunos.get(i).toString() + ", ";
			}
			i++;
		}
		
		return "";
	}
	
	/**
	 * Retorna um Map onde a chave é o email do tutor e o valor é o Tutor.
	 * 
	 * @return Map de tutores chaveados pelo email.
	 */
	public Map<String, Ocupacao> getAlunosTutoresKeyEmail(){
		Map<String, Ocupacao> tutores = new HashMap<String, Ocupacao>();
		for(Aluno aln : this.alunos.values()) {
			if (aln.isTutor()) {
				tutores.put(aln.getEmail(), aln.getTutor());
			}
		}
		
		return tutores;
	}

	/**
	 * Retorna um List dos alunos que são tutores.
	 * 
	 * @return List de alunos tutores.
	 */
	public List<Aluno> getAlunosTutores(){
		List<Aluno> tutores = new ArrayList<Aluno>();
		for(Aluno aln : this.alunos.values()) {
			if (aln.isTutor()) {
				tutores.add(aln);
			}
		}
		
		return tutores;
	}
	
	/**
	 * Retorna a quantidade de alunos tutores no sistema.
	 * 
	 * @return a quantidade de tutores.
	 */
	public int getQtdAlunosTutores(){		
		return this.getAlunosTutores().size();
	}

}
