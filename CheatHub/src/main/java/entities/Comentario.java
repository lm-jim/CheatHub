package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Comentario {
	
	@Id
	
	private int idComentario;
	
	private String contenidoComentario;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Publicacion publicacion;
	
	protected Comentario() {}

	public Comentario(int idComentario, String contenidoComentario, Usuario usuario, Publicacion publicacion) {
		this.idComentario = idComentario;
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
	
	
	
	

}
