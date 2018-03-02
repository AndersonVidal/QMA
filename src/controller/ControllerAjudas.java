package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import model.Aluno;
import utils.ComparaNotaAlunoTutor;

public class ControllerAjudas {
	
	private ControllerAlunos ctAlunos;
	
	public ControllerAjudas(ControllerAlunos ctAlunos) {
		this.ctAlunos = ctAlunos;
	}
	
	private String selecionaTutor(String disciplina) {
		List<Aluno> alunosTutores = new ArrayList<Aluno>();
		for (Aluno aln : this.ctAlunos.getAlunosTutores()) {
			if(aln.getTutor().getDisciplinas().containsKey(disciplina)) {
				alunosTutores.add(aln);
			}
		}
		
		return Collections.max(alunosTutores, new ComparaNotaAlunoTutor()).getMatricula();
	}
	
	/*public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia, String localInteresse) {
		new AjudaPresencial(id, matrAluno, this.selecionaTutor(disciplina), disciplina, horario, dia, localInteresse);
		return id;
	}*/ 	
}
