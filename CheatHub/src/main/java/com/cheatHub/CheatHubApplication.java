package com.cheatHub;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cheatHub.entities.*;
import com.cheatHub.repositories.*;
import com.cheatHub.services.ServicioUsuario;

@SpringBootApplication
public class CheatHubApplication {
	
	@Autowired
	RepositorioCategoria repositorioCategorias;
	@Autowired
	RepositorioVideojuego repositorioVideojuegos;
	
	@Autowired
	ServicioUsuario servicioUsuarios;
	/*
	@Autowired
	RepositorioUsuario repositorioUsuarios;
	*/
	
	@Autowired
	RepositorioPublicacion repositorioPublicaciones;
	@Autowired
	RepositorioComentario repositorioComentarios;
	
	public static void main(String[] args) {
		
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
		
		Videojuego vPathogen = new Videojuego("Pathogen", "Pathogen es un juego en el que manejas o te defiendes una plaga que extingue a la humanidad.", cEstrategia);
		Videojuego vSims = new Videojuego("Sims 3", "Los Sims es un juego de simulación en el que controlas de vida de unos personajes que has creado.", cSimulacion);
		Videojuego vValorant = new Videojuego("Valorant", "Valorant es un shooter competitivo de 5vs5.", cShooter);
		Videojuego vGTA = new Videojuego("Grand Theft Auto San Andreas", "GTA San Andreas es un juego de mundo  abierto donde encarnarás a CJ, "
				+ "que al volver a Los Santos tras que su madre fuera asesinada, tendrá que emprender un periplo por la ciudad para hacerserse con el control de las calles y limpiar su honor ", cAccion);
		Videojuego vCuphead = new Videojuego("Cuphead", "La historia de cuphead trata acerca de dos hermanos, el protagonista Cuphead y Mugman, que deben derrotar a varios enemigos y jefes para poder saldar una deuda que tienen pendiente con el diablo.", cPlataformas);
		Videojuego vDeadByDaylight = new Videojuego("Dead By Daylight", "Dead by Daylight es un videojuego multijugador en línea de 1 contra 4, en el que cuatro jugadores toman el papel de supervivientes y uno el de asesino.", cTerror);
		Videojuego vMonsterHunter = new Videojuego("Monster Hunter","Un juego que va de cazar monstruos y mejorar tu equipamiento",cAccion);
		Videojuego vCrabGame = new Videojuego("Crab Game","Un juego parodia de la serie del Juego del Calamar en el que puedes pasartelo bien",cSimulacion);
		Videojuego vFornite = new Videojuego("Fornite","Un juego shooter en que tendras que sobrevivir con las armas y materiales que encuentres",cShooter);

		repositorioVideojuegos.save(vPathogen);
		repositorioVideojuegos.save(vSims);
		repositorioVideojuegos.save(vValorant);
		repositorioVideojuegos.save(vGTA);
		repositorioVideojuegos.save(vCuphead);
		repositorioVideojuegos.save(vDeadByDaylight);
		repositorioVideojuegos.save(vMonsterHunter);
		repositorioVideojuegos.save(vCrabGame);
		repositorioVideojuegos.save(vFornite);
		
		Usuario user = new Usuario("Sirio120","12345", "Luismi", "Me gustan las estrellas", new Date(), "http://skyandtelescope.org/wp-content/uploads/Sirius-B-Fabio-v2.jpg");
		Usuario userJ = new Usuario("Juanmita_","abcd", "Juanma", "Me gusta Naruto", new Date(), "https://studiosol-a.akamaihd.net/uploadfile/letras/fotos/2/5/7/4/2574f9070ce48b988fe2693a60c40427.jpg");
		Usuario userV = new Usuario("ViRo","qwerty","Ismael","Me gusta Star Wars",new Date(),"https://www.xtrafondos.com/descargar.php?id=5495&vertical=1.jpg");
		
		Usuario user1 = new Usuario("UsuarioPrueba1","pass1");
		Usuario user2 = new Usuario("UsuarioPrueba2","pass2");
		Usuario user3 = new Usuario("UsuarioPrueba3","pass3");
		/*
		repositorioUsuarios.save(user);
		repositorioUsuarios.save(userJ);
		repositorioUsuarios.save(userV);
		repositorioUsuarios.save(user1);
		repositorioUsuarios.save(user2);
		repositorioUsuarios.save(user3);
		*/
		
		servicioUsuarios.registrarUsuario(user);
		servicioUsuarios.registrarUsuario(userJ);
		servicioUsuarios.registrarUsuario(userV);
		servicioUsuarios.registrarUsuario(user1);
		servicioUsuarios.registrarUsuario(user2);
		servicioUsuarios.registrarUsuario(user3);
		
		
		Publicacion publi = new Publicacion("Pathogen: bug patogenos infinitos", "Jugando con el virus, dividelo hasta obtener la cepa resistente al clima y multiplicalo para superar el limite de 2000 patogenos.", true, user, vPathogen);
		Publicacion publicacionJ1 = new Publicacion("Dinero infinito", "Si pulsas Ctrl + Shift + C se abre una consola, y si escribes \"motherlode\" ganarás 50.000 Simoleones al instante ", false , userJ, vSims);
		Publicacion publicacionJ2 = new Publicacion("Desbloquear todos los trajes", "Si pulsas Ctrl + Shift + C se abre una consola, y si escribes,\"unlockOutfits [on|off]\" desbloqueas todas las vestimentas del juego. ", false, userJ, vSims);
		Publicacion publicacionJ3 = new Publicacion("Cuidado si juegas desde un procesador AMD", "Si notas una bajada de rendimiento en el juego y usas un procesador AMD, se debe a que está mal optimizado. Escribe \"bcdedit.exe /set useplatformclock false\" en la consola de tu SO, reinicia tu ordenador y lo solucionarás. ", true, userJ, vValorant);
		Publicacion publicacionJ4 = new Publicacion("Bug Baloncesto", "NO guardes la partida en la Mansion de Madd Dogg, porque desaparecerán todas las pelotas de las canchas y estas quedarán inservibles.", true, userJ, vGTA);
		Publicacion publicacionJ5 = new Publicacion("Codigos de puntos de sangre", "A continuacion os dejo unos codigos para obtener puntos de sangre y mejorar los personajes: 4728947925 298485 2984892658 54326543", false, user1, vDeadByDaylight);
		Publicacion publicacionJ6 = new Publicacion("Moneda secreta en mundo 1", "Detras de un arbol hay una moneda secreta", false, user2, vCuphead);
		Publicacion publicacionJ7 = new Publicacion("Moneda secreta en mundo 2", "Detras de un castillo hay una moneda secreta", false, user3, vCuphead);
		Publicacion publicacionJ8 = new Publicacion("Salto lateral","Si cuando esta corriendo haces un salto moviendote hacia algun lado en vez de frente,consigues una mayor distancia de salto",false,userV,vCrabGame);
		Publicacion publicacionJ9 = new Publicacion("Golpeo te saca del mapa","Si cuando estas quieto un jugador viene corriendo y te golpea, si hay un jugador inmediantamente detras, cabe la posibilidad de que te lance con mucha fuerza y te saque del mapa debido a la colision producida",true,user2,vCrabGame);
		Publicacion publicacionJ10 = new Publicacion("Pescar Monstruo","Si usas el boton de la caña de pescar cuando un monstruo te va a atacar , puedes pescarlo evitando el ataque y ganando ventaja en el combate",false,user,vMonsterHunter);
		Publicacion publicacionJ11 = new Publicacion("Atajos en la construccion","Existen combinaciones de cuadrados a la hora de contruir que hacen que construyas mas rapido una pared modificada que si haces el reccorrido normal",false,user3,vFornite);

		repositorioPublicaciones.save(publi);
		repositorioPublicaciones.save(publicacionJ1);
		repositorioPublicaciones.save(publicacionJ2);
		repositorioPublicaciones.save(publicacionJ3);
		repositorioPublicaciones.save(publicacionJ4);
		repositorioPublicaciones.save(publicacionJ5);
		repositorioPublicaciones.save(publicacionJ6);
		repositorioPublicaciones.save(publicacionJ7);
		repositorioPublicaciones.save(publicacionJ8);
		repositorioPublicaciones.save(publicacionJ9);
		repositorioPublicaciones.save(publicacionJ10);
		repositorioPublicaciones.save(publicacionJ11);

		
		Comentario coment = new Comentario("Buena publicacion, 100% de acuerdo", user, publi);
		Comentario coment1 = new Comentario("Este bug se puede arreglar con herramientas externas. Pero son una movida", user, publicacionJ4);
		Comentario coment2 = new Comentario("Buah, recuerdo que de pequeñó me fastidió la partida de mi PS2.", user, publicacionJ4);
		Comentario coment3 = new Comentario("Prefiero el truco del motherlode. Prefiero pasta a ropa", user, publicacionJ2);
		Comentario coment4 = new Comentario("Nunca se me olvidará este truco. Ojalá poder usarlo en la vida real xD", user, publicacionJ1);

		Comentario coment5 = new Comentario("Gracias por la moneda! Voy a publicar yo otra", user3, publicacionJ6);
		Comentario coment6 = new Comentario("Eres un copion, reportado", user2, publicacionJ7);
		Comentario coment7 = new Comentario("Vamo a calmarno", user, publicacionJ7);
		Comentario coment8 = new Comentario("No me funcionan algunos :(", user1, publicacionJ5);

		Comentario coment9 = new Comentario("La verdad es que muy buen truco, yo lo he probado y en los cristales puedo ir de 2 en 2 en vez de 1 en 1",user1,publicacionJ8);
		Comentario coment10 = new Comentario("Me ha pasado y la verdad es que entre ese bug los hackers es muy molesto cuando te sacan del mapa",userV,publicacionJ9);
		Comentario coment11 = new Comentario("La verdad es que estas publicaciones son la razon por la que el juego ahora es solo de contruccion y no tanto un shooter,mejor que no subais estos trucos",user2,publicacionJ10);
		Comentario coment12 = new Comentario("La verdad es que no lo sabia, cuando vuelva a jugar lo pondre a prueba.¿Alguien sabe si funciona con los boss??",userV,publicacionJ11);

		repositorioComentarios.save(coment);
		repositorioComentarios.save(coment1);
		repositorioComentarios.save(coment2);
		repositorioComentarios.save(coment3);
		repositorioComentarios.save(coment4);
		repositorioComentarios.save(coment5);
		repositorioComentarios.save(coment6);
		repositorioComentarios.save(coment7);
		repositorioComentarios.save(coment8);
		repositorioComentarios.save(coment9);
		repositorioComentarios.save(coment10);
		repositorioComentarios.save(coment11);
		repositorioComentarios.save(coment12);
		
	}

}
