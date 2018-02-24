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
	
	public ControllerAlunos() {
		this.alunos = new HashSet<>();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {	
		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone, email);
		Validador.validadorSetCoteins(aluno, this.alunos, "Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
		
		this.alunos.add(aluno);
	}
	
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
	
	public String recuperaAluno(String matricula) {
		Validador.validadorString(matricula, "matricula invalida");
		Aluno aluno;
		aluno = buscaAluno(matricula);
		Validador.validadorObjeto(aluno, "Erro na busca por aluno: Aluno nao encontrado");
		return aluno.toString();
	}
	
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
