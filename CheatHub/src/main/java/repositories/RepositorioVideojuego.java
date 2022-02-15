package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Categoria;
import entities.Videojuego;
import java.util.List;

public interface RepositorioVideojuego extends JpaRepository<Videojuego,Long>{
	List<Videojuego> findByNombreVideojuego(String nombreVideojuego);
	
	List<Videojuego> findByCategoria(Categoria nombreCategoria);
}
