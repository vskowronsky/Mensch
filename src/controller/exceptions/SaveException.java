package controller.exceptions;

/**
 * Klasse erzeugt eine Exception, die benötigt wird, wenn über das Netzwerk der String "save" 
 * gesendet wird. Der Parameter savefile wird gespeichert.
 * @author Vanessa
 */

public class SaveException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7064876656323381448L;

	public String savefile;
	
	public SaveException(String savefile) {
		this.savefile = savefile;
	}


	
	
}
