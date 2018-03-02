package model;

import utils.Validador;

public class AjudaPresencial {

	private int id;
	private String matrAluno;
	private String matrTutor;
	private String disciplina;
	private String horario;
	private String dia;
	private String localInteresse;

	public AjudaPresencial(int id, String matrAluno, String matrTutor, String disciplina, String horario, String dia, String localInteresse) {
		Validador.validadorString(matrAluno, "Erro ao cadastrar ajuda presencial: matricula nao pode ser vazia ou nula");
		Validador.validadorString(disciplina, "Erro ao cadastrar ajuda presencial: disciplina nao pode ser vazia ou nula");
		Validador.validadorString(horario, "Erro ao cadastrar ajuda presencial: horario nao pode ser vazio ou nulo");
		Validador.validadorHorario(horario, "Erro ao cadastrar ajuda presencial: horario invalido");
		Validador.validadorString(dia, "Erro ao cadastrar ajuda presencial: dia nao pode ser vazio ou nulo");
		Validador.validadorString(localInteresse, "Erro ao cadastrar ajuda presencial: local nao pode ser vazio ou nulo");
		
		this.id = id;
		this.matrTutor = matrTutor;
		this.matrAluno = matrAluno;
		this.disciplina = disciplina;
		this.horario = horario;
		this.dia = dia;
		this.localInteresse = localInteresse;
	}

}
