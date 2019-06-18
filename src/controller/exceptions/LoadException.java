package controller.exceptions;

public class LoadException extends Exception{

	public String loadfile;
	
	public LoadException(String loadfile) {
		this.loadfile = loadfile;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 44409404075822301L;

}
