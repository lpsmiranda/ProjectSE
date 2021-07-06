package pt.ulisboa.tecnico.learnjava.mbway.controllers;
import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.PhoneNumberException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class mbwayTransferController {
	MBway mbway;
	String phoneNumberSource;
	String phoneNumberTarget;
	Services services = new Services();
	Sibs sibs = new Sibs(100,services);
	int amountTransfer;
	boolean source;
	boolean target;
	boolean amount;
	
	public mbwayTransferController(String phoneNumberSOURCE, String phoneNumberTARGET, int VALUE) {
		phoneNumberSource = phoneNumberSOURCE;
		phoneNumberTarget = phoneNumberTARGET;
		amountTransfer = VALUE;
	}
	
	public void process() throws Exception {
		//n.os existe na mbway?
		for (String elem : MBway.mbway.keySet()) {
			
			if(phoneNumberSource.equals(elem)) 
				source = true;
			
			if(phoneNumberTarget.equals(elem)) 
				target = true;
		}
		
		if(source == false)
			throw new PhoneNumberException("Wrong phone number source.");
		
		if(target == false)
			throw new PhoneNumberException("Wrong phone number target.");
		
		//verificar amount
		Account accountSource = services.getAccountByIban(MBway.mbway.get(phoneNumberSource));
		if(accountSource.getBalance() < amountTransfer) {
			throw new SibsException("Not enough money on the source account.");
		}
		
		System.out.println(accountSource.getBalance());
		sibs.transfer(MBway.mbway.get(phoneNumberSource), MBway.mbway.get(phoneNumberTarget), amountTransfer);
		System.out.println(accountSource.getBalance());
		
	}
}
