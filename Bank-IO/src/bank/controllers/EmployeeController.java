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
	
	
	@RequestMapping("/signOut")
	public String signOut() {
		
		theEmployee=null;
		
		return "redirect:/loginForm";
	}
	
}
