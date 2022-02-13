package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Videojuego {

	@Id
	private String nombreVideojuego;
	
	private String descripcion;
	
	@ManyToOne
	private String nombreCategoria;
	
	protected Videojuego() {}
	
	public Videojuego(String nombreVideoJuego, String descripcion, String nombreCategoria) {
		this.nombreVideojuego = nombreVideoJuego;
		this.descripcion = descripcion;
		this.nombreCategoria = nombreCategoria;
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

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	
	
	
}
