package model;

import utils.Validador;

/**
 * Representação de uma ajuda online.
 * 
 * @author Anderson Vidal
 *
 */
public class AjudaOnline extends Ajuda {

	public AjudaOnline(int id, String matrAluno, String matrTutor, String disciplina) {
		super(id, matrAluno, matrTutor, disciplina);
	}
	
	/**
	 * Recupera informação de ajuda através de um atributo
	 * 
	 * @param atributo Representação de um atributo de ajuda, no formato "tutor"
	 * @return Informação da ajuda
	 */
	@Override
	public String getInfoAjuda(String atributo) {
		Validador.validadorString(atributo, "Erro ao tentar recuperar info da ajuda : atributo nao pode ser vazio ou em branco");
		
		switch(atributo){
		case "disciplina":
			return this.getDisciplina();
		case "tutor":
			return this.getMatriclaTutor();
		case "aluno":
			return this.getMatriculaAluno();
		default:
			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : atributo nao encontrado");
		}
	}
	
	/**
	 * Retorna a especificação do tutor, no formato 
	 * "Tutor - 12345, disicplina - Inglês"
	 * 
	 * @return Representação textual da descrição do tutor da ajuda.
	 */
	@Override
	public String getInfoTutor() {
		return "Tutor - " + getMatriclaTutor() + ", disciplina - " + getDisciplina();
	}
	
}
