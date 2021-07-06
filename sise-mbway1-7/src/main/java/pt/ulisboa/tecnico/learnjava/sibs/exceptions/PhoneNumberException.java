package pt.ulisboa.tecnico.learnjava.sibs.exceptions;

public class PhoneNumberException extends Exception {
	
	public PhoneNumberException(String message) {
		super(message);
	}
	
	public PhoneNumberException(Exception message) {
		super(message);
	}
}
