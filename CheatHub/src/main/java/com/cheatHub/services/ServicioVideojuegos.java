package com.cheatHub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cheatHub.entities.Categoria;
import com.cheatHub.entities.Usuario;
import com.cheatHub.entities.Videojuego;
import com.cheatHub.repositories.RepositorioVideojuego;

@Service
public class ServicioVideojuegos {

	@Autowired
	private RepositorioVideojuego repositorioVideojuegos;
	
	public List<Videojuego> getAll(){
		return repositorioVideojuegos.findAll();
	}
	
	public Videojuego getVideojuegoPorNombre(String nombre) {
		Optional<Videojuego> vid = repositorioVideojuegos.findById(nombre);
		if(vid.isPresent()) {
			return vid.get();
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El videojuego '"+nombre+"' no se encuentra en la base de datos");
		}
	}
	
	public List<Videojuego> getPorCategoria(Categoria categoria) {
		
		return repositorioVideojuegos.findByCategoria(categoria);
	}

	public boolean existeVideojuego(String nombre) {
		Optional<Videojuego> vid = repositorioVideojuegos.findById(nombre);
		if(vid.isPresent()) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void guardarVideojuego(Videojuego videojuego) {
		repositorioVideojuegos.save(videojuego);
	}
}
