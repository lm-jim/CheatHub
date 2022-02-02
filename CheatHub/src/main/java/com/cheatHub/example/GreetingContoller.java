package com.cheatHub.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingContoller {
	
	@GetMapping("/")
	public String greeting(Model model) {
		model.addAttribute("name","calvo");
		return "index";
	}
}
