package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import java.util.Hashtable;
import java.util.Scanner;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBwayView;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.FamilyMembersException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.PhoneNumberException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class MbwaySplitInsuranceController {
	private Hashtable<String, Integer> familyMembers = new Hashtable<String, Integer>();
	Integer numberOfMembers;
	Integer insuranceAmount;
	Services services = new Services();
	MBwayView views = new MBwayView();
	Scanner elements = new Scanner(System.in);
	
	public MbwaySplitInsuranceController(Integer numMembers, Integer amount) {
		numberOfMembers = numMembers;
		insuranceAmount = amount;
	}
			
	public void createFamilyMemberTable() throws Exception {
			int countMembers = 0;
			views.newMemberQ();
			String question = elements.nextLine();
			
			if (question.equals("yes")) {
				while(!question.equals("no")) {	
					views.newMemberPN();
					String pn = elements.nextLine();
					int aux = 0;
					String amt = null;
					for (String elem : MBway.mbway.keySet()) {
						if(pn.equals(elem)) {  		//Só quando o numero tem mbway é que pode ser inserido
							aux++;
							views.newMemberA();
							amt = elements.nextLine();
						}
					}
					if(aux == 0) throw new PhoneNumberException("Friend <PHONE_NUMBER> is not registered.");
				
					Account account = services.getAccountByIban(MBway.mbway.get(pn));
					if(account.getBalance() < Integer.parseInt(amt)) {
						throw new SibsException("Oh no! One family member doesn’t have money to pay!");
					}
					
					if(Integer.parseInt(amt) <= 0) {
						throw new SibsException("Amount introduced is not allowed!");
					}
					
					familyMembers.put(pn, Integer.parseInt(amt));
					countMembers++;
					views.newMemberQ();
					question = elements.nextLine();
			}
		}
			if (countMembers > numberOfMembers) throw new FamilyMembersException("Oh no! Too many family members.");
			else if (countMembers < numberOfMembers) throw new FamilyMembersException("Oh no! One family member is missing.");
			
	}
	
	public void checkAmountTotal() throws SibsException {
		int sum = 0;
		for (String elem : familyMembers.keySet()) {
			sum += familyMembers.get(elem);
		}
		
		if (sum < insuranceAmount) throw new SibsException("Amount introduced by family members is not sufficient!");
		else if (sum > insuranceAmount) throw new SibsException("Something is wrong. Is the insurance amount right?");

	}
	
	public void process() throws Exception {
		createFamilyMemberTable();
		checkAmountTotal();
		
	}

}
