package com.cheatHub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheatHub.entities.Publicacion;
import com.cheatHub.entities.Videojuego;
import com.cheatHub.repositories.RepositorioPublicacion;

@Service
public class ServicioPublicacion {
	
	@Autowired
	private RepositorioPublicacion repositorioPublicacion;

	public List<Publicacion> getPublicacionPorTipo(boolean tipo)  {
		
		return repositorioPublicacion.findByTipoPublicacion(tipo);
		
	}
	public List<Publicacion> getPublicacionPorPuntuacion()  {
		
		return repositorioPublicacion.findAllByOrderByPuntuacion();
		
	}
	
	public List<Publicacion> getPublicacionPorVideojuego(Videojuego videojuego)  {
		
		return repositorioPublicacion.findByVideojuego(videojuego);
		
	}
	
	public List<Publicacion> getPublicacionPorVideojuegoYTipo(Videojuego videojuego,boolean tipo)  {
		
		return repositorioPublicacion.findByTipoPublicacionAndVideojuego(tipo,videojuego);
		
	}
	
	public void guardarPublicacion(Publicacion publicacion) {
		
		repositorioPublicacion.save(publicacion);
		
	}

}
