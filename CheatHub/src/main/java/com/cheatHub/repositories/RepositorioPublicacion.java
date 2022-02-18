package com.cheatHub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheatHub.entities.Publicacion;
import com.cheatHub.entities.Videojuego;
@Repository
public interface RepositorioPublicacion extends JpaRepository<Publicacion,Integer> {
	List<Publicacion> findByIdPublicacion(int idPublicacion);
	
	List<Publicacion> findByTipoPublicacion(boolean tipoPublicacion);
	
	List<Publicacion> findByVideojuego(Videojuego videojuego);
	
}