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
public class PublicationController {
	
	@Autowired
	private RepositorioCategoria repCategorias;
	@Autowired
	private RepositorioVideojuego repVideojuego;
	@Autowired
	private RepositorioPublicacion repPublicacion;
	@Autowired
	private RepositorioComentario repComentario;

	
	@PostMapping("/nuevaPublicacion")
	public String greetingPublicacion(Model model) {
		List<Videojuego> videojuegos = repVideojuego.findAll();
		model.addAttribute("videojuegos",videojuegos);
		
		return "publicarPost";
	}
	
	//("/publicacion/{idPublicacion}")
	@PostMapping("/publicacion")
	public String greetingPublicacion(Model model,@RequestParam String boton) {
		int n=Integer.valueOf(boton);
		System.out.println(n);
		if(n>0) { 
			List<Publicacion> publicaciones = repPublicacion.findByIdPublicacion(n);
			Publicacion pub = publicaciones.get(0);
			model.addAttribute("titulo",pub.getTitulo());
			model.addAttribute("juego",pub.getVideojuego());
			model.addAttribute("publicacion",pub.getDescripcion());
			if(pub.getTipoPublicacion())
				model.addAttribute("tipo","Bug");
			else
				model.addAttribute("tipo","Truco");
			//Cambiar al usuario que publica
			model.addAttribute("user",pub.getUsername());
			model.addAttribute("comentarios",pub.getListaComentarios());
		}
		
		return "publicacion";
	}
	@RequestMapping("/newpublicacion")
	public String greetingnuevaPublicacion(Model model, @RequestParam String titulo, String publicacion,String juego,String tipo) {
		
			if(titulo=="" || publicacion==""||tipo==null ) {
				List<Videojuego> videojuegos = repVideojuego.findAll();
				model.addAttribute("videojuegos",videojuegos);
				return "publicarPost";
			}
			
			boolean tipoPubli;
			if(tipo=="bug")
				tipoPubli=true;
			else tipoPubli=false;
			Usuario userdef=new Usuario("default","pass");	
			Videojuego juegoP= repVideojuego.findById(juego).get();
			Publicacion Nuevapublicacion=new Publicacion(titulo,publicacion,tipoPubli,userdef,juegoP);
			repPublicacion.save(Nuevapublicacion);
	
			model.addAttribute("titulo",titulo);
			model.addAttribute("juego",juego);
			model.addAttribute("publicacion",publicacion);
			model.addAttribute("tipo",tipo);
			//Cambiar al usuario que publica cuando haya login
			model.addAttribute("user",userdef);
			
	
			return "publicacion";
	}
}
