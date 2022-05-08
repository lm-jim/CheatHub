package com.cheatHub.services;


import java.security.SecureRandom;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.cheatHub.entities.Usuario;
import com.cheatHub.repositories.RepositorioUsuario;


@Service
@EnableAsync
public class ServicioUsuario {
	
	@Value("${SERVICIO_INTERNO_NAME}")
    String SERVICIO_INTERNO_NAME;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}
	
	@Autowired
	private RepositorioUsuario repositorioUsuarios;
	
	public Usuario getUsuarioByUsername(String username) {
		Optional<Usuario> user = repositorioUsuarios.findById(username);
		if(user.isPresent()) {
			return user.get();
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario '"+username+"' no se encuentra en la base de datos");
		}
	}
	
	public List<Usuario> buscarUsuarios(String username) {
		List<Usuario> listaRetorno = new ArrayList<Usuario>();
		for(Usuario cUser : repositorioUsuarios.findAll()) {
			if(cUser.getNombreUsuario().toLowerCase().contains(username.toLowerCase()))
				listaRetorno.add(cUser);
		}
		return listaRetorno;
	}
	
	public boolean existeUsername(String username) {
		Optional<Usuario> user = repositorioUsuarios.findById(username);
		if(user.isPresent()) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public void registrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setContraseña(passwordEncoder .encode(usuario.getContraseña()));
		repositorioUsuarios.save(usuario);
		
		//NOTIFICACION POR EMAIL
		if(usuario.getCorreo()!="") {
			//new Thread(()->enviarMail(usuario)).start();
			enviarMail(usuario);
		}
		
		
	}
	
	@Async
	public void enviarMail(Usuario usuario) {
		
		String url = "http://"+SERVICIO_INTERNO_NAME+":8080/email-new-sing-up";
		//String url = "http://localhost:8080/email-new-sing-up";
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		List<String> body = new ArrayList<>();
		body.add(usuario.getNombreUsuario());
		body.add(usuario.getCorreo());
		
		HttpEntity<List> entity = new HttpEntity<>(body, header);
		new RestTemplate().postForEntity(url, entity, String.class);
		
		//Thread.currentThread().interrupt();
	}
	
	
	public void guardarUsuario(Usuario usuario) {
		repositorioUsuarios.save(usuario);
	}
}
