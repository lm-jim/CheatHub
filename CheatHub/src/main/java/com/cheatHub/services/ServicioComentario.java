package com.cheatHub.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheatHub.entities.Publicacion;

import com.cheatHub.entities.Comentario;
import com.cheatHub.repositories.RepositorioComentario;


@Service
public class ServicioComentario {
	@Autowired
	private RepositorioComentario repositorioComentario;
	
	public List<Comentario> getComentariosPublicacion(Publicacion publicacion)  {
		
		return repositorioComentario.findByPublicacion(publicacion);
		
	}
	
	public void guardarComentario(Comentario comentario) {
		repositorioComentario.save(comentario);
	}
}
