package controller;

import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import model.Aluno;
import utils.Alfabetica;
import utils.ComparaMatricula;
import utils.ComparaNome;
import utils.ComparaTelefone;
import utils.Constants;
import utils.Validador;

/**
 * Controlador do sistema que gerencia a comunicação entre outros controladores e o caixa do sistema
 * 
 * @author AndersonVidal
 *
 */
public class ControllerSistema {
	
	/**
	 * Controlador do cadastro de alunos
	 */
	private ControllerAlunos controllerAlunos;
	/**
	 * Controlador de operações com tutores
	 */
	private ControllerTutores controllerTutores;
	/**
	 * Controlador do cadastro de ajudas
	 */
	private ControllerAjudas controllerAjudas;
	/**
	 * Representação do caixa do sistema
	 */
	private CaixaSistema caixaSistema;
	/**
	 * Comparador do sistema
	 */
	private Comparator<Aluno> criterioDeListagem;
	
	public ControllerSistema(){
		this.controllerAlunos = new ControllerAlunos();
		this.controllerTutores = new ControllerTutores();
		this.controllerAjudas = new ControllerAjudas();
		this.caixaSistema = new CaixaSistema();
		this.criterioDeListagem = new Alfabetica();
	}
	
	/**
	 * Cadastra um aluno com telefone no sistema.
	 * 
	 * @param nome Representação do nome do aluno, no formato String "james"
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * @param codigoCurso Representação do codigo do curso, no formato inteiro "1"
	 * @param telefone Representação do telefone do aluno, no formato String "99999999"
	 * @param email Representação do email do aluno, no formato String "james@ccc.ufcg.edu.br"
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email){
		this.controllerAlunos.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
	}
	
	/**
	 * Encontra um aluno segundo a sua matricula no sistema.
	 * 
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * 
	 * @return Representaçao textual do aluno buscado.
	 */
	public String recuperaAluno(String matricula){
		return this.controllerAlunos.recuperaAluno(matricula);
	}
	
	/**
	 * Lista todos os alunos do sistema exibindo suas representações textuais em sequencia alfabética.
	 * 
	 * @return Representação textual de uma lista de alunos.
	 */
	public String listarAlunos(){
		return this.controllerAlunos.listarAlunos(this.criterioDeListagem);
	}
	
	/**
	 * Encontra um aluno segundo sua matricula no sistema e imprime a caracteristica desejada.
	 * 
	 * @param matricula Representação do nome do aluno, no formato String "123456"
	 * @param atributo Representação de uma caractéristica do aluno
	 * @return Representação textual de uma caractéristica do aluno
	 */
	public String getInfoAluno(String matricula, String atributo){
		return this.controllerAlunos.getInfoAluno(matricula, atributo);
	}
	
	/**
	 * Torna um aluno tutor de alguma disciplina através de sua matrícula.
	 * 
	 * @param matricula Representação da matrícula do aluno, no formato String "123456".
	 * @param disciplina Representação da disciplina, no formato String "matemática".
	 * @param proeficiencia Representação da proeficiencia do tutor, no formato inteiro "0".
	 */
	public void tornarTutor(String matricula, String disciplina, int proficiencia){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		this.controllerAlunos.setAlunos(this.controllerTutores.tornarTutor(matricula, disciplina, proficiencia, alunos));
	}
	
	/**
	 * Encontra um tutor segundo a sua matricula no sistema.
	 * 
	 * @param matricula Representação do tutor, no formato String "123456"
	 * 
	 * @return Representaçao textual do tutor buscado.
	 */
	public String recuperaTutor(String matricula){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		return this.controllerTutores.recuperaTutor(matricula, alunos);
	}
	
	/**
	 * Lista todos os tutores do sistema exibindo suas representações textuais em sequencia alfabética.
	 * 
	 * @return Representação textual de uma lista de tutores.
	 */
	public String listarTutores() {
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		return this.controllerTutores.listarAlunosTutores(alunos, this.criterioDeListagem);
	}
	
	/**
	 * Adiciona um horario ao dia especificado para um tutor especifico
	 * 
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param horario Representação de um horario, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 */
	public void cadastrarHorario(String email, String horario, String dia) {
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		alunos = this.controllerTutores.cadastrarHorario(email, horario, dia, alunos);
		
		this.controllerAlunos.setAlunos(alunos);
	}
	
	/**
	 * Adiciona um local de atendimento do tutor especificado
	 * 
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param local Representação de um local de atendimento, no formato String "ufcg"
	 */
	public void cadastrarLocalDeAtendimento(String email, String local) {
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		alunos = this.controllerTutores.cadastrarLocalDeAtendimento(email, local, alunos);
		this.controllerAlunos.setAlunos(alunos);
	}
	
	/**
	 * Verifica se o horario especificado é de disponibilidade do tutor especificado.
	 *
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param horario Representação de um horario, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 * @return Representação booleana da verificação de horario do tutor
	 */
	public boolean consultaHorario(String email, String horario, String dia){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		return this.controllerTutores.consultaHorario(email, horario, dia, alunos);
	}
	
	/**
	 * Verifica se o local especificado é de disponibilidade do tutor especificado
	 * 
	 * @param email Representação textual de um email, no formato String "teste@email.com"
	 * @param local Representação de um local no formato String "ufcg"
	 * @return Representação booleana da verificação de local do tutor
	 */
	public boolean consultaLocal(String email, String local){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		return this.controllerTutores.consultaLocal(email, local, alunos);
	}
	
	/**
	 * Solicita ajuda presencial a um tutor em uma disciplina que o tutor tenha
	 * proficiência.
	 * 
	 * @param matrAluno Representação da matrícula do aluno, no formato String "123456"
	 * @param disciplina Representação da disciplina, no formato String "matemática"
	 * @param horario Representação do horario, no formato String "17:00"
	 * @param dia Representação de um dia da semana, no formato String "seg"
	 * @param localInteresse Representação de um local de atendimento, no formato String "ufcg"
	 * @return Represenetação inteira do id da ajuda
	 */
	public int pedirAjudaPresencial (String matrAluno, String disciplina, String horario, String dia, String localInteresse){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		List<Aluno> tutores = this.controllerTutores.getAlunosTutores(alunos);
		Set<String> matrAlunos = this.controllerAlunos.getMatriculaAlunos();
		return this.controllerAjudas.pedirAjudaPresencial(matrAluno, disciplina, horario, dia, localInteresse, tutores, matrAlunos);
	}
	
	/**
	 * Solicita ajuda online para um aluno em uma determinada disciplina
	 * @param matriculaAluno Rpresentação da matrícula do aluno, no formato String "123456"
	 * @param disciplina Representação da disciplina, no formato String "matemática"
	 * @return ID de cadastro da ajuda.
	 */
	public int pedirAjudaOnline (String matrAluno, String disciplina){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		List<Aluno> tutores = this.controllerTutores.getAlunosTutores(alunos);
		Set<String> matrAlunos = this.controllerAlunos.getMatriculaAlunos();
		return this.controllerAjudas.pedirAjudaOnline(matrAluno, disciplina, tutores, matrAlunos);
	}
	
	/**
	 * Retorna a descrição do tutor da ajuda.
	 * 
	 * @param idAjuda ID da ajuda, no formato inteiroidAjuda 
	 * @return Representação textual da descrição do tutor da ajuda.
	 */
	public String pegarTutor(int idAjuda){
		return this.controllerAjudas.pegarTutor(idAjuda);
	}
	
	/**
	 * Recupera um atributo de uma ajuda específica.
	 * 
	 * @param idAjuda idAjuda ID da ajuda, no formato inteiro
	 * @param atributo Representação textual de um atributo ajuda.
	 * @return
	 */
	public String getInfoAjuda(int idAjuda, String atributo){
		return this.controllerAjudas.getInfoAjuda(idAjuda, atributo);
	}
	
	/**
	 * Registra a avaliação de uma ajuda prestada de um tutor.
	 * 
	 * @param idAjuda Número identificador da ajuda, no formato inteiro "2"
	 * @param nota Valor da avaliação, no formato inteiro "4"
	 */
	public void avaliarTutor (int idAjuda, int nota){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		Validador.validadorLimiteSuperior(nota, 5, "Erro na avaliacao de tutor: nota nao pode ser maior que 5");
		Validador.validadorLimiteInferior(nota, 0, "Erro na avaliacao de tutor: nota nao pode ser menor que 0");
		String matrTutor = this.controllerAjudas.avaliarTutor(idAjuda);
		Validador.validadorString(matrTutor, "Erro na avaliacao de tutor: Ajuda ja avaliada");
		Map<String, Aluno> atualizacao = this.controllerTutores.avaliaTutor(matrTutor, nota, alunos);
		this.controllerAlunos.setAlunos(atualizacao);
	}
	
	/**
	 * Recupera a nota de um tutor no sistema.
	 * 
	 * @param matriculaTutor Matricula do tutor, no formato String "123456"
	 * @return Nota do tutor
	 */
	public String pegarNota(String matriculaTutor){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		String nota = this.controllerTutores.pegarNota(matriculaTutor, alunos);
		Validador.validadorString(nota, "Aluno não é tutor");
		return nota;
	}
	
	/**
	 * Recupera o nivel do tutor, em que:
	 * <ul><li><b>TOP</b>: nivel dado ao tutor com nota maior que 4.5</li>
	 * <li><b>Tutor</b>: nivel dado ao tutor com nota entre 3.1 e 4.5</li>
	 * <li><b>Aprendiz</b>: nivel dado ao tutor com nota menor ou igual a 3</li></ul>
	 * 
	 * @param matriculaTutor Matricula do tutor cujo nivel será resgatado, no formato String "123456"
	 * @return Nivel do tutor. Null caso matricula referencie um aluno que não é tutor.
	 */
	public String pegarNivel(String matriculaTutor){
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		String nivel = this.controllerTutores.pegarNivel(matriculaTutor, alunos);
		Validador.validadorString(nivel, "Aluno não é tutor");
		return nivel;
	}
	
	/**
	 * Insere dinheiro no sistema que será dividido entre o sistema e o tutor.
	 * 
	 * @param matriculaTutor Matricula identificadora do aluno tutor, no formato String "123456"
	 * @param totalCentavos Dinheiro a ser doado, no formato inteiro "20000"
	 */
	public void doar(String matriculaTutor, int totalCentavos) {
		Validador.validadorPositivo(totalCentavos, "Erro na doacao para tutor: totalCentavos nao pode ser menor que zero");
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		
		double taxaTutor = this.controllerTutores.getTaxaTutor(matriculaTutor, alunos);
		Validador.validadorPositivo(taxaTutor, "matricula não é de tutor");
		
		double totalSistema = Math.floor(totalCentavos * (100 - taxaTutor)/100);
		
		double totalTutor = (totalCentavos - totalSistema);
		
		this.controllerAlunos.setAlunos(this.controllerTutores.receberDinheiroTutor(matriculaTutor, totalTutor, alunos));
		this.caixaSistema.addDinheiroSistema(totalSistema);
	}
	
	/**
	 * Recupera o total de dinheiro em um tutor identificado por um email
	 * 
	 * @param emailTutor Email do tutor, no formato String "tutor@ccc.ufcg.edu.br"
	 * @return Total de dinheiro armazenado em tutor.
	 */
	public int totalDinheiroTutor(String emailTutor) {
		Map<String, Aluno> alunos = this.controllerAlunos.getAlunos();
		return (int) this.controllerTutores.getDinheiroTutor(emailTutor, alunos);
	}
	
	/**
	 * Recupera o total de dinheiro no sistema.
	 * 
	 * @return Total de dinheiro armazenado no sistema.
	 */
	public int totalDinheiroSistema(){
		return (int) this.caixaSistema.getDinheiroSistema();
	}
	
	/**
	 * Configura o criterio de ordenação dos alunos no sistema, podendo ser:<br>
	 * <ul><li>Nome</li>
	 * <li>Matricula</li>
	 * <li>Telefone</li></ul>
	 * 
	 * @param atributo Critério de ordenação.
	 */
	public void configurarOrdem(String atributo) {
		Validador.validadorString(atributo, "Erro ao configurar ordem: atributo nao pode ser vazio ou nulo");
		
		switch (atributo) {
		case "Nome":
			this.criterioDeListagem = new ComparaNome();
		case "Matricula":
			this.criterioDeListagem = new ComparaMatricula();
		case "Telefone":
			this.criterioDeListagem = new ComparaTelefone();
		default:
			throw new IllegalArgumentException("Erro ao configurar ordem: atributo invalido");
		}
		
	}

	/**
	 * Salva os cadastros de alunos e ajudas, e o estado do caixa do sistema.
	 */
	public void salvar() {
		this.controllerAjudas.salvar(Constants.FILE_PATH + "ajudas.txt");
		this.controllerAlunos.salvar(Constants.FILE_PATH + "alunos.txt");
		this.caixaSistema.salvar(Constants.FILE_PATH + "caixa.txt");
	}

	/**
	 * Recupera os cadastros de alunos e ajudas, e o estado do caixa do sistema.
	 */
	public void carregar() {
		this.controllerAjudas.carregar(Constants.FILE_PATH + "ajudas.txt");
		this.controllerAlunos.carregar(Constants.FILE_PATH + "alunos.txt");
		this.caixaSistema.carregar(Constants.FILE_PATH + "caixa.txt");
	}

	/**
	 * Limpa todo registro de cadastros e estado do caixa do sistema salvo.
	 */
	public void limpar() {
		this.controllerAjudas.limpar(Constants.FILE_PATH + "ajudas.txt");
		this.controllerAlunos.limpar(Constants.FILE_PATH + "alunos.txt");
		this.caixaSistema.limpar(Constants.FILE_PATH + "caixa.txt");
	}
}
