package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Comentario {
	
	@Id
	private String idComentario;
	
	private String contenidoComentario;
	
	@OneToOne
	private String usuario;
	
	@ManyToOne
	private String publicacion;
	
	protected Comentario() {}

	public Comentario(String idComentario, String contenidoComentario, String usuario, String publicacion) {
		this.idComentario = idComentario;
		this.contenidoComentario = contenidoComentario;
		this.usuario = usuario;
		this.publicacion = publicacion;
	}

	public String getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(String idComentario) {
		this.idComentario = idComentario;
	}

	public String getContenidoComentario() {
		return contenidoComentario;
	}

	public void setContenidoComentario(String contenidoComentario) {
		this.contenidoComentario = contenidoComentario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(String publicacion) {
		this.publicacion = publicacion;
	}
	
	
	
	

}
