package com.cheatHub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cheatHub.entities.Publicacion;
import com.cheatHub.entities.Videojuego;
import com.cheatHub.repositories.RepositorioPublicacion;

@Service
public class ServicioPublicacion {
	
	@Autowired
	private RepositorioPublicacion repositorioPublicacion;

	public List<Publicacion> getAll()  {
		
		return repositorioPublicacion.findAll();
		
	}
	
	public List<Publicacion> getPublicacionPorTipo(boolean tipo)  {
		
		return repositorioPublicacion.findByTipoPublicacion(tipo);
		
	}
	public List<Publicacion> getPublicacionPorPuntuacion()  {
		
		return repositorioPublicacion.findAllByOrderByPuntuacion();
		
	}
	
	public List<Publicacion> getPublicacionPorVideojuego(Videojuego videojuego)  {
		
		return repositorioPublicacion.findByVideojuego(videojuego);
		
	}
	
	public List<Publicacion> getPublicacionPorVideojuegoYTipo(boolean tipo, Videojuego videojuego)  {
		
		return repositorioPublicacion.findByTipoPublicacionAndVideojuego(tipo,videojuego);
		
	}
	
	public void guardarPublicacion(Publicacion publicacion) {
		
		repositorioPublicacion.save(publicacion);
		
	}
	
	public void borrarPublicacion(Publicacion publicacion) {
		
		repositorioPublicacion.delete(publicacion);
		
	}
	
	public Publicacion getPublicacionPorNombre(String titulo) {
		List<Publicacion>  publi= repositorioPublicacion.findByTitulo(titulo);
		
			return publi.get(0);
		
		
	}
	
	public Publicacion getPublicacionPorId(int id) {
		Optional<Publicacion> publi = repositorioPublicacion.findById(id);
		if(publi.isPresent()) {
			return publi.get();
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La publicacion con id '"+id+"' no se encuentra en la base de datos");
		}
	}

}
