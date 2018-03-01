package controller;

import model.Tutor;
import utils.Validador;

/**
 * Controle que contem a lógica de negocio dos tutores do sistema.
 * 
 * @author Maria Cecília Kemiac
 */
public class ControllerTutores {
	
	/**
	 * Representacao de um Controller de alunos.
	 */
	private ControllerAlunos ctAlunos;
	
	public ControllerTutores(ControllerAlunos ctrlAlunos) {
		this.ctAlunos = ctrlAlunos;
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
		Validador.validadorMapKeyNotConteins(email, this.ctAlunos.getTutores(), "Erro no cadastrar horario: tutor nao cadastrado");
		Tutor tutor = this.ctAlunos.getTutores().get(email);
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
		Validador.validadorMapKeyNotConteins(email, this.ctAlunos.getTutores(), "Erro no cadastrar local de atendimento: tutor nao cadastrado");
		Tutor tutor = this.ctAlunos.getTutores().get(email);
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
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		if (!this.ctAlunos.getTutores().containsKey(email)) return false; 
		Tutor tutor = this.ctAlunos.getTutores().get(email);
		
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
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		if (!this.ctAlunos.getTutores().containsKey(email)) return false;
		Tutor tutor = this.ctAlunos.getTutores().get(email);
		
		return tutor.consultaLocal(local);
	}

	
	
}

