package my.self.springapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import my.self.springapp.data.TestDbService;
import my.self.springapp.web.spring.UserDetailsImpl;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestDbService service;
	
	@GetMapping("/tst1")
	@ResponseBody
	public String makeTest1() {
		service.example2();
		return "Ok";
	}
	
	@GetMapping("/make-auth")
	@ResponseBody
	public String makeAuth(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		return "ID: " + (userDetails != null ? userDetails.getId() : "");
	}

	
	@GetMapping("/test2")
	@ResponseBody
	public String test2(Model model) {
		return "Id: " + model.getAttribute("userId");
	}
	
	@ModelAttribute(name = "userId")
	public Long userId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		return userDetails != null ? userDetails.getId() : null;
	}
	
}
