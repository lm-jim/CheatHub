package com.cheatHub;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

public class GameController {

	@Autowired
	private RepositorioCategoria repCategorias;
	@Autowired
	private RepositorioVideojuego repVideojuego;
	@Autowired
	private RepositorioPublicacion repPublicacion;
	
	
	@GetMapping("/juego")
	public String greetingjuego(Model model,@RequestParam String boton) {
		model.addAttribute("fill",boton);
		
		List<Videojuego> videojuego = repVideojuego.findByNombreVideojuego(boton);
		
		List<Publicacion> publicaciones = repPublicacion.findByVideojuego(videojuego.get(0));
		
		model.addAttribute("publicaciones",publicaciones);
		
		return "publicacionesVideojuegos";
	}
	
	@PostMapping("/nuevoVideojuego")
	public String greetingNuevoVideojuego(Model model) {
		List<Categoria> categorias = repCategorias.findAll();
		model.addAttribute("categoria",categorias);
		
		return "añadirJuego";
	}
	
	@RequestMapping("/añadirVideojuego")
	public String greetingAñadirVideojuego(Model model,@RequestParam String nombreVideojuego,String descripcionVideojuego,String categoriaVideojuego ) {
		if(!repVideojuego.existsById(nombreVideojuego)) {
			Categoria categoria;
			categoria=repCategorias.findByNombreCategoria(categoriaVideojuego).get(0);
			Videojuego videojuego=new Videojuego(nombreVideojuego,descripcionVideojuego,categoria);
			repVideojuego.save(videojuego);
					
		}
		
		return "añadirJuego";
	}
}
