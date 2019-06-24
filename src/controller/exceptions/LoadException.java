package controller.exceptions;

/**
 * Klasse erzeugt eine Exception, die benötigt wird, wenn über das Netzwerk der String "load" 
 * gesendet wird. Der Parameter loadfile wird gespeichert.
 * @author Vanessa
 */
public class LoadException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 44409404075822301L;

	public String loadfile;
	
	public LoadException(String loadfile) {
		this.loadfile = loadfile;
	}

	
}
