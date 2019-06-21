package controller.exceptions;



public class SaveException extends Exception {
	
	public String savefile;
	
	
	public SaveException(String savefile) {
		this.savefile = savefile;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 7064876656323381448L;

	
}
