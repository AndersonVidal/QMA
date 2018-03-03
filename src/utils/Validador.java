package utils;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;

/**
 * Representação de um validador de parametros.
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
	 * @param e Mensagem de erro padrão.
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
	 * Valida a expressão textual de um email.
	 * 
 	 * @exception IllegalArgumentException Email invalido.
	 * 
	 * @param email Representação textual de um email
	 * @param e Mensagem de erro padrão
	 */
	public static void validadorEmail(String email, String e){
		validadorObjeto(email, e);
		Pattern p = Pattern.compile(EMAIL_REGEX);
		Matcher m = p.matcher(email);
	
		if(!m.find()) throw new IllegalArgumentException(e);
	}
	
	/**
	 * Valida nao existência de um determinada key em um Map
	 * 
	 * @exception StringIndexOutOfBoundsException Caso objeto já exista no Map
	 * 
	 * @param o Representação do objeto
	 * @param c Representação do Map
	 * @param e Mensagem de erro padrão
	 */
	public static void validadorMapKeyConteins(Object o, Map<?, ?> c, String e){
		if(c.containsKey(o)) throw new StringIndexOutOfBoundsException(e);
	}
	
	/**
	 * Valida existência de um determinada key em um Map
	 * 
	 * @exception StringIndexOutOfBoundsException Caso objeto não exista no Map
	 * 
	 * @param o Representação do objeto
	 * @param c Representação do Map
	 * @param e Mensagem de erro padrão
	 */
	public static void validadorMapKeyNotConteins(Object o, Map<?, ?> c, String e){
		if(!c.containsKey(o)) throw new IllegalArgumentException(e);
	}
	
	/**
	 * Valida a existência de um determinado objeto em um conjunto de valores do Map.
	 * 
	 * @exception StringIndexOutOfBoundsException(e) Caso objeto não exista nos valores do Map.
	 * 
	 * @param o Representação do objeto
	 * @param c Representação do Map
	 * @param e Mensagem de erro padrão
	 */
	public static void validadorMapValueConteins(Object o, Map<?, ?> c, String e) {
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

	/**
	 * Valida a nao existencia de um objeto em um Set.
	 * 
	 * @param o Representação do objeto
	 * @param s Representação de um Set
	 * @param e Mensagem de erro padrão
	 */
	public static void validadorSetContains(Object o, Set<?> s, String e) {
		if(s.contains(o)) { throw new IllegalArgumentException(e); }
	}
	
	/**
	 * Valida a existencia de um objeto em um Set.
	 * 
	 * @param o Representação do objeto
	 * @param s Representação de um Set
	 * @param e Mensagem de erro padrão
	 */
	public static void validadorSetNotContains(Object o, Set<?> s, String e) {
		if(!s.contains(o)) { throw new IllegalArgumentException(e); }
	}

	/**
	 * Valida se o formato do horário eh "HH:mm".
	 * 
	 * @param horario Horario a ser validado
	 * @param iae Mensagem de exceção
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
	 * Valida se o número informado esta dentro de um intervalo fechado.
	 * 
	 * @param valor Valor a ser validado
	 * @param left Intervalo a esquerda
	 * @param right Intervalo a direita
	 * @param e Mensagem de excecao
	 */
	public static void validadorIntervalo(int valor, int left, int right, String e) {
		if (valor < left || valor > right) {
			throw new IllegalArgumentException(e);
		}
	}
	
}
