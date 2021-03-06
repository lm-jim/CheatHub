package com.cheatHub.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Videojuego {

	@Id
	private String nombreVideojuego;
	
	private String descripcion;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Categoria categoria;
	
	@OneToMany(mappedBy="videojuego")
	private List<Publicacion> listaPublicaciones;
	
	@ManyToMany
	private List<Usuario> listaSeguidores;
	
	public Videojuego() {}
	
	public Videojuego(String nombreVideoJuego, String descripcion, Categoria categoria) {
		this.nombreVideojuego = nombreVideoJuego;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.listaPublicaciones=new ArrayList<>();
		this.listaSeguidores=new ArrayList<>();
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
		return listaPublicaciones;
	}

	public void setListaPublicaciones(List<Publicacion> listaPublicaciones) {
		this.listaPublicaciones = listaPublicaciones;
	}
	
	public void addPublicacion(Publicacion publicacion) {
		if(!listaPublicaciones.contains(publicacion))
			listaPublicaciones.add(publicacion);
	}
	public List<Usuario> getListaSeguidores() {
		return listaSeguidores;
	}

	public void setListaSeguidores(List<Usuario> listaSeguidores) {
		this.listaSeguidores = listaSeguidores;
	}
	
	public void addSeguidor(Usuario seguidor) {
		if(!listaSeguidores.contains(seguidor))
			listaSeguidores.add(seguidor);
	}
	
	public void removeSeguidor(Usuario seguidor) {
		if(listaSeguidores.contains(seguidor))
			listaSeguidores.remove(seguidor);
	}
	
	@Override
	public String toString() {
		return nombreVideojuego;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreVideojuego);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Videojuego other = (Videojuego) obj;
		return Objects.equals(nombreVideojuego, other.nombreVideojuego);
	}
	
	
	
}
