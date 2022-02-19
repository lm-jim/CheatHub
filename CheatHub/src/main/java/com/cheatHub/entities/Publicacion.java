package com.cheatHub.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Publicacion {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPublicacion;
	private String titulo;
	private String descripcion;
	private boolean tipoPublicacion; //·False=Truco  ·True=Bug
	private int puntuacion;
	
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Videojuego videojuego;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaPublicacion;
	
	@OneToMany(mappedBy="publicacion", cascade=CascadeType.ALL)
	private List<Comentario> listaComentarios;
	
	public Publicacion() {}
	
	public Publicacion(String titulo, String descripcion, Boolean tipoPublicacion, Usuario username, Videojuego videojuego) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tipoPublicacion = tipoPublicacion;
		this.puntuacion = 0;
		this.usuario = username;
		this.videojuego = videojuego;
		this.listaComentarios= new ArrayList<>();
		this.fechaPublicacion = new Date();
	}

	public int getPublicacion() {
		return idPublicacion;
	}

	public void setPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getTipoPublicacion() {
		return tipoPublicacion;
	}

	public void setTipoPublicacion(Boolean tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Usuario getUsername() {
		return usuario;
	}

	public void setUsername(Usuario username) {
		this.usuario = username;
	}

	public Videojuego getVideojuego() {
		return videojuego;
	}

	public void setVideojuego(Videojuego videojuego) {
		this.videojuego = videojuego;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
		
	public List<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public void addComentario(Comentario comentario) {
		if(!listaComentarios.contains(comentario))
			listaComentarios.add(comentario);
	}
	
	public void votoMas() {
		this.puntuacion++;
	}
	
	public void votoMenos() {
		this.puntuacion--;
	}
	
	
	
}
