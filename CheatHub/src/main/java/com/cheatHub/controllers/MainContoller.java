package com.cheatHub.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cheatHub.entities.Categoria;
import com.cheatHub.entities.Publicacion;
import com.cheatHub.entities.Videojuego;
import com.cheatHub.services.ServicioCategoria;
import com.cheatHub.services.ServicioPublicacion;
import com.cheatHub.services.ServicioVideojuegos;

@Controller
public class MainContoller {

	
	@Autowired
	private ServicioCategoria servicioCategoria;
	@Autowired
	private ServicioVideojuegos servicioVideojuego;
	@Autowired
	private ServicioPublicacion servicioPublicacion;
	
	@RequestMapping("/search")
	public String greeting(Model model, @RequestParam String juego,String filtro) {
		
		boolean filtrarjuego,filtrarTipo;
		//Primero vamos a ver por lo que vamos a filtrar la búsqueda
		if(juego=="") {
			filtrarjuego=false;
			model.addAttribute("videojuego"," de cualquier juego");
		}
		else {
			filtrarjuego=true;
			model.addAttribute("videojuego","del videojuego "+juego);
		}
		
		if(filtro==null || filtro.equals("trucos,bugs")   ) {

			filtrarTipo=false;
			model.addAttribute("fill","cualquier publicación");
			
		}
		else {
			filtrarTipo=true;
			model.addAttribute("fill",filtro);
		}
		
		//Vamos a hacer la búsuqeda según lo que se ha introducido.
		List<Publicacion> publicacion;
		if(filtrarjuego) { //Si hay juego
				Videojuego v = servicioVideojuego.getVideojuegoPorNombre(juego);
			if(filtrarTipo && filtro.equals("trucos")) { //Y es un truco
				publicacion = servicioPublicacion.getPublicacionPorVideojuegoYTipo(false,v);
			}else if (filtrarTipo){ //Y es un bug
				publicacion = servicioPublicacion.getPublicacionPorVideojuegoYTipo(true,v);
			}else { //Da igual que sea truco o bug
				publicacion = servicioPublicacion.getPublicacionPorVideojuego(v);
			}
		}
		else { //Si no hay juego, es cualquiera
			if(filtrarTipo && filtro.equals("trucos")) { //Y es truco
				publicacion = servicioPublicacion.getPublicacionPorTipo(false);
			}else if (filtrarTipo){ //Y es bug
				publicacion = servicioPublicacion.getPublicacionPorTipo(true);
			}else { //Da igual que sea truco o bug. En este caso serían todas las publicaciones.
				publicacion = servicioPublicacion.getAll();
			}
		}
		
		model.addAttribute("publicaciones",publicacion);
		/*Devolvemos como cadena de texto el nombre del archivo html en el que se encuentra la plantilla
		que se debe de encontrar en la carpeta de templates.
		*/
		return "busqueda";
	}
	
	@GetMapping("/category/{category}")
	public String greetingCategory(Model model, @PathVariable String category) {
		
			model.addAttribute("fill",category);
			
			Categoria categoria = servicioCategoria.getCategoriaByName(category);

			List<Videojuego> videojuegos = servicioVideojuego.getPorCategoria(categoria); //Supuestamente solo debería de haber 1 categoría con ese nombre, así que buscamos por la pos 0
			
			model.addAttribute("juegos",videojuegos);
		
		/*Devolvemos como cadena de texto el nombre del archivo html en el que se encuentra la plantilla
		que se debe de encontrar en la carpeta de templates.
		*/
		return "category";
	}

	@GetMapping("/")
	public String greetingCond(Model model, HttpServletRequest request) {
		List<Categoria> categorias = servicioCategoria.getAll();
		List<Videojuego> juegos = servicioVideojuego.getAll();
		model.addAttribute("videojuegos",juegos);
		model.addAttribute("categorias",categorias);
		return "index";
	}
	
	
	
	
	
	
	
}
