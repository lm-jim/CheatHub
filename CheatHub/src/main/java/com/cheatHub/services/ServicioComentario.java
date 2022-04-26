package com.cheatHub.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cheatHub.entities.Publicacion;

import com.cheatHub.entities.Comentario;
import com.cheatHub.repositories.RepositorioComentario;


@EnableAsync
@Service
public class ServicioComentario {
	@Autowired
	private RepositorioComentario repositorioComentario;
	
	public List<Comentario> getComentariosPublicacion(Publicacion publicacion)  {
		
		return repositorioComentario.findByPublicacion(publicacion);
		
	}
	
	public void guardarComentario(Comentario comentario) {
		repositorioComentario.save(comentario);
		
		//NOTIFICACION POR EMAIL
		enviarMail(comentario);
		
	}
	
	@Async
	public void enviarMail(Comentario comentario) {
		String url = "http://localhost:8080/email-new-comment";
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		List<String> body = new ArrayList<>();
		body.add(comentario.getUsuario().getNombreUsuario());
		body.add(comentario.getPublicacion().getTitulo());
		body.add(comentario.getContenidoComentario());
		body.add(comentario.getPublicacion().getUsername().getCorreo());
		
		HttpEntity<List> entity = new HttpEntity<>(body, header);
		new RestTemplate().postForEntity(url, entity, String.class);
		
		//Thread.currentThread().interrupt();
	}
}
