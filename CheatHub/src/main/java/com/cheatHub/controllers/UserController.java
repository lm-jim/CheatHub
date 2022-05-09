package com.cheatHub.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.cheatHub.entities.Usuario;
import com.cheatHub.services.ServicioUsuario;

@Controller
public class UserController {

	@Autowired
	ServicioUsuario servicioUsuarios;
	


	@GetMapping("/newAccount")
	public String newAccount(Model model) {
			model.addAttribute("valorBoton","Crear Usuario");
			model.addAttribute("texto","Crear Usuario");
			model.addAttribute("textoNombre","Obligatorio");
		return "createaccount";
	}
	
	@GetMapping("/editarUsuario")
	public String editarUsuario(Model model, @RequestParam String boton) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails uloggeado = (UserDetails) principal;
		
		if(uloggeado.getUsername().equals(boton)) {
			model.addAttribute("valorBoton",boton); //Sería el usuario
			model.addAttribute("textoNombre","No modificar");
			model.addAttribute("texto","Editar usuario");
			return "createaccount";
		}
		else {
			return "redirect:/user/"+boton;
		}
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
		
		model.addAttribute("Publicaciones", user.getListaPublicaciones());

		return "user";
	}
	
	@RequestMapping("/registerUser")
	public String userPage(Model model, @RequestParam String userName, String password,String correo, String birthdate, String realName, String descryption, String url, String boton) {
		//Caso en el que hayamos llegado desde una creación de usuario
		if(boton.equals("Crear Usuario")) {
			System.out.println("------------------------------CREAMOS USUR");
			if (userName == "" || password == "") { // No se han introducido datos
				model.addAttribute("notificacion", "Por favor, introduce nombre y contraseña.");
				model.addAttribute("valorBoton","Crear Usuario");
				model.addAttribute("texto","Crear Usuario");
				model.addAttribute("textoNombre","Obligatorio");
				return "createaccount";
			} 
			else if(servicioUsuarios.existeUsername(userName)) {
				model.addAttribute("notificacion", "El nombre de usuario ya existe. Por favor, seleccione otro nombre.");
				model.addAttribute("valorBoton","Crear Usuario");
				model.addAttribute("texto","Crear Usuario");
				model.addAttribute("textoNombre","Obligatorio");
				return "createaccount";
			}
			else { // Todo en orden
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date fechaNacimiento;
						if(birthdate != "")
							fechaNacimiento = dateFormat.parse(birthdate);
						else
							fechaNacimiento = null;
						if(url == "")
							url = "https://www.royalunibrew.com/wp-content/uploads/2021/07/blank-profile-picture-973460_640.png";
						servicioUsuarios.registrarUsuario(new Usuario(userName, password,correo, realName, descryption, fechaNacimiento, url)); // Añadimos el nuevo usuario a la BD
					} catch (ParseException e) {
						e.printStackTrace();
					}
			}
			Usuario user=servicioUsuarios.getUsuarioByUsername(userName);
			model.addAttribute("nombreUsuario", user.getNombreUsuario());
			System.out.println(user.getNombreUsuario());

			// Cargaríamos los datos de la BD del nombre de usuario correspondiente
			/*
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
			*/
			return "/login";
		}
		//Caso en el que estemos editando un usuario
		else {
			System.out.println("-------------------MODIFICAMOS USUR  ->>> "+ boton);
			Usuario u = servicioUsuarios.getUsuarioByUsername(boton);
			
			System.out.println("-------------------ENCONTRADO ->>> "+ u);
			
			if(password!="") {
				u.setContraseña(password);
			}
			if(birthdate!="") {
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date fechaNacimiento = dateFormat.parse(birthdate);
					u.setFechaNacimiento(fechaNacimiento);
				}catch (ParseException e) {
						e.printStackTrace();
					}
			}
			if(realName!="") {
				u.setNombreReal(realName);
			}
			if(descryption!="") {
				u.setDescripcion(descryption);
			}
			if(url!="") {
				u.setAvatar(url);
			}
			
			
			System.out.println("-------------------AÑADIMOS ATRIBUTOS ->>> "+ u);
			model.addAttribute("notificacion","");
			model.addAttribute("usuario", u.getNombreUsuario());
			if (realName != null)
				model.addAttribute("nombre", u.getNombreReal());
			else
				model.addAttribute("nombre", "-");
			if (birthdate != null)
				model.addAttribute("fechaNacimiento", u.getFechaNacimiento());
			else
				model.addAttribute("fechaNacimiento", "-");
			if (descryption != null)
				model.addAttribute("Descripcion", u.getDescripcion());
			else
				model.addAttribute("Descripcion", "-");
			if (url != null)
				model.addAttribute("imagen", u.getAvatar());
			else
				model.addAttribute("imagen", "");
			
			model.addAttribute("Publicaciones",u.getListaPublicaciones());
			
			servicioUsuarios.editarUsuario(u);
			
			return "redirect:/user/"+u.getNombreUsuario();
		}
		
		
	}
	
	@RequestMapping("/login")
	public String userPage(Model model) {
		
		/*
		if(userName=="" || password=="") {
			model.addAttribute("notificacion", "Por favor, introduce nombre y contraseña.");
			return "login";
		}
		//Ver que hacemos con la excepcion
		
		try {
			Usuario u = servicioUsuarios.getUsuarioByUsername(userName);
				
			if(!u.getContraseña().equals(password)) {
				model.addAttribute("notificacion", "Usuario y contraseña incorrecta");
				return "login";
			}
			
			model.addAttribute("usuario", u.getNombreUsuario());
			if (u.getNombreReal() != null)
				model.addAttribute("nombre", u.getNombreReal());
			else
				model.addAttribute("nombre", "-");
			if (u.getFechaNacimiento() != null)
				model.addAttribute("fechaNacimiento",u.getFechaNacimiento());
			else
				model.addAttribute("fechaNacimiento", "-");
			if (u.getDescripcion() != null)
				model.addAttribute("Descripcion", u.getDescripcion());
			else
				model.addAttribute("Descripcion", "-");
			if (u.getAvatar() != null)
				model.addAttribute("imagen", u.getAvatar());
			else
				model.addAttribute("imagen", "");
			
			model.addAttribute("publicaciones", u.getListaPublicaciones());
			
			model.addAttribute("notificacion", "Datos correctos.");
			
			return "redirect:/user/"+userName;
		}catch(ResponseStatusException ex) {
			model.addAttribute("notificacion", "Usuario y contraseña incorrecta");
			return "login";
		}
		*/
		
		//CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		//model.addAttribute("token", token.getToken()); 
		model.addAttribute("notificacion", ""); 
		return "login";
		
	}
	
	
	@RequestMapping("/loginerror")	
	public String loginerror(Model model,HttpServletRequest request) {
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken()); 
		model.addAttribute("notificacion", "Error autentificación"); 
		
		return "login";
		
	}
	
}

