package com.cheatHub.entities;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Comentario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idComentario;
	
	private String contenidoComentario;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Publicacion publicacion;
	
	protected Comentario() {}

	public Comentario(String contenidoComentario, Usuario usuario, Publicacion publicacion) {
		this.contenidoComentario = contenidoComentario;
		this.usuario = usuario;
		this.publicacion = publicacion;
	}

	public int getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}

	public String getContenidoComentario() {
		return contenidoComentario;
	}

	public void setContenidoComentario(String contenidoComentario) {
		this.contenidoComentario = contenidoComentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	
	@Override
	public String toString() {
		return contenidoComentario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idComentario, publicacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		return idComentario == other.idComentario && Objects.equals(publicacion, other.publicacion);
	}
	
	
	
	

}
