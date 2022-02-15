package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Comentario;
import entities.Publicacion;

public interface repositorioComentario extends JpaRepository<Comentario,Long>{
	List<Comentario> findByNombreComentario(Publicacion publicacion);

}
