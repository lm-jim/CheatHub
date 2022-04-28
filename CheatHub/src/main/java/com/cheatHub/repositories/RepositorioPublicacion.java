package com.cheatHub.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.Cacheable;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheatHub.entities.Publicacion;
import com.cheatHub.entities.Videojuego;

@CacheConfig(cacheNames="publicacion")
@Repository
public interface RepositorioPublicacion extends JpaRepository<Publicacion,Integer> {
	
	/* Cada publicación es independiente, por lo tanto no haría falta borrar la caché cuando se añade una. Esto se haría por ejemplo cuando se comenta una publicación.
	@CacheEvict(allEntries=true)
	Publicacion save(Publicacion publicacion);
	*/
	@CacheEvict(allEntries=true)
	void delete(Publicacion publicacion);
	@CachePut
	Optional<Publicacion> findById(int idPublicacion);
	@CachePut
	List<Publicacion> findAll();
	@CachePut
	List<Publicacion> findByTipoPublicacion(boolean tipoPublicacion);
	@CachePut
	List<Publicacion> findByVideojuego(Videojuego videojuego);	
	@CachePut
	List<Publicacion> findByTipoPublicacionAndVideojuego(boolean tipoPublicacion,Videojuego videojuego);
	@CachePut
	List<Publicacion> findAllByOrderByPuntuacion();
	@CachePut
	List<Publicacion> findByTitulo(String titulo);
	
}
