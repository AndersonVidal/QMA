package controller;

import model.Tutor;
import utils.Validador;

/**
 * Controle que contem a logica de negocio dos tutores do sistema.
 * 
 * @author Maria Cecília Kemiac
 */
public class ControllerTutores {
	
	/**
	 * Representacao de um Controller de alunos.
	 */
	private ControllerAlunos ctAlunos;
	
	/**
	 * Consotroi um controller de tutores a partir de um controller
	 * de alunos.
	 * 
	 * @param ctrlAlunos o controller de alunos
	 */
	public ControllerTutores(ControllerAlunos ctrlAlunos) {
		this.ctAlunos = ctrlAlunos;
	}


	/**
	 * Adiciona um horario ao dia especificado para um tutor especifico
	 * 
	 * @param email o email do tutor, no formato String "teste@email.com"
	 * @param horario o horario a ser adicionado, no formato String "17:00"
	 * @param dia o dia da semana, no formato String "seg"
	 */
	public void cadastrarHorario(String email, String horario, String dia) {
		Validador.validadorString(email, "Erro no cadastrar horario: email nao pode ser vazio ou em branco");
		Validador.validadorMapKeyNotConteins(email, this.ctAlunos.getAlunosTutores(), "Erro no cadastrar horario: tutor nao cadastrado");
		
		Tutor tutor = this.ctAlunos.getAlunosTutores().get(email);
		tutor.addHorario(horario, dia);
	}

	/**
	 * Adiciona um local de atendimento do tutor especificado.
	 * 
	 * @param email o email do tutor, no formato String "teste@email.com"
	 * @param local o local de atendimento a ser adicionado, no formato String "ufcg"
	 */
	public void cadastrarLocalDeAtendimento(String email, String local) {
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		Validador.validadorMapKeyNotConteins(email, this.ctAlunos.getAlunosTutores(), "Erro no cadastrar local de atendimento: tutor nao cadastrado");
		
		Tutor tutor = this.ctAlunos.getAlunosTutores().get(email);
		tutor.addLocal(local);
	}
	
	/**
	 *
	 * Verifica se o horario especificado eh de disponibilidade do tutor especificado.
	 *
	 * @param email o email do tutor, no formato String "teste@email.com"
	 * @param horario o horario a ser consultado, no formato String "17:00"
	 * @param dia o dia da semana, no formato String "seg"
	 * @return a representacao booleana da verificacao de horario do tutor
	 */
	public boolean consultaHorario(String email, String horario, String dia) {
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		if (!this.ctAlunos.getAlunosTutores().containsKey(email)) return false; 
		Tutor tutor = this.ctAlunos.getAlunosTutores().get(email);
		
		return tutor.consultaHorario(horario, dia);
	}
	
	/**
	 * Verifica se o local especificado eh de disponibilidade do tutor especificado
	 * 
	 * @param email o email do tutor, no formato String "teste@email.com"
	 * @param local o local a ser consultado, no formato String "ufcg"
	 * @return a eepresentacao booleana da verificacao de local do tutor
	 */
	public boolean consultaLocal(String email, String local) {
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		if (!this.ctAlunos.getAlunosTutores().containsKey(email)) return false;
		Tutor tutor = this.ctAlunos.getAlunosTutores().get(email);
		
		return tutor.consultaLocal(local);
	}
}

