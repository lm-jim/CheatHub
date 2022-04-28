package com.cheatHub.repositories;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheatHub.entities.Comentario;
import com.cheatHub.entities.Publicacion;

@CacheConfig(cacheNames="publicacion")
@Repository
public interface RepositorioComentario extends JpaRepository<Comentario,Integer>{
	
	//Eliminamos de la caché la publicación correspondiente.
	//https://www.baeldung.com/spring-boot-evict-cache
	
	//@CacheEvict(key="#comentario.getPublicacion().getIdPublicacion()")
	//@CacheEvict(key="#comentario.publicacion.idPublicacion")
	@CacheEvict(allEntries=true)
	Comentario save(Comentario comentario);
	
	List<Comentario> findByPublicacion(Publicacion publicacion);

}
