package utils;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;

/**
 * Representacao de um validador de parametros.
 * 
 * @author Anderson Vasconcelos
 *
 */
public class Validador {
	
	private final static String EMAIL_REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
	
	/**
	 * Valida uma String em termos de ser vazia ou nula.
	 * 
	 * @exception NullPointerException String Null
	 * @exception IllegalArgumentException String vazia
	 * 
	 * @param string Objeto do tipo String a ser validado
	 * @param e Mensagem de erro padrao.
	 */
	public static void validadorString(String string, String e){
		if(string == null) throw new NullPointerException(e);
		if(string.trim().isEmpty()) throw new IllegalArgumentException(e);
	}
	
	/**
	 * Valida uma String em termos de ser vazia ou nula.
	 * 
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
	 * Valida um inteiro, em termos de ser menor que 0.
	 * 
	 * @exception IllegalArgumentException inteiro nao positivo.
	 * 
	 * @param numero Objeto do tipo int a ser validado.
	 * @param e Mensagem de erro padrao.
	 */
	public static void validadorPositivo(double numero, String e) {
		if(numero <= 0) throw new IllegalArgumentException(e);
	}
	
	/**
	 * Valida a expressao textual de um email.
	 * 
 	 * @exception IllegalArgumentException Email invalido.
	 * 
	 * @param email Representacao textual de um email
	 * @param e Mensagem de erro padrao
	 */
	public static void validadorEmail(String email, String e){
		validadorObjeto(email, e);
		Pattern p = Pattern.compile(EMAIL_REGEX);
		Matcher m = p.matcher(email);
	
		if(!m.find()) throw new IllegalArgumentException(e);
	}
	
	/**
	 * Valida nao existencia de um determinada key em um Map
	 * 
	 * @exception StringIndexOutOfBoundsException Caso objeto ja exista no Map
	 * 
	 * @param o Representacao do objeto
	 * @param c Representacao do Map
	 * @param e Mensagem de erro padrao
	 */
	public static void validadorMapKeyConteins(Object o, Map<?, ?> c, String e){
		if(c.containsKey(o)) throw new StringIndexOutOfBoundsException(e);
	}
	
	/**
	 * Valida existencia de um determinada key em um Map
	 * 
	 * @exception StringIndexOutOfBoundsException Caso objeto nao exista no Map
	 * 
	 * @param o Representacao do objeto
	 * @param c Representacao do Map
	 * @param e Mensagem de erro padrao
	 */
	public static void validadorMapKeyNotConteins(Object o, Map<?, ?> c, String e){
		if(!c.containsKey(o)) throw new IllegalArgumentException(e);
	}
	
	/**
	 * Valida a existencia de um determinado objeto em um conjunto de valores do Map.
	 * 
	 * @exception StringIndexOutOfBoundsException(e) Caso objeto nao exista nos valores do Map.
	 * 
	 * @param o Representacao do objeto
	 * @param c Representacao do Map
	 * @param e Mensagem de erro padrao
	 */
	public static void validadorMapValueConteins(Object o, Map<?, ?> c, String e) {
		if(!c.values().contains(o)) throw new StringIndexOutOfBoundsException(e);
	}
	
	/**
	 * Valida a existencia de um objeto.
	 * 
	 * @exception NullPointerException Caso objeto seja null
	 * 
	 * @param o Representacao do objeto
	 * @param e Mensagem de erro padrao
	 */
	public static void validadorObjeto(Object o, String e) {
		if(o == null) throw new NullPointerException(e);
	}

	/**
	 * Valida a nao existencia de um objeto em um Set.
	 * 
	 * @param o Representacao do objeto
	 * @param s Representacao de um Set
	 * @param e Mensagem de erro padrao
	 */
	public static void validadorSetContains(Object o, Set<?> s, String e) {
		if(s.contains(o)) { throw new IllegalArgumentException(e); }
	}

	/**
	 * Valida se o formato do horario eh "HH:mm".
	 * 
	 * @param horario o horario a ser validado
	 * @param iae a mensagem de excecao
	 */
	public static void validadorHorario(String horario, String iae) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.setLenient(false);
		try{
			sdf.parse(horario);
		} catch(Exception e){
			throw new IllegalArgumentException(iae);
		}
	}

	/**
	 * Valida se o numero informado esta dentro de um intervalo fechado.
	 * 
	 * @param valor o valor a ser validado
	 * @param left o intervalo a esquerda
	 * @param right o intervalo a direita
	 * @param e a mensagem de excecao
	 */
	public static void validadorIntervalo(int valor, int left, int right, String e) {
		if (valor < left || valor > right) {
			throw new IllegalArgumentException(e);
		}
	}
	
}
