package com.cheatHub;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cheatHub.entities.Categoria;
import com.cheatHub.entities.Publicacion;
import com.cheatHub.entities.Usuario;
import com.cheatHub.entities.Videojuego;
import com.cheatHub.repositories.RepositorioCategoria;
import com.cheatHub.repositories.RepositorioComentario;
import com.cheatHub.repositories.RepositorioPublicacion;
import com.cheatHub.repositories.RepositorioVideojuego;

@Controller
public class MainContoller {

	private Object filtro;
	@Autowired
	private RepositorioCategoria repCategorias;
	@Autowired
	private RepositorioVideojuego repVideojuego;
	@Autowired
	private RepositorioPublicacion repPublicacion;
	@Autowired
	private RepositorioComentario repComentario;

	@RequestMapping("/search")
	public String greeting(Model model, @RequestParam String juego,String filtro) {
		
		boolean filtrarjuego,filtrarTipo;
		//Primero vamos a ver por lo que vamos a filtrar la búsqueda
		if(juego=="") {
			filtrarjuego=false;
			model.addAttribute("videojuego","cualquier juego");
		}
		else {
			filtrarjuego=true;
			model.addAttribute("videojuego","del videojuego "+juego);
		}
		if(filtro!=null) {
			filtrarTipo=true;
			model.addAttribute("fill",filtro);
		}
		else {
			filtrarTipo=false;
			model.addAttribute("fill","ningún");
		}
		
		//Vamos a hacer la búsuqeda según lo que se ha introducido.
		List<Publicacion> publicacion;
		if(filtrarjuego) { //Si hay juego
				List<Videojuego> v = repVideojuego.findByNombreVideojuego(juego);
			if(filtrarTipo && filtro.equals("trucos")) { //Y es un truco
				publicacion = repPublicacion.findByTipoPublicacionAndVideojuego(false,v.get(0));
			}else if (filtrarTipo){ //Y es un bug
				publicacion = repPublicacion.findByTipoPublicacionAndVideojuego(true,v.get(0));
			}else { //Da igual que sea truco o bug
				publicacion = repPublicacion.findByVideojuego(v.get(0));
			}
		}
		else { //Si no hay juego, es cualquiera
			if(filtrarTipo && filtro.equals("trucos")) { //Y es truco
				publicacion = repPublicacion.findByTipoPublicacion(false);
			}else if (filtrarTipo){ //Y es bug
				publicacion = repPublicacion.findByTipoPublicacion(true);
			}else { //Da igual que sea truco o bug. En este caso serían todas las publicaciones.
				publicacion = repPublicacion.findAll();
			}
		}
		
		model.addAttribute("publicaciones",publicacion);
		/*Devolvemos como cadena de texto el nombre del archivo html en el que se encuentra la plantilla
		que se debe de encontrar en la carpeta de templates.
		*/
		return "busqueda";
	}
	
	@RequestMapping("/category")
	public String greetingCategory(Model model, @RequestParam String boton) {
		
			model.addAttribute("fill",boton);
			
			List<Categoria> categoria = repCategorias.findByNombreCategoria(boton);

			List<Videojuego> videojuegos = repVideojuego.findByCategoria(categoria.get(0)); //Supuestamente solo debería de haber 1 categoría con ese nombre, así que buscamos por la pos 0
			
			model.addAttribute("juegos",videojuegos);
		
		/*Devolvemos como cadena de texto el nombre del archivo html en el que se encuentra la plantilla
		que se debe de encontrar en la carpeta de templates.
		*/
		return "category";
	}

	@GetMapping("/")
	public String greetingCond(Model model) {
		List<Categoria> categorias = repCategorias.findAll();
		List<Videojuego> juegos = repVideojuego.findAll();
		model.addAttribute("videojuegos",juegos);
		model.addAttribute("categorias",categorias);
		return "index";
	}
	
	
	
	
	
}
