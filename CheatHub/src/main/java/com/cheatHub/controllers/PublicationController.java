package com.cheatHub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.cheatHub.entities.Comentario;
import com.cheatHub.entities.Publicacion;
import com.cheatHub.entities.Usuario;
import com.cheatHub.entities.Videojuego;
import com.cheatHub.services.ServicioComentario;
import com.cheatHub.services.ServicioPublicacion;
import com.cheatHub.services.ServicioUsuario;
import com.cheatHub.services.ServicioVideojuegos;


@Controller
public class PublicationController {

	@Autowired
	private ServicioVideojuegos servicioVideojuego;
	@Autowired
	private ServicioPublicacion servicioPublicacion;
	@Autowired
	private ServicioUsuario servicioUsuario;
	@Autowired
	private ServicioComentario servicioComentario;



	
	@PostMapping("/nuevaPublicacion")
	public String greetingPublicacion(Model model) {
		List<Videojuego> videojuegos = servicioVideojuego.getAll();
		model.addAttribute("videojuegos",videojuegos);
		
		return "publicarPost";
	}
	
	@GetMapping("/publicacion/{pubId}")
	public String greetingPublicacion(Model model, @PathVariable String pubId) {
		Publicacion pub = servicioPublicacion.getPublicacionPorId(Integer.parseInt(pubId));
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
		model.addAttribute("puntuacion", pub.getPuntuacion());
		model.addAttribute("idPublicacion", pubId);
		
		return "publicacion";
	}
	
	@RequestMapping("/newpublicacion")
	public String greetingnuevaPublicacion(Model model, HttpSession session, @RequestParam String titulo, String publicacion,String juego,String tipo) {
		
		if(titulo=="" || publicacion==""||tipo==null ) {
			List<Videojuego> videojuegos = servicioVideojuego.getAll();
			model.addAttribute("videojuegos",videojuegos);
			return "publicarPost";
		}
		
		boolean tipoPubli;
		if(tipo.equals("bug"))
			tipoPubli=true;
		else tipoPubli=false;
		
		//HAY Q	UE OBTENER EL USUARIO QUE HA INICIADO SESION
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails uloggeado = (UserDetails) principal;
		System.out.println("---exito--");
		System.out.println(uloggeado.getUsername());
		
		Usuario userdef=servicioUsuario.getUsuarioByUsername(uloggeado.getUsername());	
		Videojuego juegoP= servicioVideojuego.getVideojuegoPorNombre(juego);
		Publicacion nuevaPublicacion=new Publicacion(titulo,publicacion,tipoPubli,userdef,juegoP);
		servicioPublicacion.guardarPublicacion(nuevaPublicacion);

		model.addAttribute("titulo",titulo);
		model.addAttribute("juego",juego);
		model.addAttribute("publicacion",publicacion);
		model.addAttribute("tipo",tipo);
		//Cambiar al usuario que publica cuando haya login
		model.addAttribute("user",userdef);
		

		return "redirect:/publicacion/"+nuevaPublicacion.getPublicacion();
	}
	
	@RequestMapping("/nuevoComentario")
	public String añadirComentario(Model model, @RequestParam String contenido,String titulo) {
		
		Publicacion publi=servicioPublicacion.getPublicacionPorNombre(titulo);
		
		//HAY Q	UE OBTENER EL USUARIO QUE HA INICIADO SESION
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails uloggeado = (UserDetails) principal;		
		Usuario userdef=servicioUsuario.getUsuarioByUsername(uloggeado.getUsername());
		
		if(contenido!="") {
			Comentario comentario=new Comentario(contenido,userdef,publi);
			publi.addComentario(comentario);
			servicioComentario.guardarComentario(comentario);
		}
		model.addAttribute("titulo",publi.getTitulo());
		model.addAttribute("juego",publi.getVideojuego());
		model.addAttribute("publicacion",publi.getDescripcion());
		if(publi.getTipoPublicacion())
			model.addAttribute("tipo","Bug");
		else
			model.addAttribute("tipo","Truco");
		//Cambiar al usuario que publica
		model.addAttribute("user",publi.getUsername());
		model.addAttribute("comentarios",publi.getListaComentarios());
			
		return "redirect:/publicacion/"+publi.getPublicacion();
	}
	@RequestMapping("/votarPublicacion/{pubId}")
	public String votarPublicacion(Model model, @RequestParam String vote, @PathVariable String pubId) {
		
		Publicacion publi=servicioPublicacion.getPublicacionPorId(Integer.parseInt(pubId));
		if(vote.contentEquals("up"))
			publi.setPuntuacion(publi.getPuntuacion()+1);
		else
			publi.setPuntuacion(publi.getPuntuacion()-1);
		System.out.println(vote);
		servicioPublicacion.guardarPublicacion(publi);
		return "redirect:/publicacion/"+publi.getPublicacion();
	}
	
	@RequestMapping("/editarPublicacion")
	public String editarPublicacion(Model model,@RequestParam String boton){
		Publicacion publi=servicioPublicacion.getPublicacionPorId(Integer.parseInt(boton));
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails uloggeado = (UserDetails) principal;
		
		if(uloggeado.getUsername().equals(publi.getUsername().getNombreUsuario())) {
			
			servicioPublicacion.borrarPublicacion(publi);
			
			return "redirect:/";
		}
		else {
			return "redirect:/publicacion/"+publi.getPublicacion();
		}
		
		
			
	}
	
}
