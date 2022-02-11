package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Publicacion;

public interface RepositorioPublicacion extends JpaRepository<Publicacion,Integer> {
	List<Publicacion> findByNombreUsuario(int id_Publicacion);
}
