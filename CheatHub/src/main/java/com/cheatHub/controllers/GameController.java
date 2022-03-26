package com.cheatHub.controllers;

import java.util.List;

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

import com.cheatHub.entities.Categoria;
import com.cheatHub.entities.Publicacion;
import com.cheatHub.entities.Usuario;
import com.cheatHub.entities.Videojuego;
import com.cheatHub.services.ServicioCategoria;
import com.cheatHub.services.ServicioPublicacion;
import com.cheatHub.services.ServicioUsuario;
import com.cheatHub.services.ServicioVideojuegos;

@Controller
public class GameController {

	@Autowired
	private ServicioCategoria servicioCategoria;
	@Autowired
	private ServicioVideojuegos servicioVideojuego;
	@Autowired
	private ServicioPublicacion servicioPublicacion;
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	
	@GetMapping("/juego/{videojuego}")
	public String greetingjuego(Model model, @PathVariable String videojuego) {
		model.addAttribute("fill", videojuego);
		
		Usuario user = servicioUsuario.getUsuarioByUsername("UsuarioPrueba1");
		Videojuego juego = servicioVideojuego.getVideojuegoPorNombre(videojuego);
		
		if(user.getListaJuegosSeguidos().contains(juego))
			model.addAttribute("textoBotonSeguir", "Dejar de seguir");
		else
			model.addAttribute("textoBotonSeguir", "Seguir");
		
		List<Publicacion> publicaciones = servicioPublicacion.getPublicacionPorVideojuego(juego);
		
		model.addAttribute("publicaciones",publicaciones);
		
		return "publicacionesVideojuegos";
	}
	
	@PostMapping("/nuevoVideojuego")
	public String greetingNuevoVideojuego(Model model) {
		model.addAttribute("notificacion", "");
		List<Categoria> categorias = servicioCategoria.getAll();
		model.addAttribute("categoria",categorias);
		
		return "añadirJuego";
	}
	
	@RequestMapping("/añadirVideojuego")
	public String greetingAñadirVideojuego(Model model, @RequestParam String nombreVideojuego, String descripcionVideojuego, String categoriaVideojuego ) {
		if(nombreVideojuego=="" || descripcionVideojuego=="" || categoriaVideojuego==null) {
			model.addAttribute("notificacion", "Por favor, rellena todos los campos");
			List<Categoria> categorias = servicioCategoria.getAll();
			model.addAttribute("categoria",categorias);
			return "añadirJuego";
		}
		if(!servicioVideojuego.existeVideojuego(nombreVideojuego)) {
			Categoria categoria;
			categoria=servicioCategoria.getCategoriaByName(categoriaVideojuego);
			Videojuego videojuego=new Videojuego(nombreVideojuego,descripcionVideojuego,categoria);
			servicioVideojuego.guardarVideojuego(videojuego);
					
		}else {
			model.addAttribute("notificacion", "Por favor, introduzca un juego que no exista");
			List<Categoria> categorias = servicioCategoria.getAll();
			model.addAttribute("categoria",categorias);
			return "añadirJuego";
		}
		
		List<Videojuego> videojuegos = servicioVideojuego.getAll();
		model.addAttribute("videojuegos",videojuegos);
		
		return "publicarPost";
	}
	
	@RequestMapping("/seguirVideojuego/{videojuego}")
	public String seguirVideojuego(Model model, @PathVariable String videojuego) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails uloggeado = (UserDetails) principal;
        Usuario user=servicioUsuario.getUsuarioByUsername(uloggeado.getUsername());
        
		Videojuego juego = servicioVideojuego.getVideojuegoPorNombre(videojuego);
		
		if(user.getListaJuegosSeguidos().contains(juego)) {
			user.removeJuegoSeguido(juego);
			juego.removeSeguidor(user);
		}
		else
		{
			user.addJuegoSeguido(juego);
			juego.addSeguidor(user);
		}
		
		servicioUsuario.registrarUsuario(user);
		servicioVideojuego.guardarVideojuego(juego);
		
		model.addAttribute("fill", videojuego);
		
		return "redirect:/juego/"+videojuego;
	}
}
