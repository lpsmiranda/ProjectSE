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

	public associateMbwayController(String phoneNumber, String iBAN) throws Exception {
		this.phoneNumber = phoneNumber;
		IBAN = iBAN;
		
		//Verificar se Numero telemovel ja tem conta mbway
		for (String elem : MBway.mbway.keySet()) {
			if(phoneNumber.equals(elem)) throw new PhoneNumberException("Número de telemóvel já tem associada conta MBway");
		}
		
		//Verifica se existe banco
		Bank bank = Bank.getBankByCode(IBAN.substring(0, 3));
		if (bank == null) throw new IbanException("Banco não existente");
		
		//Confirmar se Account existe
		Account account = services.getAccountByIban(IBAN);
		if (account == null) throw new IbanException("Conta inválido");
	
		
		//Confirmar se numero telemovel tem 9 digitos
		Pattern patternPhoneNumber = Pattern.compile("^\\d{9}$");
		Matcher  confirmPhoneNumber = patternPhoneNumber.matcher(phoneNumber);
		boolean match = confirmPhoneNumber.find();
		if(match == false) throw new PhoneNumberException("Número de telemóvel inválido");
		
		//Verificação Numero telemovel
		Client client = account.getClient();
		if (!client.getPhoneNumber().equals(phoneNumber)) throw new PhoneNumberException("Número de telemóvel inválido");
		
		MBway.mbwayTemp.put(phoneNumber, iBAN);
		MBway.mbwayCode.put(phoneNumber, code);
	}
	//necessário para a view
	public int getCode() {
		return code;
	}	

}
