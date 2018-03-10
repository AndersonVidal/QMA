package controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import model.Ajuda;
import model.Aluno;
import model.Tutor;
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
	private CaixaSistema caixa;
	
	/**
	 * Consotrói um controller de tutores a partir de um controller
	 * de alunos.
	 * 
	 * @param ctrlAlunos Controller de alunos
	 */
	public ControllerTutores(ControllerAlunos ctrlAlunos, CaixaSistema caixa) {
		this.ctAlunos = ctrlAlunos;
		this.caixa = caixa;
	}

	/**
	/* Retorna um objeto aluno que é tutor da ajuda passada como parâmetro.
	 * @param idAjuda ID da ajuda no formato inteiro.
	 * @return o aluno.
	 */
	private Aluno retornaAlunoTutor(int idAjuda) {
		for(Aluno aluno : this.ctAlunos.getAlunosTutores()) {
			if(aluno.getTutor().getAjudasPrestadas().containsKey(idAjuda)) {
				return aluno;
			}
		}
		return null;
	}
	
	/**
	 * Avalia um tutor a partir da nota da ajuda concedida.
	 * 
	 * @param idAjuda ID da ajuda, no formato inteiro.
	 * @param nota nota para a ajuda do tutor, no formato inteiro.
	 * @return
	 */
	public String avaliarTutor(int idAjuda, int nota) {
		Validador.validadorNaoNegativo(nota, "Erro na avaliacao de tutor: nota nao pode ser menor que 0");
		Validador.validadorMaiorQueAlgo(nota, 5, "Erro na avaliacao de tutor: nota nao pode ser maior que 5");
		Validador.validadorTamanho(idAjuda, this.ctAlunos.getQtdAjudas(), "Erro na avaliacao de tutor: id nao encontrado ");
		
		Aluno alunoTutor = retornaAlunoTutor(idAjuda);
		Validador.validadorObjeto(alunoTutor, "Erro na avaliacao de tutor: Ajuda nao atribuida a tutor");
		Ajuda ajuda = alunoTutor.getTutor().getAjudasPrestadas().get(idAjuda);
		
		Validador.validadorBooleanTrue(ajuda.avaliado(), "Erro na avaliacao de tutor: Ajuda ja avaliada");
		
		alunoTutor.getTutor().setNota(nota);
		ajuda.avaliaAjuda();
		return alunoTutor.toString();
	}
	
	/**
	 * Retorna a nota do tutor através de sua matrícula.
	 * @param matriculaTutor a matricula do tutor, no formato String.
	 * @return
	 */
	public String pegarNota(String matriculaTutor) {
		Aluno alunoTutor = retornaAlunoTutor(matriculaTutor);
		double nota = alunoTutor.getTutor().getNota();
		
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		decimalFormat.setRoundingMode(RoundingMode.DOWN);
		
		return decimalFormat.format(nota);
	}
	
	/**
	 * Retorna o nível do tutor através de sua matrícula.
	 * @param matriculaTutor a matrícula do tutor, no formato String.
	 * @return
	 */
	public String pegarNivel(String matriculaTutor) {
		Aluno alunoTutor = retornaAlunoTutor(matriculaTutor);
		double nota = alunoTutor.getTutor().getNota();
		
		String nivel = null;
		if(nota > 4.5) {
			nivel = "TOP";
		}else if(nota > 3 && nota <= 4.5) {
			nivel = "Tutor";
		}else {
			nivel = "Aprendiz";
	}
		return nivel;
	}
	
	/**
	 * Retorna um objeto aluno que é tutor e contém a matrícula passada como parâmetro.
	 * @param matriculaTutor a matricula do aluno tutor, no formato String.
	 * @return o aluno.
	 */
	private Aluno retornaAlunoTutor(String matriculaTutor) {
		for(Aluno aluno : this.ctAlunos.getAlunosTutores()) {
			if(aluno.getMatricula().equals(matriculaTutor)) {
				return aluno;
			}
		}
		return null;
	}
	
	private Aluno retornaAlunoTutorEmail(String emailTutor) {
		for(Aluno aluno : this.ctAlunos.getAlunosTutores()) {
			if(aluno.getEmail().equals(emailTutor)) {
				return aluno;
			}
		}
		return null;
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
		
		Tutor tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
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
		
		Tutor tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
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
		Tutor tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
		
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
		Tutor tutor = this.ctAlunos.getAlunosTutoresKeyEmail().get(email);
		
		return tutor.consultaLocal(local);
	}
	
	/**
	 * Doa o dinheiro passado como parâmetro para o caixa do sistema e tutor especificado.
	 * @param matriculaTutor matrícula do tutor
	 * @param totalCentavos o dinheiro
	 */
	public void doar(String matriculaTutor, int totalCentavos){
		Validador.validadorPositivo(totalCentavos,"Erro na doacao para tutor: totalCentavos nao pode ser menor que zero");
		Aluno alunoTutor = retornaAlunoTutor(matriculaTutor);
		Validador.validadorObjeto(alunoTutor, "Erro na doacao para tutor: Tutor nao encontrado");
		double nota = alunoTutor.getTutor().getNota();
		String nivel = pegarNivel(matriculaTutor);
		
		int taxaTutor;
		if(nivel.equals("TOP")) {
			taxaTutor = ((int)((nota - 4.5) * 10)) + 90;
		}else if(nivel.equals("Tutor")) {
			taxaTutor = 80;
		}else {
			taxaTutor = 40 - ((int)(Math.ceil((3 - nota) * 10)));
		}
		double taxa = taxaTutor / 100.0;
		
		int dinheiroSistema = (int) Math.ceil((1 - taxa) * totalCentavos);
		int dinheiroTutor = totalCentavos - dinheiroSistema;
		
	    alunoTutor.getTutor().addDinheiro(dinheiroTutor);
		caixa.addDinheiroSistema(dinheiroSistema);
	}
	
	/**
	 * Retorna o total de dinheiro que o tutor do email passado como parâmetro possui.
	 * @param emailTutor o email do tutor.
	 * @return o valor total.
	 */
	public int totalDinheiroTutor(String emailTutor) {
		Validador.validadorString(emailTutor,"Erro na consulta de total de dinheiro do tutor: emailTutor nao pode ser vazio ou nulo");
		Aluno aluno = retornaAlunoTutorEmail(emailTutor);
		Validador.validadorObjeto(aluno, "Erro na consulta de total de dinheiro do tutor: Tutor nao encontrado");
		int dinheiroTotalTutor = aluno.getTutor().getDinheiro();
		return dinheiroTotalTutor;
	}
	
	/**
	 * Retorna o total do dinheiro que o caixa do sistema possui.
	 * @return o valor total.
	 */
	public int totalDinheiroSistema() {
		return caixa.getDinheiroSistema();
	}
	
}

