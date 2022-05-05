package com.cheatHub.entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	@Id
	private String nombreCategoria;
	
	@OneToMany(mappedBy="categoria")
	private List<Videojuego> listaVideojuegos;
	
	public Categoria() {}
	
	public Categoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
		this.listaVideojuegos=new ArrayList<>();
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public List<Videojuego> getListaVideojuegos() {
		return listaVideojuegos;
	}

	public void setListaVideojuegos(List<Videojuego> listaVideojuegos) {
		this.listaVideojuegos = listaVideojuegos;
	}

	@Override
	public String toString() {
		return nombreCategoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreCategoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(nombreCategoria, other.nombreCategoria);
	}

	
	
	
}
