package controller;

import utils.Validador;

public class CaixaSistema {
	
	private int dinheiroSistema;
	
	public CaixaSistema() {
		this.dinheiroSistema = 0;
	}

	public int getDinheiroSistema() {
		return dinheiroSistema;
	}
	
	public void addDinheiroSistema(int dinheiro) {
		Validador.validadorPositivo(dinheiro, "Erro ao adicionar dinheiro: valor nao pode ser menor ou igual a 0");
		this.dinheiroSistema += dinheiro; 
	}
}