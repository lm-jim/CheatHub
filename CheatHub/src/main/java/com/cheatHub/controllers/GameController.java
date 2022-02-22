package com.cheatHub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cheatHub.entities.Categoria;
import com.cheatHub.entities.Publicacion;
import com.cheatHub.entities.Videojuego;
import com.cheatHub.repositories.RepositorioCategoria;
import com.cheatHub.repositories.RepositorioPublicacion;
import com.cheatHub.repositories.RepositorioVideojuego;
import com.cheatHub.services.ServicioCategoria;
import com.cheatHub.services.ServicioPublicacion;
import com.cheatHub.services.ServicioVideojuegos;

@Controller
public class GameController {

	@Autowired
	private ServicioCategoria servicioCategoria;
	@Autowired
	private ServicioVideojuegos servicioVideojuego;
	@Autowired
	private ServicioPublicacion servicioPublicacion;
	
	
	@RequestMapping("/juego")
	public String greetingjuego(Model model,@RequestParam String boton) {
		model.addAttribute("fill",boton);
		
		Videojuego videojuego = servicioVideojuego.getVideojuegoPorNombre(boton);
		
		List<Publicacion> publicaciones = servicioPublicacion.getPublicacionPorVideojuego(videojuego);
		
		model.addAttribute("publicaciones",publicaciones);
		
		return "publicacionesVideojuegos";
	}
	
	@PostMapping("/nuevoVideojuego")
	public String greetingNuevoVideojuego(Model model) {
		List<Categoria> categorias = servicioCategoria.getAll();
		model.addAttribute("categoria",categorias);
		
		return "añadirJuego";
	}
	
	@RequestMapping("/añadirVideojuego")
	public String greetingAñadirVideojuego(Model model,@RequestParam String nombreVideojuego,String descripcionVideojuego,String categoriaVideojuego ) {
		if(!servicioVideojuego.existeVideojuego(nombreVideojuego)) {
			Categoria categoria;
			categoria=servicioCategoria.getCategoriaByName(categoriaVideojuego);
			Videojuego videojuego=new Videojuego(nombreVideojuego,descripcionVideojuego,categoria);
			servicioVideojuego.guardarVideojuego(videojuego);
					
		}
		
		return "añadirJuego";
	}
}
