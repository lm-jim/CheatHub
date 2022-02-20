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
		Categoria cPlataformas = new Categoria("Plataformas");
		Categoria cTerror = new Categoria("Terror");
		
		repositorioCategorias.save(cAccion);
		repositorioCategorias.save(cEstrategia);
		repositorioCategorias.save(cSimulacion);
		repositorioCategorias.save(cShooter);
		repositorioCategorias.save(cPlataformas);
		repositorioCategorias.save(cTerror);
		/*
		Categoria cSim = new Categoria("Simulador");
		repositorioCategorias.save(cSim);
		*/
		Videojuego vPathogen = new Videojuego("Pathogen", "Pathogen es un juego en el que manejas o te defiendes una plaga que extingue a la humanidad.", cEstrategia);
		Videojuego vSims = new Videojuego("Sims 3", "Los Sims es un juego de simulación en el que controlas de vida de unos personajes que has creado.", cSimulacion);
		Videojuego vValorant = new Videojuego("Valorant", "Valorant es un shooter competitivo de 5vs5.", cShooter);
		Videojuego vGTA = new Videojuego("Grand Theft Auto San Andreas", "GTA San Andreas es un juego de mundo  abierto donde encarnarás a CJ, "
				+ "que al volver a Los Santos tras que su madre fuera asesinada, tendrá que emprender un periplo por la ciudad para hacerserse con el control de las calles y limpiar su honor ", cAccion);
		Videojuego vCuphead = new Videojuego("Cuphead", "La historia de cuphead trata acerca de dos hermanos, el protagonista Cuphead y Mugman, que deben derrotar a varios enemigos y jefes para poder saldar una deuda que tienen pendiente con el diablo.", cPlataformas);
		Videojuego vDeadByDaylight = new Videojuego("Dead By Daylight", "Dead by Daylight es un videojuego multijugador en línea de 1 contra 4, en el que cuatro jugadores toman el papel de supervivientes y uno el de asesino.", cTerror);
		
		
		repositorioVideojuegos.save(vPathogen);
		repositorioVideojuegos.save(vSims);
		repositorioVideojuegos.save(vValorant);
		repositorioVideojuegos.save(vGTA);
		repositorioVideojuegos.save(vCuphead);
		repositorioVideojuegos.save(vDeadByDaylight);
		
		Usuario user = new Usuario("Sirio120", "12345", "Luismi", "Me gustan las estrellas", new Date(), "http://skyandtelescope.org/wp-content/uploads/Sirius-B-Fabio-v2.jpg");
		Usuario userJ = new Usuario("Juanmita_","abcd", "Juanma", "Me gusta Naruto", new Date(), "https://studiosol-a.akamaihd.net/uploadfile/letras/fotos/2/5/7/4/2574f9070ce48b988fe2693a60c40427.jpg");
		
		repositorioUsuarios.save(user);
		repositorioUsuarios.save(userJ);
		
		Usuario user1 = new Usuario("UsuarioPrueba1","pass1");
		Usuario user2 = new Usuario("UsuarioPrueba2","pass2");
		Usuario user3 = new Usuario("UsuarioPrueba3","pass3");
		
		repositorioUsuarios.save(user1);
		repositorioUsuarios.save(user2);
		repositorioUsuarios.save(user3);
		
		Publicacion publi = new Publicacion("Pathogen: bug patogenos infinitos", "Jugando con el virus, dividelo hasta obtener la cepa resistente al clima y multiplicalo para superar el limite de 2000 patogenos.", true, user, vPathogen);
		Publicacion publicacionJ1 = new Publicacion("Dinero infinito", "Si pulsas Ctrl + Shift + C se abre una consola, y si escribes \"motherlode\" ganarás 50.000 Simoleones al instante ", false , userJ, vSims);
		Publicacion publicacionJ2 = new Publicacion("Desbloquear todos los trajes", "Si pulsas Ctrl + Shift + C se abre una consola, y si escribes,\"unlockOutfits [on|off]\" desbloqueas todas las vestimentas del juego. ", false, userJ, vSims);
		Publicacion publicacionJ3 = new Publicacion("Cuidado si juegas desde un procesador AMD", "Si notas una bajada de rendimiento en el juego y usas un procesador AMD, se debe a que está mal optimizado. Escribe \"bcdedit.exe /set useplatformclock false\" en la consola de tu SO, reinicia tu ordenador y lo solucionarás. ", true, userJ, vValorant);
		Publicacion publicacionJ4 = new Publicacion("Bug Baloncesto", "NO guardes la partida en la Mansion de Madd Dogg, porque desaparecerán todas las pelotas de las canchas y estas quedarán inservibles.", true, userJ, vGTA);
		Publicacion publicacionJ5 = new Publicacion("Codigos de puntos de sangre", "A continuacion os dejo unos codigos para obtener puntos de sangre y mejorar los personajes: 4728947925 298485 2984892658 54326543", false, user1, vDeadByDaylight);
		Publicacion publicacionJ6 = new Publicacion("Moneda secreta en mundo 1", "Detras de un arbol hay una moneda secreta", false, user2, vCuphead);
		Publicacion publicacionJ7 = new Publicacion("Moneda secreta en mundo 2", "Detras de un castillo hay una moneda secreta", false, user3, vCuphead);
		
		
		repositorioPublicaciones.save(publi);
		repositorioPublicaciones.save(publicacionJ1);
		repositorioPublicaciones.save(publicacionJ2);
		repositorioPublicaciones.save(publicacionJ3);
		repositorioPublicaciones.save(publicacionJ4);
		repositorioPublicaciones.save(publicacionJ5);
		repositorioPublicaciones.save(publicacionJ6);
		repositorioPublicaciones.save(publicacionJ7);
		
		Comentario coment = new Comentario("Buena publicacion, 100% de acuerdo", user, publi);
		Comentario coment1 = new Comentario("Este bug se puede arreglar con herramientas externas. Pero son una movida", user, publicacionJ4);
		Comentario coment2 = new Comentario("Buah, recuerdo que de pequeñó me fastidió la partida de mi PS2.", user, publicacionJ4);
		Comentario coment3 = new Comentario("Prefiero el truco del motherlode. Prefiero pasta a ropa", user, publicacionJ2);
		Comentario coment4 = new Comentario("Nunca se me olvidará este truco. Ojalá poder usarlo en la vida real xD", user, publicacionJ1);
		Comentario coment5 = new Comentario("Gracias por la moneda! Voy a publicar yo otra", user3, publicacionJ6);
		Comentario coment6 = new Comentario("Eres un copion, reportado", user2, publicacionJ7);
		Comentario coment7 = new Comentario("Vamo a calmarno", user, publicacionJ7);
		Comentario coment8 = new Comentario("No me funcionan algunos :(", user1, publicacionJ5);
		repositorioComentarios.save(coment);
		repositorioComentarios.save(coment1);
		repositorioComentarios.save(coment2);
		repositorioComentarios.save(coment3);
		repositorioComentarios.save(coment4);
		repositorioComentarios.save(coment5);
		repositorioComentarios.save(coment6);
		repositorioComentarios.save(coment7);
		repositorioComentarios.save(coment8);
		
	}

}
