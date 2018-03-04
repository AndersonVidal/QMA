package model;

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

	@Override
	public String getHorario() {
		return null;
	}

	@Override
	public String getDia() {
		return null;
	}

	@Override
	public String getLocal() {
		return null;
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
