package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pt.ulisboa.tecnico.learnjava.mbway.cli.Client_MBway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.CodeException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.PhoneNumberException;

public class ConfirmMbwayController {
	String phoneNumber;
	String IBAN;
	AssociateMbwayController associateMbway;
	Client_MBway clientmbway;
	String code;
	
	public ConfirmMbwayController(String phoneNumber, String code) {
		this.phoneNumber = phoneNumber;
		this.code = code;
	}
	
	public void confirmPhoneNumberDigits() throws  CodeException {
		Pattern patternPhoneNumber = Pattern.compile("^\\d{9}$");
		Matcher  confirmPhoneNumber = patternPhoneNumber.matcher(phoneNumber);
		boolean match = confirmPhoneNumber.find();
		if(match == false) throw new CodeException("Invalid Phone Number.");
	}
	
	
	public void isMbwayCodeEmpty() throws PhoneNumberException {
		if (MBway.mbwayCode.size() == 0) throw new PhoneNumberException("Wrong confirmation code. Try association again.");
	}
	
	
	public void process() throws PhoneNumberException, CodeException {
		confirmPhoneNumberDigits();//Verifica se numero introduzido ? valido (tem 9 digitos)
		for (String elem : MBway.mbwayCode.keySet()) { //entra e corre lista PN 
			System.out.println("Entrei");
			if (Integer.parseInt(code) == MBway.mbwayCode.get(elem)) { // se code associado a PN for igual ao introd entra
				for (String elem2 : MBway.mbwayTemp.keySet()) {
					if(phoneNumber.equals(elem2)) {
						MBway.mbway.put(elem2,MBway.mbwayTemp.get(elem2));
					}else {
						throw new PhoneNumberException("Invalid Phone Number."); //Se o numero n?o tiver feito o associate-mbway
					}
				}
			}else {
				throw new CodeException("Wrong confirmation code. Try association again.");// Se o c?digo introduzido est? incorreto ou inexistente
			} } isMbwayCodeEmpty(); }
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
