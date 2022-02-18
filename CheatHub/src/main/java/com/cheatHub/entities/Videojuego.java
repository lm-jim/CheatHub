package com.cheatHub.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
public class Videojuego {

	@Id
	private String nombreVideojuego;
	
	private String descripcion;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Categoria categoria;
	
	@OneToMany(mappedBy="videojuego")
	private List<Publicacion> ListaPublicaciones;
	
	public Videojuego() {}
	
	public Videojuego(String nombreVideoJuego, String descripcion, Categoria categoria) {
		this.nombreVideojuego = nombreVideoJuego;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.ListaPublicaciones=new ArrayList<>();
	}

	public String getNombreVideoJuego() {
		return nombreVideojuego;
	}

	public void setNombreVideoJuego(String nombreVideoJuego) {
		this.nombreVideojuego = nombreVideoJuego;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Publicacion> getListaPublicaciones() {
		return ListaPublicaciones;
	}

	public void setListaPublicaciones(List<Publicacion> listaPublicaciones) {
		ListaPublicaciones = listaPublicaciones;
	}
	
	public void addPublicacion(Publicacion publicacion) {
		if(!ListaPublicaciones.contains(publicacion))
			ListaPublicaciones.add(publicacion);
	}
	
	
	
	
}
