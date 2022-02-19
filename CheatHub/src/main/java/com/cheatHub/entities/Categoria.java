package com.cheatHub.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	@Id
	private String nombreCategoria;
	
	@OneToMany(mappedBy="categoria")
	private List<Videojuego> listaVideojuegos;
	
	public Categoria() {}
	
	public Categoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
		this.listaVideojuegos=new ArrayList<>();
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public List<Videojuego> getListaVideojuegos() {
		return listaVideojuegos;
	}

	public void setListaVideojuegos(List<Videojuego> listaVideojuegos) {
		this.listaVideojuegos = listaVideojuegos;
	}

	@Override
	public String toString() {
		return nombreCategoria;
	}
	
	
}
