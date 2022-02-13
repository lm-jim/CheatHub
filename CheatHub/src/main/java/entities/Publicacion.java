package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Publicacion {

	@Id
	private int id_Publicacion;
	private String titulo;
	private String descripcion;
	private Boolean tipoPublicacion; //·False=Truco  ·True=Bug
	private int puntuacion;
	@OneToOne
	private Usuario username;
	//@OneToOne Cuando esté creada la clase videojuego. Cambiar tmb tipo por videojuego.
	private int id_Videojuego;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaPublicacion;
	
	public Publicacion() {}
	
	public Publicacion(int id_Publicacion, String titulo, String descripcion, Boolean tipoPublicacion, int puntuacion, Usuario username, int id_Videojuego, Date fechaPublicacion) {
		this.id_Publicacion = id_Publicacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tipoPublicacion = tipoPublicacion;
		this.puntuacion = puntuacion;
		this.username = username;
		this.id_Videojuego = id_Videojuego;
		this.fechaPublicacion = fechaPublicacion;
	}
	
	public Publicacion(int id_Publicacion, String titulo, String descripcion, Boolean tipoPublicacion, int puntuacion, Usuario username, int id_Videojuego) {
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

	public int getId_Videojuego() {
		return id_Videojuego;
	}

	public void setId_Videojuego(int id_Videojuego) {
		this.id_Videojuego = id_Videojuego;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	
	
	
	
}
