package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import model.Aluno;
import utils.NomeComparator;


public class ControllerAlunos {

	private HashSet<Aluno> alunos;
	
	public ControllerAlunos() {
		this.alunos = new HashSet<>();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {	
		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone,email);
		if(alunos.contains(aluno)) {
			throw new StringIndexOutOfBoundsException("Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
		}else {
			this.alunos.add(aluno);
		}
	}
	
	public String recuperaAluno(String matricula) {

		String str = "";
		for(Aluno aluno: alunos) {
			if(aluno.getMatricula().equals(matricula)) {
				str += aluno.toString();
			}
		}
		return str;
	}
	
	public String listarAlunos() {
		ArrayList<Aluno> arrayAlunos = new ArrayList<>();
		for(Aluno aluno: alunos) {
			arrayAlunos.add(aluno);
		}
		String str = "";
		Collections.sort(arrayAlunos,new NomeComparator(){
            public int compare(Aluno a1, Aluno a2){
                return a1.getNome().compareTo(a2.getNome());
            } 
        	});
		
		int now = 0;
		for(Aluno aluno: arrayAlunos) {
			if(now == arrayAlunos.size() - 1) {
				str += aluno.toString();
			}else if(now < arrayAlunos.size() - 1) {
				str += aluno.toString() + ", ";
			}
			now ++;
		}
		
		return str.trim();
	}
	
	
	public String getInfoAluno(String matricula, String atributo) {
		
		String info = "";
		for(Aluno aluno: alunos) {
			if(aluno.getMatricula().equals(matricula)) {
				if(atributo.equals("Nome")) {
					info += aluno.getNome();
				}else if(atributo.equals("Telefone")) {
					info += aluno.getTelefone();
				}else if(atributo.equals("Email")) {
					info += aluno.getEmail();
				}else if(atributo.equals("CodigoCurso")) {
					info += aluno.getCodigoCurso();
				}
			}
		}
		return info;
	}

}
