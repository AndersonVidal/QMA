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
	 * Representação de um Controller de alunos.
	 */
	private ControllerAlunos controleAlunos;
	
	/**
	 * Representação de um mapa de tutores e matrículas.
	 */
	private Map<Tutor, String> tutores;
	
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
		
        String lista = "";
		
		for(int i=0; i < alunosTutores.size(); i++) {
			if(i == alunosTutores.size() - 1) {
				lista += alunosTutores.get(i).toString();
				break;
			}
			lista += alunosTutores.get(i).toString() + ", ";
		}
		
		return lista.trim();
	}
	
	/**
	 * Busca um tutor a partir do email e o retorna
	 * 
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @return Um tutor. Caso não encontre, null
	 */
	private Tutor buscaTutor(String email) {
		Tutor tutor = null;
		for(Tutor t: this.tutores.keySet()){
			if(t.getContato().equals(email)){
				tutor = t;
				break;
			}
		}
		return tutor;
	}
	
	/**
	 * Cria uma lista de emails dos tutores.
	 * 
	 * @return List de Strings email.
	 */
	private List<String> listaContatosTutores() {
		List<String> emails = new ArrayList<>();
		for(Tutor tutor : this.tutores.keySet()){
			emails.add(tutor.getContato());
		}
		return emails;
	}
	
	/**
	 * Adiciona um horário ao dia especificado para um tutor especifico
	 * 
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param horario Representação de um horario, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 */
	public void cadastrarHorario(String email, String horario, String dia) {
		Validador.validadorString(email, "Erro no cadastrar horario: email nao pode ser vazio ou em branco");
		Validador.validadorTutoresContainsEmail(email, this.tutores, "Erro no cadastrar horario: tutor nao cadastrado");
		Tutor tutor = buscaTutor(email);
		tutor.setHorario(horario, dia);
	}

	/**
	 * Adiciona um local de atendimento do tutor especificado
	 * 
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param local Representação de um local de atendimento, no formato String "ufcg"
	 */
	public void cadastrarLocalDeAtendimento(String email, String local) {
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		Validador.validadorTutoresContainsEmail(email, this.tutores, "Erro no cadastrar local de atendimento: tutor nao cadastrado");
		Tutor tutor = buscaTutor(email);
		tutor.setLocal(local);
	}
	
	/**
	 *
	 * Verifica se o horario especificado é de disponibilidade do tutor especificado.
	 *
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param horario Representação de um horario, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 * @return Representação booleana da verificação de horario do tutor
	 */
	public boolean consultaHorario(String email, String horario, String dia) {
		List<String> emails = listaContatosTutores();
		if(!emails.contains(email)) return false;
		Tutor tutor = buscaTutor(email);
		return tutor.consultaHorario(horario, dia);
	}
	
	/**
	 * Verifica se o local especificado é de disponibilidade do tutor especificado
	 * 
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param local Representação de um local no formato String "ufcg"
	 * @return Representação booleana da verificação de local do tutor
	 */
	public boolean consultaLocal(String email, String local) {
		List<String> emails = listaContatosTutores();
		if(!emails.contains(email)) return false;
		Tutor tutor = buscaTutor(email);
		return tutor.consultaLocal(local);
	}

	
	
}

