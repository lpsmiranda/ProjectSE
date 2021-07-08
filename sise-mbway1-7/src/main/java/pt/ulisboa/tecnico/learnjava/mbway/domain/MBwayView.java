package pt.ulisboa.tecnico.learnjava.mbway.domain;

import java.util.Scanner;

import pt.ulisboa.tecnico.learnjava.mbway.controllers.AssociateMbwayController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.ConfirmMbwayController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.MbwayTransferController;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.PhoneNumberException;

public class MBwayView {
	AssociateMbwayController associateMbway;
	ConfirmMbwayController confirmMbway;
	MbwayTransferController mbwayTransfer;
	Scanner inputs = new Scanner(System.in);
	
	public void associateMbwayOutputView(AssociateMbwayController associateMbway) {
		System.out.println( "Code: " + associateMbway.getCode() + " (don't share with anyone)");
	}
	
	public void confirmMbwayOutputView(ConfirmMbwayController confirmMbway) throws PhoneNumberException {
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
		System.out.println("Add a new member to the group? [yes | no] ");
	}
	
	public void newMemberPN() {
		System.out.println("Phone Number: ");
	}
	
	public void newMemberA() {
		System.out.println("Add amount to split:");
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
