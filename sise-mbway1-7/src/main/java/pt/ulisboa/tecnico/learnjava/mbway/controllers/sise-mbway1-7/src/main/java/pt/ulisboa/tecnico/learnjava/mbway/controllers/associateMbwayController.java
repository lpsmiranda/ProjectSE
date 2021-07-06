package pt.ulisboa.tecnico.learnjava.mbway.controllers;

//import java.util.Hashtable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBwayView;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.IbanException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.PhoneNumberException;

public class associateMbwayController {
	String phoneNumber;
	String IBAN;
	Services services = new Services();
	MBwayView view;
	Client client;
	
	
	//public Hashtable<String, Integer> confirmMbwayTab = new Hashtable<String, Integer>();

	int min = 100000;
	int max = 999999;
	Random anInteger = new Random();
	int code = anInteger.nextInt((max - min) + 1) + min;
	
	public void confirmPhoneNumberDigits() throws PhoneNumberException {
		Pattern patternPhoneNumber = Pattern.compile("^\\d{9}$");
		Matcher  confirmPhoneNumber = patternPhoneNumber.matcher(phoneNumber);
		boolean match = confirmPhoneNumber.find();
		if(match == false) throw new PhoneNumberException("Invalid Phone Number.");
	}
	
	public void confirmPhoneNumber() throws PhoneNumberException {
		for (String elem : MBway.mbway.keySet()) {
			if(phoneNumber.equals(elem)) throw new PhoneNumberException("The number that you trying to add is already in use.");
		}
	}
	
	public void confirmBank() throws IbanException {
		Bank bank = Bank.getBankByCode(IBAN.substring(0, 3));
		if (bank == null) throw new IbanException("Unknown Bank.");
	}
	
	public associateMbwayController(String phoneNumber, String iBAN) throws Exception {
		this.phoneNumber = phoneNumber;
		IBAN = iBAN;
		
		confirmPhoneNumberDigits(); //Confirmar se numero telemovel tem 9 digitos
		confirmPhoneNumber(); //Verificar se Numero telemovel ja tem conta mbway
		confirmBank(); //Verifica se existe banco
		
		//Confirmar se Account existe
		Account account = services.getAccountByIban(IBAN);
		if (account == null) throw new IbanException("Invalid bank account.");
	
		//Verificação Numero telemovel
		Client client = account.getClient();
		if (!client.getPhoneNumber().equals(phoneNumber)) throw new PhoneNumberException("Invalid Phone Number.");
		
		MBway.mbwayTemp.put(phoneNumber, iBAN);
		MBway.mbwayCode.put(phoneNumber, code);
	}
	
	//necessário para a view
	public int getCode() {
		return code;
	}	

}
