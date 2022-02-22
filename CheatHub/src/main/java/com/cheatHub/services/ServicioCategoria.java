package com.cheatHub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cheatHub.entities.Categoria;
import com.cheatHub.repositories.RepositorioCategoria;

@Service
public class ServicioCategoria {
	@Autowired
	private RepositorioCategoria repositorioCategorias;
	
	public List<Categoria> getAll(){
		return repositorioCategorias.findAll();
	}
	public Categoria getCategoriaByName(String name) {
		Optional<Categoria> cat = repositorioCategorias.findById(name);
		if(cat.isPresent()) {
			return cat.get();
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La categoria '"+name+"' no se encuentra en la base de datos");
		}
	}
	
}
