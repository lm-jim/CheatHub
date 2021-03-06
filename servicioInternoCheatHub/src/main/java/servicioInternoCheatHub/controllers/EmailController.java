package servicioInternoCheatHub.controllers;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import servicioInternoCheatHub.services.EmailService;

@RestController
@EnableAsync
public class EmailController {
	@Autowired
	EmailService servicioEmails;
	
	@Async
	@PostMapping("/email-new-publication")
	public Future<ResponseEntity<String>> notificacionNuevaPublicacion(@RequestBody List<String> body) {
		String juego = body.get(0);
		String tituloPublicacion = body.get(1);
		String emailUsuario = body.get(2);
		
		servicioEmails.mandarEmail(
				"Nueva publicacion en "+juego, 
				
				"Se ha publicado una nueva publicacion en "+juego+", el cual sigues."
				+ "Nombre de la publicacion: " + tituloPublicacion
				
				, emailUsuario);
		return new AsyncResult<ResponseEntity<String>>(ResponseEntity.ok("Correo notificación nueva publicación enviado"));
	}
	
	@Async
	@PostMapping("/email-new-sing-up")
	public Future<ResponseEntity<String>>  notificacionNuevoRegistro(@RequestBody List<String> body) {
		String nombreUsuario = body.get(0);
		String emailUsuario = body.get(1);
		
		servicioEmails.mandarEmail(
				"Bienvenido a CheatHub", 
				
				"Bienvenido, "+ nombreUsuario +". Tu registro se ha completado correctamente. Esperamos que tu estancia en CheatHub sea agradable."
				, emailUsuario);
		return new AsyncResult<ResponseEntity<String>>(ResponseEntity.ok("Correo de registro enviado"));
	}
	
	@Async
	@PostMapping("/email-new-comment")
	public Future<ResponseEntity<String>>  notificacionNuevoComentario(@RequestBody List<String> body) {
		String nombreUsuario = body.get(0);
		String nombrePublicacion = body.get(1);
		String textoComentario = body.get(2);
		String emailUsuario = body.get(3);
		
		servicioEmails.mandarEmail(
				"Nuevo comentario en tu publicacion: "+nombrePublicacion, 
				
				"El usuario "+ nombreUsuario +" ha comentado en tu publicacion "+nombrePublicacion+":\n\n"+textoComentario
				, emailUsuario);
		return new AsyncResult<ResponseEntity<String>>(ResponseEntity.ok("Correo notificación nuevo comentario enviado"));
	}
}
