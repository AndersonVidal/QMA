package controller;

import java.util.List;
import java.util.Set;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.Ajuda;
import model.AjudaOnline;
import model.AjudaPresencial;
import model.Aluno;
import utils.ComparaNotaAlunoTutor;
import utils.Validador;

/**
 * Controle que contem a lógica de negócio do armazenamento de ajudas do sistema.
 * 
 * @author Daniel Coura
 */
public class ControllerAjudas {
	
	/**
	 * Representação de uma lista de ajudas.
	 */
	private List<Ajuda> ajudas;
	
	/**
	 * Constrói o controle de ajudas a partir do controle de alunos.
	 * 
	 * @param ctAlunos o controle de alunos
	 */
	public ControllerAjudas() {
		this.ajudas = new ArrayList<Ajuda>();
	}
	
	/**
	 * Retorna a matricula do tutor com maior profissiencia.<br>
	 * Em caso de empate, retorna o primeiro cadastrado entre os empatados.<br>
	 * Caso não seja encontrado um tutor, é retornado null.<br>
	 * 
	 * @param alunosTutores Lista de alunos tutores do sistema.
	 * @param matriculaAlunoAjudado Matricula do aluno que requisitou a ajuda
	 * @return Matricula do tutor selecionado. Null caso não seja selecionado nenhum tutor.
	 */
	private String selecionaMelhorTutor(List<Aluno> alunosTutores, String matriculaAlunoAjudado){
		if(alunosTutores.isEmpty()) return null;
		Comparator<Aluno> comparatorTutor = new ComparaNotaAlunoTutor();
		Aluno tutorSelecionado = Collections.max(alunosTutores, comparatorTutor);
		if(tutorSelecionado.getMatricula().equals(matriculaAlunoAjudado)){
			alunosTutores.remove(tutorSelecionado);
			return this.selecionaMelhorTutor(alunosTutores, matriculaAlunoAjudado);
		}
		return tutorSelecionado.getMatricula();
	}
	
	/**
	 * Seleciona um tutor que possui melhor proficiencia na disciplina 
	 * e retorna sua matrícula de acordo com os critérios:
	 * <ul><li>disciplina</li>
	 * <li>horario</li>
	 * <li>dia</li></ul>
	 * Caso exista empate entre tutores, é retorna o tutor mais antigo no sistema.
	 * Caso nenhum tutor seja selecionado, é retornado null.
	 * 
	 * @param matrAluno Representação da matrícula do aluno, no formato "123456"
	 * @param disciplina Represetação da disciplina, no formato "inglês"
	 * @param horario Representação do horário, no formato "12:12"
	 * @param dia Representação do dia, no formato "seg"
	 * @param local Representação do local, no formato "ufcg"
	 * @return Representação textual da matrícula do tutor. Null caso não seja selecionado tutor.
	 */
	private String selecionaTutorAjudaPresencial(String matrAluno, String disciplina, String horario, String dia, String local, List<Aluno> tutores) {
		List<Aluno> alunosTutores = new ArrayList<Aluno>();
		for (Aluno alunoTutor : tutores) {
			boolean contemDisciplina = alunoTutor.getTutor().getDisciplinas().containsKey(disciplina);
			boolean diaDisponivel = alunoTutor.getTutor().getDisponibilidade().containsKey(dia);
			boolean horarioDisponivel = alunoTutor.getTutor().getDisponibilidade().get(dia).contains(horario);
			boolean localDisponivel = alunoTutor.getTutor().getLocalAtendimento().contains(local);
			
			if(contemDisciplina && diaDisponivel && horarioDisponivel && localDisponivel) {
				alunosTutores.add(alunoTutor);
			}
		}		
		
		String tutorSelecionado = this.selecionaMelhorTutor(alunosTutores, matrAluno);
		
		return tutorSelecionado;
	}
	
	/**
	 * Retorna a matrícula do tutor que possui melhor proficiencia na disciplina.
	 * Caso exista empate entre tutores, é retorna o tutor mais antigo no sistema.
	 * Caso nenhum tutor seja selecionado, é retornado null. 
	 * 
	 * @param disciplina Represetação da disciplina, no formato "inglês"
	 * @return Representação textual da matrícula do tutor. Null caso nenhum tutor seja selecionado.
	 */
	private String selecionaTutorAjudaOnline(String matrAluno, String disciplina, List<Aluno> tutores) {
		List<Aluno> alunosTutores = new ArrayList<Aluno>();
		for (Aluno aluno : tutores) {
			if(aluno.getTutor().getDisciplinas().containsKey(disciplina)) {
				alunosTutores.add(aluno);
			}
		}
		
		String tutorSelecionado = this.selecionaMelhorTutor(alunosTutores, matrAluno);
		
		return tutorSelecionado;
	}
	
	/**
	 * Registra uma ajuda presencial no sistema.
	 * 
	 * @param matrAluno Represetanção da matrícula do aluno, no formato String "123456"
	 * @param disciplina Representação da disciplina, no formato String "inglês"
	 * @param horario Representação do horário, no formato "12:12"
	 * @param dia Representação do dia, no formato "seg"
	 * @param local Representação do local, no formato "ufcg"
	 * @return O id da ajuda cadastrada
	 */
	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia, String local, List<Aluno> tutores, Set<String> alunos) {
		Validador.validadorString(matrAluno, "Erro no pedido de ajuda presencial: matricula de aluno nao pode ser vazio ou em branco");
		Validador.validadorString(disciplina, "Erro no pedido de ajuda presencial: disciplina nao pode ser vazio ou em branco");
		Validador.validadorString(horario, "Erro no pedido de ajuda presencial: horario nao pode ser vazio ou em branco");
		Validador.validadorHorario(horario, "Erro no pedido ajuda presencial: horario invalido");
		Validador.validadorString(dia, "Erro no pedido de ajuda presencial: dia nao pode ser vazio ou em branco");
		Validador.validadorString(local, "Erro no pedido de ajuda presencial: local de interesse nao pode ser vazio ou em branco");	
		Validador.validadorSetNotContains(matrAluno, alunos, "Erro no pedido de ajuda presencial: matricula de aluno nao cadastrada");

		String tutor = this.selecionaTutorAjudaPresencial(matrAluno, disciplina, horario, dia, local, tutores);
		Validador.validadorString(tutor, "Erro no pedido de ajuda presencial: tutor nao encontrado");
		int id = this.ajudas.size() + 1;
		Ajuda ajuda = new AjudaPresencial(id, matrAluno, tutor, disciplina, horario, dia, local);
		this.ajudas.add(ajuda);
		return id;
	}
	
	/**
	 * Registra uma ajuda online no sistema.
	 * 
	 * @param matriculaAluno Representação da matrícula do aluno solicitando, no formato String "123456"
	 * @param disciplina Representação da disciplina solicitada, no formato String "Grafos"
	 * @return ID de cadastro da ajuda no formato inteiro "12"
	 */
	public int pedirAjudaOnline(String matriculaAluno, String disciplina, List<Aluno> tutores, Set<String> alunos){
		Validador.validadorString(matriculaAluno, "Erro no pedido de ajuda online: matricula de aluno nao pode ser vazio ou em branco");
		Validador.validadorString(disciplina, "Erro no pedido de ajuda online: disciplina nao pode ser vazio ou em branco");
		Validador.validadorSetNotContains(matriculaAluno, alunos, "Erro no pedido de ajuda online: matricula de aluno nao cadastrada");
		
		String tutor = this.selecionaTutorAjudaOnline(matriculaAluno, disciplina, tutores);
		Validador.validadorString(tutor, "Erro no pedido de ajuda online: tutor nao encontrado");
		int id = this.ajudas.size() + 1;
		Ajuda ajuda = new AjudaOnline(id, matriculaAluno, tutor, disciplina);
		this.ajudas.add(ajuda);
		return id;
	}

	/**
	 * Retorna a descrição do tutor da ajuda.
	 * <ul><li>Para ajudas online:</li>
	 * <tt style="font-size: 14px;">"Tutor - 12345, disciplina - ATAL"</tt>
	 * <li>Para ajudas presenciais:</li>
	 * <tt style="font-size: 14px;">"Tutor - 12345, horario - 14:00, dia - seg, disciplina - ATAL"</tt>
	 * </ul>
	 * @param idAjuda ID da ajuda, no formato inteiro
	 * @return Representação textual da descrição do tutor da ajuda.
	 */
	public String pegarTutor(int idAjuda) {
		Validador.validadorPositivo(idAjuda, "Erro ao tentar recuperar tutor : id nao pode menor que zero ");
		Validador.listConteins(this.ajudas, idAjuda  - 1, "Erro ao tentar recuperar tutor : id nao encontrado ");
		
		Ajuda ajuda = this.ajudas.get(idAjuda - 1);
			
		return ajuda.getInfoTutor();
	}

	/**
	 * Recupera um atributo de uma ajuda específica.<br>
	 * Podem ser recuperados os seguintes atribuitos:
	 * <ul><li>aluno</li>
	 * <li>tutor</li>
	 * <li>disciplina</li>
	 * <li>dia (somente ajudas presenciais)</li>
	 * <li>horario (somente ajudas presenciais)</li>
	 * <li>localInteresse (somente ajudas presenciais)</li></ul>
	 * 
	 * @param idAjuda idAjuda ID da ajuda, no formato inteiro
	 * @param atributo Representação textual de um atributo ajuda.
	 * @return Representação textual do atribuito solicitado.
	 */
	public String getInfoAjuda(int idAjuda, String atributo) {
		Validador.validadorPositivo(idAjuda, "Erro ao tentar recuperar info da ajuda : id nao pode menor que zero ");
		Validador.listConteins(this.ajudas, idAjuda - 1, "Erro ao tentar recuperar info da ajuda : id nao encontrado ");
		
		return this.ajudas.get(idAjuda - 1).getInfoAjuda(atributo);
	}
	

	public String avaliarTutor(int idAjuda){
		Validador.listConteins(this.ajudas, idAjuda - 1, "Erro na avaliacao de tutor: id nao encontrado ");
		if(this.ajudas.get(idAjuda - 1).getAvaliacao()) return null;
		this.ajudas.get(idAjuda - 1).checkAvaliacao();
		String matrTutor = this.ajudas.get(idAjuda - 1).getMatriclaTutor();
		return matrTutor;
	}

	public void salvar(String path) {
		try {
		FileOutputStream fos = new FileOutputStream(path);
		ObjectOutputStream oot = new ObjectOutputStream(fos);
		
		oot.writeObject(this.ajudas);
		oot.close();
		} catch (IOException ioe) {}
	}

	@SuppressWarnings("unchecked")
	public void carregar(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			this.ajudas = (List<Ajuda>) ois.readObject();
			ois.close();
		} catch (Exception e) {} 
	}

	public void limpar(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			this.ajudas = new ArrayList<Ajuda>();
			fos.close();
		} catch (IOException ioe) {}
	}
}
