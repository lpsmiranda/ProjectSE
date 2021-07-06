package pt.ulisboa.tecnico.learnjava.mbway.cli;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class Client_MBway {
	Client client;
	Bank bank;
	Account account;
	Integer VALUE;
	
	public Client_MBway(Client client, Bank bank, Account account) {
		this.client = client;
		this.account = account;
		this.bank = bank;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public String getClientPhoneNumber() {
		return this.client.getPhoneNumber();
	}
	
	public Account getAccount() {
		return this.account;
	}
	
	public Bank getBank() {
		return this.bank;
	}
}
