package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

	public void salvar(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oot = new ObjectOutputStream(fos);
			
			oot.writeObject(this.dinheiroSistema);
			oot.close();
		} catch (IOException ioe) {}
	}

	public void carregar(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			this.dinheiroSistema = (int) ois.readObject();
			ois.close();
		} catch (Exception ioe) {}
	}

	public void limpar(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			this.dinheiroSistema = 0;
			fos.close();
		} catch (IOException ioe) {}
	}
}