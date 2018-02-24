package view;

import controller.ControllerAlunos;

import easyaccept.EasyAccept;

public class Facade {
	
	public static void main(String[] args) { 
		args = new String[] { "view.Facade",
				"acceptance_tests/us1_test.txt"};
		EasyAccept.main(args);
	}
	
	private ControllerAlunos ctrlAlunos = new ControllerAlunos();
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		this.ctrlAlunos.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
	}
	
	public String recuperaAluno(String matricula) {
		return this.ctrlAlunos.recuperaAluno(matricula);
	}
	
	public String listarAlunos() {
		return this.ctrlAlunos.listarAlunos();
	}

	public String getInfoAluno(String matricula, String atributo) {
		return this.ctrlAlunos.getInfoAluno(matricula, atributo);
	}
}
