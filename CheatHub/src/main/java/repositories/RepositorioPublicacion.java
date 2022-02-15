package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Publicacion;
import entities.Videojuego;

public interface RepositorioPublicacion extends JpaRepository<Publicacion,Integer> {
	List<Publicacion> findByIdPublicacion(int id_Publicacion);
	
	List<Publicacion> findByTipoPublicacion(boolean tipoPublicacion);
	
	List<Publicacion> findByIdVideojuego(Videojuego id_Videojuego);
	
}
