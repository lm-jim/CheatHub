package com.cheatHub;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cheatHub.entities.*;
import com.cheatHub.repositories.*;

@SpringBootApplication
public class CheatHubApplication {
	
	@Autowired
	RepositorioCategoria repositorioCategorias;
	@Autowired
	RepositorioVideojuego repositorioVideojuegos;
	@Autowired
	RepositorioUsuario repositorioUsuarios;
	@Autowired
	RepositorioPublicacion repositorioPublicaciones;
	@Autowired
	RepositorioComentario repositorioComentarios;
	
	public static void main(String[] args) {
		//Indicamos que se ejecuta la apliación
		SpringApplication.run(CheatHubApplication.class, args);
	}
	
	@PostConstruct
	public void init()
	{
		Categoria cAccion = new Categoria("Estrategia");
		repositorioCategorias.save(cAccion);
		/*
		Categoria cSim = new Categoria("Simulador");
		repositorioCategorias.save(cSim);
		*/
		Videojuego videoj = new Videojuego("Pathogen", "Pathogen es un juego en el que manejas o te defiendes una plaga que extingue a la humanidad.", cAccion);
		Usuario user = new Usuario("Sirio120", "12345");
		Publicacion publi = new Publicacion("Pathogen juegazo", "En realidad no, es una caca, no jugaria. 0/10", false, -46, user, videoj);
		Comentario coment = new Comentario("Buena publicacion, 100% de acuerdo", user, publi);
		
		
		repositorioVideojuegos.save(videoj);
		repositorioUsuarios.save(user);
		repositorioPublicaciones.save(publi);
		repositorioComentarios.save(coment);
	}

}
