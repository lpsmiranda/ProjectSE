package pt.ulisboa.tecnico.learnjava.sibs.exceptions;

public class IbanException extends Exception{
	
	public IbanException(Exception message) {
		super(message);
	}
	
	public IbanException(String message) {
		super(message);
	}

}
