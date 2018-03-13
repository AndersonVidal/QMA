package controller;

import utils.Validador;

public class CaixaSistema {
	
	private double dinheiroSistema;
	
	public CaixaSistema() {
		this.dinheiroSistema = 0;
	}

	public double getDinheiroSistema() {
		return dinheiroSistema;
	}
	
	public void addDinheiroSistema(double centavos) {
		Validador.validadorPositivo(centavos, "Erro ao adicionar dinheiro: valor nao pode ser menor ou igual a 0");
		this.dinheiroSistema += centavos; 
	}
}