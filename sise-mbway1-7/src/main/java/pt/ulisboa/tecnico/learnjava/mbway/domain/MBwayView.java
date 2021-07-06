package pt.ulisboa.tecnico.learnjava.mbway.domain;

import java.util.Scanner;

import pt.ulisboa.tecnico.learnjava.mbway.controllers.associateMbwayController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.confirmMbwayController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.mbwayTransferController;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.PhoneNumberException;

public class MBwayView {
	associateMbwayController associateMbway;
	confirmMbwayController confirmMbway;
	mbwayTransferController mbwayTransfer;
	Scanner inputs = new Scanner(System.in);
	
	public void associateMbwayOutputView(associateMbwayController associateMbway) {
		System.out.println( "Code: " + associateMbway.getCode() + " (don't share with anyone)");
	}
	
	public void confirmMbwayOutputView(confirmMbwayController confirmMbway) throws PhoneNumberException {
			System.out.println("MBWAY association confirmed successfully!");
	}
	
	public void IbanError(String message) {
		System.out.println(message);
	}

	public void phoneNumberError(String message) {
		System.out.println(message);
	}
	
	public void codeError(String message) {
		System.out.println(message);
	}
	
	public void mbwayTransferControllerView() throws Exception {
		System.out.println("Transfer performed successfully!");
	}
	
	public void notEnoughMoneyError(String message) {
		System.out.println(message);
	}
	
	public void newMemberQ() {
		System.out.println("Adicionar novo elemento ao grupo? [sim | nao] ");
	}
	
	public void newMemberPN() {
		System.out.println("Numero de telemóvel: ");
	}
	
	public void newMemberA() {
		System.out.println("Valor a contribuir:");
	}
	
	public void EnoughMembers() {
		System.out.println("Oh no! Too many family members.");
	}
	
	public void mbwaySplitInsuranceControllerView() {
		System.out.println("Insurance paid successfully!");
	}
	
	public void exitView() {
		System.out.println("MBWAY terminated with success.");
	}
	
	
	
}
