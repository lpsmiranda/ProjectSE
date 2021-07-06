package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pt.ulisboa.tecnico.learnjava.mbway.cli.Client_MBway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.CodeException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.PhoneNumberException;

public class confirmMbwayController {
	String phoneNumber;
	String IBAN;
	associateMbwayController associateMbway;
	Client_MBway clientmbway;
	String code;
	
	public confirmMbwayController(String phoneNumber, String code) {
		this.phoneNumber = phoneNumber;
		this.code = code;
	}
	
	
	public void process() throws PhoneNumberException, CodeException {
		//verificar se phone number tem 9 digitos
		Pattern patternPhoneNumber = Pattern.compile("^\\d{9}$");
		Matcher  confirmPhoneNumber = patternPhoneNumber.matcher(phoneNumber);
		boolean match = confirmPhoneNumber.find();
		if(match == false) throw new PhoneNumberException("Número de telemóvel inválido");//Verifica se numero introduzido é valido (tem 9 digitos)
		
		
		for (String elem : MBway.mbwayCode.keySet()) {
			if (Integer.parseInt(code) == MBway.mbwayCode.get(elem)) {
				for (String elem2 : MBway.mbwayTemp.keySet()) {
					if(phoneNumber.equals(elem2)) {
						MBway.mbway.put(elem2,MBway.mbwayTemp.get(elem2));
					}else {
						throw new PhoneNumberException("Número de telemóvel inválido"); //Se o numero não tiver feito o associate-mbway
					}
				}
			}else {
				throw new CodeException("Wrong confirmation code. Try association again.");// Se o código introduzido está incorreto ou inexistente
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
