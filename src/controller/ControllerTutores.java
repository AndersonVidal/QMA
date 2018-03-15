package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import model.Aluno;
import utils.Validador;

/**
 * Controle que contém a lógica de negócio dos tutores do sistema.
 * 
 * @author Maria Cecília Kemiac e Anderson Fellipe de Vasconcelos Vidal
 */
public class ControllerTutores {
	
	/**
	 * Adiciona a propriedade tutor a um aluno cadastrado no sistema. Caso o aluno já seja tutor, é
	 * adicionado apenas a disciplina com sua profissiencia.
	 * 
	 * @param matricula Matricula do aluno, no formato String "111222333"
	 * @param disciplina Disciplina para tutor, no formato String "Calculo 2"
	 * @param proficiencia Profissiencia do tutor na disciplina, no formato inteiro "1"
	 * @param alunos Alunos cadastrados no sistema, no formato Map
	 * @return Cadastro de aluno atualizados, no formato Map
	 */
	public Map<String, Aluno> tornarTutor(String matricula, String disciplina, int proficiencia, Map<String, Aluno> alunos){
		Validador.validadorObjeto(alunos, "cadastro de alunos null");
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
	
	/**
	 * Recupera a descrição de um tutor.
	 * 
	 * @param matricula Matricula do tutor no sistema.
	 * @param alunos Alunos cadastrados no sistema, no formato Map
	 * @return Desrição de um tutor.
	 */
	public String recuperaTutor(String matricula, Map<String, Aluno> alunos){
		Validador.validadorObjeto(alunos, "cadastro de alunos null");
		Validador.validadorString(matricula, "Erro na busca por tutor: Matricula não pode ser vazio ou nulo");
		Validador.validadorMapKeyNotConteins(matricula, alunos, "Erro na busca por tutor: Tutor nao encontrado");
		
		Aluno aluno = alunos.get(matricula);
		if (!aluno.isTutor()) {
			throw new IllegalArgumentException("Erro na busca por tutor: Matricula nao eh de um aluno tutor");
		}
		return aluno.toString();
	}
	
	/**
	 * Cria e retorna uma lista de descrições de cada aluno que é tutor
	 * 
	 * @param alunos Mapa de alunos cadastrados, no formato Map
	 * @return Lista de descrições de alunos tutores
	 */
	public String listarAlunosTutores(Map<String, Aluno> alunos, Comparator<Aluno> criterioDeListagem){
		Validador.validadorObjeto(alunos, "Cadastro de alunos null");
		Validador.validadorObjeto(criterioDeListagem, "criterio de ordenação null");
		List<Aluno> arrayTutores= this.getAlunosTutores(alunos);
		Collections.sort(arrayTutores, criterioDeListagem);
		
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
	
	/**
	 * Cadastra um horário de disponibilidade de atendimeno para um tutor.
	 * 
	 * @param email Email do aluno tutor, no formato String "tutor@gmail.com"
	 * @param horario Horario de disponibilidade, no formato String "HH:mm"
	 * @param dia Dia de atendimento, no formato String "ter"
	 * @param alunos Cdastro de alunos do sistema, no formato Map
	 * @return Cadastro atualizado dos alunos no sistema, no formato Map
	 */
	public Map<String, Aluno> cadastrarHorario(String email, String horario, String dia, Map<String, Aluno> alunos) {
		Validador.validadorObjeto(alunos, "Cadastro de alunos null");
		Validador.validadorString(email, "Erro no cadastrar horario: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		Validador.validadorString(matriculaTutor, "Erro no cadastrar horario: tutor nao cadastrado");
		
		alunos.get(matriculaTutor).getTutor().addHorario(horario, dia);
		return alunos;
	}
	
	/**
	 * Registra um local de disponibilidade para atendimentos de um tutor cadastrado
	 * 
	 * @param email Email do aluno tutor, no formato String "tutor@gmail.com"
	 * @param local Local de atendimento, no formato String "CAA303"
	 * @param alunos Cdastro de alunos do sistema, no formato Map
	 * @return Cadastro atualizado dos alunos no sistema, no formato Map
	 */
	public Map<String, Aluno> cadastrarLocalDeAtendimento(String email, String local, Map<String, Aluno> alunos) {		
		Validador.validadorObjeto(alunos, "Cadastro de alunos null");
		Validador.validadorString(email, "Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		Validador.validadorString(matriculaTutor, "Erro no cadastrar local de atendimento: tutor nao cadastrado");
		
		alunos.get(matriculaTutor).getTutor().addLocal(local);
		return alunos;
	}
	
	/**
	 * Retorna a possibilidade de um atendimento em determinado horario e dia
	 * 
	 * @param email Email do aluno tutor, no formato String "tutor@gmail.com"
	 * @param horario Horario de disponibilidade, no formato String "HH:mm"
	 * @param dia Dia de atendimento, no formato String "ter"
	 * @param alunos Cdastro de alunos do sistema, no formato Map
	 * @return Booleano que representa a proposição de horário de atendimento existir.
	 */
	public boolean consultaHorario(String email, String horario, String dia, Map<String, Aluno> alunos){
		Validador.validadorObjeto(alunos, "Cadastro de alunos null");
		Validador.validadorString(email, "Erro na consulta de horario: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		if(matriculaTutor == null) return false;
		
		return alunos.get(matriculaTutor).getTutor().consultaHorario(horario, dia); 
	}
	
	/**
	 * Retorna a possibilidade de um atendimento em determinado local.
	 * 
	 * @param email Email do aluno tutor, no formato String "tutor@gmail.com"
	 * @param local Local de atendimento, no formato String "CAA303"
	 * @param alunos Cdastro de alunos do sistema, no formato Map
	 * @return Booleano que representa a proposição de local de atendimento existir.
	 */
	public boolean consultaLocal(String email, String local, Map<String, Aluno> alunos){
		Validador.validadorObjeto(alunos, "Cadastro de alunos null");
		Validador.validadorString(email, "Erro na consulta de local: email nao pode ser vazio ou em branco");
		String matriculaTutor = this.buscaTutor(alunos, email);
		if(matriculaTutor == null) return false;
		
		return alunos.get(matriculaTutor).getTutor().consultaLocal(local);
	}
	
	/**
	 * Retorna uma lista de alunos que são tutores.
	 * 
	 * @param alunos Cdastro de alunos do sistema, no formato Map
	 * @return Alunos tutores, no formato List
	 */
	public List<Aluno> getAlunosTutores(Map<String, Aluno> alunos){
		Validador.validadorObjeto(alunos, "Cadastro de alunos null");
		List<Aluno> tutores = new ArrayList<Aluno>();
		for(Aluno aln : alunos.values()) {
			if (aln.isTutor()) tutores.add(aln);
		}
		
		return tutores;
	}
	
	/**
	 * Registra uma avaliação de uma ajuda prestara por um tutor identificado pela sua matricula.
	 * 
	 * @param matrTutor Matricula do tutor responsável pela ajuda, no formato String "123456"
	 * @param nota Avaliação para a ajuda prestada, no formato inteiro "7"
	 * @param alunos Mapa de alunos cadastrados, no formato Map
	 * @return Mapa de alunos cadastrados atualizado.
	 */
	public Map<String, Aluno> avaliaTutor(String matrTutor, int nota, Map<String, Aluno> alunos){
		Validador.validadorObjeto(alunos, "Cadastro de alunos null");
		Validador.validadorString(matrTutor, "matricula do tutor não pode ser vazia ou nula");
		Validador.validadorMapKeyNotConteins(matrTutor, alunos, "Tutor não encontrado");
		alunos.get(matrTutor).getTutor().avaliar(nota);
		return alunos;
	}
	
	/**
	 * Recupera a nota de um tutor de acordo com a matricula do mesmo. <br>
	 * Caso a matricula referencie um aluno que não é tutor, é retornado -1;
	 * 
	 * @param matriculaTutor Matricula do aluno tutor, no formato String "123456"
	 * @param alunos Mapa de alunos cadastrados, no formato Map
	 * @return Nota do tutor. -1, caso matricula não referencie tutor.
	 */
	public String pegarNota(String matriculaTutor, Map<String, Aluno> alunos) {
		Validador.validadorString(matriculaTutor, "tutor não pode ser nulo ou vazio");
		Validador.validadorMapKeyNotConteins(matriculaTutor, alunos, "tutor não cadastrado");
		Aluno aluno = alunos.get(matriculaTutor);
		if(aluno.isTutor()) {
			return aluno.getTutor().getNotaString();
		}
		return null;
	}
	
	/**
	 * Recupera o nivel do tutor, em que:
	 * <ul><li><b>TOP</b>: nivel dado ao tutor com nota maior que 4.5</li>
	 * <li><b>Tutor</b>: nivel dado ao tutor com nota entre 3.1 e 4.5</li>
	 * <li><b>Aprendiz</b>: nivel dado ao tutor com nota menor ou igual a 3</li></ul>
	 * 
	 * @param matriculaTutor Matricula do tutor cujo nivel será resgatado, no formato String "123456"
	 * @param alunos Mapa de alunos cadastrados, no formato Map
	 * @return Nivel do tutor. Null caso matricula referencie um aluno que não é tutor.
	 */
	public String pegarNivel(String matriculaTutor, Map<String, Aluno> alunos) {
		Validador.validadorString(matriculaTutor, "tutor não pode ser nulo ou vazio");
		Validador.validadorMapKeyNotConteins(matriculaTutor, alunos, "tutor não cadastrado");
		Aluno aluno = alunos.get(matriculaTutor);
		if(aluno.isTutor()) {
			return aluno.getTutor().getNivel();
		}
		return null;
	}
	
	/**
	 * Recupera a taxa de um tutor identificado pela sua matricula
	 * 
	 * @param matriculaTutor Matricula do tutor cujo nivel será resgatado, no formato String "123456"
	 * @param alunos Mapa de alunos cadastrados, no formato Map
	 * @return Taxa do tutor, no formato double "30.00". -1, se matricula não referenciar um tutor.
	 */
	public double getTaxaTutor(String matriculaTutor, Map<String, Aluno> alunos){
		Validador.validadorString(matriculaTutor, "Erro na doacao para tutor: matriucla não pode ser vazia ou nula");
		Validador.validadorMapKeyNotConteins(matriculaTutor, alunos, "Erro na doacao para tutor: Tutor nao encontrado");
		Aluno aluno = alunos.get(matriculaTutor);
		if(aluno.isTutor()) {
			return aluno.getTutor().getTaxa();
		}
		return -1;
	}
	
	/**
	 * Registra uma quantia de dinheiro no cadastro do tutor e retorna o registro de alunos cadastrados atualizados.
	 * 
	 * @param matriculaTutor Matricula do tutor a receber a quantia.
	 * @param dinheiro Dinheiro a ser doado para o tutor.
	 * @param alunos Alunos cadastrados no sistema.
	 * @return Registro de alunos cadastrados no sistema. Null caso a matricula não referencie um tutor.
	 */
	public Map<String, Aluno> receberDinheiroTutor(String matriculaTutor, double dinheiro, Map<String, Aluno> alunos){
		Validador.validadorString(matriculaTutor, "Erro no recebimento de dinheiro para tutor: matriucla não pode ser vazia ou nula");
		Validador.validadorMapKeyNotConteins(matriculaTutor, alunos, "Erro no recebimento de para tutor: Tutor nao encontrado");
		Aluno aluno = alunos.get(matriculaTutor);
		if(aluno.isTutor()) {
			alunos.get(matriculaTutor).getTutor().setDinheiro(dinheiro);
			return alunos;
		}
		return null;
	}
	
	/**
	 * Retorna o total de dinheiro armazenado no tutor. Caso email não referencie um tutor, é retornado -1.
	 * 
	 * @param email Email do tutor a ser consultado.
	 * @param alunos Alunos cadastrados no sistema.
	 * @return Total de dinheiro do tutor, no formato double. -1 caso email não referencie um aluno que é tutor.
	 */
	public double getDinheiroTutor(String email, Map<String, Aluno> alunos){
		Validador.validadorString(email, "Erro na consulta de total de dinheiro do tutor: emailTutor nao pode ser vazio ou nulo");
		String matriculaTutor = this.buscaTutor(alunos, email);
		Validador.validadorString(matriculaTutor, "Erro na consulta de total de dinheiro do tutor: Tutor nao encontrado");
		if(alunos.get(matriculaTutor).isTutor()){
			return alunos.get(matriculaTutor).getTutor().getDinheiro();
		}
		return -1;
	}
	
	/**
	 * Retorna a quantidade de tutores cadastrados no sistema.
	 * 
	 * @param alunos Alunos cadastrados no sistema.
	 * @return Numero de tutores cadastrados no sistema, no formato inteiro "3"
	 */
	private int getQuatidadeTutores(Map<String, Aluno> alunos){
		return this.getAlunosTutores(alunos).size();
	}
	
	/**
	 * Realiza uma busca de um tutor nos alunos cadastrados a partir do seu email, retornando sua
	 * matricula. Caso o tutor não seja encontrado, é retornado null.
	 * 
	 * @param alunos Mapa de alunos cadastrados no sistema, no formato Map
	 * @param email Email do aluno a ser buscado, no formato "tutor@teste.com"
	 * @return Matricula do tutor. Null caso tutor não seja encontrado.
	 */
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