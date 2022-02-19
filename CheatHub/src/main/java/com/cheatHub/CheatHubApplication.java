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
		Categoria cEstrategia = new Categoria("Estrategia");
		Categoria cAccion = new Categoria("Accion");
		Categoria cSimulacion = new Categoria("Simulación");
		Categoria cShooter = new Categoria("Shooter");
		repositorioCategorias.save(cAccion);
		repositorioCategorias.save(cEstrategia);
		repositorioCategorias.save(cSimulacion);
		repositorioCategorias.save(cShooter);
		/*
		Categoria cSim = new Categoria("Simulador");
		repositorioCategorias.save(cSim);
		*/
		Videojuego vPathogen = new Videojuego("Pathogen", "Pathogen es un juego en el que manejas o te defiendes una plaga que extingue a la humanidad.", cAccion);
		Videojuego vSims = new Videojuego("Sims 3", "Los Sims es un juego de simulación en el que controlas de vida de unos personajes que has creado.", cSimulacion);
		Videojuego vValorant = new Videojuego("Valorant", "Valorant es un shooter competitivo de 5vs5.", cShooter);
		Videojuego vGTA = new Videojuego("Grand Theft Auto San Andreas", "GTA San Andreas es un juego de mundo  abierto donde encarnarás a CJ, "
				+ "que al volver a Los Santos tras que su madre fuera asesinada, tendrá que emprender un periplo por la ciudad para hacerserse con el control de las calles y limpiar su honor ", cAccion);
		repositorioVideojuegos.save(vPathogen);
		repositorioVideojuegos.save(vSims);
		repositorioVideojuegos.save(vValorant);
		repositorioVideojuegos.save(vGTA);
		
		Usuario user = new Usuario("Sirio120", "12345");
		Usuario userJ = new Usuario("Juanmita","abcd");
		repositorioUsuarios.save(user);
		repositorioUsuarios.save(userJ);
		
		Publicacion publi = new Publicacion("Pathogen juegazo", "En realidad no, es una caca, no jugaria. 0/10", false, user, vPathogen);
		Publicacion publicacionJ1 = new Publicacion("Dinero infinito", "Si pulsas Ctrl + Shift + C se abre una consola, y si escribes \"motherlode\" ganarás 50.000 Simoleones al instante ", false , userJ, vSims);
		Publicacion publicacionJ2 = new Publicacion("Desbloquear todos los trajes", "Si pulsas Ctrl + Shift + C se abre una consola, y si escribes,\"unlockOutfits [on|off]\" desbloqueas todas las vestimentas del juego. ", false, userJ, vSims);
		Publicacion publicacionJ3 = new Publicacion("Cuidado si juegas desde un procesador AMD", "Si notas una bajada de rendimiento en el juego y usas un procesador AMD, se debe a que está mal optimizado. Escribe \"bcdedit.exe /set useplatformclock false\" en la consola de tu SO, reinicia tu ordenador y lo solucionarás. ", true, userJ, vValorant);
		Publicacion publicacionJ4 = new Publicacion("Bug Baloncesto", "NO guardes ela partida en la Mansion de Madd Dogg, porque desaparecerán todas las pelotas de las canchas y estas quedarán inservibles.", true, userJ, vGTA);
		repositorioPublicaciones.save(publi);
		repositorioPublicaciones.save(publicacionJ1);
		repositorioPublicaciones.save(publicacionJ2);
		repositorioPublicaciones.save(publicacionJ3);
		repositorioPublicaciones.save(publicacionJ4);
		
		Comentario coment = new Comentario("Buena publicacion, 100% de acuerdo", user, publi);
		Comentario coment1 = new Comentario("Este bug se puede arreglar con herramientas externas. Pero son una movida", user, publicacionJ4);
		Comentario coment2 = new Comentario("Buah, recuerdo que de pequeñó me fastidió la partida de mi PS2.", user, publicacionJ4);
		Comentario coment3 = new Comentario("Prefiero el truco del motherlode. Prefiero pasta a ropa", user, publicacionJ2);
		Comentario coment4 = new Comentario("Nunca se me olvidará este truco. Ojalá poder usarlo en la vida real xD", user, publicacionJ1);
		repositorioComentarios.save(coment);
		repositorioComentarios.save(coment1);
		repositorioComentarios.save(coment2);
		repositorioComentarios.save(coment3);
		repositorioComentarios.save(coment4);
		
	}

}
