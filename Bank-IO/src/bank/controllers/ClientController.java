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
import bank.entity.Transfer;
import bank.entity.User;
import bank.service.AccountService;
import bank.service.TransferService;
import bank.service.UserService;

@Controller
@RequestMapping("/client")
public class ClientController {
	
	private User theUser;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransferService transferService;
	
	
	
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
	
	@RequestMapping("/showTransferHistory")
	public String showTransferHistory(Model model) {
		
		if(theUser==null ) {
			return "redirect:/client/signOut";
		}
		
		List<Transfer> history;
		
		history=transferService.getTransferHisory(theUser);
		
		model.addAttribute("user", theUser);
		model.addAttribute("transferHistory", history);
		
		return "show-transfer-history";
	}
	
	@RequestMapping("/makeTransfer")
	public String makeTransfer(Model model) {

		if(theUser==null ) {
			return "redirect:/client/signOut";
		}
		
		Transfer transfer = new Transfer();
		
		model.addAttribute("transfer", transfer);
		model.addAttribute("accountList",theUser.getBankAccounts());
		
		return "client-transfer";
	}
	
	@RequestMapping("/makePersonalTransfer")
	public String makePersonalTransfer(Model model) {
		
		if(theUser==null ) {
			return "redirect:/client/signOut";
		}
		
		Transfer transfer = new Transfer();
		
		model.addAttribute("transfer", transfer);
		model.addAttribute("accountList",theUser.getBankAccounts());
		
		return "client-personal-transfer";
	}
	
	@PostMapping("/makeTransferPost")
	public String makeTransferPost(@ModelAttribute ("transfer") Transfer transfer) {
		
		if(theUser==null ) {
			return "redirect:/client/signOut";
		}
		
		if(!transferService.isValid(transfer)) {
			return "redirect:/client/makeTransfer";
		}
		
		BankAccount senderAccount = accountService.getAccount(transfer.getSenderAccountId());
		BankAccount recieverAccount = accountService.getAccount(transfer.getRecieverAccountId());
		
		if(recieverAccount==null || senderAccount==null) {
			return "redirect:/client/makeTransfer";
		}
		
		if(transferService.makeTransfer(senderAccount, recieverAccount, transfer.getTitle(), transfer.getMoney())) {
		theUser=accountService.getAccounts(theUser);
		return "redirect:/client/home";
		}
		else
			return "redirect:/client/makeTransfer";
	}
	
	@RequestMapping("/showUserData")
	public String showUserData(Model model) {
		
		if(theUser==null) {
			return "redirect:/client/signOut";
		}
		
		theUser.setUserData(userService.getUserData(theUser));
		
		model.addAttribute("user", theUser);
		model.addAttribute("userData", theUser.getUserData());
		
		return "show-user-data";
	}
	
	@RequestMapping("/changePassword")
	public String changePassword(Model model) {
		
		if(theUser==null ) {
			return "redirect:/client/signOut";
		}

		
		model.addAttribute("tempUser", theUser);
		
		return "client-change-password";
	}
	
	@PostMapping("/changePasswordPost")
	public String changePasswordPost(@ModelAttribute ("tempUser") User tempUser) {
		
		if(theUser==null ) {
			return "redirect:/client/signOut";
		}
			
		int theId = tempUser.getId();
		
		if(theUser.getId()==theId) {
			theUser.setPassword(tempUser.getPassword());
			userService.saveUser(theUser);
			return "redirect:/client/showUserData";
		}
		
		return "redirect:/client/changePassword";
	}
	
	
	@RequestMapping("/signOut")
	public String signOut() {
		
		theUser=null;
		
		return "redirect:/loginForm";
	}
}
