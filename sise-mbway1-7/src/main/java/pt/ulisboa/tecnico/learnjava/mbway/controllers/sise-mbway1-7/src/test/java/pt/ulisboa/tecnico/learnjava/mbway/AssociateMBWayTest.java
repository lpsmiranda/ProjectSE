package pt.ulisboa.tecnico.learnjava.mbway;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.associateMbwayController;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBwayView;


public class AssociateMBWayTest {
	private String phoneNumber;
	private String IBAN;
	private Services services;
	private Client client;
	private MBway mbway;
	
	
	@Before
	public void setUp() throws BankException, ClientException, AccountException {
		this.services = new Services();
		Bank bank1 = new Bank("CGD");
		Bank bank2 = new Bank("BPI");
		
		Client client1 = new Client(bank1, "Maria", "Soares", "123456789", "912346987", "Rua Alves Redol", 25);
		Client client2 = new Client(bank2, "Clara", "Rodrigues", "987654321", "917895234", "Rua Redol", 30);
		Client client3 = new Client(bank2, "Marco", "Andrade", "345123678", "967896734", "Rua da Figueira", 40);
		
		String iban1 = bank1.createAccount(AccountType.CHECKING, client1, 1000, 0);
		String iban2 = bank2.createAccount(AccountType.CHECKING, client2, 5000, 0);
		String iban3 = bank2.createAccount(AccountType.CHECKING, client3, 2500, 0);
		
	}

	@Test
	public void success() throws Exception {
		String phone = "912346987";
		String iban = "CGDCK1";
		associateMbwayController associateMBWay = new associateMbwayController(phone, iban);
	}
	
	
		
		
		
//		assertEquals("CGD", bank1.getCode());
//		assertEquals("CGDCK1", iban1);
//		assertEquals("BPICK2", iban2);
//		assertEquals("BPICK3", iban3);
		
		// TODO: call associate mbway controller with data
		//MBway mbwaydb = new MBway();
		//associateMbwayController task1 = new associateMbwayController ("912346987", iban1);
		// mbwaydb.check_if_client_exists("912346987") ... podem usar outra key que nao o numero de telefone (depende da forma como criarem a vossa base de dados)
		
	}

