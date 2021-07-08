package pt.ulisboa.tecnico.learnjava.mbway.domain;

import java.util.Hashtable;
import java.util.Scanner;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.MbwaySplitInsuranceController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.AssociateMbwayController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.ConfirmMbwayController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.MbwayTransferController;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.CodeException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.IbanException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.PhoneNumberException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class MBway {
	public static Hashtable<String, String> mbway = new Hashtable<String, String>();
	public static Hashtable<String, Integer> mbwayCode = new Hashtable<String, Integer>();
	public static Hashtable<String, String> mbwayTemp = new Hashtable<String, String>();
	

	public static void main(String[] args) throws Exception {
		Scanner inputs = new Scanner(System.in);
	
		MBwayView views = new MBwayView();
		Bank bank1 = new Bank("CGD");
		Bank bank2 = new Bank("BPI");
		Services services = new Services();
		
		Client client1 = new Client(bank1, "Maria", "Soares", "123456789", "912346987", "Rua Alves Redol", 25);
		Client client2 = new Client(bank2, "Clara", "Rodrigues", "987654321", "917895234", "Rua Redol", 30);
		Client client3 = new Client(bank2, "Marco", "Andrade", "345123678", "967896734", "Rua da Figueira", 40);
		Client client4 = new Client(bank1, "Paulo", "Ribeiro", "246589324", "965200147", "Rua das Flores", 20);
		
		
		String iban1 = bank1.createAccount(AccountType.CHECKING, client1, 1000, 0);//CGDCK1
		String iban2 = bank2.createAccount(AccountType.CHECKING, client2, 5000, 0);//BPICK2
		String iban3 = bank2.createAccount(AccountType.CHECKING, client3, 2500, 0);//BPICK3
		String iban4 = bank1.createAccount(AccountType.SALARY, client4, 300, 0);//CGDSL4
		
		mbway.put(client2.getPhoneNumber(), iban2);
		mbway.put(client3.getPhoneNumber(), iban3);
		mbway.put(client4.getPhoneNumber(), iban4);
		
		System.out.println("Insert operation: ");
		String operation = inputs.nextLine();
		
		while(!operation.equals("exit")) {
			
			if(operation.equals("associate-mbway")) {
				System.out.println("Phone number: ");
				String phoneNumber = inputs.nextLine();
				
				System.out.println("IBAN: ");
				String IBAN = inputs.nextLine();
				
				try {
					AssociateMbwayController associateMbway = new AssociateMbwayController(phoneNumber, IBAN);
					associateMbway.process();
					views.associateMbwayOutputView(associateMbway);
					
				}catch (IbanException msg) {
					views.IbanError(msg.getMessage());
					
				}catch (PhoneNumberException message) {
					views.phoneNumberError(message.getMessage());
				}

				
			}else if (operation.equals("confirm-mbway")) {
				System.out.println("Phone Number: ");
				String phoneNumberCMBway = inputs.nextLine();
				
				System.out.println("Insert code: ");
				String confirmationCode = inputs.nextLine();
				
				try {
					ConfirmMbwayController confirmMbway = new ConfirmMbwayController(phoneNumberCMBway, 
																					confirmationCode);
					confirmMbway.process();
					views.confirmMbwayOutputView(confirmMbway);
					
				}catch (PhoneNumberException msg) {
					views.phoneNumberError(msg.getMessage());
					
				}catch (CodeException msg) {
					views.codeError(msg.getMessage());
				}

				System.out.println(mbway);

				
			}else if (operation.equals("mbway-transfer")) {
				System.out.println(mbway);
				System.out.println("Insert phone number source: ");
				String sourcePhoneNumber = inputs.nextLine();
				
				System.out.println("Insert phone number target: ");
				String targetPhoneNumber = inputs.nextLine();
				
				System.out.println("Insert amount to tranfer: ");
				String amountTransfer = inputs.nextLine();
				
				try {
				MbwayTransferController mbwayTransfer = new MbwayTransferController(sourcePhoneNumber,
																					targetPhoneNumber, 
																					Integer.parseInt(amountTransfer));
				mbwayTransfer.process();
				views.mbwayTransferControllerView();
				
				}catch(PhoneNumberException msg) {
					views.phoneNumberError(msg.getMessage());
					
				}catch(SibsException msg) {
					views.notEnoughMoneyError(msg.getMessage());
				}
				
			}else if (operation.equals("mbway-split-insurance")) {
				System.out.println(mbway);
				System.out.println("Insert the group size: ");
				String numberFamilyMemb = inputs.nextLine();
				
				System.out.println("Insert insurance total amount: ");
				String amountInsurance = inputs.nextLine();
				try {
					MbwaySplitInsuranceController mbwaySIC = new MbwaySplitInsuranceController(Integer.parseInt(numberFamilyMemb),
																								Integer.parseInt(amountInsurance));
					mbwaySIC.process();
					views.mbwaySplitInsuranceControllerView();
					
				}catch(PhoneNumberException msg) {
					views.phoneNumberError(msg.getMessage());
					
				}catch (SibsException msg) {
					views.IbanError(msg.getMessage());
				}
				
			}
			
			System.out.println("\nInsert operation: ");
			operation = inputs.nextLine();
		}
		
		if(operation.equals("exit")) {
			views.exitView();
		}
			
	}
	
}
	
	
	
	


