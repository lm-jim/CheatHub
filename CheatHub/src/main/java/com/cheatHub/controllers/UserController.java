package com.cheatHub.controllers;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cheatHub.entities.Usuario;
import com.cheatHub.repositories.RepositorioUsuario;
import com.cheatHub.services.ServicioUsuario;

@Controller
public class UserController {

	@Autowired
	ServicioUsuario servicioUsuarios;

	@GetMapping("/login")
	public String login(Model model) {

		return "login";
	}

	@GetMapping("/newAccount")
	public String newAccount(Model model) {
		return "createaccount";
	}

	@GetMapping("/buscarUsuario")
	public String login(Model model, String texto) {
		model.addAttribute("textoIntroducido",texto);
		model.addAttribute("usuariosEncontrados", servicioUsuarios.buscarUsuarios(texto));
		
		return "userSearch";
	}
	
	@GetMapping("/user/{username}")
	public String userPage(Model model, @PathVariable String username) {
		
		Usuario user = servicioUsuarios.getUsuarioByUsername(username);
		
		model.addAttribute("usuario", user.getNombreUsuario());
		if (user.getNombreReal() != null)
			model.addAttribute("nombre", user.getNombreReal());
		else
			model.addAttribute("nombre", "-");
		if (user.getFechaNacimiento() != null)
			model.addAttribute("fechaNacimiento", user.getFechaNacimiento());
		else
			model.addAttribute("fechaNacimiento", "-");
		if (user.getDescripcion() != null)
			model.addAttribute("Descripcion", user.getDescripcion());
		else
			model.addAttribute("Descripcion", "-");
		if (user.getAvatar() != null)
			model.addAttribute("imagen", user.getAvatar());
		else
			model.addAttribute("imagen", "");

		model.addAttribute("Publicaciones", "Ninguna");

		return "user";
	}
	
	@RequestMapping("/registerUser")
	public String userPage(Model model, @RequestParam String userName, String password, String birthdate, String realName, String descryption, String url, String boton) {
		if (userName == "" || password == "") { // No se han introducido datos
			model.addAttribute("notificacion", "Por favor, introduce nombre y contraseña.");
			if (boton.equals("Iniciar sesión"))
				return "login"; // Deberiamos de devolver a la página de la que venimos
			else
				return "createaccount";
		} 
		else if(servicioUsuarios.existeUsername(userName) && !boton.equals("Iniciar sesión")) {
			model.addAttribute("notificacion", "El nombre de usuario ya existe. Por favor, seleccione otro nombre.");
			return "createaccount";
		}
		else { // Todo en orden
			if (boton.equals("Crear Usuario")) { // Venimos de un registro
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date fechaNacimiento;
					if(birthdate != "")
						fechaNacimiento = dateFormat.parse(birthdate);
					else
						fechaNacimiento = null;
					if(url == "")
						url = "https://www.royalunibrew.com/wp-content/uploads/2021/07/blank-profile-picture-973460_640.png";
					servicioUsuarios.registrarUsuario(new Usuario(userName, password, realName, descryption, fechaNacimiento, url)); // Añadimos el nuevo usuario a la BD
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

		// Deberiamos de ver si venimos de login o create account, y cargamos de la Bd o
		// guardamos el usuario en la BD.

		// Cargaríamos los datos de la BD del nombre de usuario correspondiente
		model.addAttribute("usuario", userName);
		if (realName != null)
			model.addAttribute("nombre", realName);
		else
			model.addAttribute("nombre", "-");
		if (birthdate != null)
			model.addAttribute("fechaNacimiento", birthdate);
		else
			model.addAttribute("fechaNacimiento", "-");
		if (descryption != null)
			model.addAttribute("Descripcion", descryption);
		else
			model.addAttribute("Descripcion", "-");
		if (url != null)
			model.addAttribute("imagen", url);
		else
			model.addAttribute("imagen", "");

		model.addAttribute("Publicaciones", "Ninguna");
		model.addAttribute("notificacion", "El usuario \""+userName+"\" ha sido registrado correctamente.");
		return "redirect:/user/"+userName;
	}
}

/*
 * <h2>{{usuario}}</h2> <h4>{{nombre}}</h4> <h4>{{fechaNacimiento}}</4>
 * <h4>{{Descripcion}}</h4> <p>{{Publicaciones}}</p>
 */
