package entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	@Id
	private String nombreCategoria;
	
	@OneToMany
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
	
	public void addVideojuego(Videojuego game) {
		if(!listaVideojuegos.contains(game))
			listaVideojuegos.add(game);
	}
}
