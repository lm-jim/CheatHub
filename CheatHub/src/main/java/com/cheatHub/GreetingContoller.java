package com.cheatHub;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingContoller {
	
	private Object filtro;

	@RequestMapping("/search")
	public String greeting(Model model, @RequestParam String userName,String filtro) {
		// EL @RequestParam recogen los valores del parámetro username
		model.addAttribute("name",userName);
		if(filtro!=null) {
			model.addAttribute("fill",filtro);
		}
		else {
			model.addAttribute("fill","ningún");
		}
		
		/*Devolvemos como cadena de texto el nombre del archivo html en el que se encuentra la plantilla
		que se debe de encontrar en la carpeta de templates.
		*/
		return "principal";
	}
	
	@RequestMapping("/category")
	public String greetingCategory(Model model, @RequestParam String boton) {
		
			model.addAttribute("fill",boton);
		
		/*Devolvemos como cadena de texto el nombre del archivo html en el que se encuentra la plantilla
		que se debe de encontrar en la carpeta de templates.
		*/
		return "category";
	}

	@GetMapping("/")
	public String greetingCond(Model model) {
		
		return "index";
	}
	
	@GetMapping("/nuevaPublicacion")
	public String greetingPublicacion(Model model) {
		return "publicarPost";
	}
	
	//("/publicacion/{idPublicacion}")
	@GetMapping("/publicacion/{idPublicacion}")
	public String greetingPublicacion(Model model,@PathVariable int idPublicacion, @RequestParam String titulo, String publicacion,String juego,String tipo, String nuevoJuego) {
		if(titulo=="" || publicacion==""||tipo==null || (juego==""&&nuevoJuego=="")) {
			return "publicarPost";
		}
		model.addAttribute("titulo",titulo);
		if(juego=="") {
			//Añadimos el nuevo juego en la base de datos.
			juego=nuevoJuego;
		}
		model.addAttribute("juego",juego);
		model.addAttribute("publicacion",publicacion);
		model.addAttribute("tipo",tipo);
		//Cambiar al usuario que publica
		model.addAttribute("user","Pepito");
		
		return "publicacion";
	}
	
	
}
