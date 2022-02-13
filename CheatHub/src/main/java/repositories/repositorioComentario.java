package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Comentario;

public interface repositorioComentario extends JpaRepository<Comentario,Long>{
	List<Comentario> findByNombreComentario(String idComentario);

}
