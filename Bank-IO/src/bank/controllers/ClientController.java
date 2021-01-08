package bank.controllers;

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
import bank.entity.User;
import bank.service.AccountService;
import bank.service.UserService;

@Controller
@RequestMapping("/client")
public class ClientController {
	
	private User theUser;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	
	
	@GetMapping("/processUser")
	public String processUser(@ModelAttribute ("tempUser") User tempUser) {
		
		System.out.println(tempUser);
		
		theUser=tempUser;
		
		theUser = accountService.getAccounts(theUser);
		
		return "redirect:/client/home";
	}

	@RequestMapping("/home")
	public String userHome(Model model) {	

		if(theUser==null ) {
			return "redirect:/client/signOut";
		}
		
		model.addAttribute("user", theUser);
		model.addAttribute("user_accounts",theUser.getBankAccounts());
		
		return "client-home";
	
	}	
	
	@RequestMapping("/signOut")
	public String signOut() {
		
		theUser=null;
		
		return "redirect:/loginForm";
	}
}
