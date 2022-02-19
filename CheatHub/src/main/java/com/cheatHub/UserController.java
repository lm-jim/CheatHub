package com.cheatHub;
import java.sql.Date;
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
	public String userPage(Model model, @RequestParam String userName, String password,String birthdate,String realName,String descryption,String url,String boton) {
				if(userName=="" || password=="") {
					if(boton.equals("Iniciar sesión"))
						return "login"; //Deberiamos de devolver a la página de la que venimos
					else
						return "createaccount";
				}
				
				//Deberiamos de ver si venimos de login o create account, y cargamos de la Bd o guardamos el usuario en la BD.
				
				//Cargaríamos los datos de la BD del nombre de usuario correspondiente
		model.addAttribute("usuario",userName);
		if(realName!=null)
			model.addAttribute("nombre",realName);
		else 
			model.addAttribute("nombre","-");
		if(birthdate!=null)
		model.addAttribute("fechaNacimiento",birthdate);
		else 
			model.addAttribute("fechaNacimiento","-");
		if(descryption!=null)
		model.addAttribute("Descripcion",descryption);
		else 
			model.addAttribute("Descripcion","-");
		if(url!=null)
			model.addAttribute("imagen",url);
			else 
				model.addAttribute("imagen","");
		
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

