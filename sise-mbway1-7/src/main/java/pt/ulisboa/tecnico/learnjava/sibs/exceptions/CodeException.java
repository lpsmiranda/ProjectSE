package pt.ulisboa.tecnico.learnjava.sibs.exceptions;

public class CodeException extends Exception {
	
	public CodeException (String msg) {
		super(msg);
	}
	
	public CodeException (Exception msg) {
		super(msg);
	}

}
