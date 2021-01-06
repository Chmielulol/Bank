package bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bank.entity.User;
import bank.entity.UserType;
import bank.service.UserService;

@Controller
@RequestMapping("/")
public class StartupController {
	
	private User theUser;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/test")
	public String startUp() {
	
		return "test";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm(Model model) {
		
		theUser = new User();
		model.addAttribute("user", theUser);
		
		return "login-form";
	}
	
	@PostMapping("/loginUser")
	public String loginUser(@ModelAttribute ("user") User user, RedirectAttributes redirect) {
		
		theUser = userService.loginUser(user);
		if(theUser==null) {
			System.out.println(theUser);
			return "redirect:/loginForm";
		}
		
		redirect.addFlashAttribute("tempUser", theUser);
		System.out.println(theUser);
		if(theUser.getType()==UserType.Client)
			return "redirect:/client/processUser";
		if(theUser.getType()==UserType.Employee) {
			return "redirect:/employee/processUser";
		}
		if(theUser.getType()==UserType.Admin) {
			return "redirect:/admin";
		}
		else {
			return "redirect:/loginForm";
		}
	}
	
	@RequestMapping("/admin")
	public String admin() {
		
		return "admin";
	}
		
	@RequestMapping("/adminClient")
	public String adminClient(RedirectAttributes redirect) {
		
		redirect.addFlashAttribute("tempUser", theUser);
		
		return "redirect:/client/processUser";
	}
	
	@RequestMapping("/adminEmployee")
	public String adminEmployee(RedirectAttributes redirect) {
		
		redirect.addFlashAttribute("tempUser", theUser);
		
		return "redirect:/employee/processUser";
	}
}
