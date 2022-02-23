package com.cheatHub.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cheatHub.entities.Usuario;
import com.cheatHub.repositories.RepositorioUsuario;

@Service
public class ServicioUsuario {

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
		repositorioUsuarios.save(usuario);
	}
	
}