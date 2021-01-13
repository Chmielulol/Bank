package bank.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bank.entity.BankAccount;
import bank.entity.Card;
import bank.entity.Transfer;
import bank.entity.User;
import bank.entity.UserData;
import bank.service.AccountService;
import bank.service.CardService;
import bank.service.EmployeeService;
import bank.service.TransferService;
import bank.service.UserService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	private User theEmployee;
	
	private User theClient;
	
	private List<User> theClients;
	
	private List<UserData> clientData;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private TransferService transferService;
	
	
	private void loadClients() {
		
		if(theClients==null)
			theClients = employeeService.getUsers();
		
		if(clientData==null) {
		
			clientData = new ArrayList<UserData>();
		
			for(User tempClient : theClients) {
				clientData.add(tempClient.getUserData());
			}
		}	
	}
	
	private void reloadClients() {
		
		theClients = employeeService.getUsers();
		
		clientData = new ArrayList<UserData>();
		
		for(User tempClient : theClients) {
			clientData.add(tempClient.getUserData());
		}
	}
	

	@GetMapping("/processUser")
	public String processUser(@ModelAttribute ("tempUser") User tempUser) {
		
		System.out.println(tempUser);
		theEmployee=tempUser;
			
		return "redirect:/employee/home";
	}
	
	@RequestMapping("/home")
	public String emplyeeHome(Model model) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		loadClients();
		
		theClient=new User();
		
		model.addAttribute("employee", theEmployee);
		model.addAttribute("clients", theClients);
		model.addAttribute("client", theClient);
		
		return "employee-home";
	}
	
	@RequestMapping("/addClient")
	public String addClient(Model model) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		UserData data = new UserData();
		
		model.addAttribute("user", theEmployee);
		model.addAttribute("data", data);
		
		return "add-client";
	}
	
	@PostMapping("/addClientPost")
	public String addClientPost(@ModelAttribute ("data") UserData data) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		if(data.getFirstName().length()<3 || data.getLastName().length()<3 || data.getPesel().length()<11 ) {
			return "redirect:/employee/addClient";
		}
		
		employeeService.addClient(data);
		
		reloadClients();
		
		return "redirect:/employee/home";
	}
	
	@PostMapping("/searchClient")
	public String searchClient(@ModelAttribute ("client") User client, Model model) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		List<User> searchedClients = employeeService.searchClient(client);
		
		model.addAttribute("employee", theEmployee);
		model.addAttribute("searchedClients", searchedClients);
		model.addAttribute("client", theClient);
		
		return "search-users";
	}
	
	@GetMapping("/showClientDetails")
	public String showClientDetails(@RequestParam ("clientId") int clientId, Model model) {
		
		if(theEmployee==null ) {
			return "redirect:/employee/signOut";
		}
		
		theClient = employeeService.getClient(clientId);
		theClient = accountService.getAccounts(theClient);	
		UserData clientData = userService.getUserData(theClient);

		
		model.addAttribute("employee", theEmployee);
		model.addAttribute("client", theClient);
		model.addAttribute("clientData", clientData);
		model.addAttribute("clientAccounts", theClient.getBankAccounts());
				
		return "show-full-user";
	}
	
	@RequestMapping("/addClientAccount")
	public String addClientAccount(Model model) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		BankAccount tempAccount = new BankAccount();
		
		model.addAttribute("account", tempAccount);
		model.addAttribute("client", theClient);
		
		return "add-account";
	}
	
	@PostMapping("/addClientAccountPost")
	public String addClientAccountPost(@ModelAttribute ("account") BankAccount account) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		account.setUser(theClient);
		
		accountService.saveAccount(account);
		
		return "redirect:/employee/showClientDetails?clientId="+theClient.getId();
	}
	
	@RequestMapping("/showTransferHistory")
	public String showTransferHistory(Model model) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		List<Transfer> history;
		
		history=transferService.getTransferHisory(theClient);
		
		model.addAttribute("employee", theEmployee);
		model.addAttribute("user", theClient);
		model.addAttribute("transferHistory", history);
		
		return "show-transfer-history";
	}
	
	@RequestMapping("/showClientAccount")
	public String showClientAccount(@RequestParam ("accountId") int accountId, Model model) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		int i;
		BankAccount tempAccount=null;
		List<Card> cards = null;
		
		for(i=0 ;i< theClient.getBankAccounts().size() ;i++) {
			if(theClient.getBankAccounts().get(i).getAccountNumber() == accountId) {
				tempAccount=theClient.getBankAccounts().get(i);
				
				break;
			}
		}
		
		if(tempAccount!=null)
			cards = cardService.getCards(tempAccount);
		
		model.addAttribute("account", tempAccount);
		model.addAttribute("employee", theEmployee);
		model.addAttribute("cards", cards);
		
		
		return "show-account-detail";
	}
	
	@GetMapping("/addCard")
	public String addCard(@RequestParam ("accountId") int accountId) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		BankAccount account=null;
		
		for(int i=0;i<theClient.getBankAccounts().size();i++) {
			if(theClient.getBankAccounts().get(i).getAccountNumber()==accountId) {
				account=theClient.getBankAccounts().get(i);
				break;
			}
		}
		
		cardService.addCard(account);
		
		return "redirect:/employee/showClientAccount?accountId="+accountId;
	}
	
	
	
	@RequestMapping("/setAccountLimit")
	public String setAccountLimit(@RequestParam ("accountNumber") int accountNumber,Model model) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		model.addAttribute("employee", theEmployee);
		model.addAttribute("account", accountService.getAccount(accountNumber));
		
		return "set-account-limit";
	}
	
	@PostMapping("/setAccountLimitPost")
	public String setAccountLimitPost(@ModelAttribute ("account") BankAccount account) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		int i;

		for(i=0;i<theClient.getBankAccounts().size()-1;i++) {
			if(account.getAccountNumber() == theClient.getBankAccounts().get(i).getAccountNumber()) {
				break;
			}
		}
		
		if(account.getLimit()>=0) {
			theClient.getBankAccounts().get(i).setLimit(account.getLimit());
			userService.saveUser(theClient);	
			
		}
			
		return "redirect:/employee/showClientAccount?accountId="+account.getAccountNumber();
	}
	
	@RequestMapping("/setCardLimit")
	public String setCardLimit(@RequestParam ("cardId") int cardId,Model model) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		Card card = cardService.getCard(cardId);
		
		model.addAttribute("employee", theEmployee);
		model.addAttribute("card",card);
		model.addAttribute("account", card.getAccount());
		
		return "set-card-limit";
	}
	
	@PostMapping("/setCardLimitPost")
	public String setCardLimitPost( @ModelAttribute ("card") Card card) {
		
		if(theEmployee==null) {
			return "redirect:/employee/signOut";
		}
		
		Card tempCard = cardService.getCard(card.getId());
		
		System.out.println(card.getId());
		
		BankAccount account = tempCard.getAccount();
		
				
		if(card.getCardLimit()>=0) {
			tempCard.setCardLimit(card.getCardLimit());	
			cardService.saveCard(tempCard);
			
		}
			
		return "redirect:/employee/showclientAccount?accountId="+account.getAccountNumber();
	}
	
	@RequestMapping("/signOut")
	public String signOut() {
		
		theEmployee=null;
		
		return "redirect:/loginForm";
	}
	
}
