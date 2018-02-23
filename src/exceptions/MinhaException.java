/**
 * 
 */
/**
 * @author arthu
 *
 */
package exceptions;

public class MinhaException extends Exception{
   
	private String msg;
	
    public MinhaException(String msg){
      super(msg);
      this.msg = msg;
    }
    public String getMessage(){
      return msg;
    }
  }