package com.cheatHub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheatHub.entities.Comentario;
import com.cheatHub.entities.Publicacion;
@Repository
public interface RepositorioComentario extends JpaRepository<Comentario,Integer>{
	List<Comentario> findByPublicacion(Publicacion publicacion);

}
