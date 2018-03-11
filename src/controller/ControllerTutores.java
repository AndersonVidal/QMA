package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.Aluno;
import utils.Alfabetica;
import utils.Comparador;
import utils.Validador;

/**
 * Controle que contém a lógica de negócio dos tutores do sistema.
 * 
 * @author Maria Cecília Kemiac e Anderson Fellipe de Vasconcelos Vidal
 */
public class ControllerTutores {
	
	public ControllerTutores() {}
	
	public Map<String, Aluno> tornarTutor(String matricula, String disciplina, int proficiencia, Map<String, Aluno> alunos){
		Validador.validadorString(matricula, "Erro na definicao de papel: matricula invalida");
		Validador.validadorMapKeyNotConteins(matricula, alunos, "Erro na definicao de papel: Tutor nao encontrado");
		
		Aluno aluno = alunos.get(matricula);
		if (aluno.isTutor()) {
			aluno.getTutor().addDisciplina(disciplina, proficiencia);
		} else  {
			int id = this.getQuatidadeTutores(alunos) + 1;
			aluno.tornaTutor(id, disciplina, proficiencia);
		}
		
		return alunos;
	}
	
	public String recuperaTutor(String matricula, Map<String, Aluno> alunos){
		Validador.validadorString(matricula, "Erro na busca por tutor: Matricula não pode ser vazio ou nulo");
		Validador.validadorMapKeyNotConteins(matricula, alunos, "Erro na busca por tutor: Tutor nao encontrado");
		
		Aluno aluno = alunos.get(matricula);
		if (!aluno.isTutor()) {
			throw new IllegalArgumentException("Erro na busca por tutor: Matricula nao eh de um aluno tutor");
		}
		return aluno.toString();
	}
	
	public String listarAlunosTutores(Map<String, Aluno> alunos){
		List<Aluno> arrayTutores = this.ordenaAlfabeticaTutores(this.getAlunosTutores(alunos));
	
		int quantidadeTutores = this.getQuatidadeTutores(alunos);
		String lista = "";
		int i = 0;
		for (Aluno tutor : arrayTutores) {
			i++;
			if (i < quantidadeTutores) {
				lista += tutor.toString() + ", ";
			} else if(tutor.isTutor()){
				lista += tutor.toString();
			}
		}
		
		return lista;
	}
	
	public Map<String, Aluno> cadastrarHorario(String email, String horario, String dia, Map<String, Aluno> alunos) {
		Validador.validadorString(email, "Erro no cadastrar horario: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		Validador.validadorString(matriculaTutor, "Erro no cadastrar horario: tutor nao cadastrado");
		
		alunos.get(matriculaTutor).getTutor().addHorario(horario, dia);
		return alunos;
	}
	
	public Map<String, Aluno> cadastrarLocalDeAtendimento(String email, String local, Map<String, Aluno> alunos) {		
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		Validador.validadorString(matriculaTutor, "Erro no cadastrar local de atendimento: tutor nao cadastrado");
		
		alunos.get(matriculaTutor).getTutor().addLocal(local);
		return alunos;
	}
	
	public boolean consultaHorario(String email, String horario, String dia, Map<String, Aluno> alunos){
		Validador.validadorString(email, "Erro na consulta de horario: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		if(matriculaTutor == null) return false;
		
		return alunos.get(matriculaTutor).getTutor().consultaHorario(horario, dia); 
	}
	
	public boolean consultaLocal(String email, String local, Map<String, Aluno> alunos){
		Validador.validadorString(email, "Erro na consulta de local: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		if(matriculaTutor == null) return false;
		
		return alunos.get(matriculaTutor).getTutor().consultaLocal(local);
	}
	
	private List<Aluno> getAlunosTutores(Map<String, Aluno> alunos){
		List<Aluno> tutores = new ArrayList<Aluno>();
		for(Aluno aln : alunos.values()) {
			if (aln.isTutor()) tutores.add(aln);
		}
		
		return tutores;
	}
	
	private int getQuatidadeTutores(Map<String, Aluno> alunos){
		return this.getAlunosTutores(alunos).size();
	}
	
	private List<Aluno> ordenaAlfabeticaTutores(List<Aluno> tutores) {
		Comparador alfabetico = new Alfabetica();
		Collections.sort(tutores, alfabetico);
		return tutores;
	}
	
	private String buscaTutor(Map<String, Aluno> alunos, String email) {
		String matriculaTutor = null;
		for(Aluno aluno : alunos.values()) {
			if(aluno.isTutor()) {
				if(aluno.getEmail().equals(email)) matriculaTutor = aluno.getMatricula();
			}
		}
		
		return matriculaTutor;
	}
}

