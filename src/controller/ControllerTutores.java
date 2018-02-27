package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Tutor;
import model.Aluno;
import utils.Alfabetica;
import utils.Comparador;
import utils.Validador;

/**
 * Controle que contem a lógica de negocio do armazenamento de tutores do sistema
 * 
 * @author Maria Cecília Kemiac
 */
public class ControllerTutores {
	
	/**
	 * Representação de um mapa de tutores e matrículas.
	 */
	private Map<Tutor, String> tutores;
	/**
	 * Representação de um Controller de alunos.
	 */
	private ControllerAlunos controleAlunos;
	
	public ControllerTutores(ControllerAlunos ctrlAlunos) {
		this.tutores = new HashMap<>();
		this.controleAlunos = ctrlAlunos;
	}
	
	/**
	 * Torna um aluno tutor de alguma disciplina através de sua matrícula.
	 * 
	 * @param matricula Representação da matrícula do aluno, no formato String "123456".
	 * @param disciplina Representação da disciplina, no formato String "matemática".
	 * @param proeficiencia Representação da proeficiencia do tutor, no formato inteiro "0".
	 */
	public void tornarTutor(String matricula, String disciplina, int proeficiencia) {
		Validador.validadorMapKeyNotConteins(matricula, this.controleAlunos.getAlunos(), "Erro na definicao de papel: Tutor nao encontrado");
		Tutor tutor = new Tutor(matricula, disciplina, proeficiencia, this.controleAlunos.getInfoAluno(matricula, "Email"));
		Validador.validadorMapKeyConteins(tutor, this.tutores, "Erro na definicao de papel: Ja eh tutor dessa disciplina");
		this.tutores.put(tutor, matricula);
	}
	
	/**
	 * Encontra um tutor segundo a sua matricula no sistema.
	 * 
	 * @param matricula Representação do nome do tutor, no formato String "123456"
	 * 
	 * @return Representaçao textual do tutor buscado.
	 */
	public String recuperaTutor(String matricula) {
		Validador.validadorString(matricula, "Erro na busca por tutor: Matricula não pode ser vazio ou nulo");
		Validador.validadorMapValueConteins(matricula, this.tutores, "Erro na busca por tutor: Tutor nao encontrado");
		
		return this.controleAlunos.recuperaAluno(matricula);
	}
	
	/**
	 * Cria uma lista ordenada de tutores, em ordem alfabética.
	 * 
	 * @return List de Tutores.
	 */
	public String listarTutores() {
		List<Aluno> alunosTutores = new ArrayList<>();
		for(Tutor tutor : this.tutores.keySet()) {
			if(!alunosTutores.contains(this.controleAlunos.getAlunos().get((tutor.getMatricula())))) {
				alunosTutores.add(this.controleAlunos.getAlunos().get((tutor.getMatricula())));
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

