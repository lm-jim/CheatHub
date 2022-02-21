package com.cheatHub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheatHub.entities.Categoria;
import com.cheatHub.entities.Videojuego;

import java.util.List;
@Repository
public interface RepositorioVideojuego extends JpaRepository<Videojuego,String>{
	List<Videojuego> findByNombreVideojuego(String nombreVideojuego);
	
	List<Videojuego> findByCategoria(Categoria categoria);
}
