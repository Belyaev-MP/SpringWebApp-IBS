package my.self.springapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.self.springapp.domain.model.User;
import my.self.springapp.web.form.UserForm;

@Controller
public class UserController {

	@GetMapping("/user/registration")
	public String userRegistration() {
		return "user/registration";
	}

	@PostMapping("/user/registration")
	public String userRegistrationSubmit(@ModelAttribute UserForm form) {
		
		User u = new User();
		u.setEmail(form.getEmail());
		u.setName(form.getName());
		u.setPassword(form.getPassword());
		
		System.out.println(u);
		
		return "redirect:/";
	}
	
}
