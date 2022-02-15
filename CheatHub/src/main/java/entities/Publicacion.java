package entities;

import javax.persistence.Entity;
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
	private int id_Publicacion;
	private String titulo;
	private String descripcion;
	private boolean tipoPublicacion; //·False=Truco  ·True=Bug
	private int puntuacion;
	@ManyToOne
	private Usuario username;
	@ManyToOne 
	private Videojuego id_Videojuego;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaPublicacion;
	
	@OneToMany
	private List<Comentario> listaComentarios;
	
	public Publicacion() {}
	
	public Publicacion(int id_Publicacion, String titulo, String descripcion, Boolean tipoPublicacion, int puntuacion, Usuario username, Videojuego id_Videojuego, Date fechaPublicacion) {
		this.id_Publicacion = id_Publicacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tipoPublicacion = tipoPublicacion;
		this.puntuacion = puntuacion;
		this.username = username;
		this.id_Videojuego = id_Videojuego;
		this.fechaPublicacion = fechaPublicacion;
		this.listaComentarios= new ArrayList<>();
	}
	
	public Publicacion(int id_Publicacion, String titulo, String descripcion, Boolean tipoPublicacion, int puntuacion, Usuario username, Videojuego id_Videojuego) {
		this(id_Publicacion, titulo,  descripcion,  tipoPublicacion,  puntuacion,  username,  id_Videojuego,new Date());
	}

	public int getId_Publicacion() {
		return id_Publicacion;
	}

	public void setId_Publicacion(int id_Publicacion) {
		this.id_Publicacion = id_Publicacion;
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
		return username;
	}

	public void setUsername(Usuario username) {
		this.username = username;
	}

	public Videojuego getId_Videojuego() {
		return id_Videojuego;
	}

	public void setId_Videojuego(Videojuego id_Videojuego) {
		this.id_Videojuego = id_Videojuego;
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
