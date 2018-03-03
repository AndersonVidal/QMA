package controller;

import model.Ocupacao;
import utils.Validador;

/**
 * Controle que contém a lógica de negócio dos tutores do sistema.
 * 
 * @author Maria Cecília Kemiac
 */
public class ControllerTutores {
	
	/**
	 * Representação de um Controller de alunos.
	 */
	private ControllerAlunos ctAlunos;
	
	/**
	 * Consotrói um controller de tutores a partir de um controller
	 * de alunos.
	 * 
	 * @param ctrlAlunos Controller de alunos
	 */
	public ControllerTutores(ControllerAlunos ctrlAlunos) {
		this.ctAlunos = ctrlAlunos;
	}


	/**
	 * Adiciona um horário ao dia especificado para um tutor específico
	 * 
	 * @param email Email do tutor, no formato String "teste@email.com"
	 * @param horario Horário a ser adicionado, no formato String "17:00"
	 * @param dia Dia da semana, no formato String "seg"
	 */
	public void cadastrarHorario(String email, String horario, String dia) {
		Validador.validadorString(email, "Erro no cadastrar horario: email nao pode ser vazio ou em branco");
		Validador.validadorMapKeyNotConteins(email, this.ctAlunos.getAlunosTutoresKeyEmail(), "Erro no cadastrar horario: tutor nao cadastrado");
		
		Ocupacao tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
		tutor.addHorario(horario, dia);
	}

	/**
	 * Adiciona um local de atendimento do tutor especificado.
	 * 
	 * @param email Email do tutor, no formato String "teste@email.com"
	 * @param local Local de atendimento a ser adicionado, no formato String "ufcg"
	 */
	public void cadastrarLocalDeAtendimento(String email, String local) {
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		Validador.validadorMapKeyNotConteins(email, this.ctAlunos.getAlunosTutoresKeyEmail(), "Erro no cadastrar local de atendimento: tutor nao cadastrado");
		
		Ocupacao tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
		tutor.addLocal(local);
	}
	
	/**
	 *
	 * Verifica se o horario especificado é de disponibilidade do tutor especificado.
	 *
	 * @param email Email do tutor, no formato String "teste@email.com"
	 * @param horario Horário a ser consultado, no formato String "17:00"
	 * @param dia Dia da semana, no formato String "seg"
	 * @return Representacao booleana da verificacao de horário do tutor
	 */
	public boolean consultaHorario(String email, String horario, String dia) {
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		if (!this.ctAlunos.getAlunosTutoresKeyEmail().containsKey(email)) return false; 
		Ocupacao tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
		
		return tutor.consultaHorario(horario, dia);
	}
	
	/**
	 * Verifica se o local especificado é de disponibilidade do tutor especificado
	 * 
	 * @param email Email do tutor, no formato String "teste@email.com"
	 * @param local Local a ser consultado, no formato String "ufcg"
	 * @return Representacao booleana da verificacao de local do tutor
	 */
	public boolean consultaLocal(String email, String local) {
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		if (!this.ctAlunos.getAlunosTutoresKeyEmail().containsKey(email)) return false;
		Ocupacao tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
		
		return tutor.consultaLocal(local);
	}
}

