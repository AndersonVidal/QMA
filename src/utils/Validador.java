package utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Representação de um validador de parametros.
 * 
 * @author Anderson Vasconcelos
 *
 */
public class Validador {
	
	/**
	 * @exception NullPointerException String Null
	 * @exception IllegalArgumentException String vazia
	 * 
	 * @param string Objeto do tipo String a ser validado
	 * @param e Mensagem de erro padrão.
	 */
	public static void validadorString(String string, String e){
		if(string == null) throw new NullPointerException(e);
		if(string.trim().isEmpty()) throw new IllegalArgumentException(e);
	}
	
	/**
	 * @exception NullPointerException String Null
	 * @exception IllegalArgumentException String vazia
	 * 
	 * @param string Objeto do tipo String a ser validado
	 * @param iae Mensagem de erro para String ilegal.
	 * @param npe Mensagem de erro para String Nula.
	 */
	public static void validadorString(String string, String iae, String npe){
		if(string == null) throw new NullPointerException(npe);
		if(string.trim().isEmpty()) throw new IllegalArgumentException(iae);
	}
	
	/**
	 * @exception IllegalArgumentException inteiro não positivo.
	 * 
	 * @param numero Objeto do tipo int a ser validado.
	 * @param e Mensagem de erro padrão.
	 */
	public static void validadorPositivo(int numero, String e) {
		if(numero <= 0) throw new IllegalArgumentException(e);
	}

	
	/**
	 * Valida a expressão textual de um email.
	 * 
 	 * @exception IllegalArgumentException Email inválido.
	 * 
	 * @param email Representação textual de um email
	 * @param e Mensagem de erro padrão
	 */
	public static void validadorEmail(String email, String e){
		validadorObjeto(email, e);
		Pattern p = Pattern.compile("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");
		Matcher m = p.matcher(email);
	
		if(!m.find()) throw new IllegalArgumentException(e);
	}
	
	/**
	 * Valida não existencia de um determinado objeto em um Set
	 * 
	 * @exception StringIndexOutOfBoundsException Caso objeto já exista no set
	 * 
	 * @param o Representação do objeto
	 * @param c Representação do Set
	 * @param e Mensagem de erro padrão
	 */
	public static void validadorMapKeyCoteins(Object o, Map<?, ?> c, String e){
		if(c.containsKey(o)) throw new StringIndexOutOfBoundsException(e);
	}
	
	/**
	 * Valida a existência de um determinado objeto em um conjunto.
	 * 
	 * @exception StringIndexOutOfBoundsException(e) Caso objeto não exista no conjunto.
	 * 
	 * @param o Representação do objeto
	 * @param c Representação do Set
	 * @param e Mensagem de erro padrão
	 */
	public static void validadorMapValueCoteins(Object o, Map<?, ?> c, String e) {
		if(!c.values().contains(o)) throw new StringIndexOutOfBoundsException(e);
	}
	
	/**
	 * Valida a existência de um objeto.
	 * 
	 * @exception NullPointerException Caso objeto seja null
	 * 
	 * @param o Representação do objeto
	 * @param e Mensagem de erro padrão
	 */
	public static void validadorObjeto(Object o, String e) {
		if(o == null) throw new NullPointerException(e);
	}
}
