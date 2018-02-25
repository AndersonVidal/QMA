package model;

public class Tutor {
	private Aluno aluno;
	private String disciplina;
	private int proeficiencia;
	private int nota;
	private double dinheiro;
	
	public Tutor(Aluno aluno, String disciplina, int proeficiencia) {
		this.aluno = aluno;
		this.disciplina = disciplina;
		this.proeficiencia = proeficiencia;
		this.nota = 4;
		this.dinheiro = 0;
	}
	
	public Aluno getAluno() {
		return this.aluno;
	}
	
	public String getDisciplina() {
		return this.disciplina;
	}
	
	public int getProeficiencia() {
		return this.proeficiencia;
	}
	
	public int getNota() {
		return this.nota;
	}
	
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	public double getDinheiro() {
		return this.dinheiro;
	}
	
	public void adicionaDinheiro(double dinheiro) {
		this.dinheiro += dinheiro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tutor other = (Tutor) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		return true;
	}

	public String toString() {
		return this.aluno.toString();
	}
	
}