package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Tutor;
import model.Aluno;
import utils.Alfabetica;
import utils.Comparador;
import utils.Validador;

public class ControllerTutores {
	private Set<Tutor> tutores;
	private Set<Aluno> alunos;
	
	public ControllerTutores(Set<Aluno> alunos) {
		this.tutores = new HashSet<>();
		this.alunos = alunos;
	}
	
	private Aluno buscaAluno(String matricula) {
		Aluno busca = null;
		for(Aluno aluno: this.alunos) {
			if(aluno.getMatricula().equals(matricula)) {
				busca = aluno;
				break;
			}
		}
		
		return busca;
	}
	
	private Tutor buscaTutor(String matricula) {
		Tutor tutor = null;
		for(Tutor tal : this.tutores) {
			if(tal.getAluno().getMatricula().equals(matricula)) {
				tutor = tal;
				break;
			}
		}
		return tutor;
	}
	
	public void tornarTutor(String matricula, String disciplina, int proeficiencia) {
		Aluno aluno = buscaAluno(matricula);
		Tutor tutor = new Tutor(aluno, disciplina, proeficiencia);
		
		Validador.validadorCadeMatricula(matricula, this.alunos, "Erro na definicao de papel: Tutor nao encontrado");
		Validador.validadorPositivo(proeficiencia, "Erro na definicao de papel: Proficiencia invalida");
		Validador.validadorSetCoteins(tutor, this.tutores, "Erro na definicao de papel: Ja eh tutor dessa disciplina");
		
		if(!this.tutores.contains(tutor)) {
			this.tutores.add(tutor);
		}
	}
	
	public String recuperaTutor(String matricula) {
		Tutor tutor;
		tutor = buscaTutor(matricula);
		return tutor.toString();
	}
	
	public String listarTutores() {
		List<Aluno> alunosTutores = new ArrayList<>();
		for(Tutor tutor : this.tutores) {
			if(!alunosTutores.contains(tutor.getAluno())) {
				alunosTutores.add(tutor.getAluno());
			}
		}
		
		Comparador alfabetico = new Alfabetica();
		Collections.sort(alunosTutores, alfabetico);
		
        String str = "";
		
		for(int i=0; i < alunosTutores.size(); i++) {
			if(i == alunosTutores.size() - 1) {
				str += alunosTutores.get(i).toString();
				break;
			}
			str += alunosTutores.get(i).toString() + ", ";
		}
		
		return str.trim();
	}
	
}

