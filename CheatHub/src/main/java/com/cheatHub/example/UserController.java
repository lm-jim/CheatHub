package com.cheatHub.example;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@GetMapping("/login")
	public String login(Model model) {
		
		return "login";
	}
	
	@GetMapping("/newAccount")
	public String newAccount(Model model) {
		
		return "createaccount";
	}
	
	@GetMapping("/user")
	public String userPage(Model model, @RequestParam String userName) {
		//Cargaríamos los datos de la BD del nombre de usuario correspondiente
		model.addAttribute("usuario",userName);
		model.addAttribute("nombre","Pepito");
		model.addAttribute("fechaNacimiento","05/09/99");
		model.addAttribute("Descripcion","Me gustan los juegos retro");
		model.addAttribute("Publicaciones","Ninguna");
		
		return "user";
	}
}

/*
 * <h2>{{usuario}}</h2>
    <h4>{{nombre}}</h4>
    <h4>{{fechaNacimiento}}</4>
    <h4>{{Descripcion}}</h4>
    <p>{{Publicaciones}}</p>
 * */

