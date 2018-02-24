/**
 * Classe que abrigará os metodos responsaveis pela validação tanto de email como de nome
 * @author Anderson Vasconcelos
 */
package utils;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {
	
	public static void validadorString(String string, String e){
		if(string == null) throw new NullPointerException(e);
		if(string.trim().isEmpty()) throw new IllegalArgumentException(e);
	}
	public static void validadorString(String string, String iae, String npe){
		if(string == null) throw new NullPointerException(npe);
		if(string.trim().isEmpty()) throw new IllegalArgumentException(iae);
	}
	
	public static void validadorEmail(String email, String e){
		Pattern p = Pattern.compile("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");
		Matcher m = p.matcher(email);
	
		if(!m.find()) throw new IllegalArgumentException(e);
	}
	
	public static void validadorSetCoteins(Object o, Set<?> c, String e){
		if(c.contains(o)) throw new StringIndexOutOfBoundsException(e);
	}
	
	public static void validadorObjeto(Object o, String e) {
		if(o == null) throw new NullPointerException(e);
	}
}
