package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Videojuego {

	@Id
	private String nombreVideojuego;
	
	private String descripcion;
	
	@ManyToOne
	private Categoria nombreCategoria;
	
	@OneToMany
	private List<Publicacion> ListaPublicaciones;
	
	public Videojuego() {}
	
	public Videojuego(String nombreVideoJuego, String descripcion, Categoria nombreCategoria) {
		this.nombreVideojuego = nombreVideoJuego;
		this.descripcion = descripcion;
		this.nombreCategoria = nombreCategoria;
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

	public Categoria getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(Categoria nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
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
